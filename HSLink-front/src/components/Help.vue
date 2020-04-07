<template>
  <el-main class="body-background">
    <div class="left">
      <div class="top">
        <img src="../assets/img/left-1.gif" height="83" width="142"/>
      </div>
      <div class="bottom">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo">
          <el-menu-item index="1">首页</el-menu-item>
          <el-menu-item index="2">校园通知</el-menu-item>
          <el-menu-item index="3">家长意见</el-menu-item>
          <el-menu-item index="4">学生想法</el-menu-item>
          <el-menu-item index="5">我的班级</el-menu-item>
          <el-menu-item index="6">常见问题</el-menu-item>
        </el-menu>
      </div>
    </div>
    <div class="right">
      <div>请联系管理员更新内容</div>
      <div>管理员联系方式：15006732580</div>
      <div>管理员QQ邮箱：617594538@qq.com</div>
    </div>
  </el-main>
</template>

<script>
    export default {
        name: "Help"
    }
</script>

<style scoped>
  .right{
    border: 1px solid #419EFF;
    border-radius: 5px;
    background: #fff;
    width: calc(85% - 1rem);
    margin-left: 1rem;
    padding: 1rem;
  }
  .el-menu-item{
   text-align: center;
  }
  .left{
    border: 1px solid #419EFF;
    border-radius: 5px;
    background: #fff;
    width: 15%;
  }
  .left img{
    display: block;
    margin: 0 auto;
  }
  .el-main{
    padding: 1rem 0;
    display: flex;
  }
</style>
