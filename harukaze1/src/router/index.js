import Vue from 'vue'
import VueRouter from 'vue-router'

// import Welcome from '@/views/Welcome.vue'
// import Index from '@/views/Index'
// import Project from '@/views/Project'
// import Archives from '@/views/Archives'
// import Note from '@/views/Note'
// import Message from '@/views/Message'
// import About from '@/views/About'
// // import Test from '@/components/test/Test'
// import BlogDetail from '@/components/blog/BlogDetail'
// import Link from '@/views/Link'
// import Search from '@/views/blog/Search'

const Welcome = () => import(/* webpackChunkName: 'wel' */'@/views/Welcome.vue')
const Index = () => import(/* webpackChunkName: 'wel' */'@/views/Index')
const Project = () => import(/* webpackChunkName: 'blog' */'@/views/Project')
const Archives = () => import('@/views/Archives')
const Note = () => import(/* webpackChunkName: 'blog' */'@/views/Note')
const Message = () => import('@/views/Message')
const About = () => import('@/views/About')
const BlogDetail = () => import(/* webpackChunkName: 'blog' */'@/components/blog/BlogDetail')
const Link = () => import('@/views/Link')
const Search = () => import(/* webpackChunkName: 'blog' */'@/views/blog/Search')

Vue.use(VueRouter)

const routes = [
  // {
  //   path: '/test',
  //   name: 'Test',
  //   component: Test
  // },
  {
    path: '/',
    name: 'welcome',
    component: Welcome,
    redirect: '/index',
    children: [
      {
        path: '/index',
        name: 'index',
        component: Index
      },
      {
        path: '/project',
        name: 'project',
        component: Project
      },
      {
        path: '/archives',
        name: 'archives',
        component: Archives
      },
      {
        path: '/note',
        name: 'note',
        component: Note
      },
      {
        path: '/message',
        name: 'message',
        component: Message
      },
      {
        path: '/about',
        name: 'about',
        component: About
      },
      {
        path: '/blog/:id',
        name: 'blogDetail',
        component: BlogDetail
      },
      {
        path: '/link',
        name: 'link',
        component: Link
      },
      {
        path: '/search',
        name: 'search',
        component: Search
      }
    ]
  }
]

const router = new VueRouter({
  // base: process.env.NODE_ENV === 'production' ? '/newProject/index/': 'index', 
  base: '/',
  routes
})

export default router
