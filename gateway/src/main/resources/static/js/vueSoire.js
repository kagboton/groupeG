$(document).ready(function ()
{


    var idUser = $("#idValue").val();

    $("#creerSoireForm").validate({
        rules: {
            // simple rule, converted to {required:true}
            description: "required"

        },
        messages: {
            description: "titre est obligatoire"
        },
        submitHandler: function(forme) {

            var eventsPriveList=[8296509458424978000]  ;
            var eventsPubList=[];

            var listAmis = $("#eventsPub:checked").each(function (index, elem) {
                eventsPubList.push($(elem).val());
            });


            date = $( "input[name='dateSoire']" ).val();
            var nbrPlaces = $("#nbrPlaces :selected").text();;
            var dateSoiree = $( "input[name='dateSoire']" ).val().split("-");
            var timeSoiree = $( "input[name='timeSoire']" ).val().split("-")

            description= $('textarea#description').val();

            var date=dateSoiree[2]+"/"+dateSoiree[1]+"/"+dateSoiree[0]+" "+timeSoiree;
            console.log("DATE : "+date);
            console.log("nbr place : "+nbrPlaces);

            console.log(idUser);

            $.ajax({
                type: 'POST',
                url: "http://localhost:8082/soirees?pseudo="+idUser  ,
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({
                    "commentaires": [],
                    "dateTime": date,
                    "description": description,
                    "etatUtilisateur": [],
                    "idPrivateEvents": eventsPriveList,
                    "idPublicEvents": eventsPubList,
                    "nbrPlaces": nbrPlaces,
                    "pseudoCreateur": idUser
                }),
                success: function (rep) {
                    console.log(rep);
                    alert("Création de soirée réussie !")
                    window.location = "creerSoiree";
                },
                error: function (request, status, error) {
                    console.log(request.responseText);
                    alert("Erreur création soirée");
                    //
                }
            });

        }


        });

    axios.get("http://localhost:8082/soirees?pseudo="+idUser)
        .then(function (response) {
            donnee=response.data;
            console.log(donnee);

            var app= new Vue({
                el:'#soireVue',
                data:{
                    soireList: donnee,
                    num:0,
                    seen:false


                }

            })

        })
        .catch(function (error) {
            console.log(error);

        });

});




