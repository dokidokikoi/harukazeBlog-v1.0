import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    bgi: "images/bc1.jpg",
    isLoading: false
  },
  mutations: {
    SET_BGI(state, value) {
      state.bgi = "http://localhost:8080/" + value
    },
    LOADING(state) {
      state.isLoading = true
    },
    COMPLETE(state) {
      state.isLoading = false
    }
  },
  actions: {
    toTop({commit}) {
      document.querySelector('html').scrollTop = 0
    }
  },
  modules: {
  }
})
