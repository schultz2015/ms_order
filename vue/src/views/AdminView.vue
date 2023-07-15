<template>
  <div class="bg">
    <form action="" method="post" class="login" id="loginPage" @submit.prevent="post">
      <p id="loginTitle">管理员登录</p>
      <input type="text"
             class="usn" id="USN" name="userName"
             placeholder="管理员账户" v-model="User.username">
      <input type="password"
             class="psw" id="PSW"
             name="passWord" placeholder="管理员密码"
             v-model="User.password">
      <input type="submit" class="btn" value="登录">

    </form>
    <div id="Image">
      <img src="../assets/imgs/loginbg.png" alt="">
    </div>
  </div>

</template>

<script>
import axios from 'axios'
import router from "@/router";
export default {
  name: "LoginRegister",
  data(){
    return{
      User: {
        username:"",
        password:""
      }
    }
  },
  // created() { 生命周期created阶段便启动
  //   this.post()
  // },
  methods:{
    post(){
      console.log(this.User.password)
      if(this.User.username==="admin"){
          if (this.User.password==='123456'){

            router.push({path:'/manage'})
          }else
          {
            alert("密码错误")
            router.push({path:'/Admin'})
          }
      } else{
        alert("账号错误");
        router.push({path:'/Admin'})
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