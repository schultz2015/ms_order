<template>
  <div class="cardhead"></div>
  <div class="container">
    <div id="title">
      <span id="userinfo" @click="userinfo()">用户信息</span>
      <span id="orderinfo" @click="orderinfo()">订单信息</span>
    </div>
    <div id="showuser" v-if="state===1">
      USER
    </div>
    <div id="showorder" v-if="state===2">
      ORDER
    </div>

  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data(){
    return{
      state:1
    }
  },
  methods:{
    userinfo(){
      this.state=1
    },
    orderinfo(){
      this.state=2
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
