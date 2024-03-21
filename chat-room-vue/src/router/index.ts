import { createRouter, createWebHistory } from 'vue-router'
import base from '../components/base.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'base',
      redirect:'chat',
      component: base,
      children:[
        {
          path: 'chat',
          name: 'chat',
          component: () => import('../views/chat/index.vue')
        },
        {
          path: 'gameCenter',
          name: 'gameCenter',
          component: () => import('../views/gameCenter/index.vue')
        },
      ],
    },
    {
      path: '/login',
      name: 'login',
      component:  () => import('../views/login/index.vue')
    }
  ]
})

export default router
