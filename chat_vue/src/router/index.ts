import {createRouter, createWebHistory, Router, RouteRecordRaw} from 'vue-router'
import {getToken} from '../utils/auth'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/home',
        name: 'home',
        component: () => import('../views/home/home.vue'),
    },
    {
        path: '/',
        redirect: '/login',
        children: [
            {
                path: '/login',
                component: () => import('../views/login/login.vue')
            }
        ]
    }
]

const router: Router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to, from, next) => {
    /**
     * to: 从哪个页面
     * form: 到那个页面
     * next: 只有执行 next() 页面才会进行跳转
     */
    if (getToken()) {
        /* has token*/
        if (to.path === '/login') {
            next({ path: '/home' })
            history.replaceState(null, '', '/home')
        }else{
            next()
        }
    } else {
        // 没有token
        if (to.path === '/login') {
            next()
        }
        next('/login')
    }
})

export default router
