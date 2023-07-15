<template>
  <!--1.设置DOM容器-->
  <div id="Charts" ref="Echarts" ></div>
</template>
<script>
//2.引入echartjs
import * as echarts from "echarts";
import axios from "axios";

export default {
  name: "chart",
  data(){
    return{
      Date:[],
      Price: []
    }
  },
  created() {
    setTimeout(() => {
      this.getChart()
    }, 50);
    this.load()
  },
  methods:{
    load(){
      this.loading = true
      axios.get(`http://localhost:8080/chartDate`).then((response) => {
        this.loading = false
        console.log(response.data)
        this.Date = response.data
        console.log(this.Date)
      })
      axios.get(`http://localhost:8080/chartPrice`).then((response) => {
        this.loading = false
        console.log(response.data)
        this.Price = response.data
        console.log(this.Price)
      })
    },
    getChart(){
      // 3.定义图标对象myChart，初始化echarts实例
      let myChart=echarts.init(this.$refs.Echarts)

      // 4.添加配置项
      let options={
        title: {  //标题
          text: '本年度收入分布',  //主标题文本
          left:150,
          top:10,
        },

          xAxis: {
            type: 'category',
            data: this.Date
            // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            data:this.Price,
            // data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line'
          }]


        // tooltip: {},
        // xAxis: {  //配置要在 X 轴显示的项:
        //   data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
        //   // data: []
        // },
        // yAxis: {},  //配置要在 Y 轴显示的项:
        // series: [
        //   {
        //     name: '销量',
        //     type: 'bar',
        //     data: [
        //       {"value":"5","date":"2022-4-8"},
        //       {"value":"8","date":"2023-4-8"},
        //     ],
        //   }
        // ]
      }
      // 5.将配置给myChart
      myChart.setOption(options)
    }
  }

}
</script>

<style scoped>
#Charts{
  margin-top: 20px;
  width: 100%;
  height: 500px;
  border: 1px solid red;
}
</style>
