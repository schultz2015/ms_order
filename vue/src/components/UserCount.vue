<template>
  <div class="title">
<!--    <input v-model="search" type="text" placeholder="搜索编号或用户名">-->
<!--    <button type="submit" @click="searching(search)">-->
<!--      <svg t="1685948054653" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2543" width="20" height="20"><path d="M887.08224 853.76512 698.17216 664.30976c50.77632-58.73536 78.47936-132.89984 78.47936-211.32416 0-86.46016-33.66912-167.744-94.80448-228.88064s-142.42304-94.80576-228.88192-94.80576c-86.46016 0-167.744 33.66912-228.88064 94.80576-61.13664 61.13664-94.80576 142.42176-94.80576 228.88064 0 86.45888 33.66912 167.74528 94.80576 228.88192 61.13664 61.13536 142.42176 94.80448 228.88064 94.80448 36.68352 0 72.68736-6.09408 107.01056-18.11328 12.50688-4.37888 19.09376-18.06848 14.71488-30.57536-4.38016-12.5056-18.06464-19.09376-30.57664-14.71488-29.21216 10.22976-59.87968 15.41632-91.1488 15.41632-152.02048 0-275.6992-123.67744-275.6992-275.6992 0-152.02176 123.67872-275.6992 275.6992-275.6992 152.02176 0 275.6992 123.67872 275.6992 275.6992 0 73.53856-28.60032 142.67776-80.53376 194.72512-0.06784 0.064-0.13952 0.11904-0.20736 0.18688-9.3824 9.3568-9.40544 24.54784-0.04736 33.9328l205.22752 205.82016c4.68736 4.70016 10.83776 7.05152 16.98944 7.05152 6.12864 0 12.25856-2.33344 16.94208-7.00416C896.41728 878.34112 896.44032 863.15008 887.08224 853.76512z" p-id="2544"></path></svg>-->
<!--    </button>-->
  </div>
    <div id="users">
      <el-table :data="pageItem" style="width: 100%">
        <el-table-column label="用户编号" prop="userid" />
        <el-table-column label="用户名" prop="username" />
        <el-table-column label="密码" prop="password" />
        <el-table-column label="姓名" prop="truename" />
        <el-table-column label="电话" prop="tel" />
        <el-table-column align="right">
          <template #header>
            <el-input v-model="search" size="small" placeholder="搜索编号或用户名" @change="searching(search)"/>
          </template>
          <template #default="scope">
<!--            <el-button size="small" @click="handleEdit(scope.$index, scope.row.userid)"-->
<!--            >Edit</el-button-->
<!--            >-->
            <el-popconfirm title="确定删除？" @confirm="del(scope.row.userid)">
              <template #reference>
                <el-button
                    size="small"
                    type="danger"
                   >
                  删除
                </el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
<!--        <table class="userTable">-->
<!--          <tr>-->
<!--            <th>用户编号</th>-->
<!--            <th>用户名</th>-->
<!--            <th>密码</th>-->
<!--            <th>姓名</th>-->
<!--            <th>电话</th>-->
<!--          </tr>-->
<!--          <tr v-for="user in users" :key="user">-->
<!--            <td class="userid"> {{ user.userid }}</td>-->
<!--            <td class="username"> {{ user.username }}</td>-->
<!--            <td class="password"> {{ user.password }}</td>-->
<!--            <td class="truename"> {{ user.truename }}</td>-->
<!--            <td class="tel"> {{ user.tel }}</td>-->
<!--            <td class="delete" @click="del(user.userid)">删除</td>-->
<!--          </tr>-->
<!--        </table>-->

    </div>
  <div id="pagination">
    <el-pagination
        :page-size="pageSize"
        :pager-count="5"
        layout="prev, pager, next"
        :total="users.length"
        v-model:current-page="current"
        @current-change="dataslice()"
    />
  </div>
</template>

<script>
import request from "@/utils/request";
import axios from 'axios'
import Cookies from "js-cookie";
import UserMod from "@/components/UserMod";
import {ElNotification} from "element-plus";
export default {
  name: "UserCount",
  components: {UserMod},
  data(){
    return{
      search:" ",
      users:[],
      user:{},
      pageItem:[],
      pageSize:20,
      current:1,
    }
  },
  created() {
    this.load()
  },
  watch:{
     search:{
      handle:function (newVal,oldVal){
        console.log(newVal)
      }
    }
  },
  methods:{
    dataslice(){
      // index=Math.ceil(this.data.length/this.pageSize)
      // document.getElementById("totalPage").value=index

      this.pageItem = this.users.slice((this.current - 1) * this.pageSize, (this.current  - 1) * this.pageSize + this.pageSize)
      console.log(this.pageItem)

    },
    searching(reg){
      this.current=1
      var pattern3 = new RegExp("[0-9]+");
      if(reg==='' || reg===null || reg===undefined|| reg===" "){
        this.load()
      }else {
        if(!pattern3.test(reg)){
          this.loading = true
          axios.post(`http://localhost:8080/MyInfo`,{
            username:reg.trim()
          }).then((response) => {
                this.loading = false
                this.user = response.data
                this.users.length=0
                this.users.push(this.user)
                this.dataslice()
                console.log("MyInfo:"+response)
              }
          )
        }else {
          reg=parseInt(reg)
          this.loading = true
          axios.post(`http://localhost:8080/searchUser`,{
            userid:reg
          }).then((response) => {

                this.loading = false
                this.user = response.data
                this.users.length=0
                this.users.push(this.user)
                console.log("searchUser:"+response)
              }
          )
        }
      }
    },
    del(reg){
      this.loading = true
      axios.post(`http://localhost:8080/deleteUser`,{
        userid:reg
      }).then((response) => {
            this.loading = false
            console.log(":deleteUser"+response)
            ElNotification({
              title: 'Success',
              message: '删除成功',
              type: 'success',
            })
            this.load()

          }
      )
    },
    load(){
      this.loading = true
      axios.get(`http://localhost:8080/user`).then((response) => {
        this.loading = false
        console.log("user:"+response.data)
        this.users = response.data
        this.dataslice()
      })
    }
  }
}
</script>

<style scoped>
/*input{*/
/*  text-indent: 2em;*/
/*  margin-top: 20px;*/
/*  width: 300px;*/
/*  height: 25px;*/
/*  margin-left: 870px;*/
/*}*/
/*button{*/
/*  margin-top: 20px;*/
/*  height: 30px;*/
/*  background-color: #ffffff;*/
/*}*/
#users{
  margin-left: 50px;
  margin-right: 50px;
  display: flex;
  justify-content: center;
  align-content: center;
  background-color: #ffffff;
}
.title{
  display: flex;
  background-color: #ffffff;
  height: 60px;
}
#pagination{
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
/*.userTable{*/
/*  text-align: center;*/
/*  background-color: #ffffff;*/
/*  border-left: #000000;*/
/*  width: 1000px;*/
/*  border-collapse: collapse;*/
/*}*/
/*td{*/
/*  width: 100px;*/
/*  border: 1px solid #000000;*/

/*}*/
/*th{*/
/*  color: white;*/
/*  background-color: #005573;*/
/*}*/
/*span{*/
/*  border: 1px solid rgb(152, 118, 118);*/

/*}*/
/*  .delete{*/
/*    width: 50px;*/
/*    color: white;*/
/*    background-color: #a10109;*/
/*    border: white;*/
/*    cursor: pointer;*/
/*  }*/
/*.delete:hover{*/
/*  background-color: #000000;*/
/*}*/
</style>