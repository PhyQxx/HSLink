<template>
  <el-container>
    <el-main>
      <img src="../../assets/img/no-data.png" height="200" width="200"/>
      <div class="nodata">
        暂无数据
      </div>
    </el-main>
  </el-container>
</template>

<script>
    export default {
        name: "NoData"
    }
</script>

<style scoped>
  .el-main img{
    display: block;
    margin: 2rem auto 0;
  }
  .nodata{
    text-align: center;
    color: #999;
  }
</style>
