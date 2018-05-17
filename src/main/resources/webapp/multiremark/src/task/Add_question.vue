<template>
  <div id="app">
    <form v-on:submit="addQuestion($event)">
      <input type="text" v-model="this.question.text"/>
      <select v-model="question.type">
        <option value="SIMPLE" selected>choice question</option>
        <option value="TEXT">text question</option>
      </select>
      <div v-if="question.type === 'SIMPLE'">
        <li v-for="ans in question.answers"><input type="checkbox" :value="ans.value">{{ans.value}}</li>
        <input type="text" @keyup.enter="addAnswer($event)"/>
      </div>
      <button value="Send">Submit</button>
    </form>
  </div>
</template>

<script>

  import axios from 'axios'
  import router from 'vue-router'

  export default {
    created() {

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
          type: "SIMPLE",
          value: "Linux vs Windows",
          answers: [{value: 'Linux'}, {value: 'Windows'}],
        },
        answers: '',
      }
    },
    methods: {
      addAnswer(event) {
        this.question.answers.push({value: event.target.value});
        /*axios.get('/main', null).then(function (response) {
          if (response.status > 400) {
            this.$router.push("/login")
          }
        }.bind(this))*/
      },
      addQuestion(event) {
        axios.post('/api/questions/107', this.question).then(function (response) {
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
