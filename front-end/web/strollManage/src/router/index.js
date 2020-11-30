import Vue from 'vue'
import Router from 'vue-router'
import ManageLogin from '@/controllers/ManageLogin'
import ManageRights from '@/controllers/ManageRights'
import ManageUsers from '@/controllers/ManageUsers'
import ManageArticles from '@/controllers/ManageWorks/ManageArticles'
import ManageDrawings from '@/controllers/ManageWorks/ManageDrawings'
import ManageClassifys from '@/controllers/ManageWorks/ManageClassifys'
import ManageLabels from '@/controllers/ManageWorks/ManageLabels'
import Managethemes from '@/controllers/Managethemes'
import ManageFunctions from '@/controllers/ManageFunctions'
import ManageLogs from '@/controllers/ManageLogs'

Vue.use(Router)
const originalPush = Router.prototype.push
Router.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: ManageLogin,
      meta: {
        title: '主页',
        keepAlive:false,
      }
    },
    {
      path: '/ManageRights',
      component: ManageRights,
      meta: {
        title: '权限管理',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/ManageUsers',
      component: ManageUsers,
      meta: {
        title: '用户管理',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/ManageArticles',
      component: ManageArticles,
      meta: {
        title: '文章管理',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/ManageDrawings',
      component: ManageDrawings,
      meta: {
        title: '手绘管理',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/ManageClassifys',
      component: ManageClassifys,
      meta: {
        title: '分类管理',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/ManageLabels',
      component: ManageLabels,
      meta: {
        title: '标签管理',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/Managethemes',
      component: Managethemes,
      meta: {
        title: '主题管理',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/ManageFunctions',
      component: ManageFunctions,
      meta: {
        title: '功能管理',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/ManageLogs',
      component: ManageLogs,
      meta: {
        title: '查看日志',
        requireAuth: true,
        keepAlive:true,
      }
    },
  ]
})
