import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ManageView from "@/views/ManageView";
import AdminView from "@/views/AdminView";
import InforView from "@/views/InforView";
import Cookies from "js-cookie";
const routes = [
  {
    path: '/',
    name: 'home',
    meta: {
      noFooter: true, //在meta中自定义属性noFooter true
      requireAuth:true
    },
    component: HomeView
  },
  {
    path: '/infor',
    name: 'infor',

    meta: {
      noFooter: true, //在meta中自定义属性noFooter true
      requireAuth:true
    },
    component: InforView
  },
  {
    path: '/admin',
    name: 'admin',
    component: AdminView
  },
  {
    path: '/manage',
    name: 'manage',
    meta: {
      noFooter: true, //在meta中自定义属性noFooter true
      requireAuth:true
    },
    component: ManageView
  },
  {
    path: '/login',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
router.beforeEach((to, from, next) =>{
    console.log(to.name,from.name)
    if (to.meta.requireAuth) {
      if(to.name.match("manage")){
        if(from.name===undefined){
          next('/admin');
        }
        if(from.name.match("admin")){
          next(true);
        }else {
          next('/admin');
        }
      }

      console.log(Cookies.get("username"))
      if (Cookies.get("username")!==null && Cookies.get('username')!==undefined) {
        next(true);
      } else {
        next('/login');
      }
    } else {
      //直接放行
      next(true);
    }
  })

export default router
