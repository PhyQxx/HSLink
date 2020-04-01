<template>
  <div id="login">
    <el-span>账号：</el-span>
    <el-col :span="8">
      <el-input placeholder = "请输入用户名"></el-input>
    </el-col>
    <el-span>密码：</el-span>
    <el-col :span="8">
      <el-input placeholder = "请输入密码" show-password></el-input>
    </el-col>
    <el-button type = "text">登录</el-button>
    <router-view/>
  </div>
</template>

<script>
  export default{
    methods:{
      change() {
        // console.log(1)
        // this.$router.push({
        //   name: 'box1',
        //   params:{
        //      id:1
        //   }
        // })
      }
    },
  }
</script>

<style>
</style>
