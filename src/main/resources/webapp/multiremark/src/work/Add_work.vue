<template>
  <div id="app">
    {{task.name}}
    <br>
    {{task.text}}
    {{task.workType.type}}
    <div v-if="task.workType.type === 'TEST'">
      <form v-on:submit="addAnswer($event)">
        <textarea v-model="answerRequest.text"/>
        <template v-for="question in task.questions">
          {{question.value}}
          <li v-for="ans in question.answers">
            <input type="checkbox" :value="ans.id" @click="addNewAnswer($event, question.id)">{{ans.value}}
          </li>
        </template>
        <button value="Send">Submit</button>
      </form>
    </div>
    <div v-else>
      <form v-on:submit="addWork($event)">
        <textarea v-model="work.workAnswer.text"></textarea>
        {{work.workAnswer.text}}
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
        task: {
          name: '',
          workType: {},
          questions: [{answers: [],}]
        },
        answerRequest: {
          taskId: '',
          answerIds: [],
          text: ''

        }
      }
    },
    created() {
      const id = this.$route.params.id;
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
        var id = this.$route.params.id;
        console.log(this.work);
        axios.post('/api/work/' + id, this.work).then(function (response) {
          if (response.status > 400 && response.status < 404) {
            this.$router.push("/login")
          } else {
            this.$router.push("/tasks")
          }
        }.bind(this))
      },
      addAnswer(event) {
        this.answerRequest.taskId = this.$route.params.id;
        axios.post('/api/questions/answer', this.answerRequest).then(function (response) {
          if (response.status > 400 && response.status < 404) {
            this.$router.push("/login")
          } else {
            this.$router.push("/tasks")
          }
        }.bind(this))
      },
      addNewAnswer(event, question_id) {
        if (event.target.checked) {
          this.answerRequest.answerIds.push({answerId: event.target.value, questionId: question_id});
        } else {
          this.answerRequest.answerIds.splice(this.answerRequest.answerIds.indexOf({
            answerId: event.target.value,
            questionId: question_id
          }), 1);
        }
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
