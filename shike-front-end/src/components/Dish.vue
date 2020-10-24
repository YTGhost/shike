<template>
  <div>
    <!--面包屑导航区域-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>菜品管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!--卡片视图区域-->
    <el-card style="margin-top: 10px">
      <el-row :gutter="20" style="margin-bottom: 10px">
        <el-col :span="6">
          <el-input placeholder="模糊搜索菜品名" v-model="queryInfo" clearable @input="queryChange">
            <el-button slot="append" icon="el-icon-search" @click="queryDishList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加菜品</el-button>
        </el-col>
      </el-row>

      <!--菜品列表区域-->
      <el-table :data="dishList" border stripe :default-sort = "{prop: 'dno', order: 'ascending'}">
        <el-table-column label="Id" prop="dno" sortable></el-table-column>
        <el-table-column label="菜品名称" prop="dname"></el-table-column>
        <el-table-column label="所属食堂" prop="cname"
                         :filters="canteenFilter"
                         :filter-method="filterCanteenFilter"></el-table-column>
        <el-table-column label="价格" prop="price" sortable></el-table-column>
        <el-table-column label="操作" width="120px">
          <template slot-scope="scope">
            <el-tooltip effect="dark" content="修改菜品信息" placement="top" :enterable="false">
              <el-button type="primary" icon="el-icon-edit" size="mini"
                         @click="showEditDialog(scope.row.dno)"></el-button>
            </el-tooltip>
            <el-tooltip effect="dark" content="删除菜品信息" placement="top" :enterable="false">
              <el-button type="danger" icon="el-icon-delete" size="mini"
                         @click="deleteDishById(scope.row.dno)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--添加菜品信息的对话框-->
    <el-dialog
        title="添加菜品信息"
        :visible.sync="addDialogVisible"
        width="40%"
        @close="addDialogClosed">
      <!--内容主体区-->
      <el-form :model="addForm" ref="addFormRef" :rules="addRules" label-width="100px" class="demo-ruleForm">
        <el-form-item label="菜品名称" prop="dname">
          <el-input v-model="addForm.dname" class="item"></el-input>
        </el-form-item>
        <el-form-item label="所属食堂" prop="cno">
          <el-select v-model="addForm.cno" placeholder="请选择" class="item">
            <el-option
                v-for="item in canteenOptions"
                :key="item.cno"
                :label="item.cname"
                :value="item.cno">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="价钱" prop="price">
          <el-input-number style="width: 100px" v-model="addForm.price" :min="0" :precision="1" controls-position="right" label="输入价格"/>
        </el-form-item>
      </el-form>
      <!--底部区域-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="createDish">确 定</el-button>
      </span>
    </el-dialog>

    <!--修改菜品信息的对话框-->
    <el-dialog
        title="修改菜品信息"
        :visible.sync="editDialogVisible"
        width="40%"
        @close="editDialogClosed">
      <!--内容主体区-->
      <el-form :model="editForm" ref="editFormRef" :rules="editRules" label-width="100px" class="demo-ruleForm">
        <el-form-item label="Id" prop="dno">
          <el-input v-model="editForm.dno" class="item" disabled></el-input>
        </el-form-item>
        <el-form-item label="菜品名称" prop="dname">
          <el-input v-model="editForm.dname" class="item"></el-input>
        </el-form-item>
        <el-form-item label="所属食堂" prop="cno">
          <el-select v-model="editForm.cno" placeholder="请选择食堂" class="item">
            <el-option
                v-for="item in canteenOptions"
                :key="item.cno"
                :label="item.cname"
                :value="item.cno">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="价钱" prop="price">
          <el-input-number v-model="editForm.price" style="width: 100px;" :min="0" :precision="1" controls-position="right" label="输入价格"/>
        </el-form-item>
      </el-form>
      <!--底部区域-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="modifyDish()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Dish",
  data() {
    const isNull = (rule, value, callback) => {
      if(value === null) {
        callback(new Error("需要选择所属食堂"))
      } else {
        callback();
      }
    }
    return {
      // 食堂信息规则
      addRules: {
        dname: [{
          required: true,
          message: '菜品名称不能为空',
          trigger: 'blur'
        }]
      },
      editRules: {
        dname: [{
          required: true,
          message: '菜品名称不能为空',
          trigger: 'blur'
        }],
        cno: [
          {
            validator: isNull,
            trigger: 'blur'
          }]
      },
      queryInfo: '',
      addDialogVisible: false,
      editDialogVisible: false,
      dishList: [],
      totalLength: 0,
      addForm: {
        dname: '',
        cno: 1,
        price: 1,
      },
      editForm: {
        dno: 1,
        dname: '',
        cno: 1,
        price: 1,
      },
      canteenOptions: [],
      canteenFilter: [],
    }
  },
  mounted() {
    this.getCanteenOptions();
    this.getDishList();
  },
  methods: {
    queryChange() {
      if(this.queryInfo === '') {
        this.getDishList();
      }
    },

    async queryDishList() {
      if(this.queryInfo === '') {
        return;
      }
      const {data: res} = await this.$http.get('dish/query/' + this.queryInfo);
      this.dishList = res.data;
    },

    async getCanteenOptions() {
      const {data: res} = await this.$http.get('canteen/getCanteenList');
      this.canteenOptions = res.data.list;
      for(let i = 0; i < this.canteenOptions.length; i++)
      {
        let item = this.canteenOptions[i];
        this.canteenFilter.push({text: item.cname, value: item.cname});
      }
    },

    async getDishList() {
      const {data: res} = await this.$http.get('dish/getDishList');
      this.dishList = res.data.list;
      console.log(res.data.list);
      this.totalLength = res.data.length;
    },

    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },

    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },

    showEditDialog(id) {
      this.editForm = this.dishList.filter(obj => obj.dno === id)[0];
      this.editDialogVisible = true;
    },

    deleteDishById(id) {
      this.$confirm('此操作将删除该菜品信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        await this.$http.get('dish/delete/' + id);
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.getDishList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    createDish() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加食堂的网络请求
        await this.$http.post('dish/create', {
          dname: this.addForm.dname,
          cno: this.addForm.cno,
          price: this.addForm.price,
        })
        this.$message.success('添加菜品信息成功')
        // 隐藏添加食堂的对话框
        this.addDialogVisible = false
        // 重新获取食堂列表数据
        this.getDishList()
      })
    },

    modifyDish() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起修改食堂信息的网络请求
        await this.$http.post('dish/modify', {
          dno: this.editForm.dno,
          dname: this.editForm.dname,
          cno: this.editForm.cno,
          price: this.editForm.price,
        })
        this.$message.success('修改菜品信息成功')
        // 隐藏修改食堂信息的对话框
        this.editDialogVisible = false
        // 重新获取食堂列表数据
        this.getDishList()
      })
    },

    filterCanteenFilter(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    }
  }
}
</script>

<style scoped>
.item {
  width: 300px;
}
</style>
