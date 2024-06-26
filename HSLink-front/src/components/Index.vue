<template>
  <el-container>
    <el-header>
      <div id="header">
        <div class="date default-color">{{date}}</div>
        <div class="function">
          <span class="pointer special-font-blue " v-if="!isLogin" @click="login">登录</span>
          <span class="pointer special-font-blue " v-if="isLogin" @click="goPersonalInfo(userInfo.user_id)">欢迎：{{userInfo.real_name}}（{{userInfo.user_type}}）</span>
          <span class="pointer special-font-blue " v-if="isLogin" @click="cancellation">注销</span>
          <el-divider direction="vertical"></el-divider>
          <span class="pointer special-font-blue " v-if="userInfo.user_type === '管理员'" @click="admin">后台管理</span>
          <el-divider direction="vertical"></el-divider>
          <span class="pointer special-font-blue" @click="service">客服中心</span>
          <el-divider direction="vertical"></el-divider>
          <span class="pointer special-font-blue" @click="opinion">用户意见</span>
          <el-divider direction="vertical"></el-divider>
          <span class="pointer special-font-blue" v-if="!isLogin" @click="register">免费注册</span>
          <el-divider direction="vertical" v-if="!isLogin"></el-divider>
          <span class="pointer special-font-blue" v-if="isLogin" @click="forget">忘记密码</span>
          <el-divider direction="vertical" v-if="isLogin"></el-divider>
          <span class="pointer special-font-blue" @click="goToPage('help')">帮助中心</span>
        </div>
      </div>
    </el-header>
    <el-container class="second">
    <el-aside>
      <el-menu
        default-active="1"
        class="el-menu-vertical-demo">
        <el-menu-item :key="item.index" :index="item.path" @click="goToPage(item.path)" v-for="item in menuList">{{item.name}}</el-menu-item>
        <el-menu-item index="100" @click="goMore" disabled>更多···</el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view ref="child"></router-view>
    </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import  { getDate } from  '../assets/js/public.js'
  export default {
    data() {
      return{
        isCollapse: true,
        isLogin: false,
        date: '',
        userInfo: {
          real_name: '',
          user_type: '',
        },
        //菜单目录
        menuList: [
          {name: '首页', path: 'homepage', index: '1'},
          {name: '校园通知', path: 'schoolnotice', index: '2'},
          {name: '我的班级', path: 'myclass', index: '3'},
          {name: '家长意见', path: 'parentsproposal', index: '4'},
          {name: '学生想法', path: 'studentthought', index: '5'},
        ]
      }
    },
    mounted() {
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      /*setInterval(()=>{
        this.timingTask(userInfo.user_id)
      },60000);*/
      if (userInfo === '' || userInfo === null) {

      } else {
        this.isLogin = true;
        this.userInfo = userInfo;
      }
      setInterval(()=>{
        let day2 = new Date();
        day2.setTime(day2.getTime());
        this.date = day2.getFullYear()+"年" + (day2.getMonth()+1<10? "0"+(day2.getMonth()+1):day2.getMonth()+1)
          + "月" + (day2.getDate()<10?"0"+day2.getDate():day2.getDate())+'日 星期'+"日一二三四五六".charAt(new Date().getDay())
          +" | "+(day2.getHours()<10?"0"+day2.getHours():day2.getHours())+"时"+(day2.getMinutes()<10?"0"
            +day2.getMinutes():day2.getMinutes())+"分"+(day2.getSeconds()<10?"0"+day2.getSeconds():day2.getSeconds())+"秒";},1000)
    },
    methods:{
      /**
       * 跳转到后台管理模式
       */
      admin() {
        this.$router.push({name: 'homepagemanagement'});
      },
      timingTask(userId) {
        this.$ajax.post("/hs/timingTask",{userId:userId},r=>{
        })
      },
      goPersonalInfo(userId) {
        sessionStorage.setItem("userId",userId);
        this.$router.push({name: 'personalinfo'});
        this.$refs.child.getUserInfo();
      },
      goMore() {
        this.$notify("期待更多内容",)

      },
      goToPage(path) {
        if (path === 'myclass') {
          if (JSON.parse(sessionStorage.getItem("userInfo")).class_name === undefined) {
            this.$message.warning("您没有加入任何班级")
          } else {
            this.$router.push({name: path})
          }
        } else {
          this.$router.push({name: path})
        }
      },
      forget() {
        this.$notify("请联系管理员重置密码")
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
        this.$notify('用户意见请发邮箱至：617594538@qq.com')
      },
      service() {
        this.$notify('客服中心请拨打：15006732580')
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
          this.$notify.info('已取消退出');
        });
      }
    },
  }
</script>

<style scoped>
  .el-container{
    /*height: 100%;*/
  }
  .el-menu{
    border-radius: 5px;
  }
  .el-aside{
    background: #FFF;
    overflow-x: hidden;
    width: 10rem!important;
    margin-top: 1rem;
    text-align: center;
    height: 21rem;
    position: sticky;
    top: 1rem;
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
    padding: 0;
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
