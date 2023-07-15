<template>
  <div class="title">
<!--    <span class="add" @click="houseadd()">添加民宿</span>-->
<!--    <select  id="sid" v-model="select">-->
<!--      <option>编号</option>-->
<!--      <option>名称</option>-->
<!--    </select>-->
<!--      <input v-model="text" type="text" placeholder="请输入您要搜索的内容...">-->
<!--      <button type="submit"  @click="searching(text,select)">-->
<!--        <svg t="1685948054653" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2543" width="20" height="20"><path d="M887.08224 853.76512 698.17216 664.30976c50.77632-58.73536 78.47936-132.89984 78.47936-211.32416 0-86.46016-33.66912-167.744-94.80448-228.88064s-142.42304-94.80576-228.88192-94.80576c-86.46016 0-167.744 33.66912-228.88064 94.80576-61.13664 61.13664-94.80576 142.42176-94.80576 228.88064 0 86.45888 33.66912 167.74528 94.80576 228.88192 61.13664 61.13536 142.42176 94.80448 228.88064 94.80448 36.68352 0 72.68736-6.09408 107.01056-18.11328 12.50688-4.37888 19.09376-18.06848 14.71488-30.57536-4.38016-12.5056-18.06464-19.09376-30.57664-14.71488-29.21216 10.22976-59.87968 15.41632-91.1488 15.41632-152.02048 0-275.6992-123.67744-275.6992-275.6992 0-152.02176 123.67872-275.6992 275.6992-275.6992 152.02176 0 275.6992 123.67872 275.6992 275.6992 0 73.53856-28.60032 142.67776-80.53376 194.72512-0.06784 0.064-0.13952 0.11904-0.20736 0.18688-9.3824 9.3568-9.40544 24.54784-0.04736 33.9328l205.22752 205.82016c4.68736 4.70016 10.83776 7.05152 16.98944 7.05152 6.12864 0 12.25856-2.33344 16.94208-7.00416C896.41728 878.34112 896.44032 863.15008 887.08224 853.76512z" p-id="2544"></path></svg>-->
<!--      </button>-->
    <HouseAdd v-if="hadd" style="z-index: 10"  @state="gethAdd()" ></HouseAdd>
    <HouseMod v-if="hmod" style="z-index: 10"  @state="gethMod()" :msg="modhouses"></HouseMod>
    <RoomAdd v-if="radd"  style="z-index: 10"  @state="getrAdd()" :msg="modhouses"></RoomAdd>
    <RoomMod v-if="rmod"  style="z-index: 10"  @state="getrMod()" :msg="{...m}"></RoomMod>
  </div>
  <div id="users" >
      <el-table :data="pageItem" style="width: 100%">
        <el-table-column type="expand" >
          <template #default="props2">
            <el-table v-if="pageshow"  class="table-in-table" :data="non(props2)" style="width: 80%; margin-left: 50px" row-key="id" border>
                  <el-table-column prop="houseid"    label="民宿编号"  />
                  <el-table-column prop="roomid"   label="房间编号" />
                  <el-table-column prop="price"   label="价格"  />
                  <el-table-column prop="type"   label="类型"  />
                  <el-table-column prop="roomname"   label="房间名"  />
                  <el-table-column prop="roomimg"    label="预览图"  />
              <el-table-column align="right">
                <template #default="scope">
                  <el-button
                      size="small"
                      type="warning"
                      @click="roommod(scope.row.roomid,scope.row.houseid)">
                    修改
                  </el-button>
                  <el-popconfirm title="确定删除？" @confirm="roomdel(scope.row.roomid,scope.row.houseid)">
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
            <div id="pagination" >
              <el-pagination
                  small
                  :page-size="innerSize"
                  :pager-count="5"
                  layout="prev, pager, next"
                  :total="rooms.filter((v) => v.houseid===props2.row.houseid).length"
                  v-model:current-page="innerCurrent"
                  @current-change="dataslice(1,props2)"
              />
            </div>
          </template>
        </el-table-column>



        <el-table-column prop="houseid"   label="民宿编号"  />
        <el-table-column prop="address"   label="地址" />
        <el-table-column prop="housename" label="名称"  />
        <el-table-column align="right">
          <template #header>
            <el-button
                size="small"
                type="success"
                @click="houseadd()">
              新增
            </el-button>
            <el-select v-model="select" class="m-2" placeholder="Select" size="small">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
            <el-input v-model="text" size="small" placeholder="搜索价格、订单编号、用户编号、类型" @change="searching(text,select)"  />

          </template>
          <template #default="scope">
            <el-button
                size="small"
                type="success"
                @click="roomadd(scope.row.houseid)">
              新增
            </el-button>
            <el-button
                size="small"
                type="warning"
                @click="housemod(scope.row.houseid)">
              修改
            </el-button>
            <el-popconfirm title="确定删除？" @confirm="housedel(scope.row.houseid)">
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
  </div>
  <div id="pagination">
    <el-pagination
        :page-size="pageSize"
        :pager-count="5"
        layout="prev, pager, next"
        :total="houses.length"
        v-model:current-page="current"
        @current-change="dataslice(0)"
    />
  </div>
</template>

<script>
import request from "@/utils/request";
import axios from 'axios'
import HouseAdd from "@/components/HouseAdd";
import HouseMod from "@/components/HouseMod";
import RoomMod from "@/components/RoomMod";
import RoomAdd from "@/components/RoomAdd";
import {ElMessage} from "element-plus";
export default {
  name: "HouseInfo",
  components: {RoomAdd, RoomMod, HouseMod, HouseAdd},
  data(){
    return{
      roomfilter:[],
      hmod:false,
      hadd:false,
      rmod:false,
      radd:false,
      select:' ',
      houses:[],
      rooms:[],
      text:" ",
      modhouses:'',
      modrooms:'',
      m:{
        mr:'',
        mh:''
      },
      options : [
        {
          value: '编号',
          label: '编号',
        },
        {
          value: '名称',
          label: '名称',
        }
      ],
      pageItem:[],
      innerItem:[],
      pageSize:5,
      innerSize:5,
      current:1,
      innerCurrent:1,
      pageshow:true
    }
  },
  created() {
    this.load()

  },
  watch:{
    "$store.state.flag":{
      handler:function (newVal,oldVal) {
        this.load()
        this.pageshow=false
        this.pageshow=true
      },
    // },
    // roomfilter:{
    //   handler:function (newVal,oldVal){
    //     console.log(100,newVal)
    //   }
     }
  },
  methods:{
    non(props2){

      if (props2.$index!==-1 && this.pageshow){
        this.dataslice(1,props2)
      }
      return this.innerItem
      // this.innerCurrent+=1
      // if (this.innerCurrent===3) this.innerCurrent=1
      // this.$nextTick(() => {//重新渲染分页
      //   this.pageshow = true;
      // });
    },
    dataslice(num,props2){
      // index=Math.ceil(this.data.length/this.pageSize)
      // document.getElementById("totalPage").value=index
      if(num===0){
        this.pageItem = this.houses.slice((this.current - 1) * this.pageSize, (this.current  - 1) * this.pageSize + this.pageSize)
      }else if(num===1){
        this.roomfilter=this.rooms.filter((v) => v.houseid===props2.row.houseid)
        this.innerItem = this.roomfilter.slice((this.innerCurrent - 1) * this.innerSize, (this.innerCurrent  - 1) * this.innerSize + this.innerSize)
      }
    },
    gethAdd(show){
      this.hadd=show;
    },
    gethMod(show){
      this.hmod=show;
    },
    getrAdd(show){
      this.radd=show;
    },
    getrMod(show){
      this.rmod=show;
    },
    houseadd(){
      this.hadd=true;
    },
    housemod(reg){
      this.hmod=true;
      this.modhouses=reg
    },
    housedel(reg){
      this.loading = true
      axios.post(`http://localhost:8080/deleteHouse`,{
        houseid:reg
      }).then((response) => {
            this.loading = false
            console.log("deleteHouse:"+response)
            this.load()
            ElMessage({
              message: '已删除',
              type: 'success',
            })
          }
      )
    },
    roomadd(reg){
      this.radd=true;
      this.modhouses=reg
    },
    roommod(reg,rek){
      this.rmod=true;
      this.modrooms=reg
      this.modhouses=rek
      this.m.mh=rek
      this.m.mr=reg
    },
    roomdel(reg,rek){
      this.loading = true
      axios.post(`http://localhost:8080/deleteRoom`,{
        roomid:reg,houseid:rek
      }).then((response) => {
            this.loading = false
            console.log("deleteRoom:"+response)
            this.load()
            ElMessage({
              message: '已删除',
              type: 'success',
            })
          }
      )
    },
    searching(sea,sel){
      if(sea==="" || sea.trim()===""){
        this.load()
        return
      }
      if(sel==="编号"&&sea!==""){
        sea=parseInt(sea)
        this.loading = true
        axios.post(`http://localhost:8080/searchHouse`,{
          houseid:sea,address:-1
        }).then((response) => {
              this.loading = false
              this.houses=response.data
              console.log(response.data)
          ElMessage({
            message: '查询结果',
            type: 'success',
          })
            }
        )
      }
      if(sel==='名称'&&sea!==""){
        this.loading = true
        axios.post(`http://localhost:8080/searchHouse`,{
          housename:sea.trim(),address:-2
        }).then((response) => {
              this.loading = false
              this.houses=response.data
              console.log("searchHouse:"+response)
          ElMessage({
            message: '查询结果',
            type: 'success',
          })
            }
        )
      }
    },
    load(){
      this.loading = true
      axios.get(`http://localhost:8080/house`).then((response) => {
        this.loading = false
        console.log("house:"+response)
        this.houses = response.data
        this.dataslice(0)
      })

      axios.get(`http://localhost:8080/room`).then((response) => {
        this.loading = false
        console.log("room:",response.data)
        this.rooms = response.data
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

#users{
  margin-left: 50px;
  margin-right: 50px;
  display: flex;
  justify-content: center;
  align-content: center;
  background-color: #ffffff;
}
.title{
  margin-left: 50px;
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