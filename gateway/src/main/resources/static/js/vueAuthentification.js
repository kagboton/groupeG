// $('#authentification').modal('show');

var form;

$(document).ready(function ()
{

    var connexion = new Vue({
        el: '#errorConnexion',
        data: {
            afficher: false,
            message: 'Login / Mot de passe incorrect'
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


    var inscritpion = new Vue({
        el: '#errorInscription',
        data: {
            afficher: false,
            message: 'Inscription incorrecte'
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





    $("#connexForm").validate({
        rules: {
            // simple rule, converted to {required:true}
            login: "required",
            // compound rule
            password: "required",
        },
        messages: {
            login: "Login obligatoire",
            password: "Mot de passe obligatoire"
        },
        submitHandler: function(forme) {
            login= $( "input[name='login']" ).val();
            password= $( "input[name='password']" ).val();

            console.log(login);

            $.ajax({
                type: 'GET',
                url: "http://localhost:8084/users/"+login,
                success: function(id){
                    alert("Connexion réussie !");
                    window.location = "accueil?pseudo="+login;
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    //alert(xhr.status +" C");
                    //alert(thrownError);
                    // alert("Erreur mot de passe");
                    console.log(xhr.responseText);
                    connexion.ouvert();
                }
            });







        }
    });



    $("#inscrForm").validate({
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
                type: 'POST',
                url: "http://localhost:8084/users",
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
                    alert("L'inscription a bien été effectuée");
                    window.location = "accueil?pseudo="+pseudoInscr;
                },
                error: function (request, status, error) {
                    console.log(request.responseText);
                    alert("Erreur Inscription");
                    inscritpion.ouvert();
                }
            });







        }
    });













});