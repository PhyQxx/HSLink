<template>
  <el-container>
    <el-main>
      <div class="search">
        <el-input class="searchinp" v-model="condition" placeholder="请输入内容" @keydown.enter.native="search"></el-input>
        <el-button type="primary" class="searchbtn" @click="search">搜索</el-button>
      </div>
      <div class="class-bulletin">
        <div class="title">班级公告</div>
        <div class="bulletin">
          <div class="text">
            <div class="tit">{{bulletin.title}}</div>
            <p>{{bulletin.content}}</p>
          </div>
          <el-button class="editor" @click="editor">编辑</el-button>
        </div>
      </div>
      <div class="list">
        <div class="title">班级通知</div>
        <div class="one" v-for="item in noticeList" >
          <div class="type">[{{item.label}}]</div>
          <div class="text" @click="getContent(item.id)">{{item.title}}</div>
          <div class="release" @click="goPersonalInfo(item.user_id)">{{item.real_name}}</div>
          <div class="release-time">{{item.release_time}}</div>
        </div>
        <p class="sum">共{{length}}条数据</p>
      </div>
    </el-main>
    <el-aside>
      <div class="title">班级成员</div>
      <div class="students">
        <div class="student" v-for="item in students">
          <div class="header pointer">{{item.real_name.substring(0,1)}}</div>
          <div class="name pointer special-font-blue" @click="goPersonalInfo(item.user_id)">{{item.real_name}}</div>
          <div class="post">{{item.post}}</div>
        </div>
      </div>
    </el-aside>
    <el-dialog
      title="编辑公告"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <el-form>
        <el-form-item label="标题">
          <el-input v-model="editors.title" clearable size="small"></el-input>
        </el-form-item>
      </el-form>
      <el-input
        type="textarea"
        :rows="5"
        placeholder="请输入内容"
        v-model="editors.content">
      </el-input>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="bulletinSubmit">确 定</el-button>
  </span>
    </el-dialog>
  </el-container>

</template>

<script>
  import  { getDate } from  '../assets/js/public.js'
    export default {
        name: "MyClass",
      data() {
          return{
            condition: '',
            editors: {
              title: '',
              content: '',
            },
            bulletin: {
              title: '班主任和班长可编辑班级公告',
              content: '',
            },
            noticeList: '',
            length: '',
            students: '',
            isNull: false,
            dialogVisible: false,
          }
      },
      mounted() {
        let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
        this.$ajax.post("/hs/getClassInfo",{id:userInfo.user_id},r=>{
          if (r.bulletin == null) {
            this.isNull = true;
          } else {
            this.bulletin = r.bulletin;
          }
          this.noticeList = r. noticeList;
          this.length = r. noticeList.length;
          this.students = r.students;
        })
      },
      methods: {
        bulletinSubmit() {
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
          if (this.isNull === false) {
            this.$ajax.post("/hs/updateBulletin",{classId:userInfo.class_id,
              className:userInfo.class_name,title:this.editors.title,content:this.editors.content,
            releaseTime:getDate(),id:this.bulletin.id},r=>{
              if (r === 1) {
                let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
                this.$ajax.post("/hs/getClassInfo",{id:userInfo.user_id},r=>{
                  this.bulletin = r.bulletin;
                })
                this.$message({
                  type:'success',
                  message: '编辑公告成功'
                })
              }
            })
          } else {
            this.$ajax.post("/hs/addBulletin",{classId:userInfo.class_id,
              className:userInfo.class_name,title:this.editors.title,content:this.editors.content,
              releaseTime:getDate()},r=>{
              if (r === 1) {
                let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
                this.$ajax.post("/hs/getClassInfo",{id:userInfo.user_id},r=>{
                  this.bulletin = r.bulletin;
                })
                this.$message({
                  type:'success',
                  message: '编辑公告成功'
                })
              }
            })
          }
          this.dialogVisible = false
        },
        handleClose(done) {
          this.$confirm('确认关闭？')
            .then(_ => {
              done();
            })
            .catch(_ => {});
        },
        editor() {
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
          if (userInfo.user_type === "教师") {
            this.dialogVisible = true;
            this.editors.title = this.bulletin.title;
            this.editors.content = this.bulletin.content;
          } else {
            this.$message.error("没有权限编辑公告")
          }
        },
        search() {
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
          sessionStorage.setItem("condition",this.condition)
          this.$ajax.post("/hs/getClassInfo",{id:userInfo.user_id,text:this.condition},r=>{
            this.noticeList = r. noticeList
            this.length = r. noticeList.length
          })
        },
        getContent(id) {
          this.$router.push({name: 'content'});
          sessionStorage.setItem("noticeId",id);
        },
        goPersonalInfo(userId) {
          this.$router.push({name: 'personalinfo'});
          sessionStorage.setItem("userId",userId);
        },
      }
    }
</script>

<style scoped>

  .text .tit{
    text-align: center;
    font-size: 1.2rem;
    font-weight: bold;
  }
  .text p{
    text-indent: 2rem;
  }
  .editor{
    float: right;
    height: 2.5rem;
  }
  .class-bulletin{
    background: #fff;
    margin-bottom: 1rem;
    border-radius: 5px;
  }
  .class-bulletin .title{
    padding: 1rem;
    font-size: 1.5rem;
  }
  .list .title{
    padding-bottom: 1rem;
    font-size: 1.5rem;
  }
  .class-bulletin .bulletin{
    padding: 1rem 1rem 3rem 1rem;
    border-radius: 5px;
    border: 1px solid #419EFF;
  }
  .sum{
    color: #999;
    font-size: 0.9rem;
    float: right;
    margin-right: 1rem;
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
    text-decoration: none!important;
    cursor: default!important;
  }
  .one .text{
    flex: 4;
  }
  .one .release{
    flex: 1;
  }
  .one .release-time{
    flex: 1;
  }
  .search{
    margin: 0 0 3rem 0;
  }
  .searchinp{
    float: left;
    width: calc(100% - 6rem);
  }
  .searchbtn{
    float: left;
    width: 6rem;
  }
  .el-main{
    padding: 0;
    background: #EEFCFE;
  }
  .el-aside{
    width: 10rem!important;
    background: #fff;
  }
  .el-aside .title{
    padding: 1rem;
    font-size: 1.5rem;
    text-align: center;
  }
  .students .student{
    padding: 0 1rem;
    font-size: 0.8rem;
    display: flex;
    height: 1.5rem;
    margin-bottom: 0.5rem!important;
    margin: 0 auto;
    display: flex;
  }
  .student .header{
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
  .student .name{
    line-height: 1.5rem;
    margin-left: 0.5rem;
    flex: 1;
  }
  .student .post{
    line-height: 1.5rem;
    margin-left: 0.5rem;
    cursor: default;
    flex: 1;
  }
  .el-aside{
    margin-left: 1rem;
  }
</style>
