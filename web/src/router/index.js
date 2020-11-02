import Vue from 'vue'
import Router from 'vue-router'
import article from '../controllers/article'
import manageLogin from '../controllers/ManageLogin'
import manage from '../ctrlManage'

Vue.use(Router)

export default new Router({
  mode: 'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/',
      component: article,
      meta: { title: '文章' }
    },
    {
      path: '/manage',
      component: manageLogin,
      meta: { manage: true }
    },
    {
      path: '/managepage',
      component: manage.Layout,
      children: [

      ]
    }
  ]
})
