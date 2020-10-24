import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login'
import Home from '../components/Home'
import Welcome from "@/components/Welcome";
import Dish from "@/components/Dish";
import Canteen from "@/components/Canteen";

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'hash',
    routes: [
        {path: '/', redirect: '/login'},
        {path: '/login', component: Login},
        {
            path: '/home',
            component: Home,
            redirect: '/welcome',
            children: [
                {path: '/welcome', component: Welcome},
                {path: '/dish', component: Dish},
                {path: '/canteen', component: Canteen},
            ]
        }
    ]
})

export default router
