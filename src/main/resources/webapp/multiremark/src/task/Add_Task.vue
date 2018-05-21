<template>
  <div id="app" class="w-75" style="padding-left: 25%">
    <form v-on:submit="addTask($event)">
      <input v-model="task.name" placeholder="name" class="form-control"><br/>
      <textarea v-model="task.text" placeholder="text" class="form-control"></textarea><br/>
      <label class="col-form-label">Task type</label>
      <select v-model="task.workType.type" class="custom-select">
        <option value="TEST" selected class="dropdown-item">TEST</option>
        <option value="TEXT" class="dropdown-item">TEXT</option>
        <option value="PROJECT" class="dropdown-item">PROJECT</option>
        <option value="CODE" class="dropdown-item">CODE</option><br/>
      </select><br/>
      <button value="Send" type="submit" class="btn btn-primary">Add task</button>
      <br/>
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
        task: {
          name: null,
          text: null,
          workType: {
            type: "TEST"
          }
        },
        question: {
          text: "Linux vs Windows",
          answers: ['Linux', 'Windows'],
        },
        answers: '',
      }
    },
    methods: {
      addTask(event) {
//        this.task.answers.push(event.target.value);
        axios.post('/api/tasks', this.task).then(function (response) {
          this.$router.push("/tasks/" + response.data.id)
        }.bind(this))
      },
      /*login_method: function (event) {
        event.preventDefault();
        const form_data = new FormData();
        form_data.append("login", "teacher@teacher.com");
        form_data.append("password", "password");

        axios.post('/api/login', form_data).then(function (response) {
          console.log(response)
        }.bind(this))
      }*/
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
