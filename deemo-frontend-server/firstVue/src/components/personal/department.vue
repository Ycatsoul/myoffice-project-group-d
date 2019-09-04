<template>
<div>
<el-container class="home-container">
   <div class="head">
    <p>{{this.$router.currentRoute.name}}</p>
     <hr>
   </div>
   <div class="content">
    <div class="first">
      <div style="margin-left: 1100px;display: inline">
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="showAddDepartmentView()">
            添加部门
          </el-button>
        </div>
     </div>
     <div class="main">
          <el-table
            :data="departments"
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
              prop="departmentName"
              align="center"    
              label="部门名称"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              prop="branchName"
              label="机构"
              width="200px">
            </el-table-column>
             <el-table-column
              align="center"
              prop="principalUserId"
              label="负责人"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              prop="connectPhone"
              label="联系电话"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              prop="connectTelephone"
              label="移动电话"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              label="修改"
              width="150">
              <template slot-scope="scope">
                <el-button @click="showDepartmentView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
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
                           @click="deleteDepartment(scope.row)">
                           <i class="el-icon-delete"></i>
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini" v-if="true" :disabled="multipleSelection.length==0"
                       @click="deleteManyDepartment">批量删除
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
<!-- 点击增加部门情况界面 -->
 <el-form :model="department" :rules="rules" ref="addDepartmentForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
          width="60%">
          <el-row>
            <el-col :span="24">
                <el-form-item label="部门名称:" prop="departmentName">
                  <el-input prefix-icon="el-icon-edit" v-model="department.departmentName" size="mini" style="width: 300px"
                           placeholder="请输入部门名称"></el-input>
                </el-form-item>
            </el-col>
           <el-col :span="24">
                <el-form-item label="机构:" prop="branchName">
                  <el-select v-model="department.branchName" style="width: 300px" size="mini" >
                    <el-option
                      v-for="item in branchNames"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name">
                    </el-option>
                  </el-select>
                </el-form-item>
            </el-col>
          </el-row>
           <el-row>
            <el-col :span="24">
              <div>
                <el-form-item label="部门负责人:" prop="principalUserId">
                  <el-select v-model="department.principalUserId" style="width: 300px" size="mini" >
                    <el-option
                      v-for="item in allPrincipals"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="24">
              <div>
                <el-form-item label="联系电话:" prop="connectPhone">
                  <el-input prefix-icon="el-icon-edit" v-model="department.connectPhone" size="mini" style="width: 300px"
                         ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
           <el-row>
            <el-col :span="24">
              <div>
                <el-form-item label="移动电话:" prop="connectTelephone">
                  <el-input prefix-icon="el-icon-edit" v-model="department.connectTelephone" size="mini" style="width: 300px"
                          ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addDepartment('addDepartmentForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
   </el-form>  
 </div>
</template>

<script>
export default {
  data () {
    return {
      department:{
        id:'',
        departmentId:'',
        departmentName:'',
        connectPhone:'',
        connectTelephone:'',
        principalUserId:'',
        branchName:'',
      },
      branchNames:[],
      dialogVisible: false,
      isDisabled:0,
      totalCount: -1,
      currentPage: 1,
      tableLoading:false,
      multipleSelection:[],
      show:false,
      departments: [
        //   branchName:'华北电力科学研究院',
        //   branchShortName: '华电',
        // }, {
        //    branchName: '中国科学院声学研究院',
        //    branchShortName: '中科声研究院',
        // }, {
        //    branchName: '北大青岛集团',
        //    branchShortName: '青岛',
        // }, {
        //    branchName: '大唐国际盘山发电有限公司',
        //    branchShortName: '盘电',
        ],
        allPrincipals:[],
    }
  },
    mounted: function () {
      this.loadDepartments();
      this.getAllBranchsInfo();
      this.display();
    },
  methods:{
        getAllBranchsInfo(){
          this.postRequest("/employee/basic/listEmpVo").then(resp=> {
          if (resp.data && resp.data.status == 200) {
            console.log("获取所有机构信息",resp);
            this.branchNames = resp.data.obj;
          }
        });
        },
        display(){
          if(this.departments.length>0){
            this.show=true
          }
        },
       showAddDepartmentView(){
        this.idIsDisabled = 1;
        this.dialogTitle = "添加部门信息";
        this.$refs['addDepartmentForm'].clearValidate() // 重置验证
        this.department.branchName = '';
        this.department.departmentName = '';
        this.department.principalUserId = '';
        this.department.connectPhone = '';
         this.department.connectTelephone = '';
        this.dialogVisible = true;
        // this.getRequest("/employee/basic/maxWorkID").then(resp=> {
        //   if (resp && resp.status == 200) {
        //     _this.department.workId = resp.data;
        //   }
        // })
    },
     handleSelectionChange(val) {
        this.multipleSelection = val;
      },
     keywordsChange(val){
        if (val == '') {
          this.loadDepartments();
        }
      },
     currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadDepartments();
      },
       addDepartment(formName){
        var _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.department.id) {
              //更新
              this.tableLoading = true;
              this.postRequest("/employee/prize/updateEmpById", this.emp).then(resp=> {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyDepartmentData();
                  _this.loadDepartments();
                }
              })
            } else {
              //添加
              this.tableLoading = true;              
              this.postRequest("/employee/prize/addPrize", this.emp).then(resp=> {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyDepartmentData();
                  _this.loadDepartments();
                }
              })
            }
            _this.idIsDisabled = 0;
          } else {
            return false;
          }
        });
      },
     deleteManyDepartment(){
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          for (var i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doDeleteMultiDepartment(ids);
        }).catch(() => {
        });
      },
      doDeleteMultiDepartment(ids){
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
            _this.loadDepartments();
          }
        })
      },
       loadDepartments(){
        var _this = this;
        var datas = {
          "id":"",
          "branchId": "",
          "branchName":"",
          "size": "10",
          "start": (this.currentPage-1)*10          
        };
        this.tableLoading = true;
        this.postRequest("/user/branch/list",datas).then(resp=> {
          this.tableLoading = false;
          console.log("haha",resp);
          if (resp.data && resp.status == 200) {
            var data = resp.data.obj.empPrizeVoList;
            _this.departments = data;
            _this.totalCount = resp.data.obj.listCount;
          }
        })
      },
      showDepartmentView(row){
        console.log(row);
        this.dialogTitle = "编辑部门信息";
        this.$refs['addDepartmentForm'].clearValidate() // 重置验证
        // this.department = row;
        this.department.branchName = row.branchName;
         this.department.departmentId = row.departmentId;
        this.department.departmentName = row.departmentName;
        this.department.connectPhone = row.connectPhone;
        this.department.connectTelephone = row.connectTelephone;
        this.department.principalUserId = row.principalUserId;
        this.dialogVisible = true;
      },
      deleteDepartment(){
          this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDeleteDepartment(row.id);
        }).catch(() => {
        });
      },
      doDeleteDepartment(ids){
        this.tableLoading = true;
        var _this = this;
        var datas = {
          id:ids,
          operationUserId:1
        }
        this.postRequest("/user/branch/delete/{id}",datas).then(resp=> {
          _this.tableLoading = false;
          console.log(datas);
          if (resp && resp.status == 200) {
            var data = resp.data;
            this.$message({type: 'success', message: "删除成功！"});
            _this.loadDepartments();
          }
        })
      },
      emptyDepartmentData(){
        this.branch = {
         id: '',
         branchName: '',
         departmentName:'',
         departmentId:'',
         connectPhone:'',
         connectTelephone:'',
        }
      },
       cancelEidt(){
        this.dialogVisible = false;
        this.idIsDisabled = 0;
        this.emptyDepartmentData();
      },
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
