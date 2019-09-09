import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      name: "马小跳",
      department:"开发部",
      number:"0",
      routes:[],
    },
    routes:[], 
  },
  mutations: {
    },
   actions: {
    
    }
});
