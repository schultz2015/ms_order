<template>
  <div class="log" v-if="show">
    <div id="dismiss" @click="dismiss">X</div>
    <div class="introinfo">
      <tr>
        <th>用户编号:</th>
        <td>{{ msg.userid }}</td><td style="width: 20px"></td>
      </tr>

      <tr>
        <th>用户名:</th>
        <td>{{ modusername }}</td><td></td>
        <input v-model="modusername" type="text" placeholder="">
        <button type="submit"  @click="submitusername(msg.userid,modusername)">修改</button>
      </tr>
      <tr>
        <th>密码:</th>
        <td>{{ modpassword}}</td><td></td>
        <input v-model="modpassword" type="text" placeholder="">
        <button type="submit"  @click="submitpassword(msg.userid,modpassword)">修改</button>
      </tr>
      <tr>
        <th>姓名:</th>
        <td>{{ modtruename }}</td><td></td>
        <input v-model="modtruename" type="text" placeholder="">
        <button type="submit"  @click="submittruename(msg.userid,modtruename)">修改</button>
      </tr>
      <tr>
        <th>电话:</th>
        <td>{{ modtel }}</td><td></td>
        <input v-model="modtel" type="text" placeholder="">
        <button type="submit"  @click="submittel(msg.userid,modtel)">修改</button>
      </tr>
    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserMod",
  props:['msg'],
  data(){
    return{
      modusername:'',
      modpassword:'',
      modtel:'',
      show:true,
      modtruename:'',
      houses:[]
    }
  },
  created() {
    this.modusername=this.msg.username
    this.modpassword=this.msg.password
    this.modtruename=this.msg.truename
    this.modtel=this.msg.tel
  },
  methods:{
    dismiss(){
      this.$emit('state',this.show)
      this.show=false
    },
    submitusername(reg,rek){
      this.loading = true
      axios.post(`http://localhost:8080/submitusername`,{
        userid:reg,username:rek
      }).then((response) => {
            this.loading = false
            // this.houses=response.data
            console.log(response.data)
          }
      )
    },
    submitpassword(reg,rek){
      this.loading = true
      axios.post(`http://localhost:8080/submitpassword`,{
        userid:reg,password:rek
      }).then((response) => {
            this.loading = false

            // this.houses=response.data
            console.log(response.data)
          }
      )
    },
    submittruename(reg,rek){
      this.loading = true
      axios.post(`http://localhost:8080/submittruename`,{
        userid:reg,truename:rek
      }).then((response) => {
            this.loading = false
            console.log(response.data)
          }
      )
    },
    submittel(reg,rek){
      this.loading = true
      axios.post(`http://localhost:8080/submittel`,{
        userid:reg,tel:rek
      }).then((response) => {
            this.loading = false

            // this.houses=response.data
            console.log(response.data)
          }
      )
    }
  }
}
</script>

<style scoped>
.log{
  position: fixed;
  margin-top: -50px;
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
  background-color: #156c91;
}
</style>