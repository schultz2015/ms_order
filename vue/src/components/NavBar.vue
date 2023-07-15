<template>
  <div class="webhead">
      <router-link to="/">
        <div class="icon"><img src="../assets/imgs/icon_shu_x.png" alt=""> </div>
        <div class="title">民宿</div>
      </router-link>
    <div class="bar">
        <input type="text" placeholder="" v-model="text">
        <button type="submit" @click="search(text)" style="cursor: pointer;">
<!--          <svg t="1685948054653" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2543" width="20" height="20"><path d="M887.08224 853.76512 698.17216 664.30976c50.77632-58.73536 78.47936-132.89984 78.47936-211.32416 0-86.46016-33.66912-167.744-94.80448-228.88064s-142.42304-94.80576-228.88192-94.80576c-86.46016 0-167.744 33.66912-228.88064 94.80576-61.13664 61.13664-94.80576 142.42176-94.80576 228.88064 0 86.45888 33.66912 167.74528 94.80576 228.88192 61.13664 61.13536 142.42176 94.80448 228.88064 94.80448 36.68352 0 72.68736-6.09408 107.01056-18.11328 12.50688-4.37888 19.09376-18.06848 14.71488-30.57536-4.38016-12.5056-18.06464-19.09376-30.57664-14.71488-29.21216 10.22976-59.87968 15.41632-91.1488 15.41632-152.02048 0-275.6992-123.67744-275.6992-275.6992 0-152.02176 123.67872-275.6992 275.6992-275.6992 152.02176 0 275.6992 123.67872 275.6992 275.6992 0 73.53856-28.60032 142.67776-80.53376 194.72512-0.06784 0.064-0.13952 0.11904-0.20736 0.18688-9.3824 9.3568-9.40544 24.54784-0.04736 33.9328l205.22752 205.82016c4.68736 4.70016 10.83776 7.05152 16.98944 7.05152 6.12864 0 12.25856-2.33344 16.94208-7.00416C896.41728 878.34112 896.44032 863.15008 887.08224 853.76512z" p-id="2544"></path></svg>-->
<!--        -->
          <img src="../assets/imgs/search.png">
        </button>
    </div>
      <div class="login" @click="rout()">
        {{ state }}
      </div>
  </div>
</template>

<script>
import router from "@/router";
import axios from "axios";
import {createRouter as $router} from "vue-router";

export default {
  name: "NavBar",
  props:['status'],
  data(){
    return{
      text:" ",
      state:this.status,
      loading:"",
      con:[]
    }
  },methods: {
    search(rek){
      let reg=rek.trim()
      if(reg.length === 0 || reg===''){
        this.loading = true
        axios.get(`http://localhost:8080/room`).then((response) => {
          this.loading = false
          this.con = response.data
          this.send(this.con)
        })
      }else{
        console.log(reg)
        this.loading = true
      axios.post(`http://localhost:8080/searchIndex`,{
        stringindex:reg
      }).then((response) => {
            this.loading = false
            // console.log(response)
            // console.log(response.data)
          this.con=response.data
          this.send(this.con)
          }
      )
      }
    },
    rout(){
      console.log(this.status)
      if(this.status==="登录"){
        router.push({path:'/login'})
      }
      else{
        router.push({path:'/infor'})
      }
    },
    send(reg){
      if (reg.length===0){
        this.search(" ")
        alert('无相关数据')
      }else {
        this.$store.commit( 'changeWord' ,JSON.stringify(reg));
      }



    }
  }
}
</script>

<style scoped>
  .webhead{
    height: 60px;
    width: 100%;
    display: flex;
    justify-content: space-between;
    border: 1px solid rgb(255, 255, 255);
    background-color: rgb(255, 255, 255);
    position: fixed;
    box-shadow:1px 3px rgb(158, 156, 156);
    z-index: 5;
  }
  .webhead a{
    color: rgb(7, 7, 7);
    line-height: 60px;
    text-align: center;
    text-decoration: none;
    font: 22px “Arial”,“Microsoft YaHei””SmilySans“,sans-serif;
  }
  .title{
    display: inline-block;
    line-height: 60px;
    height: 60px;
    width: 60px;

  }
  .login{
    color: white;
    font-size: 25px;
    line-height: 60px;
    text-align: center;
    display: inline-block;
    height: 60px;
    width: 100px;
    background-color: #55bfc0;
    border-radius: 50px;
  }
  .login:hover{
    cursor: pointer;
  }

  .icon{
    margin-left: 3px;
    vertical-align: middle;
    display: inline-block;
    height: 60px;
    width: 60px;
  }

  .bar img{
    height: 35px;
    width: 35px;
  }
  .bar input {
    margin-top: 12px;
    text-indent: 2em;
    vertical-align: center;
    width: 250px;
    height: 35px;
    border-radius: 42px;
    border: 2px solid #324B4E;
    background: #ffffff;
    transition: .3s linear;
    float: right;
  }
  .bar input:focus {
    width: 300px;
  }
  .bar button {
    margin-top: 15px;
    height: 40px;
    border: 2px solid #ffffff;
    background: none;
    top: -2px;
    right: 0;
  }


</style>