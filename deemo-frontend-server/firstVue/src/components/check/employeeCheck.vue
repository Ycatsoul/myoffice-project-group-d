<template>
<el-container class="home-container">
   <div class="head">
    <p>{{this.$router.currentRoute.name}}</p>
     <hr>
   </div>
<div class="content">
  <div class="first">
     <el-form :model="branch"  ref="addBranchForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: center;margin-left:100px;">
          <el-row style="margin-top: 20px;padding: 0px;">
            <el-col :span="10">
              <div>
                <el-form-item label="签卡日期:" prop="singinTime">
                  <el-input prefix-icon="el-icon-edit" v-model="branch.branchName" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>]
              <el-col :span="10">
              <div>
                <el-form-item label="签卡备注:" prop="singinDesc">
                  <el-input type="textarea" prefix-icon="el-icon-edit" v-model="branch.branchShortName" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="4">
            <div style="margin-top:6px;">
            <el-form-item prop="singinTag">
            <el-button size="mini" type="primary"  v-model="branch.singinTag" @click="addBranch('addBranchForm')" :disabled="isDisabled=='1'">签到</el-button>
            <el-button size="mini" type="primary" v-model="branch.singinTag" @click="changeBranch('addBranchForm')" :disabled="isDisabled=='0'">签退</el-button>
            </el-form-item>
            </div>
            </el-col>
          </el-row>
      </div>
    </el-form>
  </div>
     <div class="main" v-if="show">
       <el-row>
         <el-col span="3">
           <p>您的签到信息:</p>
         </el-col>
       </el-row>
         <el-form :model="branch">
           <el-row>
             <el-col span="10">
                <el-form-item label="用 户 号:" prop="username">
                  <el-input prefix-icon="el-icon-edit" v-model="branch.username" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
             </el-col>
             <el-col span="10">
                <el-form-item label="姓 名:" prop="name">
                  <el-input prefix-icon="el-icon-edit" v-model="branch.username" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
             </el-col>
           </el-row>
            <el-row>
             <el-col span="10">
                <el-form-item label="所属部门:" prop="departName">
                  <el-input prefix-icon="el-icon-edit" v-model="branch.departName" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
             </el-col>
             <el-col span="10">
                <el-form-item label="所属机构:" prop="branchName">
                  <el-input prefix-icon="el-icon-edit" v-model="branch.branchName" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
             </el-col>
           </el-row>  
           <el-row>
             <el-col span="10">
               <el-form-item label="您的签到时间:" prop="singinTime">
                  {{branch.singinTime}}
                </el-form-item>
             </el-col>
           </el-row>
           <el-row>
             <el-col span="10">
               <el-form-item label="签卡备注:" prop="singinDesc">
                  <el-input type="textarea" prefix-icon="el-icon-edit" v-model="branch.branchName" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
             </el-col>
           </el-row>
         </el-form>
     </div>
   </div>
</el-container>
  
  
</template>
<script>
export default {
  data () {
    return {
      branch:{
        id:'',
        name:'',
        singinTime:'',
        singinTag:'',
        singinDesc:'',
        username:'',
        departmentName:'',
        branchName:'',
      },
   
      show:false,
      isDisabled:0,
      totalCount: -1,
      currentPage: 1,
      tableLoading:false,
      multipleSelection:[],
      branchs: [{
          branchName:'华北电力科学研究院',
          branchShortName: '华电',
        }, {
           branchName: '中国科学院声学研究院',
          branchShortName: '中科声研究院',
        }, {
              branchName: '北大青岛集团',
          branchShortName: '青岛',
        }, {
             branchName: '大唐国际盘山发电有限公司',
          branchShortName: '盘电',
        }],
    }
  },
    mounted: function () {

      this.loadBranchs();
 
    },
  methods:{
     handleSelectionChange(val) {
        this.multipleSelection = val;
      },
     keywordsChange(val){
        if (val == '') {
          this.loadBranchs();
        }
      },
     currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadBranchs();
      },
      //修改
      changeBranch(formName){
        var _this = this;
        _this.isDisabled=0;
        this.$refs[formName].validate((valid) => {
          if (valid) {
              this.tableLoading = true;
              this.postRequest("/user/branch/update", this.branch).then(resp=> {
                _this.tableLoading = false;
                console.log("修改",this.branch);
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyBranchData();
                  _this.loadBranchs();
                }
              })
          }
          else{
            return false;
          }
        });
      },
      //增加
       addBranch(formName){
         
        var _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
              this.postRequest("/user/branch/save", this.branch).then(resp=> {
                console.log("增加部分",resp);
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.showBranchView(data);
                }
              })
          }
          else{
            return false;
          }
        });
      },
     deleteManyBranch(){
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          for (var i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doDeleteMultiBranch(ids);
        }).catch(() => {
        });
      },
      doDeleteMultiBranch(ids){
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
            _this.loadBranchs();
          }
        })
      },
       loadBranchs(){
        
        var _this = this;
        var datas = {
          "branchName":'',
          "branchId": "",
          "size": "10",
          "start": (this.currentPage-1)*10          
        };
        this.tableLoading = true;
        this.postRequest("/user/branch/list",datas).then(resp=> {
          this.tableLoading = false;
          console.log("界面显示",resp);
          if (resp.data && resp.status == 200) {
            var data = resp.data.obj.branchVos;
            _this.branchs = data;
            _this.totalCount = resp.data.obj.listCount;
          }
        })
      },
      showBranchView(row){
        console.log(row);
        this.show=true;
        this.isDisabled = 1;
        this.branch.branchName = row.branchName;
        this.branch.branchShortName = row.branchShortName;
        this.branch.name=row.name;
        this.branch.username=row.username;
        this.branch.singinTime=row.singinTime;
        this.branch.singinDesc=row.singinDesc;
      },
      deleteBranch(row){
          this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDeleteBranch(row.id);
        }).catch(() => {
        });
      },
      doDeleteBranch(ids){
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
            _this.loadBranchs();
          }
        })
      },
      emptyBranchData(){
        this.branch = {
         id: '',
         branchId: '',
         branchName:'',
         branchShortName:'',
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
  left:140px;
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
