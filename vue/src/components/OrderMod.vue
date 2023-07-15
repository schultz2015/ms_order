<template>
  <div class="log" v-if="show">
    <div id="dismiss" @click="dismiss">X</div>
    <div class="introinfo" v-for="r in orders" :key="r">
      <tr>
        <th>订单编号:</th>
        <td>{{ modorder }}</td><td style="width: 20px"></td>
      </tr>

      <tr>
        <th>电话:</th>
        <td>{{ r.tel }}</td><td></td>
        <input v-model="modtel" type="text" placeholder="">
        <button type="submit"  @click="upTel(modtel)">修改</button>
      </tr>
      <tr>
        <th>姓名:</th>
        <td>{{ r.truename }}</td><td></td>
        <input v-model="modname" type="text" placeholder="">
        <button type="submit"  @click="upTrueName(modname)">修改</button>
      </tr>
    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "OrderMod",
  props:['msg'],
  data(){
    return{
      modorder:' ',
      modtel:' ',
      modname:' ',
      show:true,
      orders:[],
    }
  },
  created() {
    this.load()
    this.modorder=this.msg
  },
  methods:{
    dismiss(){
      this.$emit('state',this.show)
      this.show=false
      this.$store.state.flag=!this.$store.state.flag
    },
    load(){
      this.loading = true
      axios.post(`http://localhost:8080/searchOrder`,{
        orderid:this.msg,roomid:-3
      }).then((response) => {
            this.loading = false
            this.orders=response.data
            this.modtel=response.data.tel
            this.modname=response.data.truename
            console.log(response)
          }
      )
    },
    upTel(reg){
      if(reg.trim().length === 0 || reg.trim() ===''){
        alert("错误：输入空")
      }else{
        this.loading = true
        axios.post(`http://localhost:8080/updateOrderTel`,{
          orderid:this.msg,
          tel:reg
        }).then((response) => {
              this.loading = false
              console.log(response)

            }
        )
      }
    },
    upTrueName(reg){
      if(reg.trim().length === 0 || reg.trim() ===''){
        alert("错误：输入空")
      }else{
        this.loading = true
        axios.post(`http://localhost:8080/updateOrderTrueName`,{
          orderid:this.msg,
          truename:reg
        }).then((response) => {
              this.loading = false
              console.log(response)

            }
        )
      }
    }
  }
}
</script>

<style scoped>
.log{
  position: fixed;
  margin-top: -250px;
  margin-left: 50%;
  z-index: 1;
  transform: translate(-600px);
  width:1000px;
  height: 300px;
  background-color: #ffffff;
  border: 1px solid #000000;
  display: flex;
  flex-direction: column;
}
#dismiss{
  color: white;
  text-indent: 5px;
  width: auto;
  height: auto;
  font-size: large;
  background-color: #004498;
  cursor: pointer;
}
#dismiss:hover{
  color: #ff0026;
}
.introinfo{
  margin-top: 10px;
  margin-left: 30px;
  color: #000000;
  font-size: 30px;
  background-color: #ffffff;
  width: auto;
  height: auto;
}
input{
  text-indent: 2em;
  margin-top: 20px;
  width: 300px;
  height: 25px;
  margin-left: 5px;
}
button{
  margin-top: 20px;
  height: 30px;
  margin-left: 5px;
  border: 1px solid #005585;
  background-color: #00a8fa;
  cursor: pointer;
}
button:hover{
  background-color: #00517e;
}
</style>