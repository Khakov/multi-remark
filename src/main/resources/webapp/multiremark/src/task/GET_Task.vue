<template>
  <div id="app">
    <div>
      {{tasks.name}}
      <table class="table table-hover">
        <tr>
          <td> {{tasks.text}}
          </td>
        </tr>
      </table>
      <div v-if="tasks.workType.type ==='TEST'">
        <h3>Questions:</h3>
        <div v-for="q in tasks.questions">
          <div> {{q.value}}<br>
            <div v-for="a in q.answers">
              <div v-if="a.right">
                <input type="checkbox" checked/>{{a.value}}
              </div>
              <div v-else>
                <input type="checkbox"/>{{a.value}}
              </div>
              <br/>
            </div>
          </div>
        </div>
        <br/>
        <router-link :to="{ name: 'add_question', params: { id: tasks.id}}">
          <button class="btn-success btn-sm">add question</button>
        </router-link>
      </div>
      <div v-if="tasks.workType.type ==='CODE' || tasks.workType.type ==='TEXT' || tasks.workType.type ==='PROJECT'">
        <h3>Standarts:</h3>
        <table class="table">
          <tr v-for="(s, index) in tasks.standarts">
            <td> {{index + 1}}</td>
            <td>{{s.name}}</td>
            <td>
              <router-link :to="{ name: 'get_standart', params: { id: s.id}}">
                <button class="btn-success btn-sm">show</button>
              </router-link>
            </td>
          </tr>
        </table>
        <router-link :to="{ name: 'add_standart', params: { id: tasks.id}}">
          <button class="btn-success btn-sm">add standart</button>
        </router-link>
        <br/>
      </div>
      <div v-if="tasks.workType.type ==='CODE' || tasks.workType.type ==='PROJECT'">
        <h3>Tests:</h3>
        <table class="table">
          <tr v-for="t in tasks.tests">
            <td>{{index + 1}} test</td>
            <td>
              <router-link :to="{ name: 'get_test', params: { id: t.id}}">
                <button class="btn-success btn-sm">show test</button>
              </router-link>
            </td>
          </tr>
        </table>
        <br/>
        <router-link :to="{ name: 'add_test', params: { id: tasks.id}}">
          <button class="btn-success btn-sm">add standart</button>
        </router-link>
      </div>
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
        tasks: {
          standarts: {},
          tasks: {},
          workType: {},
          questions: {answers: {},}
        }
      }
    },
    created() {
      const id = this.$route.params.id;
      axios.get('/api/tasks/' + id, null).then(function (response) {
          this.tasks = response.data;
        }.bind(this)
      )
    },
    methods: {}
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
