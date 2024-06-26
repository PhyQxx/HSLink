// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router'
import '@/assets/css/public.css'
import api from './api/index.js'
import axios from 'axios'
import Pagination from "@/components/components/Pagination";
import scroll from 'vue-scroll'
import  mavonEditor  from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import { getDict } from '@/api/system/dict'

Vue.prototype.getDict = getDict;
Vue.prototype.$axios = axios;
Vue.prototype.$ajax = api;
Vue.component('Pagination', Pagination);
Vue.config.productionTip = false;
Vue.use(mavonEditor);
Vue.use(ElementUI);
Vue.use(scroll);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
