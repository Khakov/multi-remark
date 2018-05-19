<template>
  <div id="app" class="w-75" style="padding-left: 25%">
    <form v-on:submit="addQuestion($event)">
      <input type="text" class="form-control" v-model="question.value" placeholder="add question"/> <br>
      <select v-model="question.type" class="custom-select">
        <option value="SIMPLE" selected class="dropdown-item">choice question</option>
        <option value="TEXT" class="dropdown-item">text question</option>
      </select>
      <div v-if="question.type === 'SIMPLE'">
        <div v-for="(ans, index) in question.answers">
          <input class="form-check-input" type="checkbox"
                 :value="ans.id" @click="addAnswerRight($event, index)">{{ans.value}}
        </div>
        <input class="form-control" type="text" placeholder="add answer" @keyup.enter="addAnswer($event)"/>
      </div>
      <br>
      <button value="Send" type="submit" class="btn btn-primary">Add question</button>
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
        question: {
          type: "SIMPLE",
          value: "Linux vs Windows",
          answers: [{value: 'Linux', right: false}, {value: 'Windows', right: false}],
        },
        answers: '',
      }
    },
    methods: {
      addAnswer(event) {
        this.question.answers.push({value: event.target.value, right: false});
      },
      addQuestion(event) {
        const id = this.$route.params.id;
        axios.post('/api/questions/' + id, this.question).then(function (response) {
          this.$router.push("/tasks/"+ id);
          console.log(response)
        }.bind(this))
      },
      addAnswerRight(event, ans) {
        if (event.target.checked) {
          this.question.answers[ans].right = true;
          console.log(ans);
        } else {
          this.question.answers[ans].right = false;
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
