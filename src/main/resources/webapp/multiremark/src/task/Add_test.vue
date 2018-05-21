<template>
  <div id="app" class="w-75" style="padding-left: 25%">
    <form v-on:submit="addTest($event)">
      <textarea v-model="testCase.text" class="form-control"></textarea>
      <input type="text" v-model="testCase.input" class="form-control" placeholder="input value"/>
      <input type="text" v-model="testCase.expected" class="form-control" placeholder="expected result"/>
      <button value="Send" type="button" class="btn btn-primary">Add test</button>
    </form>
  </div>
</template>

<script>

  import axios from 'axios'
  import router from 'vue-router'

  export default {
    name: 'app',
    data() {
      return {
        testCase: {}
      }
    },
    created() {
    },
    methods: {
      addTest(event) {
        var id = this.$route.params.id;
        axios.post('/api/test-case/' + id, this.work).then(function (response) {
          this.$router.push("/tasks/" + id);
        }.bind(this))
      },
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
