<template>
  <div class="log" v-if="show">
    <div id="dismiss" @click="dismiss">X</div>
    <div class="introinfo">
      <el-form label-width="120px" @change="change()">
        <el-form-item label="房间编号" >
          {{addroomid}}
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
          <el-input disabled v-model="modimg" />
          <el-upload
              class="upload-demo"
              drag
              action="http://localhost:8080/upload"
              multiple
              :file-list="fileList"
              :on-change="onchange"
              :on-success="handleSuccess"
          >
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
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
          <el-button type="primary" @click="submit()">增加</el-button>
          <el-button @click="load()">取消</el-button>
        </el-form-item>
      </el-form>
<!--      <tr>-->
<!--        <th>房间编号:</th>-->
<!--        <td>{{rooms.roomid+1}}</td><td style="width: 20px"></td>-->
<!--      </tr>-->

<!--      <tr>-->
<!--        <th>价格:</th>-->
<!--        <td>{{ modprice }}</td><td></td>-->
<!--        <input v-model="modprice" type="text" placeholder="">-->

<!--      </tr>-->
<!--      <tr>-->
<!--        <th>类型:</th>-->
<!--        <td>{{ modtype }}</td><td></td>-->
<!--        <input v-model="modtype" type="text" placeholder="">-->

<!--      </tr>-->
<!--      <tr>-->
<!--        <th>房间名:</th>-->
<!--        <td>{{ modname }}</td><td></td>-->
<!--        <input v-model="modname" type="text" placeholder="">-->

<!--      </tr>-->
<!--      <tr>-->
<!--        <th>预览图:</th>-->
<!--        <td>{{ modimg }}</td><td></td>-->
<!--        <input v-model="modimg" type="text" placeholder="">-->
<!--        <button type="submit"  @click="submit()">加入</button>-->
<!--      </tr>-->
    </div>

  </div>
</template>

<script>
import axios from "axios";
import {Edit, UploadFilled} from "@element-plus/icons-vue";
import {ElMessage, ElNotification} from "element-plus";

export default {
  name: "RoomMod",
  components: {UploadFilled, Edit},
  props:['msg'],
  data(){
    return{
      show:true,
      rooms:{},
      addroomid:'',
      modimg:'',
      modname:'',
      modtype:'',
      modprice:'',
      fileList: []
    }
  },
  created() {
    this.load()
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
    submit(){
      if(this.modprice.trim().length === 0 || this.modprice.trim() ===''){
        ElMessage({
          message: '价格输入空',
          type: 'warning',
        })
      }else if(this.modname.trim().length === 0 || this.modname.trim() ===''){
        ElMessage({
          message: '名称输入空',
          type: 'warning',
        })
      }else if(this.modtype.trim().length === 0 || this.modtype.trim() ===''){
        ElMessage({
          message: '类型输入空',
          type: 'warning',
        })
      }else if(this.modimg.trim().length === 0 || this.modimg.trim() ===''){
        ElMessage({
          message: '图片输入空',
          type: 'warning',
        })
      }
      else{
        this.loading = true
        axios.post(`http://localhost:8080/InsertRoom`,{
          houseid:this.msg,
          roomid:this.addroomid,
          price:this.modprice,
          roomname:this.modname,
          roomimg:this.modimg,
          type:this.modtype
        }).then((response) => {
              this.loading = false
              this.rooms=response.data
              console.log("InsertRoom:"+response.data)
              ElNotification({
                title: 'Success',
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
    change(){
      console.log()
    },
    load(){
      this.loading = true
      axios.post(`http://localhost:8080/findFirstRoom`,{
        houseid:this.msg
      }).then((response) => {
            this.loading = false
            this.rooms=response.data
            if(response.data===''){
              this.addroomid=1
            }else {
              this.addroomid=this.rooms.roomid+1
            }
            console.log("findfirstRoom:"+response.data)
          }
      )
    }
  }
}
</script>

<style scoped>
.log{
  position: fixed;
  margin-top: 5%;
  margin-left: 50%;
  z-index: 1;
  transform: translate(-500px);
  width:500px;
  height: 600px;
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
  font-size: 16px;
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