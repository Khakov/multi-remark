<template>
  <div id="app">
    <table>
      <tr v-for="(line, index) in code">
        <td>{{index + 1}}</td>
        <td>{{line.text}}</td>
        <td>
          <button @click="saveComment(index)" v-if="editingLine == index">Save</button>
          <button @click="editComment(index)" v-else>Edit</button>
        </td>
        <td style="position: relative">
          <textarea v-model="line.comment" :disabled="editingLine != index" class="comment-disabled" :class="{'comment-editing': index == editingLine}"></textarea>
        </td>

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
        code: [{text: "lol", comment: "lol"}, {text: "lol", comment: ""}, {text: "lol", comment: "lol"}],
        work: {
          workAnswer: {text: ''},
          name: '',
          task: {}
        },
        stage: {
          id: '',
          review: {comments: [{}]},
          stage: {
            id: null,
            name: null,
            type: null
          },
          stageStatus: null
        },
        editingLine: null,
      }
    },
    created() {
//      const id = this.$route.params.id;
//      axios.get('/api/stage/' + id, null).then(function (response) {
//        if (response.status > 400 && response.status < 404) {
//          this.$router.push("/login")
//        } else {
//          this.stage = response.data;
//        }
//      }.bind(this)),
//        axios.get('/api/code/' + id, null).then(function (response) {
//          if (response.status > 400 && response.status < 404) {
//            this.$router.push("/login")
//          } else {
//            this.code = response.data;
//          }
//        }.bind(this))

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
      },
      editComment(index){
        this.editingLine = index;
      },
      saveComment(index){
        this.editingLine = null;
      }
    }
  }
</script>

<style lang="scss" scoped>
  .comment-disabled{
    position: absolute;
    top:0;
    left: 0;
    height: 2em;
    resize: none;
    width: 100px;
    transition: all 2s ease-in-out;

  }
  .comment-editing{

    width: 200px;
    height: 100px;
    z-index: 2;
  }
</style>
