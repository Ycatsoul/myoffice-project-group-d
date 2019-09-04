<template>
<el-container class="home-container">
   <div class="head">
    <p>角色功能设置</p>
     <hr>
   </div>
   <div class="content">
    <div class="first">
     <el-form :model="role"  ref="addRoleForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: center;margin-left:100px;">
          <el-row style="margin-top: 20px;padding: 0px;">
            <el-col :span="10">
              <div>
                <el-form-item label="角色名称:" prop="roleName">
                  <el-input prefix-icon="el-icon-edit" v-model="role.roleName" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
              </div>
            </el-col>
              <el-col :span="10">
              <div>
                <el-form-item label="角色说明:" prop="roleDesc">
                  <el-input prefix-icon="el-icon-edit" v-model="role.roleDesc" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="4">
            <div style="margin-top:6px;">
            <el-button size="mini" type="primary" @click="addRole('addRoleForm')" :disabled="isDisabled=='1'">添加</el-button>
            <el-button size="mini" type="primary" @click="changeRole('addRoleForm')" :disabled="isDisabled=='0'">保存修改</el-button>
            </div>
            </el-col>
          </el-row>
      </div>
    </el-form>
     </div>
     <div class="main">
          <el-table
            :data="roles"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%">
            <el-table-column
              prop="id"
              type="selection"
              align="left"
              width="30">
            </el-table-column>
            <el-table-column
              prop="roleName"
              align="center"    
              label="角色名称"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              prop="roleDesc"
              label="角色说明"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              label="修改"
              width="150">
              <template slot-scope="scope">
                <el-button @click="showRoleView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">
                           <i class="el-icon-edit" ></i>
                </el-button>
                 </template>
               </el-table-column>
              <el-table-column
              align="center"
              label="删除"
              width="150">
              <template slot-scope="scope">
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteRole(scope.row)">
                           <i class="el-icon-delete"></i>
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini"  :disabled="multipleSelection.length==0"
                       @click="deleteManyRole">批量删除
            </el-button>
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage"
              @current-change="currentChange"
              layout="prev, pager, next"
              :total="totalCount">
            </el-pagination>
          </div> 
     </div>
   </div>
</el-container>
  
  
</template>
<script>
export default {
  data () {
    return {
      role:{
        id:'',
        roleId:'',
        roleName:'',
        roleDesc:'',
      },
      isDisabled:0,
      totalCount: -1,
      currentPage: 1,
      tableLoading:false,
      multipleSelection:[],
      // roles: [{
      //     roleName:'华北电力科学研究院',
      //     roleDesc: '华电',
      //   }, {
      //      roleName: '中国科学院声学研究院',
      //     roleDesc: '中科声研究院',
      //   }, {
      //         roleName: '北大青岛集团',
      //     roleDesc: '青岛',
      //   }, {
      //        roleName: '大唐国际盘山发电有限公司',
      //     roleDesc: '盘电',
      //   }],
    }
  },
    mounted: function () {

      this.loadRoles();
 
    },
  methods:{
     handleSelectionChange(val) {
        this.multipleSelection = val;
      },
     keywordsChange(val){
        if (val == '') {
          this.loadRoles();
        }
      },
     currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadRoles();
      },
      //修改
      changeRole(formName){
        var _this = this;
        _this.isDisabled=0;
        this.$refs[formName].validate((valid) => {
          if (valid) {
              this.tableLoading = true;
              this.postRequest("/user/role/update", this.role).then(resp=> {
                _this.tableLoading = false;
                console.log("修改",this.role);
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyRoleData();
                  _this.loadRoles();
                }
              })
          }
          else{
            return false;
          }
        });
      },
      //增加
       addRole(formName){
        var _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
              this.tableLoading = true;
              this.postRequest("/user/role/save", this.role).then(resp=> {
                _this.tableLoading = false;
                console.log("增加部分",resp);
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyRoleData();
                  _this.loadRoles();
                }
              })
          }
          else{
            return false;
          }
        });
      },
     deleteManyRole(){
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          for (var i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doDeleteMultiRole(ids);
        }).catch(() => {
        });
      },
      doDeleteMultiRole(ids){
        this.tableLoading = true;
        var _this = this;
        var datas = {
          deleteList:ids
        }
        this.postRequest("/employee/prize/deleteBatchPrize",datas).then(resp=> {
          _this.tableLoading = false;
          console.log("==============",datas);
          if (resp && resp.status == 200) {
            var data = resp.data;
            this.$message({type: 'success', message: "删除成功！"});
            _this.loadRoles();
          }
        })
      },
       loadRoles(){
        var _this = this;
        var datas = {
          "roleName":'',
          "roleId": "",
          "size": "10",
          "start": (this.currentPage-1)*10          
        };
        this.tableLoading = true;
        this.postRequest("/user/role/list",datas).then(resp=> {
          this.tableLoading = false;
          console.log("界面显示",resp);
          if (resp.data && resp.status == 200) {
            var data = resp.data.obj.roleVos;
            _this.roles = data;
            _this.totalCount = resp.data.obj.listCount;
          }
        })
      },
      showRoleView(row){
        console.log(row);
        this.isDisabled = 1;
        this.role.roleName = row.roleName;
        this.role.roleDesc = row.roleDesc;
      },
      deleteRole(row){
          this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDeleteRole(row.id);
        }).catch(() => {
        });
      },
      doDeleteRole(ids){
        this.tableLoading = true;
        var _this = this;
        var datas = {
          id:ids,
        }
        this.postRequest("/user/role/delete/{id}",datas).then(resp=> {
          _this.tableLoading = false;
          console.log(datas);
          if (resp && resp.status == 200) {
            var data = resp.data;
            this.$message({type: 'success', message: "删除成功！"});
            _this.loadRoles();
          }
        })
      },
      emptyRoleData(){
        this.role = {
         id: '',
         roleId: '',
         roleName:'',
         roleDesc:'',
        }
      }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.head{
  position: absolute;
  width:1200px;
  top:30px;
  left:50px;
  font-size: 30px;
}
.first{
  font-family:  Georgia, Times, 'Times New Roman', serif;
  position: relative;
  width:1000px;
  top:160px;
  left:50px;
}
.main{
  position:relative;
  top:200px;
  left:100px;
}
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
