<template>
  <div class="title">
  </div>
  <div id="users">
    <el-form :model="User" label-width="120px" @change="change()">
      <el-form-item label="用户名">
        <el-input v-model=" User.username" />
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="User.truename" />
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="User.tel" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit()">修改</el-button>
        <el-button @click="post">取消</el-button>
      </el-form-item>
    </el-form>

    <div id="separate"></div>

    <el-form label-width="120px" @change="change()">
      <el-form-item label="新密码">
        <el-input v-model="newpassword" />
      </el-form-item>
      <el-form-item label="确认新密码">
        <el-input v-model="confirmpassword" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="safe()">修改</el-button>
        <el-button @click="post">取消</el-button>
      </el-form-item>
    </el-form>


  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";
import axios from 'axios'
import router from "@/router";
import UserMod from "@/components/UserMod";
import {ElMessage, ElNotification} from "element-plus";
export default {
  name: "MyInfo",
  components: {UserMod},
  data(){
    return{
      User:{
        userid:'',
        username:'',
        truename:'',
        password:'',
        tel:'',
      },
      newpassword:'',
      confirmpassword:''
    }
  },
  created() {
    this.post()
  },
  methods:{
    post(){
      this.loading = true
      axios.post(`http://localhost:8080/MyInfo`,{
        username:Cookies.get('username')
      }).then((response) => {
            this.loading = false
            this.User=response.data
            console.log(response)

          }
      )
    },
    safe(){
      if (this.newpassword!==this.confirmpassword){
        ElMessage({
          message: '两次输入的密码不相同',
          type: 'warning',
        })
      }else{
        this.loading = true
        axios.post(`http://localhost:8080/submitpassword`,{
          userid:this.User.userid,
          password:this.newpassword
        }).then((response) => {
              this.loading = false
              // this.houses=response.data
              console.log(response.data)
          ElNotification({
                message: '密码修改成功',
                type: 'success',
              })
            }
        )
      }

    },
    submit(){
      this.loading = true
      axios.post(`http://localhost:8080/modify`,{
        userid:this.User.userid,
        username:this.User.username,
        truename:this.User.truename,
        tel:this.User.tel,
      }).then((response) => {
            this.loading = false
            // this.houses=response.data
            console.log(response.data)
            ElNotification({
              message: '信息修改成功',
              type: 'success',
            })
          }
      )
    },
    change(){
      console.log(this.User)
    }
  }
}
</script>

<style scoped>
.title{
  height: 20px;
}
#users{
  display: flex;
  flex-direction: row;
  margin-left: 50px;

}

.modify{
  text-align: center;
  line-height: 30px;
  margin-left: 20px;
  background-color: #de5d1e;
  color: white;
  width: 80px;
  height: 30px;
  cursor: pointer;
}
.modify:hover{
  background-color: #64290c;
}
#separate{
  width: 300px;
  height: 100%;
}
</style>