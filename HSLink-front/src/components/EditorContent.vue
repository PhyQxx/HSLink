<template>
  <el-container>
    <el-main>
      <el-page-header @back="goBack" content="编辑内容"></el-page-header>
      <div class="choice-plate">
        <el-input v-model="label" placeholder="输入文章标签"></el-input>
      </div>
      <div class="title">
        <el-input v-model="title" placeholder="输入文章标题"></el-input>
      </div>

      <div class="indexContainer">
        <mavon-editor
          :placeholder="'开始创作···'"
          :toolbars="toolbars"
          :toolbarsBackground="'#f9f9f9'"
          @change="change"
          @imgAdd="imgAdd"
          @imgDel="imgDel"
          ref="md"
          style="height: 70vh"
          v-model="content"
        />
      </div>
      <el-button class="" type="primary" @click="publish">保 存</el-button>
      <div class="notes">
        <ul>
          <li>请遵守HSLink用户行为准则，不得违反国家法律法规。</li>
          <li>转载文章请注明出自“HSLink”，如是商业用途请联系原作者。</li>
          <li>为了维护良好的技术讨论环境，请不要讨论政治 相关话题。</li>
        </ul>
      </div>
    </el-main>
    <el-aside>
      <footers></footers>
    </el-aside>
  </el-container>
</template>

<script>
    import {getDate} from "../assets/js/public";
    import footers from "./components/Footer";

    export default {
        name: "EditorContent",
      data() {
          return{
            //参数
            toolbars: {
              bold: true, // 粗体
              italic: true, // 斜体
              header: true, // 标题
              underline: true, // 下划线
              strikethrough: true, // 中划线
              mark: true, // 标记
              superscript: true, // 上角标
              subscript: true, // 下角标
              quote: true, // 引用
              ol: true, // 有序列表
              ul: true, // 无序列表
              link: true, // 链接
              imagelink: true, // 图片链接
              code: true, // code
              table: true, // 表格
              fullscreen: false, // 全屏编辑
              readmodel: false, // 沉浸式阅读
              htmlcode: true, // 展示html源码
              help: true, // 帮助
              /* 1.3.5 */
              undo: true, // 上一步
              redo: true, // 下一步
              trash: true, // 清空
              save: false, // 保存（触发events中的save事件）
              navigation: true, // 导航目录
              alignleft: true, // 左对齐
              aligncenter: true, // 居中
              alignright: true, // 右对齐
              subfield: true, // 单双栏模式
              preview: false // 预览
            },
            label: "",
            title:"",
            type:'',
            content:'',
            dilogStatus:false,
            flag:false,
          }

      },
      mounted() {
        this.$ajax.post("/hs/getOneContent",{id:sessionStorage.getItem("noticeId")},r=>{
          this.label = r.data.content.label;
          this.title  = r.data.content. title;
          this.content = r.data.content.content
        })
      },
      methods: {
        imgAdd (pos, file) {
          // 第一步.将图片上传到服务器.
          const formData = new FormData();
          formData.append('image', file);
          this.$ajax.post('/open/uploadImage', formData, res => {
            this.$refs.md.$img2Url(pos, 'https://phy0412.top' + res.imgUrl)
          })
        },
        imgDel (pos) {
          delete this.img_file[pos]
        },
        change (value, render) {
          this.html = render
        },
        goBack() {
          this.$router.back(-1)
        },
        publish() {
          if (this.label.length !== 4) {
            this.$notify({
              message: "文章标签只能为4个汉字",
              type: "warning"
            })
          } else if (this.title === '') {
            this.$notify({
              message: "文章标题不能为空",
              type: "warning"
            })
          } else if (this.content === '') {
            this.$notify({
              message: "编辑内容不能为空",
              type: "warning"
            })
          } else {
            this.$ajax.post("/hs/updateOneContent",{label:this.label,title:this.title,
              content:this.html,content_md: this.content ,id:sessionStorage.getItem("noticeId")},r=>{
              if (r.data === 1) {
                this.$notify({
                  type: "success",
                  message: "编辑成功"
                })
                /*this.$router.push({name: 'content'});
                sessionStorage.setItem("noticeId",sessionStorage.getItem("noticeId"));*/
                this.$router.back(-1)
              } else {
                this.$notify.error("编辑失败")
              }
            })
          }
        }
      },
      components: {
        footers
      },
    }
</script>

<style scoped>
  .el-aside{
    margin: -1rem 0 0 1rem;
    width: 14rem!important;
  }
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
    padding: 1rem;
  }
  .el-main{
    padding: 1rem;
    background: #fff;
    overflow: hidden;
  }
</style>
