<template>
  <div>
    <!--面包屑导航区域-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>食堂管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!--卡片视图区域-->
    <el-card style="margin-top: 10px">
      <el-row :gutter="20" style="margin-bottom: 10px">
        <el-col :span="6">
          <el-input placeholder="模糊搜索食堂名" v-model="queryInfo" clearable @input="queryChange">
            <el-button slot="append" icon="el-icon-search" @click="queryCanteenList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加食堂</el-button>
        </el-col>
      </el-row>

      <!--食堂列表区域-->
      <el-table :data="canteenList" border stripe :default-sort = "{prop: 'cno', order: 'ascending'}">
        <el-table-column label="id" prop="cno" sortable></el-table-column>
        <el-table-column label="食堂名称" prop="cname"></el-table-column>
        <el-table-column label="操作" width="120px">
          <template slot-scope="scope">
            <el-tooltip effect="dark" content="修改食堂信息" placement="top" :enterable="false">
              <el-button type="primary" icon="el-icon-edit" size="mini"
                         @click="showEditDialog(scope.row.cno)"></el-button>
            </el-tooltip>
            <el-tooltip effect="dark" content="删除食堂信息" placement="top" :enterable="false">
              <el-button type="danger" icon="el-icon-delete" size="mini"
                         @click="deleteCanteenById(scope.row.cno)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--添加食堂信息的对话框-->
    <el-dialog
        title="添加食堂信息"
        :visible.sync="addDialogVisible"
        width="40%"
        @close="addDialogClosed">
      <!--内容主体区-->
      <el-form :model="addForm" ref="addFormRef" :rules="addRules" label-width="100px" class="demo-ruleForm">
        <el-form-item label="食堂名称" prop="cname">
          <el-input v-model="addForm.cname" class="item"></el-input>
        </el-form-item>
      </el-form>
      <!--底部区域-->
      <span slot="footer" class="dialog-footer">
                  <el-button @click="addDialogVisible= false">取 消</el-button>
                  <el-button type="primary" @click="createCanteen">确 定</el-button>
              </span>
    </el-dialog>

    <!--修改食堂信息的对话框-->
    <el-dialog
        title="修改食堂信息"
        :visible.sync="editDialogVisible"
        width="40%"
        @close="editDialogClosed">
      <!--内容主体区-->
      <el-form :model="editForm" :rules="editRules" ref="editFormRef" label-width="100px" class="demo-ruleForm">
        <el-form-item label="Id" prop="cno">
          <el-input v-model="editForm.cno" class="item" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="cname">
          <el-input v-model="editForm.cname" class="item"></el-input>
        </el-form-item>
      </el-form>
      <!--底部区域-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="modifyCanteen()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Canteen",
  data() {
    // 校验食堂名称是否存在
    const rulesName = (rule, value, callback) => {
      // 使用Axios进行校验
      this.$http.get('canteen/checkCname/' + value)
          .then((res => {
            // 请求成功
            if (res.data.code === 0) {
              callback()
            } else {
              callback(new Error("食堂已存在"))
            }
          }))
          .catch((err) => {
            // 如果请求失败则在控制台打印
            console.log(err)
          })
    }
    return {
      // 食堂信息规则
      addRules: {
        cname: [{
          required: true,
          message: '食堂名称不能为空',
          trigger: 'blur'
        },
          {
            validator: rulesName,
            trigger: 'blur'
          }],
      },
      editRules: {
        cname: [{
          required: true,
          message: '食堂名称不能为空',
          trigger: 'blur'
        },
          {
            validator: rulesName,
            trigger: 'blur'
          }],
      },
      queryInfo: '',
      addDialogVisible: false,
      editDialogVisible: false,
      canteenList: [],
      totalLength: 0,
      addForm: {
        cname: '',
      },
      editForm: {
        cno: '',
        cname:'',
      },
      canteenOptions: [],
    }
  },
  mounted() {
    this.getCanteenList();
  },
  methods: {
    async getCanteenList() {
      const {data: res} = await this.$http.get('canteen/getCanteenList');
      this.canteenList = res.data.list;
      this.totalLength = res.data.length;
    },

    queryChange() {
      if(this.queryInfo === '') {
        this.getCanteenList();
      }
    },

    async queryCanteenList() {
      if(this.queryInfo === '') {
        return;
      }
      const {data: res} = await this.$http.get('canteen/query/' + this.queryInfo);
      this.canteenList = res.data;
    },

    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },

    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },

    showEditDialog(id) {
      this.editForm = this.canteenList.filter(obj => obj.cno === id)[0];
      this.editDialogVisible = true;
    },

    deleteCanteenById(id) {
      this.$confirm('此操作将删除该食堂信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        await this.$http.get('canteen/delete/' + id);
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.getCanteenList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    createCanteen() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加食堂的网络请求
        await this.$http.post('canteen/create', {
          cname: this.addForm.cname,
        })
        this.$message.success('添加食堂信息成功')
        // 隐藏添加食堂的对话框
        this.addDialogVisible = false
        // 重新获取食堂列表数据
        this.getCanteenList()
      })
    },

    modifyCanteen() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起修改食堂信息的网络请求
        await this.$http.post('canteen/modify', {
          cno: this.editForm.cno,
          cname: this.editForm.cname,
        })
        this.$message.success('修改食堂信息成功')
        // 隐藏修改食堂信息的对话框
        this.editDialogVisible = false
        // 重新获取食堂列表数据
        this.getCanteenList()
      })
    },
  }
}
</script>

<style scoped>
.item {
  width: 300px;
}
</style>
