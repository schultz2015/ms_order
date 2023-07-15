<template>
  <div class="log" v-if="show">
    <div id="dismiss" @click="dismiss">X</div>
    <div class="introinfo">


      <el-form :model="rooms[0]" label-width="120px" @change="change()">
        <el-form-item label="房间编号" >
          {{modrooms}}
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="modprice" />
        </el-form-item>
        <el-form-item label="类型">
          <el-input v-model="modtype" />
        </el-form-item>
        <el-form-item label="房间名">
          <el-input v-model="modname" />
        </el-form-item>
        <el-form-item label="预览图">
          <el-input v-model="modimg" disabled/>
          <el-upload
              class="upload-demo"
              drag
              action="http://localhost:8080/upload"
              multiple
              :file-list="fileList"
              :on-change="onchange"
              :on-success="handleSuccess"
          >
            <el-icon class="el-icon--upload"><upload-filled/></el-icon>
            <div class="el-upload__text">
              可以拖动至此 <em>点击上传</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">
                jpg/png格式的图片并小于500kb
              </div>
            </template>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit()">修改</el-button>
          <el-button @click="load()">取消</el-button>
        </el-form-item>
      </el-form>

    </div>

  </div>
</template>

<script>
import axios from "axios";
import {UploadFilled} from "@element-plus/icons-vue";
import {ElMessage, ElNotification} from "element-plus";

export default {
  name: "RoomMod",
  components: {UploadFilled},
  props:['msg'],
  data(){
    return{
      modtype:'',
      modname:'',
      modprice:'',
      modimg:'',
      show:true,
      rooms:[],
      modrooms:'',
      modhouses:'',
      fileList: []
    }
  },
  created() {
    this.load()
    this.modrooms=this.msg.mr
    this.modhouses=this.msg.mh
  },
  watch:{
    "$store.state.flag":{
      handler:function (newVal,oldVal) {
        this.load()
      },
    }
  },
  methods:{
    onchange(file, fileList) {
      console.log("上传时的动作")
      console.log(fileList);
      // console.log(file.raw)
      this.fileList = fileList;//每一个改变都会将el-upload里面的图片传递的参数复制到this.filelist去
    },
    handleSuccess(response,file) {
      this.fileList = []; // 清空已上传的文件
      // this.$message.success('文件上传成功！');
      console.log('上传结果：', URL.createObjectURL(file.raw));
      this.modimg=file.name

    },
    dismiss(){
      this.$emit('state',this.show)
      this.show=false
      this.$store.state.flag=!this.$store.state.flag
    },
    change(){
      console.log(this.User)
    },
    load(){
      this.loading = true
      axios.post(`http://localhost:8080/searchRoom`,{
        houseid:this.msg.mh,roomid:this.msg.mr
      }).then((response) => {
            this.loading = false
            this.rooms=response.data
            console.log(response.data)
            this.modtype=this.rooms[0].type
            this.modname=this.rooms[0].roomname
            this.modprice=this.rooms[0].price
            this.modimg=this.rooms[0].roomimg
          }
      )
    },
    submit(){
      this.submitRName()
    },
    submitRName(){
      if(this.msg.mh===''){
        ElMessage({
          message: '未知的旅社',
          type: 'warning',
        })
      }
      else if( this.msg.mr===''){
        ElMessage({
          message: '未知的房间',
          type: 'warning',
        })
      }
      else if(this.modname.trim().length === 0 || this.modname.trim() ===''){
        ElMessage({
          message: '名称输入空',
          type: 'warning',
        })
      }else{
        this.loading = true
        axios.post(`http://localhost:8080/submitRName`,{
          houseid:this.msg.mh,roomid:this.msg.mr,roomname:this.modname
        }).then((response) => {
              this.loading = false
              // this.houses=response.data
              console.log(response.data)
              this.submitType()
            }
        )
      }

    },
    submitPrice(){
      if(this.msg.mh===''){
        ElMessage({
          message: '未知的旅社',
          type: 'warning',
        })
      }
      else if( this.msg.mr===''){
        ElMessage({
          message: '未知的房间',
          type: 'warning',
        })
      }
      else if(this.modprice.trim().length === 0 || this.modprice.trim() ===''){
        ElMessage({
          message: '价格输入空',
          type: 'warning',
        })
      }else{
        this.loading = true
        axios.post(`http://localhost:8080/submitPrice`,{
          houseid:this.msg.mh,roomid:this.msg.mr,price:this.modprice
        }).then((response) => {
              this.loading = false
              // this.houses=response.data
              console.log(response.data)
              this.submitImg()
            }
        )
      }

    },
    submitType(){
      if(this.msg.mh===''){
        ElMessage({
          message: '未知的旅社',
          type: 'warning',
        })
      }
      else if( this.msg.mr===''){
        ElMessage({
          message: '未知的房间',
          type: 'warning',
        })
      }
      else if(this.modtype.trim().length === 0 || this.modtype.trim() ===''){
        ElMessage({
          message: '类型输入空',
          type: 'warning',
        })
      }else{
        this.loading = true
        axios.post(`http://localhost:8080/submitType`,{
          houseid:this.msg.mh,roomid:this.msg.mr,type:this.modtype
        }).then((response) => {
              this.loading = false
              // this.houses=response.data

              console.log(response.data)
               this.submitPrice()

            }
        )
      }

    },
    submitImg(){
      if(this.msg.mh===''){
        ElMessage({
          message: '未知的旅社',
          type: 'warning',
        })
      }
      else if( this.msg.mr===''){
        ElMessage({
          message: '未知的房间',
          type: 'warning',
        })
      }
      else if(this.modimg.trim().length === 0 || this.modimg.trim() ===''){
        ElMessage({
          message: '图片输入空',
          type: 'warning',
        })
      }else{
        this.loading = true
        axios.post(`http://localhost:8080/submitImg`,{
          houseid:this.msg.mh,roomid:this.msg.mr,roomimg:this.modimg
        }).then((response) => {
              this.loading = false
              // this.houses=response.data
              console.log(response.data)
              ElNotification({
                title: 'Success',
                message: '修改成功',
                type: 'success',
              })
              this.dismiss()
            }
        )
      }
    },
  }
}
</script>

<style scoped>
.log{
  position: fixed;
  margin-top: 2%;
  margin-left: 50%;
  z-index: 1;
  transform: translate(-500px);
  width:500px;
  height: 650px;
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
  margin-left: 30px;
  margin-right: 50px;
  color: #000000;
  font-size: 30px;
  background-color: #ffffff;
  width: auto;
  height: auto;
}

</style>