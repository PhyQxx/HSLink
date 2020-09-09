import Vue from 'vue'
import Router from 'vue-router'
// import Login from '../components/Login.vue'

Vue.use(Router)

export default new Router({
    routes: [{
        path: '/',
        name: 'login',
        component:() => import('@/components/Login')
      },
      {
        path: '/upload',
        name: 'upload',
        component:() => import('@/view/upload')
      },
      {
        path: '/admin',
        name: 'admin',
        component:() => import('@/view/admin/Admin'),
        children: [
          {
            path: '/articlereview',
            name: 'articlereview',
            component:() => import('@/view/admin/articleReview/ArticleReview')
          },
          {
            path: '/personnelmanagement',
            name: 'personnelmanagement',
            component:() => import('@/view/admin/personnelManagement/PersonnelManagement')
          },
          {
            path: '/homepagemanagement',
            name: 'homepagemanagement',
            component:() => import('@/view/admin/homepageManagement/HomepageManagement')
          }
        ]
      },
      {
        path:'/index',
        name:'index',
        component:() => import('@/components/Index'),
        // redirect: '/homepage',
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
          },
          {
            path:'search',
            name:'search',
            component:() => import('@/components/Search')
          },
          {
            path:'personalinfo',
            name:'personalinfo',
            component:() => import('@/components/PersonalInfo')
          },
          {
            path:'editorcontent',
            name:'editorcontent',
            component:() => import('@/components/EditorContent')
          },
        ]
      }



    ]
})
