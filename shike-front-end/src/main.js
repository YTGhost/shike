import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueRouter from 'vue-router'
import './plugins/element.js'
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'

axios.defaults.baseURL = 'https://www.hihia.top/shike-back-end/'
Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.use(VueRouter)


new Vue({
  render: h => h(App),
  router: router
}).$mount('#app')
