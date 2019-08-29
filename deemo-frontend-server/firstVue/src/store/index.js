import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      name: "马小跳",
      department:"开发部",
      number:"0",
    },
    // routes:[
    //   {
    //     path: '/',
    //     name: 'home',
    //     component: home
    //   },
    //   {
    //     path: '/index',
    //     name: '主页面',
    //     component: index
    //   },
    //   {
    //     path: '/HelloWorld',
    //     name: '哈哈',
    //     component: HelloWorld
    //   }
    // ]
  }
});
