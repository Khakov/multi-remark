<template>
  <div id="app">
    <div v-if="this.$store.state.user.role === 'ROLE_STUDENT'">
      <h3 class="h3">Done tasks</h3><br/>
      <table class="table">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Name</th>
          <th scope="col">Type</th>
          <th scope="col">More</th>
        </tr>
        </thead>
        <tr v-for="t in tasks.true">
          <td>
            {{t.id}}
          </td>
          <td>
            {{t.name}}
          </td>
          <td>
            {{t.workType.type}}
          </td>
          <td>
            <router-link :to="{ name: 'get_works', params: { id: t.id}}">
              <button class="btn-success btn-sm">show works</button>
            </router-link>
          </td>
        </tr>
      </table>
    <h3 class="h3">Not done tasks</h3><br/>
    <table class="table">
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Type</th>
        <th scope="col">More</th>
      </tr>
      </thead>
      <tr v-for="t in tasks.false">
        <td>
          {{t.id}}
        </td>
        <td>
          {{t.name}}
        </td>
        <td>
          {{t.workType.type}}
        </td>
        <td>
          <router-link :to="{ name: 'add_work', params: { id: t.id}}">
            <button class="btn-success btn-sm">Do task</button>
          </router-link>
        </td>
      </tr>
    </table>
    </div>
    <div v-else>
      <table class="table">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Name</th>
          <th scope="col">Type</th>
          <th scope="col">More</th>
        </tr>
        </thead>
        <tr v-for="t in tasks.false">
          <td>
            {{t.id}}
          </td>
          <td>
            {{t.name}}
          </td>
          <td>
            {{t.workType.type}}
          </td>
          <td>
            <router-link :to="{ name: 'get_task', params: { id: t.id}}">
              <button class="btn-success btn-sm">Show task</button>
            </router-link>
          </td>
        </tr>
      </table>
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
        tasks: Map
      }
    },
    created() {
      axios.get('/api/tasks/all', null).then(function (response) {
          if (response.status > 400 && response.status < 404) {
            this.$router.push("/login")
          } else {
            this.tasks = response.data;
          }
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
