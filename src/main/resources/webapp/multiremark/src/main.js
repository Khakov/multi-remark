import Vue from 'vue'
import App from './App.vue'
import Main from './Main.vue'
import Work from './work/Work.vue'
import Add_Work from './work/Add_work.vue'
import Login from './Login.vue'
import Add_question from './task/Add_question.vue'
import Add_Task from './task/Add_Task.vue'
import Add_Student from './Add_Student.vue'
import Get_Tasks from './task/GET_Tasks.vue'


import VueRouter from 'vue-router'
import VueCookie from 'vue-cookie'
import Vuex from 'vuex'

Vue.use(Vuex);
Vue.use(VueCookie);
Vue.use(VueRouter);

const routes = [
  {path: '/work', component: Work},
  {path: '/add_work/:id', component: Add_Work},
  {path: '/login', component: Login},
  {path: '/main', component: Main},
  {path: '/add_question', component: Add_question},
  {path: '/add_task', component: Add_Task},
  {path: '/add_student', component: Add_Student},
  {path: '/tasks', component: Get_Tasks}


];
const router = new VueRouter({
  routes // сокращение от `routes: routes`
});

const store = new Vuex.Store({
  state: {
    user: null
  },
  mutations: {
    add_user(state, username) {
      state.user = username;
    }
  }
});

new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store
});
