<template>
  <div class="cardhead"></div>
  <div class="container">
    <div class="user">
      <div id="avatar">
        {{user}}
      </div>
      <div class="side">
        <div class="section" id="userCount" @click="choose(1)">个人资料</div>
        <div class="section" id="userOrder" @click="choose(2)">我的订单</div>
        <div class="section" id="logout" @click="logout()">退出登录</div>
      </div>
    </div>
    <div v-if="choice!==0" class="item">
      <div v-if="choice===1">
        <MyInfo></MyInfo>
      </div>
      <div v-if="choice===2">
        <MyOrder></MyOrder>
      </div>
    </div>


<!--    <div class="side">-->
<!--      <div class="section" id="userCount" @click="choose(1)">账号管理</div>-->
<!--      <div class="section" id="userOrder" @click="choose(2)">订单管理</div>-->
<!--      <div class="section" id="logout" @click="logout()">退出登录</div>-->
<!--    </div>-->
<!--    <div class="item">-->

<!--    </div>-->
  </div>
  <div class="bing">

    <div id="bingtitle">
      <p>BING推荐</p>
      <el-icon :size="30" id="refresh" @click="imgturn()">
        <RefreshRight /></el-icon></div>
    <div id="copyright">
      {{ copyright }}
    </div>
      <el-image
          style="width: 900px;
          height: 100%;
          margin-left: 50%;
          transform: translate(-450px)"
          :src="url" :fit="cover" lazy >
        <template #error>
          <div class="image-slot"></div>
        </template>
      </el-image>
    <div class="demonstration">{{ title }}</div>


  </div>
</template>


<script>
import AlertLog from "@/components/AlertLog";
import MyInfo from "@/components/MyInfo";
import MyOrder from "@/components/MyOrder";
import router from "@/router";
import Cookies from "js-cookie";
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'
import DatePicker from "@/components/DatePicker"
import axios from "axios";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


export default {
  name: "UserInfo",
  components: {DatePicker, MyOrder, MyInfo, AlertLog},
  data(){
    return{
      play:0,
      api:[],
      copyright:'',
      copyrightlink:'',
      date:'',
      title:'',
      url:'',
      show:false,
      choice:0,
      user:'',
    }
  },
  mounted() {
    this.user=Cookies.get("username");
    this.getApi();
  },
  methods:{
    getApi(){
      this.loading = true
      axios.get(`https://api.oioweb.cn/api/bing`)
          .then((response) => {
                this.loading = false
                this.api=response.data.result
                this.copyright=this.api[this.play].copyright
                this.date=this.api[this.play].date
                this.url=this.api[this.play].url
                this.title=this.api[this.play].title
                this.copyrightlink=this.api[this.play].copyrightlink
              }
          )
    },
    imgturn(){
      if (this.play===7){
        this.play=0
      }else {
        this.play+=1
      }
      this.getApi();
    },
    choose(reg){
      this.choice=reg
    },
    getState(show){
      this.show=show;
    },
    logout(){
      Cookies.remove('username')
      Cookies.remove('password')
      router.push({path:'/'})
    }
  }
}
</script>

<style scoped>
.container {
  margin-left: 50%;
  transform: translate(-600px);
  width: 1200PX;
  z-index: 2;
  display: flex;
  flex-direction: column;
}
.cardhead{
  height: 80px;
}
.side{
  margin-top: 190px;
  margin-right: 20px;
  height: 50px;
  width: 450px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.item {
  margin-top: 50px;
  margin-left: 50%;
  transform: translate(-600px);
  width: 1200PX;
  border:2px solid #bbb4b4;
  border-radius: 10px;
  padding-bottom: 20px;
}
.section{
  text-align: center;
  line-height: 30px;
  padding-left: 5px;
  padding-right: 5px;
  color: #000000;
  font-size: 16px;
  width: 100px;
  height: 30px;
  border: 1px solid #266c6e;
  border-radius: 10px;
}
.section:hover{
  cursor: pointer;
  background-color: #00517e;
}

.bing{
  margin-top: 50px;
  margin-left: 50%;
  transform: translate(-600px);
  width: 1200PX;
  border:2px solid #bbb4b4;
  border-radius: 10px;
}

.bing #copyright{
  width: 800px;
  margin-top: 20px;
  margin-bottom: 20px;
  margin-left: 50%;
  transform: translate(-450px);
  font-size: 30px;
  font-family: "Microsoft YaHei UI","SmilySans",serif;
  font-weight: bold;
  color: #00517e;
}
.bing .demonstration{
  margin-bottom: 20px;
  margin-left: 50%;
  text-align: center;
  transform: translate(-300px);
  font-family: "Microsoft YaHei UI","SmilySans",serif;
  font-weight: bold;
  color: #000000;
}
.bing #bingtitle{
  margin-top: 20px;
  margin-left: 30px;
  font-size: 30px;
  font-family: "Microsoft YaHei UI","SmilySans",serif;
  font-weight: bold;
  color: #696b73;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
#bingtitle p{
  margin-top:0 ;
  margin-bottom: 0;
}
.user{
  border:2px solid #bbb4b4;
  border-radius: 10px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
#avatar{
  margin-left: 20px;
  margin-top:20px;
  margin-bottom: 20px;
  width: 200px;
  height: 200px;
  background-color: #266c6e;
  border-radius: 200px;
  text-align: center;
  line-height: 200px;
  color: white;
  font-family: "Berlin Sans FB","Microsoft YaHei UI",sans-serif;
  font-size: 40px;
}
#refresh{
  margin-top: 5px;
  margin-right: 20px;
}
#refresh :hover{
  color: black;
}


</style>