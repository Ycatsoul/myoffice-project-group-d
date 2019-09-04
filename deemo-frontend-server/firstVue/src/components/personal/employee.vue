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
                     @click="showAddEmployeeView()">
            添加员工
          </el-button>
        </div>
     </div>
     <div class="main">
          <el-table
            :data="employees"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width:100%">
            <el-table-column
              prop="id"
              type="selection"
              align="left"
              width="30">
            </el-table-column>
            <el-table-column
              prop="username"
              align="center"    
              label="用户ID"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              prop="name"
              label="姓名"
              width="200px">
            </el-table-column>
             <el-table-column
              align="center"
              prop="password"
              label="密码"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              prop="roleName"
              label="角色"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              label="用户详情"
              width="">
              <template slot-scope="scope">
                <el-button @click="showEmployeeDetail(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">详情             
                </el-button>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              label="修改"
              width="150">
              <template slot-scope="scope">
                <el-button @click="showEmployeeView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
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
                           @click="deleteEmployee(scope.row)">
                           <i class="el-icon-delete"></i>
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini"  :disabled="multipleSelection.length==0"
                       @click="deleteManyEmployee">批量删除
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
<!-- 点击增加员工情况界面 -->
 <el-form :model="employee" :rules="rules" ref="addEmployeeForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
          width="60%">
          <el-row>
            <el-col :span="12">
              <div>
                <el-form-item label="用户登录名:" prop="username">
                  <el-input prefix-icon="el-icon-edit" v-model="employee.username" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
              </div>
            </el-col>
             <el-col :span="12">
              <div>
                <el-form-item label="密码:" prop="password">
                <el-input prefix-icon="el-icon-edit" v-model="employee.password" size="mini" style="width: 300px"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
                <el-form-item label="确认密码:" prop="rPassword">
                <el-input prefix-icon="el-icon-edit" v-model="employee.rPassword" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
            </el-col>
          </el-row>
          <el-row>
           <el-col :span="12">
                <el-form-item label="真实姓名:" prop="name">
                   <el-input prefix-icon="el-icon-edit" v-model="employee.name" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
           </el-col>
          </el-row>
           <el-row>
             <el-col :span="12">
                <el-form-item label="所在部门:" prop="departmentName">
                  <el-select v-model="employee.departmentName" style="width: 300px" size="mini" >
                    <el-option
                      v-for="item in departments"
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
                <el-form-item label="性别:" prop="gender">
                  <el-radio-group v-model="employee.gender">
                    <el-radio label="男" >男</el-radio>
                    <el-radio style="margin-left: 15px" label="女">女</el-radio>
                  </el-radio-group>
                </el-form-item>
            </el-col>
            </el-row>
            <el-row>
            <el-col :span="12">
                <el-form-item label="角色:" prop="roleName">
                  <el-input prefix-icon="el-icon-edit" v-model="employee.roleName" size="mini" style="width: 300px"
                         ></el-input>
                </el-form-item>
            </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="添加头像" prop="imageUrl">
                 <el-upload
                    class="avatar-uploader"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <img v-if="employee.imageUrl" :src="employee.imageUrl" class="avatar" v-model="employee.imageUrl">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
                </el-form-item>
             </el-col>
            </el-row>
            <el-row>
             <el-col :span="12">
                <el-form-item label="当前状态" prop="idBlocked">
                  <el-select v-model="employee.idBlocked" style="width: 300px" size="mini" >
                    <el-option
                      v-for="item in status"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addEmployee('addEmployeeForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
   </el-form>  
   <!-- 显示员工信息详情 -->
   <el-form :model="employee" :rules="rules" ref="showEmployeeForm" style="margin:200px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle2"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible2"
          width="60%">
          <el-row>
            <el-col :span="12">
                <el-form-item label="用户登录名:" prop="username"> 
                {{employee.username}}
                </el-form-item>
            </el-col>
          </el-row>
          <el-row>
             <el-col :span="12">
                <el-form-item label="密码:" prop="password">
                 {{employee.password}}
                </el-form-item>
            </el-col>
          </el-row>
          <el-row>
           <el-col :span="12">
                <el-form-item label="真实姓名:" prop="name">
                   {{employee.name}}
                </el-form-item>
            </el-col>
          </el-row>
           <el-row>
             <el-col :span="24">
                <el-form-item label="所在部门:" prop="departmentName">
                  {{employee.departmentName}}
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="gender:" prop="gender">
                  {{employee.gender}}
                </el-form-item>
            </el-col>
           </el-row>
            <el-row>
            <el-col :span="12">
                <el-form-item label="角色:" prop="roleName">
                {{employee.roleName}}
                </el-form-item>
            </el-col>
           </el-row>
             <el-col :span="12">
                <el-form-item label="当前状态" prop="idBlocked">
                  <span v-if="employee.idBlocked==1">正常</span>
                  <span v-if="employee.idBlocked==0">被屏蔽</span>
                </el-form-item>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">返回</el-button>
          </span>
       <div style="width:200px;height:200px;position:relative;bottom:400px;left:500px;">
        <el-col :span="24">
                 <img :src="employee.imageUrl" class="avatar">
                </el-form-item>
        </el-col>
      </div>
        </el-dialog>
      </div>
   </el-form>  
 </div>
</template>

<script>
export default {
  data () {
    return {
      employee:{
        imageUrl: '',
        id:'',
        name:'',
        gender:'',
        roleName:'',
        username:'',
        password:'',
        rPassword:'',
        departmentName:'',
        idBlocked:'',
      },
      status:[{id:0,name:"被屏蔽"},{id:1,name:"正常"}],
      employees:[
        {
          username:"ad123",
          password:123456,
          name:"帅哥",
          gender:"男",
          idBlocked:1,
          imageUrl:require("@/assets/1.jpg"),
        }
      ],
      allPrincipals:[],
      departments: [{
          id:1,
          name:'华北电力科学研究院',
        }, {
          id:2,
           name: '中国科学院声学研究院',
        }, {
          id:3,
           name: '北大青岛集团',
        }, {
          id:4,
           name: '大唐国际盘山发电有限公司',
        }],
      dialogVisible:false,
      dialogVisible2:false,
      dialogTitle:'',
      dialogTitle2:'',
      isDisabled:0,
      totalCount: -1,
      currentPage: 1,
      tableLoading:false,
      multipleSelection:[],
    }
  },
    mounted: function () {
      this.loadEmployees();
      this.getAllUsersInfo();
    },
  methods:{
    showEmployeeDetail(row){
        this.dialogTitle2 = "用户详细信息";
        this.$refs['showEmployeeForm'].clearValidate() // 重置验证
        this.employee = row;
        this.employee.departmentName = row.departmentName;
        this.employee.gender = row.gender;
        this.employee.imageUrl = row.imageUrl;
        this.employee.roleName = row.roleName;
        this.employee.username = row.username;
        this.employee.name = row.name;
        this.employee.idBlocked = row.idBlocked;
        this.employee.password = row.password;
        this.dialogVisible2 = true;
    },
      handleAvatarSuccess(res, file) {
        this.employee.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt1M = file.size / 1024 / 1024 < 1;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt1M) {
          this.$message.error('上传头像图片大小不能超过 1MB!');
        }
        return isJPG && isLt1M;
      },
  
      getAllUsersInfo(){
          this.postRequest("/employee/basic/listEmpVo").then(resp=> {
          if (resp.data && resp.data.status == 200) {
            console.log("1111",resp);
            this.allPrincipals = resp.data.obj;
          }
        });
        },
       showAddEmployeeView(){
        this.idIsDisabled = 1;
        this.dialogTitle = "添加员工记录";
        this.$refs['addEmployeeForm'].clearValidate() // 重置验证
        this.employee.departmentName = '';
        this.employee.rPassword='',
        this.employee.imageUrl = '';
        this.employee.gender = '';
        this.employee.name = '';
        this.employee.roleName = '';
        this.employee.idBlocked = '';
        this.employee.username = '';
         this.employee.password = '';
        this.dialogVisible = true;
        // this.getRequest("/employee/basic/maxWorkID").then(resp=> {
        //   if (resp && resp.status == 200) {
        //     _this.employee.workId = resp.data;
        //   }
        // })
    },
     showEmployeeView(row){
        console.log(row);
        this.dialogTitle = "修改员工信息";
        this.$refs['addEmployeeForm'].clearValidate() // 重置验证
        // this.employee = row;
        this.employee.departmentName = row.departmentName;
        this.employee.name = row.name;
        this.employee.gender = row.gender;
        this.employee.imageUrl = row.imageUrl;
        this.employee.roleName = row.roleName;
        this.employee.username = row.username;
        this.employee.idBlocked = row.idBlocked;
        this.employee.password = row.password;
        this.employee.rPassword = row.rPassword;
        this.dialogVisible = true;
      },
     handleSelectionChange(val) {
        this.multipleSelection = val;
      },
     keywordsChange(val){
        if (val == '') {
          this.loadEmployees();
        }
      },
     currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadEmployees();
      },
       addEmployee(formName){
        var _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.employee.id) {
              //更新
              this.tableLoading = true;
              this.postRequest("/employee/prize/updateEmpById", this.emp).then(resp=> {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyEmployeeData();
                  _this.loadEmployees();
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
                  _this.emptyEmployeeData();
                  _this.loadEmployees();
                }
              })
            }
            _this.idIsDisabled = 0;
          } else {
            return false;
          }
        });
      },
     deleteManyEmployee(){
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          for (var i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doDeleteMultiEmployee(ids);
        }).catch(() => {
        });
      },
      doDeleteMultiEmployee(ids){
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
            _this.loadEmployees();
          }
        })
      },
       loadEmployees(){
        var _this = this;
        var datas = {
          "id":"",
          "size": "10",
          "start": (this.currentPage-1)*10          
        };
        this.tableLoading = true;
        this.postRequest("/user/branch/list",datas).then(resp=> {
          this.tableLoading = false;
          console.log("haha",resp);
          if (resp.data && resp.status == 200) {
            var data = resp.data.obj.empPrizeVoList;
            _this.employees = data;
            _this.totalCount = resp.data.obj.listCount;
          }
        })
      },
     
      deleteEmployee(){
          this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDeleteEmployee(row.id);
        }).catch(() => {
        });
      },
      doDeleteEmployee(ids){
        this.tableLoading = true;
        var _this = this;
        var datas = {
          id:ids,
        }
        this.postRequest("/user/branch/delete/{id}",datas).then(resp=> {
          _this.tableLoading = false;
          console.log(datas);
          if (resp && resp.status == 200) {
            var data = resp.data;
            this.$message({type: 'success', message: "删除成功！"});
            _this.loadEmployees();
          }
        })
      },
      emptyEmployeeData(){
        this.employee = {
         imageUrl:'',
         id: '',
         departmentName: '',
         gender:'',
         roleName:'',
         idBlocked:'',
         username:'',
         password:'',
         rPassword:'',
         name:'',
        }
      },
       cancelEidt(){
        this.dialogVisible2=false;
        this.dialogVisible = false;
        this.idIsDisabled = 0;
        this.emptyEmployeeData();
      },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
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
