<template>
  <el-container>
    <el-main>
      <el-page-header @back="goBack" content="发布班级通知" v-if="addClassNotice"/>
      <addArticle
        v-if="addClassNotice"
        @editorTitle="editorTitle"
        @goInfo="goInfo"
        @refresh="refresh"
      />
      <div class="search" v-if="!addClassNotice">
        <el-input class="searchinp" v-model="condition" placeholder="请输入内容" @keydown.enter.native="search"/>
        <el-button type="primary" class="searchbtn" @click="search">搜索</el-button>
      </div>
      <div class="class-bulletin" v-if="!addClassNotice">
        <div class="title">班级公告</div>
        <div class="bulletin">
          <div class="text">
            <div class="tit">{{bulletin.title}}</div>
            <p>{{bulletin.content}}</p>
          </div>
          <el-button class="editor" @click="editor">编辑</el-button>
        </div>
      </div>
      <div class="list" v-loading="loading">
        <div class="title">班级通知</div>
        <el-button class="releasebut" size="small" @click="release" plain>发布通知</el-button>
        <nodate v-if="noDate"/>
        <div class="list-in-box" v-if="!addClassNotice && !noDate">
          <div class="one" v-for="item in noticeList">
            <div class="type">[{{item.label}}]</div>
            <div class="text" @click="getContent(item.id)">{{item.title}}</div>
            <div class="release" @click="goPersonalInfo(item.user_id)">{{item.real_name}}</div>
            <div class="release-time">{{item.release_time}}</div>
          </div>
          <p class="sum" v-if="!noDate">共{{length}}条数据</p>
        </div>
      </div>
    </el-main>
    <el-aside>
      <div class="title">班级成员 <span v-if="userInfo.post==='班主任'" class="admin" @click="drawer=true">管理</span></div>
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
      width="30%">
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
    <el-drawer
      :title="userInfo.class_name"
      :visible.sync="drawer"
      custom-class="class-table-box"
      direction="ltr">
      <div class="class-table" style="padding: 1rem;">
        <el-table :data="students">
          <el-table-column label="序号" width="80" align="center">
            <template slot-scope="scope">
              <span>{{scope.$index + 1}}</span>
            </template>
          </el-table-column>
          <el-table-column label="学号" align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.post==='班主任'">{{scope.row.student_number}}</span>
              <el-input v-if="scope.row.post!=='班主任'"
                        size="mini"
                        @change="editStudentNumber($event, scope.row)"
                        v-model="scope.row.student_number"
                        placeholder="请输入学号"/>
            </template>
          </el-table-column>
          <el-table-column label="姓名" align="center" prop="real_name" />
          <el-table-column label="职务" align="center" prop="post" >
            <template slot-scope="scope">
              <span v-if="scope.row.post==='班主任'">{{scope.row.post}}</span>
              <el-select v-if="scope.row.post!=='班主任'"
                         v-model="scope.row.post"
                         filterable
                         size="mini"
                         allow-create
                         default-first-option
                         @change="changePost($event, scope.row)"
                         placeholder="请选择职务">
                <el-option
                  v-for="item in postList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="手机号" align="center" prop="mobile" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div style="display:flex;justify-content: flex-end;margin: 1rem">
          <el-button type="primary" @click="addStudent">添加学生</el-button>
        </div>
      </div>
    </el-drawer>
    <el-dialog
      title="添加学生"
      :visible.sync="addStudentFlag"
      width="50%">
      <div class="student-list">
        <div class="button">
          <el-button type="primary" @click="registerFlag=true">添加学生</el-button>
        </div>
        <div class="search">
          <el-input v-model="searchCode" placeholder="请输入学生姓名" @change="getNoClassStudent"/>
        </div>
        <el-table
          :data="noClassStudent"
          style="width: 100%">
          <el-table-column label="姓名" align="center" prop="real_name" />
          <el-table-column label="性别" align="center" >
            <template slot-scope="scope">
              <span>{{scope.row.sex || '未填写'}}</span>
            </template>
          </el-table-column>
          <el-table-column label="年级" align="center" prop="grade" />
          <el-table-column label="手机号" align="center" prop="mobile" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleAdd(scope.row)"
              >添加</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-dialog>
    <el-dialog
      title="学生注册"
      :visible.sync="registerFlag"
      width="40%">
      <el-form :model="registerForm"
               :rules="registerRules"
               ref="form1"
               label-width="80px"
               class="demo-ruleForm"
               close-on-click-modal=false
               label-position="left">
        <el-form-item label="姓名" prop="realname">
          <el-input v-model="registerForm.realname" type="text"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="registerForm.mobile" type="text"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="registerStudent">确 定</el-button>
      </span>
    </el-dialog>
  </el-container>
</template>

<script>
  import  { register, getPostList } from  '@/api/index/user'
  import nodate from './components/NoData'
  import addArticle from './AddArticle'

  export default {
        name: "MyClass",
      data() {
          return{//加载标志
            loading: false,
            registerForm: {
              type: '学生',
              realname: '',
              mobile: '',
              username: '',
              password: '123456',
              takeEffect: '1'
            },
            registerRules: {
              realname: [
                { required: true, message: '请输入真实姓名', trigger: 'blur' },
                { min: 2, max: 10, message: '请输入正确的姓名', trigger: 'blur' }
              ],
              mobile: [
                { required: true, message: '请输入手机号', trigger: 'blur' },
                { min: 11, max: 11, message: '手机号格式不正确', trigger: 'blur' }
              ],
            },
            //注册标志
            registerFlag: false,
            //搜索关键字
            searchCode: '',
            //添加学生标志
            addStudentFlag: false,
            //职务列表
            postList: [
              {label: '班长', value: '班长'},
              {label: '团支书', value: '团支书'},
              {label: '副班长', value: '副班长'},
            ],
            //学生管理标志
            drawer: false,
            userInfo: JSON.parse(sessionStorage.getItem("userInfo")),
            addClassNotice: false,
            noDate: false,
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
            students: [],
            //未加入班级学生
            noClassStudent: [],
            isNull: false,
            dialogVisible: false,
          }
      },
      mounted() {
        this.getPostList();
        this.getPageDate()
      },
    watch: {
      addClassNotice(oldValue, newValue) {
        if (newValue === true) {
          this.getPageDate()
        }
      }
    },
      methods: {
          /**
           * 获取职务列表
           */
        getPostList() {
            getPostList({className: this.userInfo.class_name}).then(res => {
              if (res.data.data.length > 0) {
                res.data.data.forEach(item => {
                  if (!(item.post === '班长' || item.post === '副班长'  || item.post === '团支书')) {
                    this.postList.push({
                      label: item.post, value: item.post
                    })
                  }
                })
              }
              this.postList = this.postList.filter(post => {
                return post.label !== '班主任'
              });
              let obj = {};
              this.postList = this.postList.reduce((item, next) => {
               if (!obj[next.label]) {
                 obj[next.label] = item.push(next)
               }
                return item;
              }, [])
            })
        },
          /**
           * 注册学生
           */
        registerStudent() {
            register(this.registerForm).then(res => {
              if (res.data.data > 0) {
                this.getNoClassStudent();
                this.registerForm = {
                  type: '学生',
                    realname: '',
                    mobile: '',
                    username: '',
                    password: '123456',
                    takeEffect: '1'
                };
                this.registerFlag = false
              }
            })
        },
          /**
           * 修改学生学号
           */
        editStudentNumber(studentNumber, row) {
          if (studentNumber === '') {
            this.$notify.error('学号不能为空！')
          } else {
            this.$ajax.post('/hs/updatePersonalInfo',
              {studentNumber: studentNumber, userId: row.user_id}, res => {
                if (res.data > 0) {
                  this.$notify.success('修改成功');
                  this.getPageDate()
                } else {
                  this.$notify.error('网络异常，请刷新页面重新尝试')
                }
              })
          }
        },
          /**
           * 将学生加入该班级
           */
        handleAdd(row) {
            this.$ajax.post('/hs/updatePersonalInfo',
              {className: this.userInfo.class_name, userId: row.user_id}, res => {
                if (res.data > 0) {
                  this.addStudentFlag = false;
                  this.$notify.success('添加成功');
                  this.getPageDate()
                } else {
                  this.$notify.error('网络异常，请刷新页面重新尝试')
                }
              })
        },
          /**
           * 打开未加入班级学生
           */
          addStudent() {
            this.addStudentFlag = true;
            this.getNoClassStudent();
          },
          /**
           * 获取未加入班级的学生
           */
          getNoClassStudent() {
            this.$ajax.post('/admin/getPersonnelManagement', {
              name: this.searchCode, userType: '学生', className: ''
            }, res => {
              this.noClassStudent = res.data
            })
          },
          getPageDate() {
            this.loading = true;
            this.$ajax.post("/hs/getClassInfo",{id:this.userInfo.user_id},r=>{
              if (r.data.bulletin == null) {
                this.isNull = true;
              } else {
                this.bulletin = r.data.bulletin;
              }
              this.noticeList = r.data.noticeList;
              this.length = r.data.noticeList.length;
              let studentList = r.data.students;
              studentList.forEach(item => {
                if (item.post === '班主任') {
                  item.index = 1
                } else if (item.post === '班长') {
                  item.index = 2
                } else if (item.post === '团支书') {
                  item.index = 3
                } else if (item.post === '副班长') {
                  item.index = 4
                } else if (item.post === '同学') {
                  item.index = 6
                } else if (item.post === '' || item.post === undefined) {
                  item.index = 7
                } else {
                  item.index = 5
                }
              });
              this.students = studentList.sort((a, b) => {return a.index - b.index});
              if (r.data.noticeList.length === 0) {
                this.noDate = true
              }
              this.loading = false;
            })
          },
          /**
           * 改变学生职务
           */
        changePost(post, row) {
            this.$ajax.post('/hs/updatePersonalInfo',
              {post: post, userId: row.user_id}, res => {
                if (res.data > 0) {
                  this.dialogVisible = false;
                  this.$notify.success('修改成功');
                  this.getPostList()
                } else {
                  this.$notify.error('网络异常，请刷新页面重新尝试')
                }
              })
        },
          /**
           * 删除学生信息
           */
        handleDelete(row) {
            this.$confirm(`确认将${row.real_name}同学移除班级`, '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.$ajax.post('/hs/updatePersonalInfo',
                {className: '', userId: row.user_id}, res => {
                  if (res.data > 0) {
                    this.getPageDate();
                    this.$notify.success('删除成功');
                  } else {
                    this.$notify.error('网络异常，请刷新页面重新尝试')
                  }
                })
            })
        },
        goBack() {
          this.addClassNotice = false
        },
        refresh() {
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
          this.$ajax.post("/hs/getClassInfo",{id:userInfo.user_id},r=>{
            if (r.data.bulletin == null) {
              this.isNull = true;
            } else {
              this.bulletin = r.data.bulletin;
            }
            this.noticeList = r.data. noticeList;
            this.length = r.data. noticeList.length;
            this.students = r.data.students;
            if (r.data.noticeList.length === 0) {
              this.noDate = true
            }
          })
        },
        editorTitle(pageTitle) {
          this.pageTitle = pageTitle
        },
        goInfo() {
          this.addClassNotice = false
        },
        release() {
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
          if (userInfo.post === "班主任" || userInfo.post === "班长") {
            this.addClassNotice = true;
            sessionStorage.setItem("releaseType","classNotice")
          } else {
            this.$notify.error("没有权限发布通知")
          }
        },
        bulletinSubmit() {
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
          if (this.isNull === false) {
            this.$ajax.post("/hs/updateBulletin",{classId:userInfo.class_id,
              className:userInfo.class_name,title:this.editors.title,content:this.editors.content,id:this.bulletin.id},r=>{
              if (r.data === 1) {
                let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
                this.$ajax.post("/hs/getClassInfo",{id:userInfo.user_id},r=>{
                  this.bulletin = r.data.bulletin;
                })
                this.$notify({
                  type:'success',
                  message: '编辑公告成功'
                })
              }
            })
          } else {
            this.$ajax.post("/hs/addBulletin",{classId:userInfo.class_id,
              className:userInfo.class_name,title:this.editors.title,content:this.editors.content,
              releaseTime:getDate()},r=>{
              if (r.data === 1) {
                let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
                this.$ajax.post("/hs/getClassInfo",{id:userInfo.user_id},r=>{
                  this.bulletin = r.data.bulletin;
                })
                this.$notify({
                  type:'success',
                  message: '编辑公告成功'
                })
              }
            })
          }
          this.dialogVisible = false
        },
        editor() {
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
          if (userInfo.user_type === "教师" || userInfo.post === '班主任' || userInfo.post === '班长') {
            this.dialogVisible = true;
            this.editors.title = this.bulletin.title;
            this.editors.content = this.bulletin.content;
          } else {
            this.$notify.error("没有权限编辑公告")
          }
        },
        search() {
          let userInfo = JSON.parse(sessionStorage.getItem("userInfo"))
          sessionStorage.setItem("condition",this.condition)
          this.$ajax.post("/hs/getClassInfo",{id:userInfo.user_id,text:this.condition},r=>{
            this.noticeList = r.data.noticeList
            this.length = r.data.noticeList.length
          })
        },
        getContent(id) {
          this.$router.push({name: 'content'});
          sessionStorage.setItem("noticeId",id);
          sessionStorage.setItem("contentType","classContent");
        },
        goPersonalInfo(userId) {
          this.$router.push({name: 'personalinfo'});
          sessionStorage.setItem("userId",userId);
        },
      },
      components: {
        nodate,
        addArticle
      }
    }
</script>

<style lang="scss" scoped>
  .student-list {
    .button {
      display: flex;
      justify-content: flex-end;
      margin-bottom: 1rem;
    }
    .search {
      margin: 0;
    }
    .one-student {
      margin: 1rem;
      border-bottom: 1px solid #00afff;
      display: flex;
      justify-content: space-between;
      align-items: center;
      color: #4d4d4d;
    }
  }
  .title .admin {
    font-size: 0.9rem;
    color: #00afff;
    cursor: pointer;
  }
  .el-page-header{
    padding: 1rem;
    background: #fff;
  }
  .releasebut{
    float: right;
    margin: -3rem 0 0 0;
  }
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
  .list {
    min-height: 10rem;
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
    flex: 1.5;
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
    padding: 1rem;
    background: #EEFCFE;
  }
  .el-aside{
    width: 14rem!important;
    background: #fff;
  }
  .el-aside .title{
    padding: 1rem;
    font-size: 1.5rem;
    text-align: center;
  }
  .students .student{
    padding: 0 1rem;
    width: 10rem;
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
    white-space: nowrap;
  }
  .student .post{
    line-height: 1.5rem;
    margin-left: 0.5rem;
    cursor: default;
    flex: 1;
  }
  .el-aside{
    margin-top: 1rem;
  }
</style>
