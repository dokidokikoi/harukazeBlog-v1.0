import Vue from 'vue'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'
// import './plugins/element.js'
// import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/global.css'
// import 'jquery'
// import LightTimeLine from 'vue-light-timeline'
import axios from 'axios'
// import VueLazyLoad from 'vue-lazyload'
import '@/icons' // icon 文件夹
import SvgIcon from '@/components/SvgIcon'
import CanvasBg from '@/components/CanvasBg'
import TagPane from '@/components/tag/TagPane'
import UserPane from '@/components/userPane/UserPane'
import CategoryPane from '@/components/CategoryPane'
import Loading from '@/components/Loading'
import DatePicker from 'v-calendar/lib/components/date-picker.umd'

Vue.component('date-picker', DatePicker)

// Vue.use(VueLazyLoad, {
//   preLoad: 1,
//   error: require('./assets/img/error.jpg'),
//   loading: require('./assets/img/homePage_top.jpg'),
//   attempt: 2,
// })

axios.defaults.baseURL = 'http://localhost:8080/'
Vue.config.productionTip = false
Vue.component('canvas-bg',CanvasBg)
Vue.component('tag-pane',TagPane)
Vue.component('user-pane',UserPane)
Vue.component('category-pane',CategoryPane)
Vue.component('loading',Loading)

Vue.filter('dateFormat', function(originVal) {

  const dt = new Date(originVal)
  const y = dt.getFullYear()
  const m = (dt.getMonth() + 1 + '').padStart(2, '0')
  const d = (dt.getDate() + '').padStart(2, '0')
  const hh = (dt.getHours() + '').padStart(2, '0')
  const mm = (dt.getMinutes() + '').padStart(2, '0')
  const ss = (dt.getSeconds() + '').padStart(2, '0')

  return `${y}-${m}-${d} ${hh}:${mm}:${ss}`
})

Vue.filter('yearFormat', function(originVal) {
  const dt = new Date(originVal)
  return dt.getFullYear() + '年'
})

Vue.filter('monthFormat', function(originVal) {
  const dt = new Date(originVal)
  return (dt.getMonth() + 1 + '').padStart(2, '0') + '月'
})

Vue.filter('timeFormat', function(originVal) {

  const dt = new Date(originVal)
  const d = (dt.getDate() + '').padStart(2, '0')
  const hh = (dt.getHours() + '').padStart(2, '0')
  const mm = (dt.getMinutes() + '').padStart(2, '0')
  const ss = (dt.getSeconds() + '').padStart(2, '0')

  return `${d}日 ${hh}:${mm}:${ss}`
})

Vue.filter('ellipsis', function(originVal, num = 30) {
  if (!originVal) return ''
  if (originVal.length > num) {
    return originVal.slice(0, num) + "..."
  } else return originVal
})

Vue.prototype.$http = axios

new Vue({
  router,
  store,
  render: h => h(App),
  beforeCreate() {
    Vue.prototype.$bus = this
  }
}).$mount('#app')
