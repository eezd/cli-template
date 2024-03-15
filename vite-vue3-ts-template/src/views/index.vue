<template>
  <div class="app-container home">
    <svg-icon icon-class="user" />

    <el-icon class="expand" color="#409EFC" :size="30">
      <expand />
    </el-icon>

    <p :style="{ color: variables.menuColor }">Home</p>

    <p>123</p>

    <button @click="testRequest">testRequest</button>
  </div>
</template>

<script setup name="Index" lang="ts">
import variables from '@/assets/styles/variables.module.scss'

import SvgIcon from '@/components/SvgIcon/index.vue'

import request from '@/utils/request'

const testRequest = () => {
  request({
    url: '/',
    method: 'POST',
    headers: {
      isToken: false,
    },
  }).then((result: any) => {
    // console.log(result)
  })
}

// const version = ref('3.8.5')

// function goTarget(url: string) {
//   window.open(url, '__blank')
// }
</script>

<style scoped lang="scss">
@import '@/assets/styles/variables.module.scss';
.home {
  width: $base-sidebar-width;
}
</style>
