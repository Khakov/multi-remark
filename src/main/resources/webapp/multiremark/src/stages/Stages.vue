<template>
  <div id="app">
    <table>
      <tr v-for="stage in stages">
        <td>{{stage.id}}</td>
        <td>{{stage.stageStatus}}</td>
        <td>{{stage.stage.type}}</td>
        <router-link :to="{ name: 'get_stage', params: { id: stage.id}}">перейти</router-link>
      </tr>
    </table>
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
        stages: [{
          id: '',
          stage: {
            id: null,
            name: null,
            type: null
          },
          stageStatus: null
        }]
      }
    },
    created() {
      const id = this.$route.params.id;
      axios.get('/api/stages/' + id, null).then(function (response) {
          this.stages = response.data;
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
