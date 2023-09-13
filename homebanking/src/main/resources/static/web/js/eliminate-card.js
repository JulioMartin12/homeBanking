Vue.createApp({
    data() {
        return {
         cardsNumber: [],
         selectCard:null,
            number: "none",
            errorToats: null,
            errorMsg: null,

           }
    },

    created() {
        this.getData();
    },
    methods: {
    getData: function () {
                axios.get("/api/clients/current/cards")
                    .then((response) => {
                        //get loan types ifo
                         this.cardsNumber = response.data;
                         console.log(this.cardsNumber);
                    })
                    .catch((error) => {
                        this.errorMsg = "Error getting data";
                        this.errorToats.show();
                    })
            },
             signOut: function () {
                        axios.post('/api/logout')
                            .then(response => window.location.href = "/web/index.html")
                            .catch(() => {
                                this.errorMsg = "Sign out failed"
                                this.errorToats.show();
                            })
                    },

       eliminate: function (event) {
                   event.preventDefault();
                   if (this.number == "none" ) {
                       this.errorMsg = "You must select a card number";
                       this.errorToats.show();
                   } else {
                       let config = {
                           headers: {
                               'content-type': 'application/x-www-form-urlencoded'
                           }
                       }
                       axios.patch(`/api/clients/current/cards?number=${this.number}`,config)
                                           .then(response => window.location.href = "/web/cards.html")
                                           .catch((error) => {
                                               this.errorMsg = error.response.data;
                                               this.errorToats.show();
                                           })
                   }
               }


            },


    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));

    }
}).mount('#app')