<template>
  <div id="app">
    {{task.name}}
    <br>
    {{task.text}}
    {{task.workType.type}}
    <div v-if="task.workType.type === 'TEST'">
      <form v-on:submit="addAnswer($event)">
        <textarea v-model="this.answerRequest.workAnswer.text"/>
        <li v-for="question in this.task.questions">
          {{question.text}}
        <li v-for="ans in question.answers">
          <input type="checkbox" :value="ans.id" @click="addNewAnswer($event)">{{ans.value}}
        </li>
        <button value="Send">Submit</button>
      </form>
    </div>
    <div v-else>
      <form v-on:submit="addWork($event)">
        <textarea v-model="this.work.workAnswer.text"/>
        <button value="Send">Submit</button>
      </form>
    </div>
  </div>
</template>

<script>

  import axios from 'axios'
  import router from 'vue-router'

  export default {
    name: 'app',
    data() {
      return {
        work: {
          workAnswer: {text: ''},
          name: ''
        },
        task: {},
        answerRequest: {
          taskId: '',
          answerIds: [],
          text: ''

        }
      }
    },
    created() {
      var id = $route.params.id;
      axios.get('/api/tasks/' + id, null).then(function (response) {
          if (response.status > 400 && response.status < 404) {
            this.$router.push("/login")
          } else {
            this.task = response.data;
          }
        }.bind(this)
      )
    },
    methods: {
      addWork(event) {
        var id = $route.params.id;
        axios.post('/api/work/' + id, this.work).then(function (response) {
          if (response.status > 400 && response.status < 404) {
            this.$router.push("/login")
          } else {
            this.$router.push("/tasks")
          }
        }.bind(this))
      },
      addAnswer(event) {
        this.answerRequest.taskId = $route.params.id;
        axios.post('/api/questions/answer', this.answerRequest).then(function (response) {
          if (response.status > 400 && response.status < 404) {
            this.$router.push("/login")
          } else {
            this.$router.push("/tasks")
          }
        }.bind(this))
      },
      addNewAnswer(event) {
        this.answerRequest.answerIds.push(event.target.value);
        this.answerRequest.answerIds.splice(this.answerRequest.answerIds.indexOf(event.target.value), 1);
        axios.post('/api/questions/answer', this.answerRequest).then(function (response) {
          if (response.status > 400 && response.status < 404) {
            this.$router.push("/login")
          } else {
            this.$router.push("/tasks")
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
