import Vue from 'vue'
import App from './App.vue'
import Main from './Main.vue'
import Work from './Work.vue'
import Login from './Login.vue'
import Add_question from './Add_question.vue'


import VueRouter from 'vue-router'
import VueCookie from 'vue-cookie'
import Vuex from 'vuex'

Vue.use(Vuex);
Vue.use(VueCookie);
Vue.use(VueRouter);

const routes = [
  {path: '/work', component: Work},
  {path: '/login', component: Login},
  {path: '/main', component: Main},
  {path: '/add_question', component: Add_question}

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
