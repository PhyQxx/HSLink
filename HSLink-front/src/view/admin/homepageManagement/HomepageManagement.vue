<template>
  <div class="page">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="轮播图管理" name="1">
        <rotation-photo-management></rotation-photo-management>
      </el-tab-pane>
      <el-tab-pane label="版块管理" name="2">
        <plate-management></plate-management>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import rotationPhotoManagement from '@/view/admin/homepageManagement/homepageManagement/RotationPhotoManagement';
  import plateManagement from '@/view/admin/homepageManagement/plateManagement/PlateManagement.vue';
    export default {
      components: {
        rotationPhotoManagement,
        plateManagement
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
        },

      }
    }
</script>

<style scoped>

</style>
