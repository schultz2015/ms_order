<template>
  <div class="log" v-if="show">
    <div id="dismiss" @click="dismiss">X</div>
    <div class="introinfo" v-for="h in houses" :key="h">

      <el-form label-width="120px">
        <el-form-item label="房间编号" >
          {{ modhouses }}
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="modaddress" />
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="modname" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit()">修改</el-button>
          <el-button @click="load()">取消</el-button>
        </el-form-item>
      </el-form>




<!--      <tr>-->
<!--        <th>民宿编号:</th>-->
<!--        <td>{{ modhouses }}</td><td style="width: 20px"></td>-->
<!--      </tr>-->

<!--      <tr>-->
<!--        <th>地址:</th>-->
<!--        <td>{{ h.address }}</td><td></td>-->
<!--        <input v-model="modaddress" type="text" placeholder="">-->
<!--        <button type="submit"  @click="submitAddress()">修改</button>-->
<!--      </tr>-->
<!--      <tr>-->
<!--        <th>名称:</th>-->
<!--        <td>{{ h.housename }}</td><td></td>-->
<!--        <input v-model="modname" type="text" placeholder="">-->
<!--        <button type="submit"  @click="submitName()">修改</button>-->
<!--      </tr>-->
    </div>

  </div>
</template>

<script>
import axios from "axios";
import {ElMessage, ElNotification} from "element-plus";

export default {
  name: "HouseMod",
  props:['msg'],
  data(){
    return{
      modaddress:'',
      modname:'',
      show:true,
      modhouses:this.msg,
      houses:[]
    }
  },
  created() {
    this.load()
  },
  methods:{
    dismiss(){
      this.$emit('state',this.show)
      this.show=false
      this.$store.state.flag=!this.$store.state.flag
    },
    load(){
      this.loading = true
      axios.post(`http://localhost:8080/searchHouse`,{
        houseid:this.modhouses,address:-1
      }).then((response) => {
            this.loading = false
            this.houses=response.data
            console.log(response.data)
            this.modname=this.houses[0].housename
            this.modaddress=this.houses[0].address
          }
      )
    },
    submit(){
      this.submitAddress()
    },
    submitAddress(){
      if(this.modaddress.trim().length === 0 || this.modaddress.trim() ===''){
        ElMessage({
          message: '地址输入空',
          type: 'warning',
        })
      }
      else{
        this.loading = true
        axios.post(`http://localhost:8080/submitAddress`,{
          houseid:this.modhouses,address:this.modaddress
        }).then((response) => {
              this.loading = false
              // this.houses=response.data
              console.log(response.data)
              // ElNotification({
              //   message:"成功",
              //   type:"success"
              // })
              this.submitName()
            }
        )
      }

    },
    submitName(){
      if(this.modname.trim().length === 0 || this.modname.trim() ===''){
        ElMessage({
          message: '名称输入空',
          type: 'warning',
        })
      }
      else{
        this.loading = true
        axios.post(`http://localhost:8080/submitName`,{
          houseid:this.modhouses,housename:this.modname
        }).then((response) => {
              this.loading = false
              // this.houses=response.data
              console.log(response.data)
              ElNotification({
                message:"成功",
                type:"success"
              })
              this.dismiss()
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
  margin-top: 250px;
  margin-left: 50%;
  z-index: 1;
  transform: translate(-500px);
  width:500px;
  height: 300px;
  background-color: #ffffff;
  border: 1px solid #000000;
  display: flex;
  flex-direction: column;
  box-shadow: 0 0 1000px 1000px rgba(0,0,0,0.5);
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
  color: #ff0000;
}
.introinfo{
  margin-top: 10px;
  margin-right: 50px;
  color: #000000;
  font-size: 30px;
  background-color: #ffffff;
  width: auto;
  height: auto;
}
/*input{*/
/*  text-indent: 2em;*/
/*  margin-top: 20px;*/
/*  width: 300px;*/
/*  height: 25px;*/
/*  margin-left: 5px;*/
/*}*/
/*button{*/
/*  margin-top: 20px;*/
/*  height: 30px;*/
/*  margin-left: 5px;*/
/*  border: 1px solid #005585;*/
/*  background-color: #00a8fa;*/
/*  cursor: pointer;*/
/*}*/
/*button:hover{*/
/*  background-color: #00517e;*/
/*}*/
</style>