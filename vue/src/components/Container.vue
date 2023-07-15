<template>
  <div class="cardhead"></div>
  <AlertLog v-if="show" @state="getState()" :msg="a"></AlertLog>
  <div class="container" id="whereAmI">
      <div class="item" v-for="d in pageItem" :key="d" @click=alertlog(d)>
        <div class="Introduce">
          <img v-bind:src="getAddress(d.roomimg)" alt="">
          <div class="mask">
            <div class="word">{{ d.roomname }}</div>
          </div>
        </div>
      </div>
  </div>
  <div id="pagination">
    <el-pagination
        :page-size="pageSize"
        :pager-count="5"
        layout="prev, pager, next"
        :total="data.length"
        v-model:current-page="current"
        @current-change="dataslice()"
    />
  </div>

</template>

<script>
import AlertLog from "@/components/AlertLog";
import axios from "axios";
import {ElMessage} from "element-plus";
export default {
  name: "Container",
  components: {AlertLog},
  data(){
    return{
      a:{},
      show:false,
      data:[],
      pageItem:[],
      pageSize:18,
      current:1,
      flag:''
    }
  },
  created() {
    this.load()
    console.log(this.flag)
  },
  watch: {
    "$store.state.word":{
      handler:function(newVal,oldVal){
        ElMessage({
          message: '查询成功',
          type: 'success',
        })
        console.log(newVal);
        this.data=JSON.parse(newVal)
        this.dataslice()
      }
    },
    "$store.state.flag":{
      handler:function (newVal,oldVal) {
        console.log(newVal,oldVal)
      }
    }
  },
  methods:{
    alertlog(D){
        this.show=true;
        this.a=D

    },
    dataslice(){
      // index=Math.ceil(this.data.length/this.pageSize)
      // document.getElementById("totalPage").value=index

      this.pageItem = this.data.slice((this.current - 1) * this.pageSize, (this.current  - 1) * this.pageSize + this.pageSize)
      console.log(this.pageItem)

    },
    // refresh(){
    //   console.log(JSON.parse(this.$store.state.word))
    //   this.data=JSON.parse(this.$store.state.word)
    // },
    getState(show){
      this.show=show;
    },
    getAddress(frontpage){
      return "http://localhost:8080/"+frontpage
      // return require("../assets/roomimg/"+frontpage)
    },
    load(){
      this.loading = true
      axios.get(`http://localhost:8080/room`).then((response) => {
        this.loading = false
        console.log("room:"+response.data)
        this.data = response.data
        this.dataslice()
      })
    }
  }
}

</script>

<style scoped>
  .container {
    margin-left: 50%;
    transform: translate(-600px);
    width: 1200PX;
    height: auto;
    z-index: 2;
    background-color: #ffffff;
    display: grid;
    justify-content: center;
    grid-template-columns: repeat(3, minmax(380px,380px));
    gap: 10px 10px;
    grid-auto-flow: row;
    grid-auto-rows: minmax(380px,380px);
  }
  .cardhead{
    height: 80px;
  }
  .item {
    background-color: burlywood;
  }
  #pagination{
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: center;
  }

  #whereAmI .Introduce{
    position: relative;
    overflow: hidden;
    width: 380px;
    height: 380px;
  }
  #whereAmI img{
    position: absolute;
    transition: all .5s;
    width: 380px;
    height: 380px;
  }
  #whereAmI .Introduce:hover img{
    transform: scale(1.05);
    transition: all .5s;
  }
  #whereAmI .word{
    margin-left: 10px;
    margin-top: 300px;
    color: rgb(256,256,256);
    font-size: 45px;
    font-weight: bold;
  }
  #whereAmI .mask{
    position: absolute;
    display:none;
    width: 380px;
    height: 380px;
    background: rgb(85, 85, 85,0);
    transition: all 0.5s;
  }
  #whereAmI .Introduce:hover .mask{
    display: block;
    background: rgb(85, 85, 85,0.4);
    transition: all 0.5s;
  }

</style>