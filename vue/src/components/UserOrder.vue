<template>
  <div class="title">
    <el-tabs class="demo-tabs" v-model="tabname" @tab-click="handleClick">
      <el-tab-pane label="订单一览" name="first" >
      </el-tab-pane>
      <el-tab-pane label="退订状态" name="second" >
      </el-tab-pane>
    </el-tabs>
  </div>
  <div class="users" v-if="show===true">
    <el-table :data="pageItem" style="width: 100%">
      <el-table-column prop="roomname" label="类型"  />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="tel" label="电话"  />
      <el-table-column prop="truename" label="姓名"  />
      <el-table-column prop="cancel" label="状态"  />
      <el-table-column prop="start" label="入住时间"  />
      <el-table-column prop="end" label="退房时间" />
      <el-table-column align="right">
        <template #header>
          <el-select v-model="select" class="m-2" placeholder="Select" size="small">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
          <el-input v-model="search" size="small" placeholder="搜索价格、订单编号、用户编号、类型" @change="searching(search,select)" />
        </template>
      </el-table-column>
    </el-table>
    <div id="pagination">
      <el-pagination
          :page-size="pageSize"
          :pager-count="5"
          layout="prev, pager, next"
          :total="orders.length"
          v-model:current-page="current"
          @current-change="dataslice(0)"
      />
    </div>
  </div>
  <div class="users" v-if="show===false">
    <el-table :data="pageItem" style="width: 100%">
      <el-table-column prop="roomname" label="类型"  />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="tel" label="电话"  />
      <el-table-column prop="truename" label="姓名"  />
      <el-table-column prop="cancel" label="状态"  />
      <el-table-column prop="start" label="入住时间"  />
      <el-table-column prop="end" label="退房时间" />
      <el-table-column align="right">
        <template #header>
          <el-select v-model="select" class="m-2" placeholder="Select" size="small">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
          <el-input v-model="search" size="small" placeholder="搜索价格、订单编号、用户编号、类型" @change="searching(search,select)" />
        </template>
          <template #default="scope">
            <el-button
                size="small"
                type="success"
                @click="del(scope.row.orderid)">
              允许
            </el-button>
            <el-button
                size="small"
                type="danger"
                @click="refuse(scope.row.orderid)">
              拒绝
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
</template>

<script>
import request from "@/utils/request";
import axios from 'axios'
import OrderMod from "@/components/OrderMod";
import {ElNotification} from "element-plus";
export default {
  name: "UserOrder",
  components: {OrderMod},
  data(){
    return{
      modorder:" ",
      show:true,
      select:"",
      search:" ",
      tabname:"first",
      orders:[],
      table1:[],
      table2:[],
      text:" ",
      options : [
        {
          value: '搜索价格',
          label: '搜索价格',
        },
        {
          value: '订单编号',
          label: '订单编号',
        },
        {
          value: '用户编号',
          label: '用户编号',
        },
        {
          value: '类型',
          label: '类型',
        }
      ],
      index:0,
      pageItem:[],
      pageSize:10,
      current:1,
    }
  },
  created() {
    this.load()
  },
  watch:{
    "$store.state.flag":{
      handler:function (newVal,oldVal) {

        this.tabname='first'
        this.show=true
        this.load()
      }
    }
  },

  methods:{
    dataslice(num){
      // index=Math.ceil(this.data.length/this.pageSize)
      // document.getElementById("totalPage").value=index
      if(num===1){
        this.pageItem = this.table1.slice((this.current - 1) * this.pageSize, (this.current  - 1) * this.pageSize + this.pageSize)
        console.log(this.pageItem)
      }else if (num===0){
        this.pageItem = this.orders.slice((this.current - 1) * this.pageSize, (this.current  - 1) * this.pageSize + this.pageSize)
        console.log(this.pageItem)
      }


    },
    handleClick(data){
      this.current=1
      this.orders=this.table2
      this.filterTable()
      this.index=parseInt(data.index)
      if(data.index==="0"){
        this.show=true
        this.dataslice(0)
      }
      else if(data.index==="1"){
        this.show=false
        this.dataslice(1)
      }

    },
    filterTable(){
      this.table1=[]
      for(let i=0;i<this.orders.length;i++){
        if (this.orders[i].cancel==='退订'){
          this.table1.push(this.orders[i])
        }
      }
      console.log(1,this.table1)
    },
    del(reg){
      this.loading = true
      axios.post(`http://localhost:8080/deleteOrder`,{
        orderid:reg
      }).then((response) => {
            this.loading = false
            console.log("deleteOrder:"+response)
        ElNotification({
          title: 'Success',
          message: '退订成功',
          type: 'success',
        })
        this.$store.commit('changeFlag');
        console.log(this.$store.state.flag)
          })
    },
    refuse(reg){
      this.loading = true
      axios.post(`http://localhost:8080/updateOrder`,{
        orderid:reg,
        cancel:' '
      }).then((response) => {
            this.loading = false
            console.log("updateOrder:"+response)
        ElNotification({
          title: 'Success',
          message: '已拒绝请求',
          type: 'success',
        })
        this.$store.commit('changeFlag');
        console.log(this.$store.state.flag)
          })
    },
    searching(sea,sel){
      alert(sea+sel)
      this.current=1
      if(sea==="" || sea.trim()===""){
        this.orders=this.table2
        this.dataslice(this.index)
        return
      }else
      if(sel==="搜索价格"){
        sea=parseInt(sea)
        this.loading = true
        axios.post(`http://localhost:8080/searchOrder`,{
          price:sea,roomid:-4
        }).then((response) => {
              this.loading = false
              console.log("searchOrder:"+response.data)
              this.orders=response.data
              this.dataslice(this.index)
            }
        )
      }else
      if(sel==='订单编号'){
        sea=parseInt(sea)
        this.loading = true
        axios.post(`http://localhost:8080/searchOrder`,{
          orderid:sea,roomid:-3
        }).then((response) => {
              this.loading = false
              this.orders=response.data
              console.log("searchOrder:"+response)
          this.filterTable()
            }
        )
      }else
      if(sel==='用户编号'){
        sea=parseInt(sea)
        this.loading = true
        axios.post(`http://localhost:8080/searchOrder`,{
          userid:sea,roomid:-2
        }).then((response) => {
              this.loading = false
              this.orders=response.data
              console.log("searchOrder:"+response)
          this.filterTable()
            }
        )
      }else
      if(sel==='类型'){
        alert(sel)
        this.loading = true
        axios.post(`http://localhost:8080/searchOrder`,{
          type:sea,roomid:-1
        }).then((response) => {
              this.loading = false
              this.orders=response.data
              console.log("searchOrder:"+response)
               this.filterTable()
            }
        )
      }


    },
    load(){
      this.loading = true
      axios.get(`http://localhost:8080/order`).then((response) => {
        this.loading = false
        console.log("order:"+response.data)
        this.orders = response.data
        this.table2=this.orders
        this.filterTable()
        this.dataslice(0)
      })
      // axios.post(`http://localhost:8080/index`).then((response) => {
      //   this.loading = false
      //   console.log(response.data)
      // })
    }
  }
}
</script>

<style scoped>
.users{
  margin-left: 50px;
  margin-right: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
  background-color: #ffffff;
}
.title{
  margin-left: 60px;
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

</style>
