$(document).ready(function ()
{


    var app;

    axios.get('https://data.orleans-metropole.fr/api/datasets/1.0/search/?rows=10&start=0')
        .then(function (response) {
            donnee=response.data.datasets;
            // console.log(donnee);

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




