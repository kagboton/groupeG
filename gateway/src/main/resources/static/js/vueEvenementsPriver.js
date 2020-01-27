$(document).ready(function ()
{

    var idUser = $("#idValue").val();

    $("#creerEventForm").validate({
        rules: {
            // simple rule, converted to {required:true}
            titre: "required",
            // compound rule
            ville: "required",
        },
        messages: {
            titre: "titre est obligatoire",
            ville: "ville est obligatoire"
        },
        submitHandler: function(forme) {
            titre= $( "input[name='titre']" ).val();
            ville= $( "input[name='ville']" ).val();
            adresse= $( "input[name='adresse']" ).val();
            dateDebut= $( "input[name='dateDebut']" ).val();
            dateFin= $( "input[name='dateFin']" ).val();
            descriptionV= $('textarea#description').val();
            tags= $( "input[name='tags']" ).val();

            var dateDebut=dateDebut[2]+"/"+dateDebut[1]+"/"+dateDebut[0];

            var dateFin=dateFin[2]+"/"+dateFin[1]+"/"+dateFin[0];

            console.log(idUser);

            $.ajax({
                type: 'POST',
                url: "http://localhost:8085/private-events",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify ({
                    "adresse": adresse,
                    "dateDebut": "15/05/2019",
                    "dateFin": "16/05/2019",
                    "description": descriptionV,
                    "lieuEvent": "bahamas",
                    "pseudo": idUser,
                    "tags": tags,
                    "tarification": "gratuit",
                    "titre": titre,
                    "ville": ville
                }),
                success: function(id){
                    alert("L'évènement a bien été créé")
                    window.location = "creerEvenement";
                },
                error: function (request, status, error) {
                    console.log(request.responseText);
                   alert("Erreur création évènement");
                    //
                }
            });







        }
    });

    axios.get('http://127.0.0.1:8085/private-events?pseudo='+idUser)
        .then(function (response) {
            donnee=response.data;
            console.log("mes donnes : "+donnee);

            var app= new Vue({
                el:'#evenementsPriveVue',
                data:{
                    evenementPriveList: donnee,
                    num:0,
                    seen:false

                }

            })

        })
        .catch(function (error) {
            console.log(error);

        });


});




