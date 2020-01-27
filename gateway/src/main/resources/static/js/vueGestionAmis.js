(function() {
    // TODO: be more elegant here
    function format(text) {
        return text.replace(/ /g,'').replace(/(<([^>]+)>)/ig, '').toLowerCase();
    }

    var SearchOnList = {
        $LIST           : '[data-search-on-list=list]',
        $SEARCH         : '[data-search-on-list=search]',
        $LIST_ITEM      : '[data-search-on-list=list-item]',
        $COUNTER        : '[data-search-on-list=counter]',
        TEMPLATE_EMTPY  : '<li class="list-item list-item--disable">No results found</li>',


        init: function($element) {
            this.items = [];
            this.itemsMatched = [];

            this.$element = $element;
            this.$list = this.$element.find(this.$LIST);
            this.$search = this.$element.find(this.$SEARCH);
            this.$counter = this.$element.find(this.$COUNTER);

            this.items = this._getAllItems();
            this.itemsMatched = this.items;

            this._updateCounter();
            this._handleResults();
            this._setEventListeners();
        },

        _setEventListeners: function() {
            this.$search
                .on('keyup', $.proxy(this._onKeyup, this))
                .on('query:changed', $.proxy(this._handleQueryChanged, this))
                .on('query:results:some', $.proxy(this._handleResults, this))
                .on('query:results:none', $.proxy(this._handleNoResults, this))
        },

        _onKeyup: function() {
            var query = this.$search.val(),
                previousQuery = this.$search.data('previousQuery', query);


            // TODO: Decide when query actually changed

            if (this._queryChanged()) {
                this.$search.trigger('query:changed', {
                    query: query,
                    previousQuery: previousQuery
                });
            }
        },

        _queryChanged: function() {
            var query = this.$search.val();
            if ($.trim(query).length === 0 && this.$search.data('previousQuery') === undefined) {
                return false;
            }
            return true;
        },

        _handleQueryChanged: function(e, data) {
            this.itemsMatched = this.items.map(function(item) {
                if (format(item.name).match(format(data.query))) {
                    return {
                        name: item.name,
                        visible: true
                    }
                }
                return {
                    name: item.name,
                    visible: false
                }
            });

            this._render();
            this._updateCounter();
        },

        _handleNoResults: function() {
            this.$list.html(this.TEMPLATE_EMTPY);
        },

        _handleResults: function() {
            this.$list.empty().append(this._renderItemsVisible())
        },

        _someItemsVisible: function() {
            return this.itemsMatched.some(function(item) {
                return item.visible;
            });
        },

        _render: function() {
            (this._someItemsVisible()) ?
                this.$search.trigger('query:results:some') :
                this.$search.trigger('query:results:none');
        },

        _updateCounter: function() {
            (this._someItemsVisible()) ?
                this.$counter.text(this._renderItemsVisible().length) :
                this.$counter.text('');
        },

        _getAllItems: function() {
            var $items = this.$list.find(this.$LIST_ITEM);

            return $items.map(function() {
                var $item = $(this);

                return {
                    name: $item.html(),
                    visible: true
                };
            }).toArray();
        },

        _renderItemsVisible: function() {
            var itemInTemplate;
            return this.itemsMatched.sort(function(a, b) {
                if (a.name < b.name) return -1
                if (a.name > b.name) return 1;
                return 0;
            }).reduce(function(items, item) {
                itemInTemplate = '<li class="list-item" data-search-on-list="list-item">' + item.name + '</li>';
                if (item.visible) {
                    items.push(itemInTemplate);
                }
                return items;
            }, []);
        }
    };

    window.SearchOnList = SearchOnList;
})();

$(document).ready(function () {

    var idUser = $("#idValue").val();
    var pseudoOfFriend;

    var idInv;

    var donnee;




    axios.get('http://localhost:8084/users/')
        .then(function (response) {
            donnee=response.data;
            // console.log(donnee);


            $.ajax({
                url: 'http://localhost:8084/users/'+idUser+"/friends",
                type: 'GET',
                async: false,
                success:function(results) {
                    var frienduser = results;
                    console.log(donnee);

                    var app= new Vue({
                        el:'#ListAmisVue',
                        data:{
                            users: donnee,
                            num:0,
                            seen:false,
                            pseudoUser:idUser,
                            friendsamisRES:frienduser


                        },
                        methods:{
                            ExistUser:function (pseudo) {
                                if(pseudo == idUser){
                                    return false;
                                }
                            }
                        }

                    })
                }
            });



        })
        .catch(function (error) {
            console.log(error);

        });


    axios.get('http://localhost:8084/users/'+idUser+"/friends")
        .then(function (response) {
            donnee=response.data;
            console.log(donnee);



            var app= new Vue({
                el:'#friendsVue',
                data:{
                    friends: donnee,
                    num:0,
                    seen:false


                }

            })

        })
        .catch(function (error) {
            console.log(error);

        });



    // $.ajax({
    //     url: 'http://localhost:8084/users/',
    //     type: 'GET',
    //     success:function(results) {
    //         var user = results;
    //         console.log(results);
    //
    //         $(".prenNom").text(results["nom"]+ " "+results["prenom"]);
    //         // $(".mail").text(results["email"]);
    //         // $(".sexe").text(results["sexe"]);
    //         // $(".situation").text(results["situation"]);
    //         // $(".tel").text(results["telephone"]);
    //         // $(".dateNaiss").text(results["dateDeNaissance"]);
    //         results.forEach(function (t) {
    //             $(".listAmis").append("<li class=\"list-item\" data-search-on-list=\"list-item\">\n" +
    //                 "                                                      <a href=\"\" class=\"list-item-link\">"+t["nom"]+" <span class=\"item-list-subtext\">"+t["prenom"]+"</span></a> <input class=\"form-check-input\" type=\"checkbox\" name=\"amis\" value=\""+t["pseudo"]+" \" id=\"defaultCheck1\"> \n" +
    //
    //                 "                                                    </li>");
    //         })
    //
    //
    //
    //
    //         SearchOnList.init($('[data-behaviour=search-on-list]'));
    //     }
    // });


    $("#new-friend").validate({
        submitHandler: function (forme) {
            var listAmis = $("input:checked").each(function (index, elem) {


                pseudoOfFriend=$(elem).val();

                 console.log(pseudoOfFriend);
                 console.log(idUser );


                function getRandomInt(max) {
                    return Math.floor(Math.random() * Math.floor(max));
                }

                idInv=getRandomInt(50);
                console.log(idInv);


                //idUser= personne qui envoie l'invitation



                $.ajax({
                    type: 'POST',
                    url: "http://localhost:8084/users/"+ idUser +"/invitation-out",
                    async: true,
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify({
                        "userReceivingInvitation": pseudoOfFriend,

                        "userSendingInvitation": idUser,
                        "id": 1




                    }),
                    success: function (id) {
                         console.log("Invitation envoyée");


                    },
                    error: function (request, status, error) {
                        console.log(request.responseText);
                        alert("Erreur invitation amis");

                    }
                });

                $.ajax({
                    type: 'POST',
                    url: "http://localhost:8084/users/"+ pseudoOfFriend +"/invitation-in",
                    async: true,
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify({

                        "userReceivingInvitation": pseudoOfFriend,
                        "userSendingInvitation": idUser,
                        "id": 1



                    }),
                    success: function (id) {
                        // console.log(msg);
                        alert("Votre invitation a bien été envoyée");


                        window.location = "ajouterAmis";

                    },
                    error: function (request, status, error) {
                        console.log(request.responseText);
                        alert("Erreur invitation amis");

                    }
                });


            });
        }
    })


})