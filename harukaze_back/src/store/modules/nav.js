import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex)

const state = {
    activeUri: '/'
}

const mutations = {
    SET_URI:(state, path) => {
        state.activeUri = path
    }
}

export default {
    namespaced: true,
    state,
    mutations,
}