<template>
  <div id="app">
    <form v-on:submit="login_method($event)">

      <input v-model="info.login" placeholder="Login">
      <input v-model="info.password" placeholder="password" type="password">
      <button type="submit">send</button>
    </form>
    <input type="text" @keyup.enter="addAnswer($event)">

  </div>
</template>

<script>

  import axios from 'axios'
  import router from 'vue-router'

  export default {
    created(){

    },
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
        answers: '',
      }
    },
    methods: {
      addAnswer(event) {
        this.question.answers.push(event.target.value);
        axios.get('/main', null).then(function (response) {
          if (response.status >400) {
            this.$router.push("/login")
          }
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
