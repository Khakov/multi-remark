<template>
  <div id="app">
    <div>
      {{tasks.name}}
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
          <button class="btn-success btn-sm">add question</button></router-link>
        <router-view></router-view>
      </div>
      <div v-if="tasks.workType.type ==='CODE' || tasks.workType.type ==='TEXT' || tasks.workType.type ==='PROJECT'">
        <h3>Standarts:</h3>
        <div v-for="s in tasks.standarts">
          <div> {{s.id}}
          </div>
        </div>
        <br/>
      </div>
      <div v-if="tasks.workType.type ==='CODE' || tasks.workType.type ==='PROJECT'">
        <h3>Tests:</h3>
        <div v-for="t in tasks.tests">
          <div> {{t.id}}
          </div>
        </div>
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
