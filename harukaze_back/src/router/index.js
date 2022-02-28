import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'
import { getToken } from '@/request/token'
// import { Message } from 'element-ui'

// import Home from '@/views/Home'
// import Login from '@/views/Login'
// import ShowArticle from '@/views/blog/ShowArticle'
// import EditArticle from '@/views/blog/EditArticle'
// import WriteArticle from '@/views/blog/WriteArticle'
// import Index from '@/views/Index'
// import Tag from '@/views/tag/Tag'
// import Category from '@/views/category/Category'
// import Deleted from '@/views/blog/Deleted'
// import MyMessage from '@/views/message/Message'
// import Log from '@/views/log/Log'
// import Personal from '@/views/Personal'
// import User from '@/views/User'
// import Link from '@/views/Link'
// import Test from '@/views/Test'

const Home = () => import(/* webpackChunkName: 'login_frame' */'@/views/Home')
const Login = () => import(/* webpackChunkName: 'login_frame' */'@/views/Login')
const Index = () => import(/* webpackChunkName: 'login_frame' */'@/views/Index')

const ShowArticle = () => import(/* webpackChunkName: 'article' */'@/views/blog/ShowArticle')
const EditArticle = () => import(/* webpackChunkName: 'article' */'@/views/blog/EditArticle')
const WriteArticle = () => import(/* webpackChunkName: 'article' */'@/views/blog/WriteArticle')
const Tag = () => import('@/views/tag/Tag')
const Category = () => import('@/views/category/Category')
const Deleted = () => import(/* webpackChunkName: 'article' */'@/views/blog/Deleted')
const MyMessage = () => import('@/views/message/Message')
const Log = () => import('@/views/log/Log')
const Personal = () => import('@/views/Personal')
const User = () => import('@/views/User')
const Link = () => import('@/views/Link')

Vue.use(VueRouter)

const routes = [
  // {
  //   path: '/test',
  //   component: Test,
  // },
  {
    path: '/',
    name: 'home',
    component: Home,
    redirect: '/index',
    children: [
      {
        path: '/index',
        name: 'index',
        component: Index
      },
      {
        path: '/show',
        name: 'showArticle',
        component: ShowArticle
      },
      {
        path: '/edit',
        name: 'EditArticle',
        component: EditArticle
      },
      {
        path: '/write',
        name: 'writeArticle',
        component: WriteArticle
      },
      {
        path: '/tags',
        name: 'tag',
        component: Tag
      },
      {
        path: '/category',
        name: 'category',
        component: Category
      },
      {
        path: '/deleted',
        name: 'deleted',
        component: Deleted
      },
      {
        path: '/message',
        name: 'messge',
        component: MyMessage
      },
      {
        path: '/log',
        name: 'log',
        component: Log
      },
      {
        path: '/personal',
        name: 'personal',
        component: Personal
      },
      {
        path: '/user',
        name: 'user',
        component: User
      },
      {
        path: '/link',
        name: 'link',
        component: Link
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') return next()
  if (to.path === '/test') return next()
  if (getToken) {
    store.dispatch('user/getUserInfo').then(() => {
      next()
    }).catch(err => {
      // ELEMENT.Message.error(err)
      next('/login')
    }) 
  } else {
    next('/login')
  }
})

export default router
