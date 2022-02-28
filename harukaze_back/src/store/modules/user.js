import Vuex from 'vuex'
import Vue from 'vue'
import {getToken, setToken, removeToken} from '@/request/token'
import {login, logout} from '@/api/login'
import {getUserInfo} from '@/api/user'

Vue.use(Vuex);

const state = {
    token: '',
    id:'',
    name: '',
    avatar: '',
    account: '',
    roles: []
  }
  
const mutations = {
    SET_TOKEN: (state, token) => {
        state.token = token;
    },
    SET_ACCOUNT: (state, account) => {
        state.account = account
    },
    SET_NAME: (state, name) => {
        state.name = name
    },
    SET_AVATAR: (state, avatar) => {
        state.avatar = avatar
    },
    SET_ID: (state, id) => {
        state.id = id
    },
    SET_ROLES: (state, roles) => {
        state.roles = roles
    }
}
  
const actions = {
    login({commit}, user) {
        return new Promise((reslove, reject) => {
            login(user).then(res => {
                commit('SET_TOKEN', res.headers['Authorization'])
                setToken(res.headers['authorization'])
                reslove()
            }).catch(err => {
                reject(err)
            })
        })
    },
    logout({commit}) {
        return new Promise((reslove, reject) => {
            logout().then(res => {
                commit('SET_TOKEN', "")
                removeToken()
                reslove()
            }).catch(err => {
                reject(err)
            })
        })
    },
    getUserInfo({commit}) {
        return new Promise((reslove, reject) => {
            getUserInfo().then(res => {
                commit('SET_ACCOUNT', res.data.data.account)
                commit('SET_NAME', res.data.data.nickname)
                commit('SET_AVATAR', res.data.data.avatar)
                commit('SET_ID', res.data.data.id)
                commit('SET_ROLES', res.data.data.roleList)
                reslove()
            }).catch(err => {
                reject(err)
            })
        })
    }
}
  
export default {
    namespaced: true,
    state,
    mutations,
    actions
}
  