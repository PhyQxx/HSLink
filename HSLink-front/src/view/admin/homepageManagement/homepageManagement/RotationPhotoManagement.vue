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
        <div class="add-img pointer">
          <i class="el-icon-plus"></i>
        </div>
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
            srcList: []
          }
      },
      mounted() {
        this.getRotationPhotoList();
      },
      methods: {
        /**
         * 删除图片
         */
        deleteImg(img) {

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
  .title{
    font-size: 1.2rem;
  }
  .rotation-photo{
    width: 10rem;
    height: 10rem;
  }
</style>
