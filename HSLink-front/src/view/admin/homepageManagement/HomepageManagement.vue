<template>
  <div class="page">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="轮播图管理" name="1">
        <rotation-photo-management></rotation-photo-management>
      </el-tab-pane>
      <el-tab-pane label="版块管理" name="2">
        <registration></registration>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import rotationPhotoManagement from '@/view/admin/homepageManagement/homepageManagement/RotationPhotoManagement';
    export default {
      components: {
        rotationPhotoManagement
      },
      name: "HomepageManagement",
      data() {
        return{
          activeName: '1'
        }
      },
      mounted() {
      },
      methods: {
        /**
         * 切换tab
         */
        handleClick(tab, event) {
          console.log(tab, event);
        },
        
      }
    }
</script>

<style scoped>

</style>
