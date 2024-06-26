<template>
  <el-container>
    <el-main class="body-background">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="首页" name="1">
          <p>首页板块展示最新校园动态，并且展示校园通知、家长建议、学生想法三大板块的最新信息</p>
          <img style="width: 100%" src="../assets/img/help/homepage.jpg" alt="">
        </el-tab-pane>
        <el-tab-pane label="校园通知" name="2">
          <p>校园通知板块通过日历的表达形式展示校园通知，直接明了</p>
          <img style="width: 100%" src="../assets/img/help/schoolNotice1.jpg" alt="">
          <hr>
          <p>有校园通知的日期上会有该通知的标签，点击该日期会展示该日期的全部校园通知</p>
          <img style="width: 100%" src="../assets/img/help/schoolNotice2.jpg" alt="">
        </el-tab-pane>
        <el-tab-pane label="我的班级" name="3">
          <p>我的班级板块具有班级公告、班级通知、班级成员三大模块组成，班级公告、班级通知只有老师可以编辑、发布</p>
          <img style="width: 100%" src="../assets/img/help/myClass.jpg" alt="">
        </el-tab-pane>
        <el-tab-pane label="家长意见" name="4">
          <p>家长建议板块展示各位家长的建议，家长建议的发布在家长的个人中心里点击发表按钮</p>
          <img style="width: 100%" src="../assets/img/help/parentAdvice.jpg" alt="">
        </el-tab-pane>
        <el-tab-pane label="学生想法" name="5">
          <p>学生想法板块展示各位学生的想法，学生想法的发布在学生的个人中心里点击发表按钮</p>
          <img style="width: 100%" src="../assets/img/help/studentThinking.jpg" alt="">
        </el-tab-pane>
        <el-tab-pane label="常见问题" name="6">
          <p>搜索</p>
          <img style="width: 100%" src="../assets/img/help/search.jpg" alt="">
          <img style="width: 100%" src="../assets/img/help/search1.jpg" alt="">
          <hr>
          <p>个人中心，在头部点击姓名可进入，可以查看自己发表的全部内容，可以发表和修改密码</p>
          <img style="width: 100%" src="../assets/img/help/personalInfo.jpg" alt="">
        </el-tab-pane>
      </el-tabs>
    </el-main>
    <el-aside>
      <footers></footers>
    </el-aside>
  </el-container>

</template>

<script>
  import footers from './components/Footer'
    export default {
        name: "Help",
      data() {
          return{
            activeName: '1'
          }
      },
      methods:{
        handleClick(tab, event) {
        }
      },
      components: {
          footers
      }
    }
</script>

<style scoped>
  .el-tabs{
    width: 100%;
  }
  .el-aside{
    margin-left: 1rem;
    margin-top:-1rem ;
    width: 14rem!important;
  }
  .el-container{
    margin: 1rem;
  }
  .el-main{
    background: #fff;
  }
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
    width: 100%;
  }
  .el-main{
    padding: 1rem;
    display: flex;
  }

</style>
