<template>
  <el-container>
    <el-main>
      <div class="choice-plate">
        <el-input v-model="label" placeholder="输入文章标签"></el-input>
      </div>
      <div class="title">
        <el-input v-model="title" placeholder="输入文章标题"></el-input>
      </div>

      <div class="indexContainer">
        <div class="maskContainer" v-if="dilogStatus">
          <div class="contentContainer">
            <div class="closeBtnContainer" @click="closeMaskFn"></div>
            <textarea class="showAreaContainer" v-model="msgShow" readonly></textarea>
          </div>
        </div>
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
      <el-button class="" type="primary" @click="publish">发 表</el-button>
      <div class="notes">
        <ul>
          <li>请遵守HSLink用户行为准则，不得违反国家法律法规。</li>
          <li>转载文章请注明出自“HSLink”，如是商业用途请联系原作者。</li>
          <li>为了维护良好的技术讨论环境，请不要讨论政治 相关话题。</li>
        </ul>
      </div>
    </el-main>
  </el-container>
</template>

<script>
  import markdown from './components/Mdeditor'
  import  { getDate } from  '../assets/js/public.js'
  export default {
    name: "Posting",
    data() {
      return {
        label: "",
        title:"",
        type:'',
        msgShow:'我要显示的内容',
        dilogStatus:false,
        msg: {
          mdValue:''
        },
        flag:false,
      }
    },
    mounted() {
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      if (userInfo.user_type === "教师") {
        this.$emit("editorTitle","新增校园通知")
        // if (sessionStorage.getItem("release") == )
        this.type = "校园通知"
      } else if (userInfo.user_type === "家长") {
        this.$emit("editorTitle","新增家长建议")
        this.type = "家长建议"
      } else if (userInfo.user_type === "学生") {
        this.$emit("editorTitle","新增学生想法")
        this.type = "学生想法"
      }
    },
    methods: {
      goBack() {
        this.$router.back(-1)
      },
      childEventHandler:function(res){
        // res会传回一个data,包含属性mdValue和htmlValue，具体含义请自行翻译
        this.msg=res;
      },
      closeMaskFn:function(){
        this.msgShow='';
        this.dilogStatus=false;
      },
      handleChange(value) {
        console.log(value);
      },
      publish() {
        if (this.label.length != 4) {
          this.$message({
            message: "文章标签只能为4个汉字",
            type: "warning"
          })
        } else if (this.title == '') {
          this.$message({
            message: "文章标题不能为空",
            type: "warning"
          })
        } else if (this.msg.mdValue == '') {
          this.$message({
            message: "编辑内容不能为空",
            type: "warning"
          })
        } else {
          if (sessionStorage.getItem("releaseType") === "classNotice") {
            let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
            this.$ajax.post("/hs/addClassNotice",{classId:userInfo.class_id,className:userInfo.class_name,
              label:this.label,title:this.title,content:this.msg.mdValue,releaseId:userInfo.user_id,
              release_time:getDate(),type:this.type},r=> {
              if (r === 1) {
                this.$ajax.post("/hs/timingTask",{userId:userInfo.user_id},r=>{
                  console.log(r)
                });
                this.$message({
                  message: "发布成功",
                  type: "success"
                });
                this.$emit("goInfo");
                this.$emit("refresh");
                this.$emit("getUserInfo");
              }
            });
            sessionStorage.setItem("releaseType","")
          } else {
            let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
            this.$ajax.post("/hs/addArticle",{label:this.label,title:this.title,content:this.msg.mdValue,release_id:userInfo.user_id,
              release_time:getDate(),type:this.type},r=>{
              if (r === 1) {
                this.$ajax.post("/hs/timingTask",{userId:userInfo.user_id},r=>{
                  console.log(r)
                });
                this.$message({
                  message: "发表成功",
                  type: "success"
                });
                this.$emit("goInfo");
                this.$emit("refresh");
                this.$emit("editorTitle","个人中心");
              }
            })
          }
        }
      }
    },
    components: {
      markdown
    },
  }
</script>

<style scoped>
  .el-page-header{
    padding-bottom: 1rem;
  }
  .page-title{
    font-size: 1.5rem;
    padding: 0 1rem 1rem 0;
    cursor: default;
    font-family: 楷体;
  }
  .notes{
    border: 1px dashed #999;
  }
  .el-button--primary{
    margin: 1rem;
  }
  .choice-plate{
    padding-bottom: 1rem;
  }
  .editorContainer{
    height: 500px;
  }
  .integral img{
    display: inline-block;
    margin: 1rem 0.5rem;
    float: left;
  }
  .integral{
    width: 12rem;
  }
  .integral div{
    line-height: 2.4rem;
  }
  .title{
    display: flex;
  }
  .indexContainer{

  }
  .el-container{
    padding: 0;
  }
  .el-main{
    padding: 1rem;
    background: #fff;
    overflow: hidden;
  }
</style>
