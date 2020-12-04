// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import './theme/index.css'

import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import App from './App.vue'
import ElementUI from 'element-ui'

import axios from 'axios'
import qs from 'qs'

Vue.use(VueRouter)

Vue.prototype.$http = axios
axios.defaults.withCredentials = false
axios.defaults.headers['Access-Control-Allow-Origin'] = '*'
axios.defaults.baseURL = 'http://39.97.169.97:8080' // 关键步骤–填写后台请求统一的地址
Vue.config.productionTip = false

Vue.use(ElementUI, { size: 'small', zIndex: 3000 })

let vm = new Vue()

axios.interceptors.request.use((config) => {
  // const MANAGE_TOKEN = sessionStorage.getItem('managetoken')
  const TOKEN = localStorage.getItem('token');
  //不需要传token的情况
  if(TOKEN && !config.url.includes('login')){
    config.headers.Authorization = 'Bearer ' + TOKEN
  }
  // if (config.method === 'get') {
  //   config.params = config.data
  // }
  return config
}, (error) => {
  return Promise.reject(error)
})


axios.interceptors.response.use(
  res => {
    if (res.data && res.data.result.code === 1){
      return res.data
    } else if (res.data && res.data.result.code === 0){
      Vue.prototype.$message.error(res.data.result.message)
    } else {
      Vue.prototype.$message.error('系统出错')
    }
  }, err => {
    console.log(err)
    Vue.prototype.$message.error('请求失败，请稍后重试');
    return Promise.resolve(err)
  })


router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
    // if (sessionStorage.getItem('managetoken')) { //判断本地是否存在token
    if (localStorage.getItem('token')) { //判断本地是否存在token
      // console.log("存在啦！")
      // console.log(localStorage.getItem('token'))
      next();
    } else {
      // console.log("不存在！")
      // console.log(localStorage.getItem('token'))
      next({
        path: '/'
      })
    }
  } else {
    next();
  }
});



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
