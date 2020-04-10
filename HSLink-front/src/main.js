// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router'
import '@/assets/css/public.css'
// 引用API文件
import api from './api/index.js'
// 将API方法绑定到全局
import marked from 'marked'
import scroll from 'vue-scroll'
Vue.prototype.$ajax = api
Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(scroll)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
