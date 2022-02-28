import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import http from '@/request'

import '@/assets/global.css'

import MyBreadcrumb from '@/components/breadcrumb/MyBreadcrumb'

import * as echarts from 'echarts'

Vue.prototype.$echarts = echarts

Vue.config.productionTip = false
Vue.prototype.$http = http
Vue.component('my-breadcrumb', MyBreadcrumb)

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

Vue.filter('ellipsis', function(originVal, num = 30) {
  if (!originVal) return ''
  if (originVal.length > num) {
    return originVal.slice(0, num) + "..."
  } else return originVal
})

new Vue({
  router,
  store,
  render: h => h(App),
  beforeCreate() {
		Vue.prototype.$bus = this //安装全局事件总线
	}
}).$mount('#app')
