<template>
  <div id="app" class="w-75" style="padding-left: 25%">
    <h3>Name:</h3>
    {{testCase.name}}
    <h3>Text:</h3>
    <table class="table table-hover">
      <tr v-for="(line, index) in lines">
        <td>{{index + 1}}</td>
        <td>{{line}}</td>
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
        testCase: {},
        lines: []
      }
    },
    created() {
      var id = this.$route.params.id;
      axios.get('/api/standart/' + id, this.work).then(function (response) {
        this.testCase = response.data;
        this.lines = this.testCase.text.match(/[^\r\n]+/g);
      }.bind(this))
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
