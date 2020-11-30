import Vue from 'vue'
import Router from 'vue-router'
import index from '../controllers/index'
import login from '../controllers/login'
import mine from '../controllers/mine'
import drawings from '../controllers/drawings'
import drawingsDetail from "../controllers/drawingsDetail";
import set from "../controllers/set";
import Article from '../controllers/Article';
import ArticleDetails from "../controllers/ArticleDetails";
import EditInformation from "../controllers/EditInformation";
import Release from "../controllers/Release";

Vue.use(Router)
const originalPush = Router.prototype.push
Router.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

export default new Router({
  mode: 'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/',
      component: index,
      meta: {
        title: '主页',
        keepAlive:false,
      }
    },
    {
      path: '/login',
      component: login,
      meta: {
        title: '登录',
        keepAlive:false,
      }
    },
    {
      path: '/article',
      component: Article,
      meta: {
        title: '文章',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/articleDetails',
      component: ArticleDetails,
      meta: {
        title: '文章详情',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/drawings',
      component: drawings,
      meta: {
        title: '手绘',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/drawingsDetail',
      component: drawingsDetail,
      meta: {
        title: '手绘详情',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/mine',
      component: mine,
      meta: {
        title: '我的',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/set',
      component: set,
      meta: {
        title: '设置',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/editInformation',
      component: EditInformation,
      meta: {
        title: '信息编辑',
        requireAuth: true,
        keepAlive:true,
      }
    },
    {
      path: '/release',
      component: Release,
      meta: {
        title: '发布',
        requireAuth: true,
        keepAlive:true,
      }
    },
  ]
})
