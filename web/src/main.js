// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import './theme/index.css'
import './static/GlobalStyle.css'

import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import App from './App.vue'
import ElementUI from 'element-ui'
import $ from 'jquery'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min'
import axios from 'axios'
import qs from 'qs'

Vue.use(VueRouter)

Vue.prototype.$http = axios
axios.defaults.withCredentials = true
axios.defaults.baseURL = '' // 关键步骤–填写后台请求统一的地址
axios.defaults.headers.post['x-csrf-token'] = localStorage.getItem('token')
Vue.config.productionTip = false

Vue.use(ElementUI, { size: 'small', zIndex: 3000 })

let vm = new Vue()
axios.interceptors.request.use((config) => {
  if (config.method === 'post') {
    config.data = qs.stringify(config.data)
  }
  return config
}, (error) => {
  return Promise.reject(error)
})
axios.interceptors.response.use(
  res => {

    return res
  }, err => {

  return Promise.resolve(err)
})


/* eslint-disable no-new */
vm = new Vue({
  data () {
    return {

    }
  },
  methods: {

  },
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  render: h => h(App)
})
