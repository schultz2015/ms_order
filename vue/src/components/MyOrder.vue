<template>
  <div class="title">
    <el-tabs class="demo-tabs" v-model="index" @tab-click="handleClick">
      <el-tab-pane label="订单一览" name="first" >
      </el-tab-pane>
      <el-tab-pane label="退订状态" name="second" >
      </el-tab-pane>
    </el-tabs>
  </div>
  <div class="users" v-if="show===true">
    <el-table :data="pageItem" style="width: 100%" >
      <!--      <el-table-column prop="orderid" label="订单编号"  />-->
      <!--      <el-table-column prop="userid" label="用户编号"  />-->
      <!--      <el-table-column prop="houseid" label="民宿编号" />-->
      <!--      <el-table-column prop="roomid" label="房间编号"  />-->
      <el-table-column prop="roomname" label="类型"/>
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="tel" label="电话"  />
      <el-table-column prop="truename" label="姓名"  />
      <el-table-column prop="start" label="入住时间"  />
      <el-table-column prop="end" label="退房时间" />
      <el-table-column align="right">
      <template #default="scope">
        <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row.orderid,scope.row.cancel)">
          取消订单
        </el-button>
      </template>
      </el-table-column>
    </el-table>
    <div id="pagination">
      <el-pagination
          :page-size="pageSize"
          :pager-count="5"
          layout="prev, pager, next"
          :total="table1.length"
          v-model:current-page="current"
          @current-change="dataslice(1)"
      />
    </div>
  </div>
  <div class="users" v-if="show===false">
    <el-table :data="pageItem" style="width: 100% " >
<!--      <el-table-column prop="orderid" label="订单编号"  />-->
<!--      <el-table-column prop="userid" label="用户编号"  />-->
<!--      <el-table-column prop="houseid" label="民宿编号" />-->
<!--      <el-table-column prop="roomid" label="房间编号"  />-->
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="tel" label="电话"  />
      <el-table-column prop="roomname" label="类型"  />
      <el-table-column prop="truename" label="姓名"  />
      <el-table-column prop="start" label="入住时间"  />
      <el-table-column prop="end" label="退房时间" />
      <el-table-column align="right">
        <template #default="scope">
          <el-button
              size="small"
              type="success"
              v-if="scope.row.cancel==='退订'"
              @click="handleDelete(scope.row.orderid,scope.row.cancel)">
            等待通过
          </el-button>
          <el-button
              size="small"
              type="danger"
              v-if="scope.row.cancel!=='退订'"
              @click="handleDelete(scope.row.orderid,scope.row.cancel)">
            取消订单
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div id="pagination">
      <el-pagination
          :page-size="pageSize"
          :pager-count="5"
          layout="prev, pager, next"
          :total="table2.length"
          v-model:current-page="current"
          @current-change="dataslice(2)"
      />
    </div>
  </div>
</template>

<script>
import Cookies from "js-cookie";
import axios from 'axios'
import OrderMod from "@/components/OrderMod";
import {ElMessage, ElNotification} from "element-plus";

export default {
  name: "MyOrder",
  components: {OrderMod},
  data(){
    return{
      show:true,
      modorderid:" ",
      search:" ",
      orders:[],
      table1:[],
      table2:[],
      User:{
        userid:'',
        username:'',
        truename:'',
        password:'',
        tel:'',
      },
      index:'first',
      pageItem:[],
      pageSize:10,
      current:1,
    }
  },
  created() {
    this.post()
  },
  mounted() {

  },
  watch: {
      "$store.state.flag":{
        handler:function (newVal,oldVal) {
          console.log(newVal,oldVal)
          ElNotification({
            message: '成功',
            type: 'success',
          })
          this.post()
        }
      }
    },
  methods:{
    // searching(){
    //   console.log(this.search)
    // },
    dataslice(num){
      // index=Math.ceil(this.data.length/this.pageSize)
      // document.getElementById("totalPage").value=index
      if(num===1){
        this.pageItem = this.table1.slice((this.current - 1) * this.pageSize, (this.current  - 1) * this.pageSize + this.pageSize)
        console.log(this.pageItem)
      }else if (num===2){
        this.pageItem = this.table2.slice((this.current - 1) * this.pageSize, (this.current  - 1) * this.pageSize + this.pageSize)
        console.log(this.pageItem)
      }


    },
    filterTable(){
      this.table2=[]
      this.table1=[]
      for(let i=0;i<this.orders.length;i++){
        if (this.orders[i].cancel==='退订'){

          this.table2.push(this.orders[i])
        }else {
          this.table1.push(this.orders[i])
        }
      }
      console.log(this.table1)
    },
    handleClick(data){
      this.current=1
      if(data.index==="0"){
        this.show=true
        this.dataslice(1)
      }
      else if(data.index==="1"){
        this.show=false
        this.dataslice(2)
      }
    },
    post(){
      this.loading = true
      axios.post(`http://localhost:8080/MyInfo`,{
        username:Cookies.get('username')
      }).then((response) => {
            this.loading = false
            this.User=response.data
            console.log(response)
            this.load()
          }
      )
    },
    handleDelete(reg,rek){
      console.log(reg,rek)
      if(rek==="退订"){
        rek="取消退订"
      }else rek="退订"
      axios.post(`http://localhost:8080/updateOrder`,{
        orderid:reg,cancel:rek
      }).then((response) => {
            this.loading = false
            console.log(response)
            this.load()
          }
      )
      this.$store.commit('changeFlag');
      console.log(this.$store.state.flag)
    },
    load(){
      axios.post(`http://localhost:8080/MyOrder`,{
        userid:this.User.userid
      }).then((response) => {
            this.loading = false
            this.orders=response.data
            console.log(response)
            this.filterTable();
            this.dataslice(1)
            this.index='first'
          }
      )
    }
  }
}
</script>

<style scoped>
.title{
  margin-left: 50px;
  margin-top: 20px;
  margin-bottom: 0;
  display: flex;
  flex-direction: row;
}

.users{
  margin-left: 20px;
  margin-right: 20px;
}
#pagination{
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

</style>