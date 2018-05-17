<template>
  <div id="app">
    <div v-for="work in works">
      <router-link :to="{name:'get_work', params: {id: work.id}}">{{work.number}} - {{work.name}}</router-link>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>

  import axios from 'axios'
  import router from 'vue-router'

  export default {
    name: 'app',
    data() {
      return {
        works: [{
          answers: [],
          workAnswer: {},
          workStages: {}
        }]
      }
    },
    created() {
      const id = this.$route.params.id;
      axios.get('/api/works/' + id, null).then(function (response) {
          if (response.status > 400 && response.status < 404) {
            this.$router.push("/login")
          } else {
            this.works = response.data;
          }
        }.bind(this)
      )
    },
    methods: {
      addAnswer(event) {
        this.question.answers.push(event.target.value);
        axios.get('/main', null).then(function (response) {
          console.log(response)
        }.bind(this))
      },
      login_method: function (event) {
        event.preventDefault();
        const form_data = new FormData();
        form_data.append("login", "teacher@teacher.com");
        form_data.append("password", "password");

        axios.post('/api/login', form_data).then(function (response) {
          console.log(response)
        }.bind(this))
      }
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }

  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
