import { createApp } from 'vue'
import Vue from "vue";
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios";
import VueAxios from "vue-axios"
import * as echarts from 'echarts'
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// import mitt from "mitt"


const app = createApp(App);

// app.config.globalProperties.$bus = new mitt();


for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.config.globalProperties.$echarts = echarts;


app.use(store)
    .use(router)
    .use(VueAxios,axios)
    .use(VueDatePicker)
    .use(ElementPlus)
    // .use(mitt)
    .mount('#app')


// router.beforeEach((to, from, next) => {
//     store.commit('getAccessToken')
//     const token = store.state.access_token
//     if (!token && to.name !== 'login') {
//         next({ path: '/'})
//         console.log("login")
//     }
//     else if (token && to.name === 'login') {
//         next({ path: '/' })
//         console.log("home")
//     }
//     else (
//         next({ path: '/' })
//     )
// })
