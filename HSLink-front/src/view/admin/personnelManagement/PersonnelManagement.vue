<template>
  <div class="page">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="用户管理" name="1">
        <person></person>
      </el-tab-pane>
      <el-tab-pane label="注册管理" name="2">
        <registration></registration>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import noDate from '@/components/components/NoData'
  import person from '@/view/admin/personnelManagement/personnelManagement/PersonnelManagement'
  import registration from '@/view/admin/personnelManagement/registrationManagement/RegistrationManagement'
    export default {
      components: {
        noDate,
        person,
        registration
      },
        name: "PersonnelManagement",
      data() {
          return{
            activeName: '1'
          }
      },
      methods: {
        handleClick(tab, event) {
          console.log(tab, event);
        }
      }
    }
</script>

<style scoped>

</style>
