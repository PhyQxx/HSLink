<template>
  <div>
      <div class="title">轮播图管理</div>
      <div class="img-list">
        <div class="img" v-for="(img, index) in rotationPhotoList" :key='index'>
          <el-image class="rotation-photo" 
                    :src='imgPath(img)' 
                    :preview-src-list="srcList"
                    fit='scale-down'></el-image>
          <i class="el-icon-circle-close delete-icon pointer" @click="deleteImg(img)"></i>
        </div>
        <el-upload
          class="upload-demo"
          :action="url"
          list-type="picture"
          :show-file-list='false'
          :on-success='success'
          :on-error='error'>
          <div class="add-img pointer">
            <i class="el-icon-plus"></i>
          </div>
        </el-upload>
        
      </div>
  </div>
</template>

<script>
    export default {
      data() {
          return{
            //轮播图图片
            rotationPhotoList: [],
            //轮播图片URL列表
            srcList: [],
            //服务器地址
            url: `${sessionStorage.getItem('url')}/hs/upload`,
          }
      },
      mounted() {
        this.getRotationPhotoList();
      },
      methods: {
        /**
         * 上传图片成功
         */
        success(response, file, fileList) {
          console.log('上传图片', response);
          this.$ajax.post('/admin/uploadRotationPhoto', {fileId: response.fileInfo.id}, res => {
            if (res === 1) {
              this.$message({
                type: 'success',
                message: '上传成功'
              })
              this.getRotationPhotoList();
            } else {
              this.$message({
                type: 'error',
                message: '网络异常，请稍后再试'
              })
            }
          })
          
        },
        /**
         * 上传图片失败
         */
        error() {
          this.$message({
            type: 'error',
            message: '网络异常，请稍后再试'
          })
        },
        /**
         * 删除图片
         */
        deleteImg(img) {
          this.$ajax.post('/admin/deleteRotationPhoto', {
            id: img.id,
            fileEncryption: img.fileEncryption
          }, res => {
            if (res === 1) {
              this.$message({
                type: 'success',
                message: '删除成功'
              })
              this.getRotationPhotoList();
            } else {
              this.$message({
                type: 'error',
                message: '网络异常，请稍后再试'
              })
            }
          })
        },
        /**
         * 图片地址
         */
        imgPath(item) {
          return `${sessionStorage.getItem('url')}/hs/showImg/${item.fileEncryption}`
        },
        //获取轮播图片
        getRotationPhotoList() {
          this.$ajax.post('/hs/getRotationPhotoList',{},res=>{
            console.log("轮播图列表",res);
            if (res.rotationPhotoList.length > 0) {
              this.rotationPhotoList = res.rotationPhotoList;
              res.rotationPhotoList.forEach(img => {
                this.srcList.push(`${sessionStorage.getItem('url')}/hs/showImg/${img.fileEncryption}`)
              });
            }
          })
        },
      }
    }
</script>

<style scoped>
  .delete-icon{
    position: absolute;
    margin-left: -1rem;
    margin-top: -0.4rem;
    color: #ff0000;
    font-size: 1.5rem;
  }
  .img-list{
    display: flex;
    flex-wrap: wrap;
  }
  .add-img{
    width: 10rem;
    height: 10rem;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .add-img i{
    font-size: 5rem;
    color: #999999;
  }
  .img, .add-img{
    border: 1px solid #999999;
    margin: 0.5rem;
    padding: 0.5rem;
    border-radius: 0.5rem
  }
  .rotation-photo{
    width: 10rem;
    height: 10rem;
  }
</style>
