<template>
  <div id="app">
    <div v-if="this.$store.state.user != null">
      <button v-on:click="logout($event)">logout</button>
    </div>
    <div v-else>
      <router-link to="/login">login</router-link><br>
      <router-link to="/add_question">add question</router-link>

    </div>
    <router-view></router-view>

  </div>
</template>

<script>

  import axios from 'axios'

  export default {
    name: 'app',
    data() {
      return {
        info: {
          login: "",
          password: ""
        },
        msg: 'Welcome to Your Vue.js App',
        question: {
          text: "Linux vs Windows",
          answers: ['Linux', 'Windows'],
        },
        answer: '',
      }
    },
    methods: {
      logout(event) {
        axios.post('/api/logout', null).then(function (response) {
          this.$store.commit('add_user', null);
          console.log(response)
        }.bind(this))
      },
      login_method: function (event) {
        event.preventDefault();
        const form_data = new FormData();
        form_data.append("login", "teacher@teacher.com");
        form_data.append("password", "password");

        axios.post('/api/login', form_data).then(function (response) {
          console.log(response);
          console.log(this.$store);
          if (response.status === 200) {
            this.$store.commit('add_user', {'user': 'user'});
            console.log(this.$store.state.user);
            this.$router.push('/main')
          }
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
