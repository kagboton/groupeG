$(document).ready(function () {

    var idUser = $("#idValue").val();

    var donnee;




    axios.get("http://localhost:8084/users/"+ idUser +"/invitation-in")
        .then(function (response) {
            donnee=response.data;
             console.log(donnee);

            var app= new Vue({
                el:'#invitationVue',
                data:{
                    invitationList: donnee

                },
                methods:{
                    confirmer: function(idInv,pseudoRes){

                        console.log("id Inv: "+ idInv);
                        console.log("qui invite: "+pseudoRes);
                        console.log("qui recoit:" + idUser);
                        $.ajax({
                            type: 'PUT',
                            url: "http://localhost:8084/users/"+idUser+"/invitation-in/"+idInv+"?invitationState=accepter",
                            async: true,
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                            },
                            data: JSON.stringify({
                                "idInv": idInv,
                                "pseudo": u=idUser


                            }),
                            success: function () {
                                $.ajax({
                                    type: 'PUT',
                                    url: "http://localhost:8084/users/"+pseudoRes+"/invitation-out/"+idInv+"?invitationState=accepter",
                                    async: true,
                                    headers: {
                                        'Accept': 'application/json',
                                        'Content-Type': 'application/json'
                                    },
                                    data: JSON.stringify({
                                        "idInv": idInv,
                                        "pseudo": u=pseudoRes


                                    }),
                                    success: function () {
                                         console.log("ajouter user");





                                    },
                                    error: function (request, status, error) {
                                        console.log(request.responseText);
                                       // alert("Erreur Invitation Amis");

                                    }
                                });


                            },
                            error: function (request, status, error) {
                                console.log(request.responseText);
                                alert("Erreur invitation amis");

                            }
                        });

                        $.ajax({
                            type: 'POST',
                            url: "http://localhost:8084/users/"+idUser+"/friends",
                            async: true,
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                            },
                            data: JSON.stringify({

                                "pseudoOfFriend": pseudoRes,
                                "pseudoOfUser": idUser



                            }),
                            success: function () {
                                // console.log(msg);
                                //



                            },
                            error: function (request, status, error) {
                                console.log(request.responseText);
                               // alert("Erreur Invitation Amis");

                            }
                        });

                        $.ajax({
                            type: 'POST',
                            url: "http://localhost:8084/users/"+pseudoRes+"/friends",
                            async: true,
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                            },
                            data: JSON.stringify({

                                "pseudoOfFriend": idUser,
                                "pseudoOfUser": pseudoRes



                            }),
                            success: function () {
                                // console.log(msg);
                                alert("Invitation acceptée");

                            },
                            error: function (request, status, error) {
                                console.log(request.responseText);
                               // alert("Erreur Invitation Amis");

                            }
                        });



                    },
                    refuser: function (idInv) {
                        if(confirm('Voulez-vous vraiment refuser cette invitation ?')){
                            $.ajax({
                                type: 'PUT',
                                url: "http://localhost:8084/users/"+idUser+"/invitation-in/"+idInv+"?invitationState=refuser",
                                async: true,
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json'
                                },
                                data: JSON.stringify({
                                    "idInv": idInv,
                                    "pseudo": u=idUser


                                }),
                                success: function () {
                                    $.ajax({
                                        type: 'PUT',
                                        url: "http://localhost:8084/users/"+pseudoRes+"/invitation-out/"+idInv+"?invitationState=refuser",
                                        async: true,
                                        headers: {
                                            'Accept': 'application/json',
                                            'Content-Type': 'application/json'
                                        },
                                        data: JSON.stringify({
                                            "idInv": idInv,
                                            "pseudo": u=pseudoRes


                                        }),
                                        success: function () {
                                            // console.log(msg);
                                           // alert("Invitation suprimée");

                                        },
                                        error: function (request, status, error) {
                                            console.log(request.responseText);
                                           // alert("Erreur suppression Amis");

                                        }
                                    });

                                },
                                error: function (request, status, error) {
                                    console.log(request.responseText);
                                    //alert("Erreur Suppresion Amis");

                                }
                            });


                        }

                    }
                }

            })

        })
        .catch(function (error) {
            console.log(error);

        });



})