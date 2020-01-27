$(document).ready(function () {

    var idUser = $("#idValue").val();

    $("#form-Inv button").click(function(ev){
        ev.preventDefault()// cancel form submission
        if($(this).attr("value")=="acceptID"){
            console.log("accepter");
            alert("accepter");
        }
        if($(this).attr("value")=="refuserID"){
            console.log("refuser")
        }
        // $("#my-form").submit(); if you want to submit the form
    });


    axios.get("http://localhost:8084/users/"+ idUser +"/invitation-in")
        .then(function (response) {
            donnee=response.data;
             console.log(donnee);

            var app= new Vue({
                el:'#invitationVue',
                data:{
                    invitationList: donnee,
                    num:0,
                    seen:false


                }

            })

        })
        .catch(function (error) {
            console.log(error);

        });



})