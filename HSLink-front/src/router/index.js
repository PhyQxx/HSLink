import Vue from 'vue'
import Router from 'vue-router'
// import Login from '../components/Login.vue'

Vue.use(Router)

export default new Router({
    routes: [{
        path: '/login',
        name: 'login',
        component:() => import('@/components/Login')
    },
      {
        path:'/',
        name:'index',
        component:() => import('@/components/Index'),
        children: [
          {
            path:'help',
            name:'help',
            component:() => import('@/components/Help')
          },
          {
            path:'homepage',
            name:'homepage',
            component:() => import('@/components/Homepage')
          },
          {
            path:'schoolnotice',
            name:'schoolnotice',
            component:() => import('@/components/SchoolNotice')
          },
          {
            path:'parentsproposal',
            name:'parentsproposal',
            component:() => import('@/components/ParentsProposal')
          },
          {
            path:'studentthought',
            name:'studentthought',
            component:() => import('@/components/StudentThought')
          },
          {
            path:'myclass',
            name:'myclass',
            component:() => import('@/components/MyClass')
          },
          {
            path:'content',
            name:'content',
            component:() => import('@/components/Content')
          }
        ]
      }



    ]
})
