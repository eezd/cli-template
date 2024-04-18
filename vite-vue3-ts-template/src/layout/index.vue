<template>
  <el-container :class="classObj">
    <el-aside class="sidebar-container"><Sidebar /></el-aside>
    <el-container class="main-container">
      <el-header>
        <Navbar />
      </el-header>
      <el-main>
        <RouterView />
        123312
      </el-main>
    </el-container>
  </el-container>
</template>
<script lang="ts" setup>
import Navbar from '@/layout/Navbar.vue'
import Sidebar from '@/layout/Sidebar.vue'

import { useWindowSize } from '@vueuse/core'
// import defaultSettings from '@/settings'
import useAppStore from '@/store/modules/app'
// import useSettingsStore from '@/store/modules/settings'
import { computed, watchEffect, ref } from 'vue'

// const settingsStore = useSettingsStore()
// const theme = computed(() => settingsStore.theme)
// const sideTheme = computed(() => settingsStore.sideTheme)
const sidebar = computed(() => useAppStore().sidebar)
const device = computed(() => useAppStore().device)
// const needTagsView = computed(() => settingsStore.tagsView)
// const fixedHeader = computed(() => settingsStore.fixedHeader)

const classObj = computed(() => ({
  hideSidebar: !sidebar.value.opened,
  openSidebar: sidebar.value.opened,
  withoutAnimation: sidebar.value.withoutAnimation,
  mobile: device.value === 'mobile',
}))

const { width, height } = useWindowSize()
const WIDTH = 992 // refer to Bootstrap's responsive design

watchEffect(() => {
  if (device.value === 'mobile' && sidebar.value.opened) {
    useAppStore().closeSideBar({ withoutAnimation: false })
  }
  if (width.value - 1 < WIDTH) {
    useAppStore().toggleDevice('mobile')
    useAppStore().closeSideBar({ withoutAnimation: true })
  } else {
    useAppStore().toggleDevice('desktop')
  }
})

function handleClickOutside() {
  useAppStore().closeSideBar({ withoutAnimation: false })
}

const settingRef = ref<any>(null)
function setLayout() {
  settingRef.value?.openSetting()
}
</script>
<style lang="scss" scoped>
// .el-header {
//   height: 60px;
//   line-height: 60px;
//   box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
//   display: flex;
//   flex-direction: row-reverse;
//   background-color: #b3c0d1;
// }
</style>
