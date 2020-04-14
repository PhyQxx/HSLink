<template>
  <div class="container">
    <el-main>
      <div class="list">
        <div class="one" @click="service">
          <img src="../../assets/img/qq.png" />
          <span>QQ客服</span>
        </div>
        <div class="one" >
          <a class="emil" rel="nofollow" href="https://mail.qq.com/" target="_blank">
            <i class="el-icon-s-promotion"></i>
            <span>{{emil}}</span>
          </a>
        </div>
        <div class="one" @click="serviceBlog">
          <i class="el-icon-s-home"></i>
          <span>客服论坛</span>
        </div>
        <div class="one">
          <i class="el-icon-phone"></i>
          <span>150-0673-2580</span>
        </div>
        <p>工作时间 8:30-22:00</p>
        <a class="beian" rel="nofollow" href="http://www.beian.miit.gov.cn" target="_blank">
          <p>备案号：</p>
          <p>鲁ICP备19037910号-1</p>
        </a>
      </div>
    </el-main>
    <el-dialog
      title="QQ客服"
      :visible.sync="dialogVisible"
      width="30%">
      <img class="QQservice" src="../../assets/img/QQservice.png" alt="" size="80%">
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "Footer",
      data() {
          return{
            dialogVisible: false,
            emil: "617594538@qq.com"
          }
      },
      methods:{
        service() {
          this.dialogVisible = true
        },
        serviceBlog() {
          this.$alert('客服论坛建设中', '客服论坛', {
            confirmButtonText: '确定',
          });
        },
      },
    }
</script>

<style scoped>
  .QQservice{
    height: 20rem;
    margin: 0 auto;
    display: block;
  }
  .emil{
    color: #8590A6;
  }
  .container{
    padding: 0;
    margin-top: 1rem;
  }
  .el-main{
    background: #fff;
    overflow: inherit!important;
  }
  .list{
    font-size: 0.9rem;
  }
  .list p{
    color: #8590A6;
    margin: 0.2rem 0;
  }
  .one{
    height: 1.5rem;
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
    width: 10rem;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
  }
  .beian{
    text-decoration: none;
  }
  .beian p{
    font-size: 0.9rem;
  }
</style>
