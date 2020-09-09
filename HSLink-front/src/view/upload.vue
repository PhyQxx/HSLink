<template>
  <div id="upload">
    <el-upload
      class="upload-demo"
      action="http://127.0.0.1:8048/hs/upload"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList"
      list-type="picture">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>
    <div class="img">
      <img v-for="(item,index) in fileList" :src="item.path" height="100" width="100"/>
    </div>
    <el-button @click="fileUpload">
      下载
    </el-button>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        fileList: []
      };
    },
    mounted() {
      this.getPhoto();
    },
    methods: {
      //下载
      fileUpload() {
        this.$axios(
          {
            method: 'post',
            url: 'http://127.0.0.1:8048/hs/fileDownload',
            params: {
              fileId: '111'
            },
            responseType: 'blob'//注意：一定要定义返回数据类型，否则获取到的文件流无法解析，下载的文件会无数据
          }).then(res=>{
            this.download(res.data,"text.png")
          }
        )
      },
      // 下载文件
      download (data,fileName) {
        if (!data) {
          return
        }
        let url = window.URL.createObjectURL(new Blob([data]));
        let link = document.createElement('a');
        link.style.display = 'none';
        link.href = url;
        link.setAttribute('download', fileName);
        document.body.appendChild(link);
        link.click();
      },
      //获取图片列表
      getPhoto() {
        this.$ajax.post('/hs/getFileList',{},r=>{
          this.fileList = [];
          for (let i = 0; i < r.fileList.length; i++) {
            this.fileList.push({path: require('../assets/file/'+r.fileList[i].fileEncryption)})
          }
        })
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      }
    }
  }
</script>

<style scoped>
  .upload-demo >>> ul{
    display: none!important;
  }
</style>
