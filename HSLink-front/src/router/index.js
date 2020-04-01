import Vue from 'vue'
import Router from 'vue-router'
// import Login from '../components/Login.vue'

Vue.use(Router)

export default new Router({
    routes: [{
        path: '/login',
        name: 'login',
        component:() => import('@/components/Login'),
        children:[{
          //path和name不区分大小写
          path:'homepage',
          name:'homepage',
          component:() => import('@/components/Homepage')
        }]
    },



    ]
})
