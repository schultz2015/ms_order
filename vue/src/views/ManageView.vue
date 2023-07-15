<template>
  <div id="adminbg">
    <div class="top">后台管理界面</div>
    <div class="side">
      <div class="section" id="userCount" @click="choose(1)">用户信息管理</div>
      <div class="section" id="userOrder" @click="choose(2)">订单管理</div>
      <div class="section" id="houseInfo" @click="choose(3)">民宿管理</div>
      <div class="section" id="Chart" @click="choose(4)">收入管理</div>
    </div>
    <div class="container">

      <div v-if="choice===0">
      </div>
      <div v-if="choice===1">
        <UserCount></UserCount>
      </div>
      <div v-if="choice===2">
        <UserOrder></UserOrder>
      </div>
      <div v-if="choice===3">
        <HouseCount></HouseCount>
      </div>
      <div v-if="choice===4">
        <Chart></Chart>
      </div>

    </div>
  </div>

</template>

<script>
import UserCount from "@/components/UserCount";
import UserOrder from "@/components/UserOrder";
import HouseCount from "@/components/HouseInfo";
import Chart from "@/components/Chart";

export default {
  name: "ManageView",
  components: { Chart, HouseCount, UserOrder, UserCount},
  data(){
    return{
      choice:0
    }
  },
  methods:{
    choose(reg){
      this.choice=reg
    }
  }
}
</script>

<style scoped>
#adminbg{
  height: 100vh;
  display: grid;
  grid-template-columns: 250px;
  grid-auto-rows: 100px minmax(100px, auto);
  background-color: #ffffff;

}
.top{
  padding-left: 50px;
  line-height: 100px;
  color: #ffffff;
  font-size: 35px;
  grid-column-start: 1;
  grid-column-end: 6;
  background-color: #005573;
}
.side{
  background-color: #6ca2b4;
  grid-column-start: 1;
  grid-column-end: 2;
  height: 100vh;
  /*height: 100vh;*/
  /*width: 300px;*/

}
.container{
  background-color: #ffffff;
  grid-column-start: 2;
  grid-column-end: 6;
  height: 100vh;
}
.section{
  text-align: center;
  line-height: 100px;
  color: #ffffff;
  font-size: 35px;
  width: 100%;
  height: 100px;
  border: 1px solid rgb(85, 191, 192);
  cursor: pointer;
}
.section:hover{
  background-color: #00517e;
}
</style>