import Vue from 'vue';
import App from './App';
import '@/static/css/public.css';
import '@/static/js/public.js';
import '@/static/js/jquery-1.12.2.js';
import store from './store';

Vue.config.productionTip = false;

Vue.prototype.$store = store;

App.mpType = 'app';

const app = new Vue({
	store,
    ...App
})
app.$mount()
