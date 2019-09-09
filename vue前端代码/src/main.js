// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import Qs from 'qs';
import moment from 'moment'//导入文件 

 Vue.prototype.$moment = moment;//赋值使用


Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.prototype.axios=axios;

/* eslint-disable no-new */
new Vue({
  el: '#app',
	render: h => h(App),
  router,
  components: { App },
  template: '<App/>'
})
