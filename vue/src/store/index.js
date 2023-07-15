import { createStore } from 'vuex'
// import Cookies from "core-js/internals/queue";
// import Cookies from 'vue3-cookies'
// import Cookies from "js-cookie";

export default createStore({
  state: {
    word:'',
    flag:false,
    date:""
    // access_token: '',
    // refresh_token: '',
    // last_token_refresh_time: new Date("October 01, 1975 00:00:00"),  // 以1975-10-01 00:00:00 为初始值
  },
  getters: {
  },
  mutations: {
    changeWord(state, d) {
      state.word = d;
    },
    changeFlag(state){
      state.flag = !state.flag;
    },
    changeDateRange(state,date){
      state.date=date;
    }
    // // 额外还有一个更新access token的函数
    // // access token 和 refresh token 我们是存储在cookie中，这个很简单，看代码就能理解了，想要深入了解的需要自行搜索
    // setAccessToken(state, val) {
    //   state.access_token = val
    //   Cookies.set('access_token', val)
    // },
    // clearAccessToken(state) {
    //   state.access_token = ''
    //   Cookies.remove('access_token')
    // },
    // getAccessToken(state) {
    //   state.access_token = state.access_token || Cookies.get('access_token')
    // },
    // setRefreshToken(state, val) {
    //   state.refresh_token = val
    //   Cookies.set('refresh_token', val)
    // },
    // clearRefreshToken(state) {
    //   state.refresh_token = ''
    //   Cookies.remove('refresh_token')
    // },
    // getRefreshToken(state) {
    //   state.refresh_token = state.refresh_token || Cookies.get('refresh_token')
    // },
    // updateLastRefreshTime(state) {
    //   state.last_token_refresh_time = new Date().getTime()
    //   Cookies.set('last_token_refresh_time', state.last_token_refresh_time)
    // },
  },
  actions: {
  },
  modules: {
  }
})

