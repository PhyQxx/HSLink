<template>
  <el-container>
    <el-header>
      <div id="header">
        <div class="date default-color">{{date}}</div>
        <div class="function">
          <span class="pointer special-font-blue " v-if="!isLogin" @click="login">登录</span>
          <span class="pointer special-font-blue " v-if="isLogin">欢迎：{{userInfo.real_name}}（{{userInfo.user_type}}）</span>
          <span class="pointer special-font-blue " v-if="isLogin" @click="cancellation">注销</span>
          <el-divider direction="vertical"></el-divider>
          <span class="pointer special-font-blue" @click="service">客服中心</span>
          <el-divider direction="vertical"></el-divider>
          <span class="pointer special-font-blue" @click="opinion">用户意见</span>
          <el-divider direction="vertical"></el-divider>
          <span class="pointer special-font-blue" v-if="!isLogin" @click="register">免费注册</span>
          <el-divider direction="vertical" v-if="!isLogin"></el-divider>
          <span class="pointer special-font-blue" v-if="isLogin" @click="forget">忘记密码</span>
          <el-divider direction="vertical" v-if="isLogin"></el-divider>
          <span class="pointer special-font-blue" @click="help">帮助中心</span>
        </div>
      </div>
    </el-header>
    <el-container class="second">
    <el-aside>
      <el-menu
        default-active="1"
        class="el-menu-vertical-demo">
        <el-menu-item index="1" @click="goHomepage">首页</el-menu-item>
        <el-menu-item index="2" @click="goSchoolNotice">校园通知</el-menu-item>
        <el-menu-item index="3" @click="goMyClass">我的班级</el-menu-item>
        <el-menu-item index="4" @click="goParentsOpinion">家长意见</el-menu-item>
        <el-menu-item index="5" @click="goStudentThought">学生想法</el-menu-item>
        <el-menu-item index="6" @click="goMore">更多···</el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <!--<div id="tab">
        <span class="pointer white text-underline" @click="goHomepage">首页</span>
        <el-divider direction="vertical"></el-divider>
        <span class="pointer white text-underline" @click="goSchoolNotice">校园通知</span>
        <el-divider direction="vertical"></el-divider>
        <span class="pointer white text-underline" @click="goParentsOpinion">家长建议</span>
        <el-divider direction="vertical"></el-divider>
        <span class="pointer white text-underline" @click="goStudentThought">学生想法</span>
        <el-divider direction="vertical"></el-divider>
        <span class="pointer white text-underline" @click="goMyClass">我的班级</span>
        <el-divider direction="vertical"></el-divider>
        <span class="pointer white text-underline" @click="goMore">更多···</span>
        <span class="pointer white text-underline user-info" v-if="!isLogin" @click="login">登录</span>
        <span class="pointer white text-underline user-cancellation" v-if="isLogin" @click="cancellation">注销</span>
        <span class="pointer white text-underline user-info" v-if="isLogin">欢迎：{{userInfo.real_name}}（{{userInfo.user_type}}）</span>
      </div>-->
      <router-view></router-view>
    </el-main>
    </el-container>
  </el-container>
</template>

<script>
  export default {
    data() {
      return{
        isCollapse: true,
        isLogin: false,
        date: '',
        userInfo: '',
      }
    },
    mounted() {
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      this.userInfo = userInfo;
      if (userInfo != '' || userInfo != null) {
        this.isLogin = true;
      }
      setInterval(()=>{
        let day2 = new Date();
        day2.setTime(day2.getTime());
        this.date = day2.getFullYear()+"年" + (day2.getMonth()+1) + "月" + day2.getDate()+'日 星期'+"日一二三四五六".charAt(new Date().getDay())
          +" | "+day2.getHours()+"时"+day2.getMinutes()+"分"+day2.getSeconds()+"秒";},1000)
    },
    methods:{
      goMore() {
        this.$message({
          message:"期待更多内容",
        })

      },
      goMyClass() {
        this.$router.push({name: "myclass"})
      },
      goStudentThought() {
        this.$router.push({name: "studentthought"})
      },
      goParentsOpinion() {
        this.$router.push({name: "parentsproposal"})
      },
      goSchoolNotice() {
        this.$router.push({name: "schoolnotice"})
      },
      goHomepage() {
        this.$router.push({name: "homepage"})
      },
      help() {
        this.$router.push({
          name: "help",
        })
      },
      forget() {
        this.$message("请联系管理员重置密码")
      },
      register() {
        this.$router.push({
          name: "homepage",
          params: {
            type: "register"
          }
        })
      },
      opinion() {
        this.$message('用户意见请发邮箱至：617594538@qq.com')
      },
      service() {
        this.$message('客服中心请拨打：15006732580')
      },
      login() {
        this.$router.push({
          name: "login"
        })
      },
      cancellation() {
        this.$confirm('确认退出?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          sessionStorage.setItem("userInfo","");
          this.$router.push({
            name: "login"
          })
          // location.reload()
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消退出'
          });
        });
      }
    },
  }
</script>

<style scoped>
  .el-menu{
    border-radius: 5px;
  }
  .el-aside{
    background: #FFF;
    overflow-x: hidden;
    width: 10rem!important;
    margin-top: 1rem;
    text-align: center;
  }
  .el-radio-group{
    margin-bottom: 0!important;
  }
  .second{
    background: #EEFCFE;
    padding: 0 20%;
  }
  .user-cancellation{
    float: right;
  }
  .user-info{
    float: right;
    margin-right: 1rem;
  }
  .el-main{
    padding: 1rem;
  }
  #tab{
    background-image: url("../assets/img/dh-002.gif");
    padding: 0 20%;
    height: 2rem;
    font-size: 0.9rem;
    line-height: 2rem;

  }
  #header{
    font-size: 0.9rem;
    padding: 0.2rem 0 0.4rem 0;
  }
  #header .date{
    margin-left: 20%;
    float: left;
  }
  #header .function{
    float: right;
    margin-right: 20%;
  }
  .el-header{
    height: 7.8rem!important;
    background-image: url("../assets/img/title.jpg");
    background-size: 100%;
    background-position: 0 2rem;
    background-repeat: no-repeat;
  }
</style>
