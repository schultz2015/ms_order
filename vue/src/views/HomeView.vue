<template>
  <div class="home">
<!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <NavBar :status="username"></NavBar>
    <Container></Container>
    <Foot></Foot>
  </div>
</template>

<script>
// @ is an alias to /src
import Cookies from "js-cookie";
import HelloWorld from '@/components/HelloWorld.vue'
import NavBar from "@/components/NavBar";
import Container from "@/components/Container";
import Foot from "@/components/Foot";


export default {
  name: 'HomeView',
  components: {
    Foot,
    Container,
    NavBar,
    HelloWorld
  }
  ,data(){
    return{
      username:''
    }
  },
  created() {
    // 接收事件，当前实例vm接收自定义事件
    this.flag()
  },
  methods:{
    flag(){
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      if(username==null){
        this.username="登录"
      }else this.username=username
    },


  }
}
</script>
<style src="../assets/css/body.css">

</style>