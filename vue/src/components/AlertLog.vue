<template>
  <div class="log" v-if="show" style="cursor: pointer;">
    <div id="dismiss" @click="dismiss">X</div>
    <img class="introimg" v-bind:src="getAddress(msg.roomimg)" alt="{{ msg.roomimg }}">
    <div class="introinfo">
      <div id="name">{{ house.housename }}</div>
      <div id="address">{{ house.address }}</div>
      <div id="title">{{ msg.roomname }}</div>
      <div id="info">
        <div id="type" >{{ msg.type }}</div>
        <div id="price">{{ msg.price }}</div>
        <div id="order" @click="state()">预订</div>
      </div>
      <div id="picker" v-if="look">
        <DatePicker v-model="date" :msg="d" ></DatePicker>
        <div v-if="date!==' '&&  order() && date!==null">
          <div id="time">{{STIME}}至{{ETIME}}共{{N}}天</div>
          <div id="total">总价：{{TOTAL}}</div>
          <div id="confirm" @click="confirm()">确认订单</div>
        </div>
      </div>
<!--      <img class="introimg" v-bind:src="getAddress(msg.roomimg)" alt="{{ msg.roomimg }}">-->
    </div>



  </div>
</template>

<script>
import DatePicker from "@/components/DatePicker";
import axios, {keys} from "axios";
import Ojbect from "core-js/stable/dom-collections";
// import addDays from 'date-fns/addDays';
import VuePicker from '@vuepic/vue-datepicker';
// import '@vuepic/vue-datepicker/dist/main.css'
import {differenceInDays, parseISO} from "date-fns";
import Cookies from "js-cookie";
import {ElNotification} from "element-plus";
export default {
  name: "AlertLog",
  components: {DatePicker},
  props:['msg'],
  data(){
    return{
      User:{},
      show:true,
      look:false,
      d:[],
      house:{},
      date:' ',
      TOTAL:' ',
      STIME:' ',
      ETIME:' ',
      N:' ',
      S:[]
    }
  },created() {
    this.load()
    this.post()
  },
  // watch: {
  //   "$store.state.flag":{
  //     handler:function (newVal,oldVal) {
  //       alert("检测")
  //       alert(newVal)
  //       // this.$store.state.flag=!this.$store.state.flag
  //     }
  //   }
  // },
  methods:{
    confirm(){
      this.loading = true
      axios.post(`http://localhost:8080/confirmOrder`,{
        userid:this.User.userid,
        houseid:this.msg.houseid,
        roomid:this.msg.roomid,
        roomname:this.msg.roomname,
        price:this.TOTAL,
        tel:this.User.tel,
        truename:this.User.truename,
        start:this.STIME,
        end:this.ETIME,
        cancel:' '

      }).then((response) => {
            this.loading = false
            this.house=response.data
            console.log(response.data)
            this.dismiss()
            ElNotification({
              title: 'Success',
              message: '下单成功',
              type: 'success',
            })
          }
      )
    },
    state(){
      this.look=true
    },
    formateDate(datetime) {
      function addDateZero(num) {
        return (num < 10 ? '0' + num : num)
      }
      const d = new Date(datetime)
      const formatdatetime = d.getFullYear() + '-' + addDateZero(d.getMonth() + 1) + '-' + addDateZero(d.getDate())
      // + ' ' + addDateZero(d.getHours()) + ':' + addDateZero(d.getMinutes()) + ':' + addDateZero(d.getSeconds())
      return formatdatetime
    },
    order(){
      if(this.date===null){
        return false
      }
      this.STIME=this.formateDate(this.date[0])
      this.ETIME=this.formateDate(this.date[1])
      console.log(this.STIME)
      this.N = differenceInDays( parseISO(this.ETIME),parseISO( this.STIME)); // 相差的年份,其它类似
      this.TOTAL=this.N*this.msg.price
      console.log(this.N)
      return true
    },
    load(){
      this.loading = true
      axios.post(`http://localhost:8080/findHouseById`,{
        houseid:this.msg.houseid
      }).then((response) => {
            this.loading = false
            this.house=response.data
            console.log(response.data)
          }
      )
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
      axios.post(`http://localhost:8080/findOrderByRH`,{
        houseid:this.msg.houseid,roomid:this.msg.roomid
      }).then((response) => {
            this.loading = false
            this.S=response.data
            console.log(this.S)
            for (const Key in this.S) {
               let n = differenceInDays(parseISO(this.S[Key].end),parseISO(this.S[Key].start))+1
               let m = differenceInDays(parseISO(this.S[Key].start),parseISO(this.formateDate(new Date())))
             while(n--){
                this.d.push(m)
                 m++
             }
          }
            console.log(this.d)
      }
      )

    },
    getAddress(frontpage){
      return  "http://localhost:8080/"+frontpage
    },
    dismiss(){
      this.$emit('state',this.show)
      this.show=false
    }
  }
}
</script>

<style scoped>
  .log{
    position: fixed;
    margin-top: 20px;
    margin-left: 50%;
    z-index: 6;
    transform: translate(-500px);
    width:1000px;
    height: 690px;
    display: flex;
    flex-direction: row;
    background-color: rgb(255, 255, 255);
    border-radius: 20px;
    box-shadow: 0px 0px 500px 500px rgba(0,0,0,0.5);
  }
  #dismiss{
    height: 45px;
    padding-left: 10px;
    line-height: 45px;
    font-size: 20px;
    color: #000000;
  }
  #dismiss:hover{
    color: #212121;
  }
  .introimg{
    margin-left: 25px;
    margin-top: 100px;
    width: 500px;
    height: 350px;

  }
  .introinfo{
    background-color: #fcfafa;
    margin-top: 80px;
    margin-left: 20px;
    width: 420px;
    height: 600px;
  }
  #title{
    background-color: #ffffff;
    color: #000000;
    font-size: 50px;
    height: 90px;
    line-height: 90px;
    text-indent: 10px;
    border-top: 10px solid #266c6e;
    border-radius: 50px;
    margin-top: 15px;
  }
  #name{
    color: #000000;
    font-size: 60px;
    height: 60px;
    line-height: 60px;
    text-indent: 10px;
    margin-top: 10px;
  }
  #address{
    color: #000000;
    font-size: 30px;
    height: 50px;
    line-height: 50px;
    text-indent: 10px;
  }
  #info{
    color: #000000;
    display: flex;
    flex-direction: row;
  }
  #type {
    color: #000000;
    line-height: 100px;
    background-color: rgb(255, 255, 255);
    font-size: 30px;
    height: 80px;
    text-indent: 10px;
  }
  #price{
    text-align: center;
    margin-left: 20px;
    line-height: 100px;
    font-size: 30px;
    background-color: #ffffff;
    height: 80px;
  }
  #order{
    margin-top: 30px;
    color: white;
    text-align: center;
    line-height: 40px;
    padding-left: 10px;
    padding-right: 10px;
    margin-left: 20px;
    font-size: 20px;
    background-color: #de5d1e;
    height: 40px;
    border-radius: 10px;
  }
  #order:hover{
    background-color: #622f17;
  }
  #picker{
    margin-top: 30px;
    background-color: #ffffff;
  }
  #confirm{
    margin-left: 10px;
    margin-top: 15px;
    color: white;
    text-align: center;
    font-size: 20px;
    line-height: 35px;
    width: 100px;
    background-color: #de5d1e;
    height: 35px;
    border-radius: 10px;
  }
  #confirm:hover{
    background-color: #622f17;
  }
  #time,#total{
    margin-left: 10px;
    margin-top: 15px;
    font-size: 25px;
  }

</style>