<template>
  <el-container>
    <el-main>
      <el-page-header @back="goBack" :content="pageTitle"></el-page-header>
      <div class="" v-if="editor">
        <div class="search">
          <el-input class="searchinp" v-model="condition" placeholder="请输入内容" @keydown.enter.native="search"></el-input>
          <el-button type="primary" class="searchbtn" @click="search">搜索</el-button>
        </div>
        <div class="list">
          <div class="one" v-for="item in list">
            <div class="type" @click="searchLabel(item.label)">
              [{{item.label}}]
            </div>
            <div class="text" @click="getContent(item.id)">{{item.title}}</div>
<!--            <div class="release">{{item.real_name}}</div>-->
            <div class="release-time">{{item.release_time}}</div>
            <div class="editorbtn" @click="editorContent(item.id)" v-if="isOwn">编辑</div>
            <div class="delete" @click="deleteMy(item.id)" v-if="isOwn">删除</div>
          </div>
          <p class="sum">共{{length}}条数据</p>
        </div>
      </div>
      <addArticle
        v-if="!editor"
        @editorTitle="editorTitle"
        @goInfo="goInfo"
        @refresh="refresh"
      ></addArticle>
    </el-main>
    <el-aside>
      <div class="person-info">
        <div class="info">
          <div class="name">
            <div class="header-photo">{{all.header_photo}}</div>
            <div class="username">{{all.real_name}}</div>
            <div class="go-homepage pointer" v-if="!isOwn">HSLink</div>
            <div class="privateLetter pointer" v-if="isOwn" @click="privateLetter">私信(未读:{{all.letter_number}})</div>
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
        <div class="operation" v-if="!isOwn">
          <el-button type="primary" plain size="mini" @click="follow">关 注</el-button>
          <el-button type="primary" plain size="mini" @click="sendLetter(all.user_id,all.real_name)">私 信</el-button>
        </div>
        <div class="operation" v-if="isOwn">
          <el-button type="primary" plain @click="goEditor" size="mini">发 表</el-button>
          <el-button type="primary" plain size="mini" @click="editorPassword">修改密码</el-button>
        </div>
      </div>
      <footers></footers>
    </el-aside>
    <el-dialog
      title="修改密码"
      :visible.sync="dialogVisible"
      width="30%">
      <el-form :model="editorPasswordForm"
               :rules="editorPasswordRules"
               ref="editorPasswordForm"
               label-width="100px"
               class="demo-ruleForm"
               close-on-click-modal=false
               label-position="left">
        <el-form-item label="原始密码：" prop="oldPassword">
          <el-input v-model="editorPasswordForm.oldPassword" placeholder="请输入原始密码" type="password"></el-input>
        </el-form-item>
        <el-form-item label="新密码：" prop="newPassword">
          <el-input v-model="editorPasswordForm.newPassword" placeholder="请输入新密码" type="password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码：" prop="againPassword">
          <el-input v-model="editorPasswordForm.againPassword" placeholder="请确认密码" type="password"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="editorSubmit">确 定</el-button>
  </span>
    </el-dialog>
    <el-drawer
      :visible.sync="drawer"
      :direction="direction"
      :show-close = false>
      <div class="privateLetterList">
        <div class="header">
          <div class="content">内容</div>
          <div class="name">姓名</div>
          <div class="time">发送时间</div>
        </div>
        <div class="unread">
          <p>未读：</p>
          <p v-if="unreadIsNull">暂无未读</p>
          <div class="privateLetterOne" v-for="item in privateLetterList" v-if="(item.already_read === '0')" >
            <div class="box" @click="read(item.id)">
              <div class="content">{{item.content}}</div>
              <div class="name">{{item.real_name}}</div>
              <div class="time">{{item.letter_create_time}}</div>
            </div>
            <div class="delete" @click="deleteLetter(item.id)">删除</div>
          </div>
        </div>
        <div class="already-read">
          <p>已读：</p>
          <p v-if="alreadyReadIsNull">暂无已读</p>
          <div class="privateLetterOne" v-for="item in privateLetterList" v-if="(item.already_read === '1')">
            <div class="box" @click="read(item.id)">
              <div class="content">{{item.content}}</div>
              <div class="name">{{item.real_name}}</div>
              <div class="time">{{item.letter_create_time}}</div>
            </div>
            <div class="delete" @click="deleteLetter(item.id)">删除</div>
          </div>
        </div>

      </div>
    </el-drawer>
  </el-container>
</template>

<script>
  import addArticle from './AddArticle'
  import footers from './components/Footer'
  import {getDate} from "../assets/js/public";
    export default {
        name: "PersonalInfo",
      data() {
        let againPassword = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请再次输入密码'));
          } else if (value !== this.editorPasswordForm.newPassword) {
            callback(new Error('两次输入密码不一致!'));
          } else {
            callback();
          }
        }
          return{
            unreadIsNull: true,
            alreadyReadIsNull: true,
            privateLetterList: '',
            editorPasswordRules: {
              oldPassword: [
                { required: true, message: '请输入原始密码', trigger: 'blur' },
                { min: 6, max: 10, message: '长度在 6 到 8 个字符', trigger: 'blur' }
              ],
              newPassword: [
                { required: true, message: '请输入新密码', trigger: 'blur' },
                { min: 6, max: 10, message: '长度在 6 到 8 个字符', trigger: 'blur' }
              ],
              againPassword: [
                { required: true, message: '请确认密码', trigger: 'blur' },
                { validator:againPassword, trigger: 'blur' }
              ],
            },
            editorPasswordForm: {
              oldPassword: '',
              newPassword: '',
              againPassword: ''
            },
            pageTitle: '个人中心',
            editor: true,
            condition: '',
            list: '',
            all: '',
            isOwn: true,
            length: '',
            dialogVisible: false,
            drawer: false,
            direction: 'rtl',
          }
      },
      mounted() {
        // setInterval(()=>{
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
          if (userInfo.user_id === sessionStorage.getItem("userId")) {
            this.isOwn = true
          } else {
            this.isOwn = false
          }
          this.$ajax.post("/hs/getPersonalInfo",{text:"",releaseId: sessionStorage.getItem("userId")},r=>{
            this.list = r.personalList;
            this.length = r.personalList.length;
            this.all = {
              real_name: r.personalInfo.real_name,
              header_photo: r.personalInfo.real_name.substring(0, 1),
              integral: r.personalInfo.integral,
              letter_number: r.personalInfo.letter_number,
              user_id: r.personalInfo.user_id,
            }
          })
        // },1000)
      },
      methods: {
        deleteLetter(id) {
          this.$confirm('删除该私信？, 是否继续?', '删除', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$ajax.post("/hs/deleteLetter",{id:id},r=>{
              if (r === 1) {
                let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
                this.$ajax.post("/hs/getPersonalPrivateLetter",{userId:userInfo.user_id},res=>{
                  this.privateLetterList = res
                  if (res.length === 0) {
                    this.unreadIsNull = true;
                    this.alreadyReadIsNull = true;
                  }
                  for (let i = 0; i < res.length; i++) {
                    debugger
                    if (res[i].already_read === "0") {
                      this.unreadIsNull = false
                    } else {
                      this.unreadIsNull = true
                    }
                    if (res[i].already_read === "1") {
                      this.alreadyReadIsNull = false
                    } else {
                      this.unreadIsNull = true
                    }
                  }
                })
                this.$message({
                  type: 'success',
                  message: '删除成功'
                });
              } else {
                this.$message.error("删除失败")
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
        read(id) {
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
          this.$ajax.post("/hs/readPrivateLetter",{userId:userInfo.user_id,id:id},r=>{
            this.$confirm(`<p style="font-size: 0.9rem">发送者：${r.onePrivateLetter[0].real_name}</p>
                  <p style="font-size: 0.9rem">内容：${r.onePrivateLetter[0].content}</p>
                  <p style="font-size: 0.9rem">发送时间：${r.onePrivateLetter[0].letter_create_time}</p>
                `, '私信', {
              cancelButtonText: '回复',
              dangerouslyUseHTMLString: true
            }).catch(() => {
              this.sendLetter(r.onePrivateLetter[0].user_id,r.onePrivateLetter[0].real_name)
            });
            this.$ajax.post("/hs/getPersonalPrivateLetter",{userId:userInfo.user_id},r=>{
              this.privateLetterList = r
              for (let i = 0; i < r.length; i++) {
                if (r[i].already_read === "0") {
                  this.unreadIsNull = false
                } else {
                  this.unreadIsNull = true
                }
                if (r[i].already_read === "1") {
                  this.alreadyReadIsNull = false
                } else {
                  this.unreadIsNull = true
                }
              }
            })
            this.$ajax.post("/hs/getPersonalInfo",{text:"",releaseId: sessionStorage.getItem("userId")},r=>{
              this.list = r.personalList;
              this.length = r.personalList.length;
              this.all = {
                real_name: r.personalInfo.real_name,
                header_photo: r.personalInfo.real_name.substring(0, 1),
                integral: r.personalInfo.integral,
                letter_number: r.personalInfo.letter_number,
                user_id: r.personalInfo.user_id,
              }
            })
          })
        },
        privateLetter() {
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
          this.drawer = true;
          this.$ajax.post("/hs/getPersonalPrivateLetter",{userId:userInfo.user_id},r=>{
            this.privateLetterList = r
            for (let i = 0; i < r.length; i++) {
              if (r[i].already_read === "0") {
                this.unreadIsNull = false
              }
              if (r[i].already_read === "1") {
                this.alreadyReadIsNull = false
              }
            }
          })
        },
        editorSubmit() {
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
          if (this.editorPasswordForm.oldPassword === userInfo.pass_word) {
            if (this.editorPasswordForm.againPassword === this.editorPasswordForm.newPassword) {
              this.$ajax.post("/hs/updatePassword",{password:this.editorPasswordForm.againPassword,id:userInfo.user_id},r=>{
               if (r === 1) {
                 this.$message({
                     type:"success",
                      message: "修改密码成功，3秒后重新登录"
                    })
                 const loading = this.$loading({
                   lock: true,
                   text: 'Loading',
                   spinner: 'el-icon-loading',
                   background: 'rgba(0, 0, 0, 0.7)'
                 });
                 this.dialogVisible = false
                 sessionStorage.setItem("userInfo","");
                 setTimeout(()=>{
                   loading.close();
                   this.$router.push({name:"login"})
                 },3000)
               } else {
                 this.$message.error("修改密码失败")
               }
              })
            }
          } else {
            this.$message.error("原始密码不正确")
            this.editorPasswordForm = {};
          }
        },
        editorPassword() {
          this.dialogVisible = true
        },

        follow() {
          this.$message({
            type:"success",
            message: "喜欢就好"
          })
        },
        refresh() {
          this.$ajax.post("/hs/getListByAttribute",{text:"",releaseId: sessionStorage.getItem("userId")},r=>{
            this.list = r;
            this.length = r.length;
            this.all = {
              real_name: r[0].real_name,
              header_photo: r[0].real_name.substring(0, 1),
              integral: r[0].integral
            }
          })
        },
        editorTitle(pageTitle) {
          this.pageTitle = pageTitle
        },
        goBack() {
          this.$router.back(-1)
        },
        goEditor() {
          this.editor = false;
          sessionStorage.setItem("releaseType","")
        },
        goInfo() {
          this.editor = true
        },
        searchLabel(label) {
          sessionStorage.setItem("condition",label)
          this.$router.push({name: "search"})
        },
        search() {
          sessionStorage.setItem("condition",this.condition)
          this.$ajax.post("/hs/getListByAttribute",{text:this.condition,releaseId: sessionStorage.getItem("userId")},r=>{
            this.list = r
            this.length = r.length
          })
        },
        getContent(id) {
          this.$router.push({name: 'content'});
          sessionStorage.setItem("noticeId",id);
        },
        editorContent(id) {
          this.$router.push({name: 'editorcontent'});
          sessionStorage.setItem("noticeId",id);
        },
        deleteMy(id) {
          this.$confirm('确定删除该条数据, 是否继续?', '删除', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$ajax.post("/hs/deleteOne",{id:id},r=>{
             if (r === 1) {
               this.$message({
                 type: 'success',
                 message: '删除成功!'
               });
               this.$ajax.post("/hs/getListByAttribute",{text:"",releaseId: sessionStorage.getItem("userId")},r=>{
                 this.list = r;
                 this.length = r.length;
                 this.all = {
                   real_name: r[0].real_name,
                   header_photo: r[0].real_name.substring(0, 1),
                   integral: r[0].integral
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
        }
      },
      components: {
        addArticle,
        footers
      }
    }
</script>

<style scoped>
  .privateLetterList .delete{
    color: red;
    flex: 0.5;
  }
  .privateLetterList{
    padding: 1rem;
    margin-top: -1rem;
  }
  .already-read{
    margin-top: 1rem;
    padding: 0 1rem;
  }
  .already-read p:nth-child(1){
    font-size: 1.2rem;
    font-weight: bold;
    color: #419EFF;
    margin: 0.5rem 0 0 0;
  }
  .already-read p:nth-child(2), .unread p:nth-child(2){
    color: #419EFF;
    text-align: center;
  }
  .unread{
    color: red;
    margin-top: 1rem;
    padding: 0 1rem;
  }
  .unread p:nth-child(1){
    font-size: 1.2rem;
    font-weight: bold;
    color: red;
    margin: 0.5rem 0 0 0;
  }
  .header{
    display: flex;
    padding: 0.5rem;
    margin: 0.5rem;
    border: 1px solid #419EFF;
    border-radius: 5px;
  }
  .header div{
    text-align: center;
  }
  .header .content{
    flex: 3;
  }
  .header .name{
    flex: 1;
  }
  .header .time{
    flex: 2;
  }
  .privateLetterOne{
    padding: 0.5rem;
    /*margin: 0.5rem;*/
    display: flex;
    border-bottom: 1px solid;
    font-size: 0.9rem;
    cursor: pointer;
  }
  .box{
    display: flex;
    flex: 8;
  }
  .box:hover{
    color: #419EFF;
  }
  .privateLetterOne div{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .privateLetterOne .content{
    flex: 3;
  }
  .privateLetterOne .name{
    flex: 1;
    text-align: center;
  }
  .privateLetterOne .time{
    flex: 2;
    text-align: center;
  }
  .el-container{
    margin: 1rem;
  }
  .el-page-header{
    padding: 1rem;
  }
  .search{
    padding: 1rem;
    margin-bottom: 2rem;
  }
  .searchinp{
    float: left;
    width: calc(100% - 6rem);

  }
  .searchbtn{
    float: left;
    width: 6rem;
  }
  .el-aside{
    margin-left: 1rem;
  }
  .el-main{
    padding: 0;
  }
  .sum{
    color: #999;
    font-size: 0.9rem;
    float: right;
    margin-right: 1rem;
  }
  .el-main{
    background: #fff;
  }
  .list{
    background: #fff;
    border-radius: 5px;
    padding: 1rem 1rem 3rem 1rem;
  }
  .list .one{
    display: flex;
    height: 1.5rem;
    line-height: 1.5rem;
    margin-bottom: 0.5rem;
    font-size: 0.9rem;
  }
  .one div{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    cursor: pointer;
    color: #1B94D4;
  }
  .one div:hover{
    text-decoration: underline;
  }
  .release-time{
    color: #999!important;
    text-decoration: none!important;
    cursor: default!important;
  }
  .one .type{
    flex: 1;
  }
  .one .text{
    flex: 4;
  }
  .one .release{
    flex: 1;
  }
  .one .release-time{
    flex: 2;
  }
  .one .editorbtn{
    flex: 0.5;
  }
  .one .delete{
    flex: 0.5;
  }
  .operation .el-button:nth-child(1){
    /*margin-left: 1.5rem;*/
  }
  .operation .el-button:nth-child(2){
    /*margin-right: 1.5rem;*/
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
  .person-info{
    background: #fff;
    padding: 1rem;
  }
  .username{
    margin-left: 3.5rem;
    font-size: 0.9rem;
  }
  .privateLetter{
    font-size: 0.9rem;
    color: #3399ea;
    margin-left: 3.5rem;
    margin-top: 0.5rem;
  }
  .go-homepage{
    height: 0.9rem;
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
  .el-aside{
    width: 14rem!important;
    overflow: hidden;
  }
</style>
