import {createRouter, createWebHistory} from 'vue-router'
import Tsutsumeki from '@/components/Tsutsumeki.vue'

const routes = [
    {path: '/', component: Tsutsumeki},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router