<template>
  <div class="bg">
    <form action="" method="post" class="login" id="loginPage" @submit.prevent="post">
      <p id="loginTitle">登录</p>
      <input type="text"
             class="usn" id="USN" name="userName"
             placeholder="用户名" v-model="User.username">
      <input type="password"
             class="psw" id="PSW"
             name="passWord" placeholder="密码"
             v-model="User.password">
      <input type="submit" class="btn" value="登录">

      <p id="regPageSwitchButton"
         @mouseover='onMouseOver()' @click="switchPage('reg')">
        点击注册</p>
    </form>
    <form action="" method="post" class="register" id="registerPage" @submit.prevent="register">
      <p id="registerTitle">注册</p>
      <input type="text"
             class="usn"  name="ruserName"
             placeholder="用户名" v-model="User.username">
      <input type="text"
             class="trn"  name="trueName"
             placeholder="姓名" v-model="User.truename">
      <input type="text"
             class="tel"  name="usertel"
             placeholder="电话" v-model="User.tel">
      <input type="password"
             id="MYpw" name="rpassWord" class="psw"
             placeholder="密码" v-model="User.password">
      <input type="password"
             id="MYrepw" name="rpassWordConfirm" class="pswagain"
             placeholder="确认密码" v-model="User.apassword">
      <input type="submit" class="btn" value="注册">
      <p id="logPageSwitchButton" @mouseover='onMouseOver()' @click="switchPage('log')">点此登录</p>
    </form>
    <div id="Image">
      <img src="../assets/imgs/loginbg.png" alt="">
    </div>
  </div>

</template>

<script>
import axios from 'axios'
import router from "@/router";
import store from "../store/index.js"
import Cookies from "js-cookie";
import {ElMessage, ElNotification} from "element-plus";

export default {
  name: "LoginRegister",
  data(){
    return{
      User: {
        username:"",
        password:"",
        apassword:"",
        tel:"",
        truename:""
      }
    }
  },
  created() { //生命周期created阶段便启动
    this.getCookie()
  },
  methods:{
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      console.log(username)
      if(username===null || username===undefined){
        console.log("无cookie")
      }else{
        this.User.username=username
        this.User.password=password
        console.log("cookie"+Cookies.get("username"))
        this.post()
      }
       // this.User.username =
       //     username === undefined ? this.User.username : username;
       // this.User.userpassword =
       //     userpassword === undefined ? this.User.userpassword : password;
    },
    serCookie(){
      Cookies.set('username', this.User.username, { expires: 30 });
      console.log("username:"+Cookies.get('username'))
      Cookies.set('password', this.User.password, { expires: 30 });
      console.log("username:"+Cookies.get('password'))
    },
    post(){
      this.loading = true
      axios.post(`http://localhost:8080/LoginIn`,{
        username:this.User.username,
        password:this.User.password,
      }).then((response) => {
        this.loading = false
        console.log(response)
        if(response.data.msg.match("不存在")){
          ElMessage({
            message: '用户不存在',
            type: 'warning',
          })
        }
        else if(response.data.code === "2"){
          ElMessage({
            message: '密码错误',
            type: 'warning',
          })
        }
        else if(response.data.code === "200" && response.data.msg.match("成功")){
          console.log(response.data)
          this.serCookie()
          ElNotification({
            message: '登录成功',
            type: 'success',
          })
          router.push({path:'/'})
          }

        }
      )
    },
    register(){
      if(this.User.username.trim().length === 0 || this.User.username.trim() ===''){
        ElMessage({
          message: '错误：用户名输入空',
          type: 'warning',
        })
      }
      else if(this.User.tel.trim().length === 0 || this.User.tel.trim() ===''){
        ElMessage({
          message: '错误：电话输入空',
          type: 'warning',
        })
      }
      else if(this.User.truename.trim().length === 0 || this.User.truename.trim() ===''){
        ElMessage({
          message: '错误：姓名输入空',
          type: 'warning',
        })
      }
      else if(!this.User.apassword.match(this.User.password)){
        ElMessage({
          message: '错误：两次密码输入不一致',
          type: 'warning',
        })
      }else
      {
        this.loading = true
        axios.post(`http://localhost:8080/register`,{
          username:this.User.username,
          password:this.User.password,
          truename:this.User.truename,
          tel:this.User.tel,
        }).then((response) => {
              this.loading = false
              console.log(response)
              if(response.data.code === "200" && response.data.msg.match("成功")){
                console.log(response.data)
                ElNotification({
                  message: '注册成功',
                  type: 'success',
                })
                this.serCookie()
                router.push({path:'/'})
              }
              else {
                ElMessage({
                  message: '错误：用户名存在',
                  type: 'warning',
                })
              }

            }
        )
      }
    },


    onMouseOver() {
      let log = document.getElementById("logPageSwitchButton");
      let reg= document.getElementById("regPageSwitchButton");
      log.style.cursor="pointer";
      reg.style.cursor="pointer";
    },
    switchPage(reg) {
      let login = document.getElementById("loginPage");
      let register = document.getElementById("registerPage");
      if(reg === 'log'){
        login.style.display = 'block';
        register.style.display = 'none';
      }else if(reg === 'reg'){
        login.style.display = 'none';
        register.style.display = 'block';
      }
    }

  }
}
</script>

<style src="../assets/css/loginAndRegister.css" scoped></style>

<style scoped>
.bg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow-y: auto;
  background-image: url("../assets/imgs/loginview.png") !important;
  background-size: cover;
  background-attachment: fixed;
}
</style>
