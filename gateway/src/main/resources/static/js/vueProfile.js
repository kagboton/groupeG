$(document).ready(function () {

    var idUser = $("#idValue").val();


    var profile = new Vue({
        el: '#errorProfile',
        data: {
            afficher: false,
            message: 'Erreur dans la mis a jour de user '
        },
        methods:{
            ouvert: function () {
                this.afficher= true
            },
            fermer: function () {
                this.afficher= false
            }
        }
    });

    $("#UpdateProfileForm").validate({
        rules: {
            // simple rule, converted to {required:true}
            pseudoInscr: "required",
            email: "required",
            nom: "required",
            prenom: "required",
            numtel: "required",
            dateNaiss: "required",
            // compound rule
            passwordInscr: "required",
            passwordConf:{
                required: true,
                equalTo: "#passwordInscr"
            }
        },
        messages: {
            pseudoInscr: "Login est Obligatoire",
            email: "email est Obligatoire",
            nom: "nom est Obligatoire",
            prenom: "prenom est Obligatoire",
            numtel: "num Tel est Obligatoire",
            dateNaiss: "Date Naissance est Obligatoire",
            // compound rule
            passwordInscr: "password est Obligatoire",
            passwordConf: "verifie que les deux password sont identiques "
        },
        submitHandler: function(forme) {

            var pseudoInscr= $( "input[name='pseudoInscr']" ).val();
            var email= $( "input[name='email']" ).val();
            var nom= $( "input[name='nom']" ).val();
            var prenom= $( "input[name='prenom']" ).val();
            var numtel= $( "input[name='numtel']" ).val();
            var dateNaiss= $( "input[name='dateNaiss']" ).val().split("-");
            var passwordInscr= $( "input[name='passwordInscr']" ).val();
            var sexeRadio= $( "input[name='sexeRadio']" ).val();
            var situationRadio= $( "input[name='situationRadio']" ).val();

            var dateNaiss=dateNaiss[2]+"/"+dateNaiss[1]+"/"+dateNaiss[0];



            $.ajax({
                type: 'PUT',
                url: "http://localhost:8084/users/"+idUser,
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data:JSON.stringify ({
                    "dateDeNaissance": dateNaiss,
                    "email": email,
                    "nom": nom,
                    "passwd": passwordInscr,
                    "photo": "string",
                    "prenom": prenom,
                    "pseudo": pseudoInscr,
                    "sexe": sexeRadio,
                    "situation": situationRadio,
                    "telephone": numtel

                }),
                success: function(id){
                    // console.log(msg);
                    alert("Le profil a correctement été mis à jour");
                    window.location = "accueil?pseudo="+pseudoInscr;
                },
                error: function (request, status, error) {
                    console.log(request.responseText);
                    alert("Erreur modification utilisateur");
                    profile.ouvert();
                }
            });







        }
    });



    $.ajax({
        url: 'http://localhost:8084/users/'+idUser,
        type: 'GET',
        success:function(results) {
            var user = results;
            //console.log(results);
            $(".prenNom").text(results["nom"]+ " "+results["prenom"]);
            $(".mail").text(results["email"]);
            $(".sexe").text(results["sexe"]);
            $(".situation").text(results["situation"]);
            $(".tel").text(results["telephone"]);
            $(".dateNaiss").text(results["dateDeNaissance"]);


        }
    });


    axios.get('http://localhost:8084/users/'+idUser)
        .then(function (response) {
            donnee=response.data;
             console.log(donnee);

            var app= new Vue({
                el:'#UpdateProdilVue',
                data:{
                    user: donnee,
                    num:0,
                    seen:false


                }

            })

        })
        .catch(function (error) {
            console.log(error);

        });

})