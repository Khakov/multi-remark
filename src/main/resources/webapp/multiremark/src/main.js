import 'bootstrap/dist/css/bootstrap.min.css'

import Vue from 'vue'
import App from './App.vue'
import Main from './Main.vue'
import Work from './work/Work.vue'
import Works from './work/Works.vue'
import Add_Work from './work/Add_work.vue'
import Login from './Login.vue'
import Add_question from './task/Add_question.vue'
import Add_Task from './task/Add_Task.vue'
import Add_Student from './Add_Student.vue'
import Get_Tasks from './task/GET_Tasks.vue'
import Get_Task from './task/GET_Task.vue'
import Stages from './stages/Stages.vue'
import Stage from './stages/Stage.vue'


import VueRouter from 'vue-router'
import VueCookie from 'vue-cookie'
import Vuex from 'vuex'

import * as VuexLocal from 'vuex-local'

Vue.use(Vuex)
Vue.use(VuexLocal, {
  // local modules will be registered under `locals` module
  parentModulePath: ['locals']
});

Vue.use(VueCookie);
Vue.use(VueRouter);

const routes = [
  {path: '/work/:id', component: Work, name: 'get_work'},
  {path: '/works/:id', component: Works, name: 'get_works'},
  {path: '/stage/:id', component: Stage, name: 'get_stage'},
  {path: '/stages/:id', component: Stages, name: 'get_stages'},
  {path: '/add_work/:id', component: Add_Work, name: 'add_work'},
  {path: '/login', component: Login},
  {path: '/main', component: Main},
  {path: '/add_question/:id', component: Add_question, name: 'add_question'},
  {path: '/add_task', component: Add_Task},
  {path: '/add_student', component: Add_Student},
  {path: '/tasks', component: Get_Tasks},
  {path: '/tasks/:id', component: Get_Task, name: 'get_task'}


];
const router = new VueRouter({
  routes // сокращение от `routes: routes`
});

const store = new Vuex.Store({
  state: {
    user: null,
    redirect: true
  },
  mutations: {
    add_user(state, username) {
      state.user = username;
    },
    add_redirect(state) {
      console.log('HUI');
      state.redirect = false;
    }
  }
});
export default {
  store
}
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store
});
