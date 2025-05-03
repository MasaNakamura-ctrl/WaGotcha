import {createRouter, createWebHistory} from 'vue-router'
import Tsutsumeki from '@/components/Tsutsumeki.vue'
import About from '@/components/About.vue'

const routes = [
    {path: '/', component: Tsutsumeki},
    {path: '/about', component: About}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router