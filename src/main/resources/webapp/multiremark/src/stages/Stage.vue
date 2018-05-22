<template>
  <div id="app">
    <table class="table table-hover table-borderless">
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Code</th>
        <th scope="col">Comment</th>
      </tr>
      </thead>
      <tr v-for="(line, index) in code" scope="row">
        <td>{{index + 1}}</td>
        <td>{{line.text}}</td>
        <td style="position: relative">
          <div v-if="edit">
            <button class="btn btn-sm btn-success" @click="saveComment(index)"
                    v-if="editingLine == index">
              Save
            </button>
            <button class="btn btn-sm btn-success" @click="editComment(index)" v-else>Edit</button>
            <textarea v-model="line.comment.comment" :disabled="editingLine != index"
                      class="comment-disabled form-control"
                      :class="{'comment-editing': index == editingLine}"></textarea>
          </div>
          <div v-else>
            {{line.comment}}
          </div>
        </td>
      </tr>
    </table>
    <div v-if="edit">
      <button class="btn btn-sm btn-success" @click="reviewDone()">Add review</button>
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
        code: [{text: "lol", comment: {comment: "lol", id: 1}}, {text: "lol", comment: ""}, {
          text: "lol",
          comment: "lol"
        }],
        work: {
          workAnswer: {text: ''},
          name: '',
          task: {}
        },
        review: {},
        edit: false,
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
      const id = this.$route.params.id;
      axios.get('/api/stage/' + id, null).then(function (response) {
        this.stage = response.data;
      }.bind(this)),
        axios.get('/api/stage-edit/' + id, null).then(function (response) {
          this.edit = response.data;
        }.bind(this)),
        axios.get('/api/code/' + id, null).then(function (response) {
          this.code = response.data;
        }.bind(this)),
        axios.get('/api/review/' + id, null).then(function (response) {
          this.review = response.data;
          console.log(response.data);
          if (this.review.id === 0 && this.edit) {
            axios.post('/api/review/' + id, null).then(function (response) {
              this.review = response.data;
            })
          }
        }.bind(this)).error(function (e) {
          if (this.review === null && this.edit) {
            axios.post('/api/review/' + id, null).then(function (response) {
              this.review = response.data;
            })
          }
        });

    },
    methods: {
      editComment(index) {
        this.editingLine = index;
      },
      saveComment(index) {
        let com = this.code[index].comment;
        let req = {
          comment: com.comment,
          line: index,
          id: com.id
        };
        if (com.id === null) {
          req = {
            comment: com.comment,
            line: index
          };
        }
        console.log(this.review);
        console.log(this.review.id);
        axios.post('/api/comment/' + this.review.id, req).then(function (response) {
          this.code[index].comment.id = response.data;
        }.bind(this));
        this.editingLine = null;
      },
      reviewDone() {
        let id = this.review.id;
        axios.post('/api/review-done/' + id, null).then(function (response) {
          this.$router.push("/tasks")
        }.bind(this))
      },
      addMark() {
        let id = this.stage.id;
        axios.post('/api/mark/' + id, null).then(function (response) {
          this.$router.push("/tasks")
        }.bind(this))
      }
    }
  }
</script>

<style lang="scss" scoped>
  .comment-disabled {
    position: absolute;
    top: 0;
    left: 0;
    height: 2em;
    resize: none;
    width: 100px;
    transition: all 2s ease-in-out;

  }

  .comment-editing {

    width: 200px;
    height: 100px;
    z-index: 2;
  }
</style>
