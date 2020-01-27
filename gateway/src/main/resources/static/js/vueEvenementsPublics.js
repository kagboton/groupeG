$(document).ready(function ()
{


    var app;

    axios.get('http://127.0.0.1:8085/public-events?rows=6&start=1')
        .then(function (response) {
            donnee=response.data;
             console.log("mes data : "+donnee);

            var app= new Vue({
                el:'#evenementsVue',
                data:{
                    evenementList: donnee,
                    num:0,
                    seen:false

                }

            })

        })
        .catch(function (error) {
            console.log(error);

        });

});




