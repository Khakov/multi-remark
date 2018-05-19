<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand" href="#">Multi remark</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

          <div v-if="this.$store.state.user != null">
            <button v-on:click="logout($event)" class="btn btn-outline-dark my-2 my-sm-0"> logout
            </button>
          </div>
          <div v-else>
            <router-link class="nav-link" to="/login">
              <button class="btn btn-outline-dark my-0 my-sm-0">login </button>
            </router-link>
          </div>
          <!--get task-->
          <li class="nav-item active">
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/tasks">tasks</router-link>
          </li>
          <!--add student-->
          <li class="nav-item">
            <router-link class="nav-link" to="/add_student">add student</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/add_task">add task</router-link>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
              Dropdown
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <a class="dropdown-item" href="#">Action</a>
              <a class="dropdown-item" href="#">Another action</a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#">Something else here</a>
            </div>
          </li>

        </ul>

        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
      </div>
    </nav>
    <div v-if="this.$store.state.user === null">
      <div class="w-75" style="padding-left: 25%">
        <form v-on:submit="login_method($event)">
          <input v-model="info.login" placeholder="Login" class="form-control">
          <input v-model="info.password" placeholder="password" type="password" class="form-control">
          <button type="submit" class="btn btn-primary">login</button>
        </form>
      </div>
    </div>
    <div v-else>
      <router-view></router-view>
    </div>
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
        answers: '',
      }
    },
    created() {
      axios.get('/api/user', null).then(function (response) {
        this.$store.commit('add_user', response.data);
        console.log(this.$store.state.user);
      }.bind(this))
    },
    methods: {
      logout(event) {
        axios.post('/api/logout', null).then(function (response) {
          this.$store.commit('add_user', null);
          console.log(response);
          this.$router.push('/login')
        }.bind(this))
      },
      login_method: function (event) {
        event.preventDefault();
        const form_data = new FormData();
        form_data.append("login", this.info.login);//"teacher@teacher.com"
        form_data.append("password", this.info.password);//"password"

        axios.post('/api/login', form_data).then(function (response) {
          console.log(response);
          console.log(this.$store);
          if (response.status === 200) {
            axios.get('/api/user', null).then(function (response) {
              this.$store.commit('add_user', response.data);
              console.log(this.$store.state.user);
            }.bind(this));
            console.log(this.$store.state.user);
            this.$router.push('/tasks')
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
