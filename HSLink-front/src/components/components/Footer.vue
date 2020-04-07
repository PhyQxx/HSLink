<template>
  <div class="container">
    <el-main>
      <div class="list">
        <div class="one">
          <img src="../../assets/img/qq.png" />
          <span>QQ客服</span>
        </div>
        <div class="one">
          <i class="el-icon-s-promotion"></i>
          <span>{{emil}}</span>
        </div>
        <div class="one">
          <i class="el-icon-s-home"></i>
          <span>客服论坛</span>
        </div>
        <div class="one">
          <i class="el-icon-phone"></i>
          <span>150-0673-2580</span>
        </div>
        <p>工作时间 8:30-22:00</p>
        <a class="beian" rel="nofollow" href="http://www.beian.miit.gov.cn" target="_blank"><p>备案号：鲁ICP备19037910号-1</p></a>
      </div>
    </el-main>
  </div>
</template>

<script>
    export default {
        name: "Footer",
      data() {
          return{
            emil: "617594538@qq.com"
          }
      }
    }
</script>

<style scoped>
  .container{
    padding: 0;
    margin-top: 1rem;
  }
  .el-main{
    background: #fff;
  }
  .list p{
    color: #8590A6;
  }
  .one{
    height: 2rem;
    color: #8590A6;
    cursor: pointer;
  }
  .one img{
    margin-top: 0.5rem;
    height: 1rem;
    width: 1rem;
  }
  .one i{
    margin-top: 0.5rem;
  }
  .one span{
    position: absolute;
    margin-top: 0.3rem;
    margin-left: 0.5rem;
  }
  .beian{
    text-decoration: none;
  }
  .beian p{
    font-size: 0.9rem;
  }
</style>
