<template>
  <div class="log" v-if="show">
    <div id="dismiss" @click="dismiss">X</div>
    <div class="introinfo">
      <el-form label-width="120px">
        <el-form-item label="民宿编号" >
          {{ modhouses }}
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="address" />
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="housename" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit()">增加</el-button>
          <el-button @click="load()">取消</el-button>
        </el-form-item>
      </el-form>

<!--      <tr>-->
<!--        <th>民宿编号:</th>-->
<!--        <td>{{ modhouses }}</td><td style="width: 20px"></td>-->
<!--      </tr>-->

<!--      <tr>-->
<!--        <th>地址:</th>-->
<!--        <td>{{ address }}</td><td></td>-->
<!--        <input v-model="address" type="text" placeholder="">-->
<!--      </tr>-->
<!--      <tr>-->
<!--        <th>名称:</th>-->
<!--        <td>{{ housename }}</td><td></td>-->
<!--        <input v-model="housename" type="text" placeholder="">-->
<!--        <button type="submit"  @click="submit()">加入</button>-->
<!--      </tr>-->
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {ElMessage, ElNotification} from "element-plus";

export default {
  name: "RoomMod",
  props:['msg'],
  data(){
    return{
      show:true,
      rooms:{},
      modhouses:'',
      address:'',
      housename:'',

    }
  },
  created() {
    this.load()
  },
  methods:{
    submit(){
      if(this.housename.trim().length === 0 || this.housename.trim() ===''){
        ElMessage({
          message: '名称输入空',
          type: 'warning',
        })
      }else if(this.address.trim().length === 0 || this.address.trim() ===''){
         ElMessage({
          message: '地址输入空',
          type: 'warning',
        })
      }
      else{
        this.loading = true
        axios.post(`http://localhost:8080/InsertHouse`,{
          houseid:this.modhouses,housename:this.housename,address:this.address
        }).then((response) => {
              this.loading = false
              console.log(response.data)
              ElNotification({
                message: '成功',
                type: 'success',
              })
              this.dismiss()
            }
        )
      }


    },
    dismiss(){
      this.$emit('state',this.show)
      this.show=false
      this.$store.state.flag=!this.$store.state.flag
    },
    load(){
      this.loading = true
      axios.post(`http://localhost:8080/findFirstHouse`,{
        houseid:this.msg
      }).then((response) => {
            this.loading = false
            this.rooms=response.data
            if(response.data===''){
              this.modhouses=1
            }else {
              this.modhouses=this.rooms.houseid+1
            }
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
  margin-top: 200px;
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
  color: #ff0026;
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

</style>