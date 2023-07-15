<template>
  <VueDatePicker
       range format="yyyy-MM-dd" value-format="yyyy-MM-dd" min-range="1"
        @range-start="func" @open="refresh"  @cleared="alertFn"
       :max-date="e"
       :partial-range="false"
      :highlight="highlightedDates"
      :disabled-dates="highlightedDates"
      :min-date="s"
      highlight-disabled-days
  />
</template>

<script setup>
import {onMounted, ref, watch} from 'vue';
import addDays from 'date-fns/addDays';
import subDays from  'date-fns/subDays'
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'
import {differenceInDays, parseISO} from "date-fns";
const props = defineProps(['msg'])
const date = ref(new Date());

onMounted(() => {
  const startDate = new Date();
  const endDate = new Date(new Date().setDate(startDate.getDate() + 2));
  date.value = [startDate, endDate];
})


let tim=[]
for (let i=0; i<props.msg.length; i++){
  tim.push(addDays(new Date(), props.msg[i]))
}
const highlightedDates = ref(tim)



const e = ref(addDays(new Date(), 200000));
const s = ref(subDays(new Date(), 0));


// watch(fu,(newvalue,oldvalue)=>{
//   console.log("chose"+fu.value)
// },{immediate:true})


let func=(reg)=>{
    let X=[]
    for (let i = 0; i<props.msg.length; i++){
      if (props.msg[i]>0){
        X.push(parseInt(props.msg[i]))
      }
    }
    X.sort((a,b)=>{return a-b})

    let fu=ref(2)
    fu.value=reg
    const short = differenceInDays(fu.value,new Date())+1
    X.push(short)
    X.sort((a,b)=>{return a-b})

    // console.log(X)
    // for (let i = 0; i<X.length-1; i++){
    //   if (X[i]>short){
    //     e.value=addDays(new Date(), X[i])
    //     console.log("触发1",fu.value,short,X)
    //     if(i!==0){
    //       s.value=addDays(new Date(), X[i-1])
    //     }
    //
    //   }else {
    //     console.log("触发2",fu.value,short,X,X[X.length-1])
    //     s.value=addDays(new Date(), X[X.length-1])
    //     e.value=addDays(new Date(), 20000)
    //
    //   }
    // }
    // X.sort((a,b)=>{return a-b})
    let chosen=0
    for (let i = 0; i<X.length; i++){
      if (X[i]===short){
        chosen=i
      }
    }
    console.log("触发"+fu.value)
    console.log(short+"X:"+X)
  if (X.length===1){
    console.log(1)
    s.value=addDays(new Date(), 0)
    e.value=addDays(new Date(), 200000)
  }else if(chosen===0){
    console.log(0)
    s.value=addDays(new Date(), 0)
    e.value=addDays(new Date(), X[chosen+1])
  }else if(chosen===X.length-1){
    console.log(3)
    s.value=addDays(new Date(), X[chosen-1])
    e.value=addDays(new Date(), 200000)
  } else{
    console.log(2)
    s.value=addDays(new Date(), X[chosen-1])
    e.value=addDays(new Date(), X[chosen+1])
  }


  console.log("e"+e.value)
  console.log("s"+s.value)
}



let refresh=()=>{
  e.value = addDays(new Date(), 200000)
  s.value = subDays(new Date(), 0)
}

let alertFn=()=>{
  const startDate = new Date();
  const endDate = new Date(new Date().setDate(startDate.getDate() + 2));
  date.value = [startDate, endDate];
  alert(date.value)
}
const handleDate = (modelData) => {
  date.value = modelData;
  // do something else with the data
}



</script>