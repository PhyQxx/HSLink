<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted() {
    const url = `http://192.168.10.207:8048`;
    sessionStorage.setItem('url', url);
  }
}
</script>

<style scoped>
  .el-container{
    height: 100%;
  }
</style>
