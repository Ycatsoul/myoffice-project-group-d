// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import {getRequest} from './utils/api'
import {postRequest} from './utils/api'
import {postRequestLogin} from './utils/api'
import {deleteRequest} from './utils/api'
import {putRequest} from './utils/api'
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VueRouter)

/* eslint-disable no-new */
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.postRequestLogin = postRequestLogin;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
