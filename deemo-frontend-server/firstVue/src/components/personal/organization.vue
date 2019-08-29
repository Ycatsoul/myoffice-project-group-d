<template>
<el-container class="home-container">
   <div class="head">
    <p>{{this.$router.currentRoute.name}}</p>
     <hr>
   </div>
   <div class="content">
    <div class="first">
     <el-form :model="branch"  ref="addOrganizationForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: center;margin-left:100px;">
          <el-row style="margin-top: 20px;padding: 0px;">
            <el-col :span="10">
              <div>
                <el-form-item label="机构名称:" prop="branchName">
                  <el-input prefix-icon="el-icon-edit" v-model="branch.branchName" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
              </div>
            </el-col>
              <el-col :span="10">
              <div>
                <el-form-item label="机构简称:" prop="branchShortName">
                  <el-input prefix-icon="el-icon-edit" v-model="branch.branchShortName" size="mini" style="width: 300px"
                           ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="4">
            <div style="margin-top:6px;">
            <el-button size="mini" type="primary" @click="addOrganization('addOrganizationForm')" :disabled="isDisabled=='1'">添加</el-button>
            <el-button size="mini" type="primary" @click="changeOrganization('addOrganizationForm')" :disabled="isDisabled=='0'">保存修改</el-button>
            </div>
            </el-col>
          </el-row>
      </div>
    </el-form>
     </div>
     <div class="main" v-if="branchs">
          <el-table
            :data="branchs"
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
              prop="branchName"
              align="center"    
              label="机构名称"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              prop="branchShortName"
              label="机构简称"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              label="修改"
              width="150">
              <template slot-scope="scope">
                <el-button @click="showOrganizationView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
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
                           @click="deleteOrganization(scope.row)">
                           <i class="el-icon-delete"></i>
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini" v-if="branchs.length>0" :disabled="multipleSelection.length==0"
                       @click="deleteManyOrganization">批量删除
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
      branch:{
        id:'',
        branchId:'',
        branchName:'',
        branchShortName:'',
      },
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

      this.loadOrganizations();
 
    },
  methods:{
     handleSelectionChange(val) {
        this.multipleSelection = val;
      },
     keywordsChange(val){
        if (val == '') {
          this.loadOrganizations();
        }
      },
     currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadOrganizations();
      },
      //修改
      changeOrganization(formName){
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
                  _this.emptyOrganizationData();
                  _this.loadOrganizations();
                }
              })
          }
          else{
            return false;
          }
        });
      },
      //增加
       addOrganization(formName){
        var _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
              this.tableLoading = true;
              this.postRequest("/user/branch/save", this.branch).then(resp=> {
                _this.tableLoading = false;
                console.log("增加部分",resp);
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyOrganizationData();
                  _this.loadOrganizations();
                }
              })
          }
          else{
            return false;
          }
        });
      },
     deleteManyOrganization(){
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          for (var i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doDeleteMultiOrganization(ids);
        }).catch(() => {
        });
      },
      doDeleteMultiOrganization(ids){
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
            _this.loadOrganizations();
          }
        })
      },
       loadOrganizations(){
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
      showOrganizationView(row){
        console.log(row);
        this.isDisabled = 1;
        this.branch = row;
        this.branch.branchName = row.branchName;
        this.branch.branchShortName = row.branchShortName;
      },
      deleteOrganization(row){
          this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDeleteOrganization(row.id);
        }).catch(() => {
        });
      },
      doDeleteOrganization(ids){
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
            _this.loadOrganizations();
          }
        })
      },
      emptyOrganizationData(){
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
