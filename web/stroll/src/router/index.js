import Vue from 'vue'
import Router from 'vue-router'
import index from '../controllers/index'
import Article from '../controllers/Article'
import mine from '../controllers/mine'
import drawings from '../controllers/drawings'
import ArticleDetails from "../controllers/ArticleDetails";

Vue.use(Router)

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
      path: '/mine',
      component: mine,
      meta: {
        title: '我的',
        requireAuth: true,
        keepAlive:true,
      }
    },
  ]
})
