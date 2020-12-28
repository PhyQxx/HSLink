<template>
  <el-container>
    <el-main>
      <div class="login-box" v-loading="loading">
        <el-tabs type="border-card" v-model="defaultTabs">
          <el-tab-pane name="login">
            <span slot="label"><i class="el-icon-user"></i> 登录</span>
            <el-form :model="form"
                     :rules="rules"
                     ref="form"
                     label-width="80px"
                     class="demo-ruleForm"
                     close-on-click-modal=false
                     label-position="left">
              <el-form-item label="角色" prop="role">
                <el-select v-model="form.role" placeholder="请选择">
                  <el-option :value="item.dd_detail" v-for="(item,index) in userTypeList" :key="index"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="账号" prop="username">
                <!--<el-select
                  v-if="isSelect"
                  v-model="form.username"
                  filterable
                  allow-create
                  default-first-option
                  placeholder="请输入手机号或姓名"
                  @change="selectUsername">
                  <el-option
                    v-for="(item,index) in rememberList"
                    :key="item.username"
                    :label="item.username"
                    :value="item.username">
                    <span style="float: left">{{ item.username}}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px" @click="deleteUser(item.username,index)"><i class="el-icon-circle-close"></i></span>
                  </el-option>
                </el-select>-->
                <el-input v-model="form.username" type="text" placeholder="请输入手机号或姓名"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input v-model="form.password" type="password" placeholder="请输入密码" @keyup.enter.native="login"></el-input>
              </el-form-item>
            </el-form>
            <div id="forget" @click="forget">忘记密码</div>
            <el-checkbox class="remember-password" v-model="rememberPassword">记住密码</el-checkbox>
            <el-button type="primary" @click="login">登 录</el-button>
          </el-tab-pane>
          <el-tab-pane name="register">
            <span slot="label"><i class="el-icon-circle-plus-outline"></i>注册</span>
            <el-form :model="form1"
                     :rules="rules1"
                     ref="form1"
                     label-width="80px"
                     class="demo-ruleForm"
                     close-on-click-modal=false
                     label-position="left">
              <el-form-item label="角色" prop="role">
                <el-select v-model="form1.role" placeholder="请选择">
<!--                  <el-option value="学生"></el-option>-->
                  <el-option value="家长"></el-option>
                  <el-option value="教师"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="姓名" prop="realName">
                <el-input v-model="form1.realName" type="text"></el-input>
              </el-form-item>
              <el-form-item label="手机号" prop="mobile">
                <el-input v-model="form1.mobile" type="text"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input v-model="form1.password" type="password"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="againPassword" @keyup.enter.native="register">
                <el-input v-model="form1.againPassword" type="password"></el-input>
              </el-form-item>
              <el-button type="primary" @click="register">提 交</el-button>
            </el-form>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-main>
  </el-container>
</template>

<script>
  export default{
    data() {
      let againPassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.form1.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      }
      let type;
      if (this.$route.params.type == "login" || this.$route.params.type == "register") {
        type = this.$route.params.type
      } else {
        type = "login"
      }
      return{
        //用户类型字典项
        userTypeList: [],
        isSelect: true,
        rememberList: JSON.parse(sessionStorage.getItem("rememberList")),
        rememberPassword: false,
        defaultTabs: type,
        loading: false,
        form: {
          role: '',
          username: '',
          password: '',
        },
        rules: {
          role: [
            { required: true, message: '请选择身份', trigger: 'blur' },
            { min: 2, max: 2, message: '请选择身份', trigger: 'blur' }
          ],
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 2, max: 11, message: '长度在 2 到 11 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 8, message: '长度在 6 到 8 个字符', trigger: 'blur' }
          ],
        },
        form1: {
          role: '',
          realName: '',
          mobile: '',
          username: '',
          password: '',
          againPassword: '',
        },
        rules1: {
          role: [
            { required: true, message: '请选择身份', trigger: 'blur' },
            { min: 2, max: 2, message: '请选择身份', trigger: 'blur' }
          ],
          realName: [
            { required: true, message: '请输入真实姓名', trigger: 'blur' },
            { min: 2, max: 10, message: '请输入正确的姓名', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '请输入手机号', trigger: 'blur' },
            { min: 11, max: 11, message: '手机号格式不正确', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 8, message: '长度在 6 到 8 个字符', trigger: 'blur' }
          ],
          againPassword: [
            { required: true, message: '请确认密码', trigger: 'blur' },
            { validator:againPassword, trigger: 'blur'},
          ],

        }
      }
    },
    mounted() {
      this.getUserType();
    },
    methods:{
      /*deleteUser(username,index) {
        if (this.rememberList != null) {
          this.rememberList.forEach((item)=>{
            if (item.username == username) {
              this.rememberList.splice(index,1);
            }
          })
        }
        sessionStorage.setItem("rememberList",JSON.stringify(this.rememberList));
        this.form.role = '';
        this.form.password = '';
        setTimeout(()=>{
          this.form.username = '';
        },10)
        console.log(this.form.username)
      },*/
      /*selectUsername(event) {
        if (this.rememberList != null) {
          this.rememberList.forEach((item)=>{
            if (item.username == event) {
              this.form = item
              this.rememberPassword = true
            }
          })
        }
        this.isSelect = false
        this.isSelect = true
      },*/
      /**
       * 获取用户类型字典项
       */
      getUserType() {
        this.$ajax.post('/hs/getDictionariesData',{code: 'userType'},res => {
          console.log('用户类型字典项', res);
          this.userTypeList = res;
        })
      },
      forget() {
        this.$message("请联系管理员重置密码")
      },
      login() {
        this.loading = true;
        this.$ajax.post("/hs/login",{role:this.form.role,username: this.form.username,password:this.form.password},r=>{
          if (r == ''  || r == null) {
            this.$message.error('用户名和密码不匹配');
            this.loading = false;
          } else {
            if (this.form.role === '管理员') {
              this.$router.push({
                name: "admin"
            })
            } else {
              this.$router.push({
                name: "homepage"
            })
            }
            this.$message({
              message: "登录成功",
              type: "success"
            });
            /*if (this.rememberPassword == true) {
              let one = {
                role: this.form.role,
                username: this.form.username,
                password: this.form.password,
              }
              if (this.rememberList != null) {
                let flag = false;
                this.rememberList.forEach((item)=>{
                  if (item.username == one.username) {
                    item = one;
                    flag = true;
                  }
                })
                sessionStorage.setItem("rememberList",JSON.stringify(this.rememberList));
                if (flag == false) {
                  this.rememberList.push(one)
                  sessionStorage.setItem("rememberList",JSON.stringify(this.rememberList));
                }
              } else {
                let rememberList = [];
                rememberList.push(one)
                sessionStorage.setItem("rememberList",JSON.stringify(rememberList));
              }
            }*/
            this.loading = false;
            sessionStorage.setItem("userInfo",JSON.stringify(r));
            // location.reload();
          }
        })
      },
      register() {
        this.$ajax.post("/hs/register",{type:this.form1.role,realname:this.form1.realName,mobile:this.form1.mobile,password:this.form1.password},r=>{
          console.log(r)
          if (r == '0') {
            this.$message.error('该手机号已被注册');
            this.form1.password = '';
            this.form1.againPassword = '';
          } else {
            this.$message({
              message: "注册成功",
              type: "success"
            });
            this.form.role = this.form1.role;
            this.form.username = this.form1.realName;
            this.form.password = this.form1.password;
            this.form1.role = '';
            this.form1.realName = '';
            this.form1.mobile = '';
            this.form1.password = '';
            this.form1.againPassword = '';
            this.defaultTabs = 'login';
          }
        })
      }
    },
  }
</script>

<style scoped>
  .remember-password{
    float: right;
    margin-right: 1rem;
  }
  #forget{
    font-size: 0.9rem;
    float: right;
    cursor: pointer;
    margin-right: 1rem!important;
  }
  #forget:hover{
    text-decoration: underline;
    color: #419EFF;
  }
  .login-box{
    width: 30rem;
    /*height: 20rem;*/
    margin: 8rem auto;
    /*color: #FAFAFA;*/
    /*border-radius: 5px;*/
    /*border: 1px solid #419EFF;*/
    /*padding: 2rem;*/
  }
  .el-button--primary{
    width: 6rem;
    margin-left: 11rem;
    margin-top: 2rem;
  }
  .el-main{
    background: #EEFCFE;
    height: 55rem;
  }
</style>
