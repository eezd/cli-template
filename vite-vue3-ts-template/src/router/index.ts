import { createWebHistory, createRouter, RouteRecordRaw } from 'vue-router'

// 公共路由
export const constantRoutes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('@/views/index.vue'),
  },
  {
    path: '/login',
    component: () => import('@/views/login.vue'),
  },
  {
    path: '',
    component: () => import('@/layout/index.vue'),
    redirect: '/index',
    children: [
      {
        path: '/index',
        component: () => import('@/views/index.vue'),
        name: 'Index',
        meta: { title: '首页', icon: 'dashboard', affix: true },
      },
    ],
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes: constantRoutes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { top: 0 }
    }
  },
})

export default router
