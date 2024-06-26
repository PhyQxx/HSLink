<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="6rem">
      <el-form-item label="获奖人员姓名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入获奖人员姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="获奖类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择获奖类型" clearable size="small">
          <el-option v-for="item in winnerTypeList"
                     :label="item.dd_detail"
                     :key="item.id"
                     :value="item.dd_detail" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="winnersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="人员姓名" align="center" prop="userName" />
      <el-table-column label="获奖类型" align="center" prop="type" />
      <el-table-column label="获奖人职务" align="center" prop="post" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改获奖对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="6rem">
        <el-form-item label="获奖人员姓名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入人员姓名" />
        </el-form-item>
        <el-form-item label="获奖类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择获奖类型">
            <el-option v-for="item in winnerTypeList"
                       :label="item.dd_detail"
                       :key="item.id"
                       :value="item.dd_detail" />
          </el-select>
        </el-form-item>
        <el-form-item label="获奖人职务" prop="post">
          <el-input v-model="form.post" placeholder="请输入获奖人职务" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listWinners, delWinners, addWinners, updateWinners } from "@/api/admin/winners";
  export default {
    name: "PlateManagement",
    data() {
      return {
        //获奖类型列表
        winnerTypeList: [],
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 获奖表格数据
        winnersList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          userName: null,
          type: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      };
    },
    created() {
      this.getList();
      this.getDict('WinnerType').then(res => {
        this.winnerTypeList = res.data.data;
      })
    },
    methods: {
      /** 查询获奖列表 */
      getList() {
        this.loading = true;
        listWinners(this.queryParams).then(response => {
          this.winnersList = response.data.data;
          this.total = response.data.data.length;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          userId: null,
          userName: null,
          type: null,
          post: null,
          version: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          remark: null
        };
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.getList();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加获奖";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids;
        listWinners({id: row.id}).then(response => {
          this.form = response.data.data[0];
          this.open = true;
          this.title = "修改获奖";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateWinners(this.form).then(response => {
                this.$notify.success("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addWinners(this.form).then(response => {
                this.$notify.success("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$confirm(`是否确认删除${row.userName}的获奖信息`, "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delWinners(ids);
        }).then(() => {
          this.getList();
          this.$notify.success("删除成功");
        })
      },
    }
  }
</script>

<style lang='scss' scoped>

</style>
