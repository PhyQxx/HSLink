<template>
  <el-container>
    <el-main>
      <div class="content">
        <el-page-header @back="goBack" content="">
        </el-page-header>
        <div class="header">
          <h1>{{all.title}}</h1>
          <span>作者：</span>
          <span class="blue pointer authorspan" @click="goPersonalInfo(all.author_id)">{{all.author_name}}</span>
          <span class="margin-right">发布时间：</span>
          <span class="grey">{{all.create_time}}</span>
          <div class="label">
            <span>文章标签：</span>
            <span class="special-text-yellow blue pointer" @click="searchLabel(all.label)">{{all.label}}</span>
          </div>
        </div>
        <div class="middle">
          <div class="editorContainer">
            <markdown
              :mdValuesP="msg.mdValue"
              :fullPageStatusP="false"
              :editStatusP="true"
              :previewStatusP="true"
              :navStatusP="true"
              :icoStatusP="true"
              @childevent="childEventHandler"
              ref="markdown"
            ></markdown>
          </div>
        </div>
        <div class="footer">
          <el-row>
            <el-button type="primary" icon="el-icon-thumb" size="mini" plain @click="follow">点 赞</el-button>
            <el-button type="primary" icon="el-icon-share" size="mini" plain @click="follow">分 享</el-button>
          </el-row>
        </div>
      </div>
      <div class="comment-area">
        <el-button class="message" type="primary" @click="messages">留言</el-button>
        <div class="comment-list">
          <div class="comment-one" v-for="(item,index) in message">
            <div class="comment-header">
              <div class="header-photo">{{item.header_photo}}</div>
              <div class="name">{{item.real_name}}</div>
              <div class="time">{{item.create_time}}</div>
              <span class=" delete pointer blue delete-my" v-if="(userId == item.messager_id)" @click="deleteMy(item.id)">删除</span>
            </div>
            <div class="comment-content">{{item.content}}</div>
            <hr>
          </div>
        </div>
      </div>
    </el-main>
    <el-aside>
      <div class="person-info">
        <div class="info">
          <div class="name">
            <div class="header-photo">{{all.header_photo}}</div>
            <div class="username" >{{all.author_name}}</div>
            <div class="go-homepage pointer" @click="goPersonalInfo(all.author_id)">TA的个人主页 ></div>
          </div>
          <div class="grades">
            <div class="grade">
              <span>等级:</span>
              <div class="number">
                <span>博客</span>
                <span>{{Math.floor(all.integral/1000)+1}}</span>
              </div>
            </div>
            <div class="integral">
              <span>积分: </span>
              <span>{{all.integral}}</span>
            </div>
          </div>
        </div>
        <hr>
        <div class="operation">
          <el-button type="primary" plain size="mini" @click="follow">关 注</el-button>
          <el-button type="primary" plain size="mini" @click="sendLetter(all.author_id,all.author_name)">私 信</el-button>
        </div>
      </div>
      <footers></footers>
    </el-aside>
  </el-container>
</template>

<script>
  import  { getDate } from  '../assets/js/public.js'
  import footers from './components/Footer'
  import markdown from "./components/Mdeditor";
    export default {
      name: "Content",
      data() {
        return {
          all: "",
          message: '',
          id:'',
          msgShow:'我要显示的内容',
          dilogStatus:false,
          msg: {
            mdValue:''
          },
          flag:false,
          userId: JSON.parse(sessionStorage.getItem("userInfo")).user_id
        }
      },
      created() {
      },
      mounted() {
        this.$ajax.post("/hs/getOneContent", {
          id: sessionStorage.getItem("noticeId")
        }, r => {
          this.all = r.content;
          this.message = r.message;
          this.msg.mdValue = r.content.content;
          this.all.header_photo = this.all.author_name.substring(0, 1);
          for (let i = 0; i < this.message.length; i++) {
            this.message[i].header_photo = this.message[i].real_name.substring(0, 1);
          }
          setTimeout(()=>{this.$refs.markdown.addLine()},1)
        })
      },
      methods: {
        deleteMy(id) {
          this.$confirm('确定删除该条留言, 是否继续?', '删除', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$ajax.post("/hs/deleteOneMessage",{id:id},r=>{
              if (r === 1) {
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                this.$ajax.post("/hs/getOneContent", {
                  id: sessionStorage.getItem("noticeId")
                }, r => {
                  this.message = r.message;
                  for (let i = 0; i < this.message.length; i++) {
                    this.message[i].header_photo = this.message[i].real_name.substring(0, 1)
                  }
                })
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        sendLetter(id,name) {
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
          this.$prompt('接受者：'+name, '发送私信', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern: /\S/,
            inputErrorMessage: '内容不能为空'
          }).then(({ value }) => {
            this.$ajax.post("/hs/sendLetter",{sendId:userInfo.user_id,receiveId:id,content:value,time:getDate()},r=>{
              if (r === 1) {
                this.$message({
                  type: 'success',
                  message: '发送成功'
                });
              } else {
                this.$message.error("发送失败")
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消输入'
            });
          });
        },
        follow() {
          this.$message({
            type:"success",
            message: "喜欢就好"
          })
        },
        searchLabel(label) {
          sessionStorage.setItem("condition",label)
          this.$router.push({name: "search"})
        },
        goPersonalInfo(userId) {
          this.$router.push({name: 'personalinfo'});
          sessionStorage.setItem("userId",userId);
        },
        goBack() {
          this.$router.back(-1)
        },
        messages() {
          this.$prompt('请输入留言', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputValidator:  (value) => {
              if(!value) {
                return '输入不能为空';
              }
            },
            inputErrorMessage: '不能为空'
          }).then(({ value }) => {
            this.$ajax.post("/hs/addMessage",{noticeId:sessionStorage.getItem("noticeId"),content:value,
              createTime:getDate(),userId:JSON.parse(sessionStorage.getItem("userInfo")).user_id},r=>{
              if (r === 1) {
                this.$message({
                  type: 'success',
                  message: '留言成功'
                });
                this.$ajax.post("/hs/getOneContent", {
                  id: sessionStorage.getItem("noticeId")
                }, r => {
                  this.message = r.message;
                  for (let i = 0; i < this.message.length; i++) {
                    this.message[i].header_photo = this.message[i].real_name.substring(0, 1)
                  }
                })
              }
            });
            this.$ajax.post("/hs/timingTask",{userId:JSON.parse(sessionStorage.getItem("userInfo")).user_id},r=>{
              console.log(r)
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消输入'
            });
          });
        },
        childEventHandler:function(res){
          // res会传回一个data,包含属性mdValue和htmlValue，具体含义请自行翻译
          this.msg=res;
        },
        closeMaskFn:function(){
          this.msgShow='';
          this.dilogStatus=false;
        },
        handleChange(val) {
          console.log(val);
        },
      },
      components: {
        footers,
        markdown
      },
    }
</script>
<style lang="scss" scoped>
  .comment-one:hover .delete-my{
    display: inline-block;
  }
  .delete{
    height: 1.5rem;
    line-height: 1.5rem;
    margin-left: 0.2rem;
    display: none;
  }
  .header h1{
    font-size: 1.5rem;
  }
  .el-container{
    margin: 1rem;
  }
  .margin-right{
    margin-left: 1rem;
  }
  .message{
    margin: 1rem 1rem 0 0;
    float: right;
  }
  .mdContainer >>> .editContainer,.mdContainer >>> .navContainer{
    display: none!important;
  }
  .list{
    .title{
      padding-left: 1rem;
      height: 3rem;
      line-height: 3rem;
      background: #999;
      color: #fff;
    }
    background: #fff;
    ul{
      margin: 0;
      list-style: none;
      li{
        padding: 0.5rem 0;
        }
      }
  }
  .aside-right{
    margin-left: 1rem;
  }
  .authorspan{
    /*float: right;*/
    /*margin-left: 2.5rem;*/
  }
  .navContainer{
    display: none!important;
  }
  .comment-content{
    margin: 0.5rem 0 0 2rem;
  }
  .reply{
    display: inline-block;
    float: right;
    font-size: 0.9rem;
    line-height: 1.5rem;
  }
  .comment-one{
    padding: 1rem 1rem 0 1rem;
  }
  .comment-header{
    display: flex;
  }
  .comment-header .name{
    font-weight: bold;
  }
  .comment-header .name, .comment-header .time{
    line-height: 1.5rem;
  }
  .comment-one div{
    margin-right: 0.5rem;
  }
  .comment-one .header-photo{
    height: 1.5rem;
    width: 1.5rem;
    line-height: 1.5rem;
    font-weight: bold;
    border-radius: 50%;
    background: #3399ea;
    color: #fff;
    text-align: center;
    float: left;
    font-size: 1rem;
  }
  .comment-area{
    margin-top: 1rem;
    background: #fff;
    min-height: 4.5rem;
  }
  .achievement{
    font-size: 0.9rem;
    margin-left: 3.5rem;
    margin-top: 0.5rem;
  }
  .content .middle{
    margin: 1rem 0;
  }
  .type span:nth-child(2){
     background: #F5F6F7;
    font-size: 0.9rem;
   }
  .label span:nth-child(n+2){
    background: #F5F6F7;
    font-size: 0.9rem;
  }
  .original1{
    position: absolute;
    color: #ca0c16;
    background: #f9ecec;
    font-size: 0.9rem;
    margin-right: 1rem;
  }
  .reprint{
    color: #74cf59;
    background: #eaf9e3;
    font-size: 0.9rem;
    margin-right: 1rem;
  }
  .header span:nth-child(-n+4){
    /*margin-right: 0.5rem;*/
  }
  .content{
    padding: 1rem;
    background: #fff;
  }
  .content .header .label{
    margin-top: 0.5rem;
  }
  .operation .el-button:nth-child(1){
    /*margin-left: 2rem;*/
  }
  .operation .el-button:nth-child(2){
    /*margin-right: 2rem;*/
    float: right;
  }
  .integral span:nth-child(2)，.ranking span:nth-child(2){
    font-weight: bold;
  }
  .number{
    font-size: 0.8rem;
    color: #fff;
    border-radius: 5px;
    position: absolute;
    margin-left: 2.6rem;
    margin-top: -1.1rem!important;
  }
  .number span:nth-child(1){
    background: #9DC75F;
    float: left;
  }
  .number span:nth-child(2){
    background: #2D5315;
    float: left;
    min-width: 1rem;
    text-align: center;

  }
  .grades{
    margin-top: 1rem;
  }
  .grades div{
    margin-top: 0.5rem;
  }
  .read{
    margin: 0.5rem 0;
  }
  .read div{
    flex: 1;
    text-align: center;
    width: 20%;
  }
  .read div p:nth-child(2){
    overflow: hidden;
    text-overflow: ellipsis;
    font-weight: bold;
  }
  .person-info{
    background: #fff;
    padding: 1rem;
  }
  .username{
    margin-left: 3.5rem;
    font-size: 0.9rem;
  }
  .go-homepage{
    font-size: 0.9rem;
    color: #3399ea;
    margin-left: 3.5rem;
    margin-top: 0.5rem;
  }
  .header-photo{
    height: 3rem;
    width: 3rem;
    line-height: 3rem;
    font-weight: bold;
    border-radius: 50%;
    background: #3399ea;
    color: #fff;
    text-align: center;
    float: left;
    font-size: 1.5rem;
    margin-right: 1rem;
  }
  .el-container{
    padding: 0;
  }
  .el-aside{
    /*margin-top: 1rem;*/
    width: 14rem!important;
  }
  .el-main{
    padding: 0;
    margin-right: 1rem;
  }
  .read{
    display: flex;
  }
</style>
