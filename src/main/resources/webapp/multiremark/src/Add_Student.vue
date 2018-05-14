<template>
  <div id="app">
    <form v-on:submit="addStudent($event)">
      <input v-model="student.name" placeholder="name">
      <input v-model="student.email" placeholder="email">
      <input v-model="student.password" type="password" placeholder="password">
      <button type="submit">send</button>
    </form>
  </div>
</template>

<script>

  import axios from 'axios'
  import router from 'vue-router'

  export default {
    name: 'app',
    data() {
      return {
        student: {
          name: null,
          password: null,
          email: null
        }
      }
    },
    methods: {
      addStudent(event) {
        axios.post('/api/student', this.student).then(function (response) {
          console.log(response)
        }.bind(this));

//        axios.get('/main', null).then(function (response) {
//          console.log(response)
//        }.bind(this))
      },
      /*login_method: function (event) {
        event.preventDefault();
        const form_data = new FormData();
        form_data.append("login", "teacher@teacher.com");
        form_data.append("password", "password");

        axios.post('/api/login', form_data).then(function (response) {
          console.log(response)
        }.bind(this))
      }*/
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
