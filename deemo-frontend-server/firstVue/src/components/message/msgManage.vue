<template>
<div>
<el-container class="home-container">
   <div class="head">
    <p>{{this.$router.currentRoute.name}}</p>
     <hr>
   </div>
   <div class="content">
     <div class="second">
       <el-row style="margin-top: 10px">
                <el-col :span="10">
                   请选择查询日期范围:
                   <el-date-picker
                      v-model="dateScope"
                      type="datetimerange"
                      align="right"
                      unlink-panels
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                      value-format="yyyy-MM-dd-HH-mm"
                      :picker-options="pickerOptions">
                    </el-date-picker>
                </el-col>
                <el-col :span="5"  style="margin-top:25px;">
                  <el-button icon="el-icon-search" type="primary" size="mini" @click="searchEmp">搜索</el-button>
                </el-col>
              </el-row>
     </div>
    <div class="first">
      <div style="margin-left: 1100px;display: inline">
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="showAddMessageView()">
            添加新消息
          </el-button>
        </div>
     </div>
     <div class="main" >
          <el-table
            :data="messages"
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
              prop="messageTitle"
              align="center"    
              label="消息标题"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              prop="messageType"
              label="消息类型"
              width="200px">
            </el-table-column>
            <el-table-column
              align="center"
              prop="messageContent"
              label="消息内容"
              width="150">
              <template slot-scope="scope">
                <el-button @click="showContentDetail(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">
                           {{scope.row.messageContent}}
                </el-button>
                 </template>
             </el-table-column>
             <el-table-column
              align="center"
              prop="sendUserId"
              label="创建者"
              width="">
            </el-table-column>
             <el-table-column
              prop="recipientId"
              align="center"
              label="发送对象"
              width="150">
              <template slot-scope="scope">
                <el-button @click="showToUserId(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">
                           {{scope.row.recipientId}}
                </el-button>
                 </template>
             </el-table-column>
            <el-table-column
              align="center"
              prop="beginTime"
              label="开始时间"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              prop="endTime"
              label="结束时间"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              prop="publishTime"
              label="创建时间"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              label="修改"
              width="80">
              <template slot-scope="scope">
                <el-button @click="showMessageView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">
                           <i class="el-icon-edit" ></i>
                </el-button>
                 </template>
             </el-table-column>
              <el-table-column
              align="center"
              label="删除"
              width="80">
              <template slot-scope="scope">
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteMessage(scope.row)">
                           <i class="el-icon-delete"></i>
                </el-button>
              </template>
            </el-table-column>
             <el-table-column
              align="center"
              label="发布"
              width="80">
              <template slot-scope="scope">
                <el-button type="danger" :disabled="pulishButton" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="pulish(scope.row)">
                           发布
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini" v-if="true" :disabled="multipleSelection.length==0"
                       @click="deleteManyMessage">批量删除
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
 <!-- 显示消息基本内容的模态框 -->
        <el-dialog title="该消息的基本内容" :visible.sync="dialogMessageVisible">
          <el-form :model="message" ref="basicContent">
            <el-form-item>
              <el-input  type="textarea" v-model="message.messageContent" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer" align="center">
            <el-button @click="dialogMessageVisible = false">退 出</el-button>
          </div>
        </el-dialog>
        <!-- 显示发送对象的模态框 -->
        <el-dialog title="消息的发送对象" :visible.sync="dialogToUserVisible">
          <el-form :model="message" ref="basicContent">
            <el-form-item>
              <el-input type="textarea" v-model="message.recipientId" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer" align="center">
            <el-button @click="dialogToUserVisible = false">退 出</el-button>
          </div>
        </el-dialog>
  <!-- 点击增加消息情况界面 -->
 <el-form :model="message" :rules="rules" ref="addMessageForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
           width="60%">
          <el-row>
            <el-col :span="24">
                <el-form-item label="消息标题:" prop="messageTitle">
                  <el-input prefix-icon="el-icon-edit" v-model="message.messageTitle" size="mini" style="width: 300px"
                          ></el-input>
                </el-form-item>
            </el-col>
          </el-row>
          <el-row>
           <el-col :span="24">
                <el-form-item label="消息类型:" prop="messageType">
                  <el-select v-model="message.messageTypeName" style="width: 300px" size="mini" >
                    <el-option
                      v-for="item in messageTypes"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name">
                    </el-option>
                  </el-select>
                </el-form-item>
            </el-col>
          </el-row>
          <el-row>
             <el-col :span="12">
               <el-form-item label="开始时间:" prop="beginTime">
                  <el-date-picker
                    v-model="message.beginTime"
                    type="datetime"
                    placeholder="选择日期时间"
                    default-time="12:00:00">
                  </el-date-picker>
               </el-form-item>
            </el-col>
          </el-row>
          <el-row>
             <el-col :span="12">
               <el-form-item label="结束时间:" prop="endTime">
                  <el-date-picker
                    v-model="message.endTime"
                    type="datetime"
                    placeholder="选择日期时间"
                    default-time="12:00:00">
                  </el-date-picker>
               </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
                <el-form-item label="发送对象:" >
                  <el-radio-group v-model="message.recipientId" @change="userChange()">
                    <el-radio label="1" :disabled="recipientIdButton">所有人</el-radio>
                    <el-radio label="2" :disabled="recipientIdButton" check="checked">选定特定对象</el-radio>
                  </el-radio-group>
                </el-form-item>
            </el-col>
          </el-row>
          <!-- 筛选特定对象的表单 -->
          <div v-if="showRange">
          <el-row >
              <el-col :span="24">
                <el-form-item label="筛选范围"  >
                  <el-checkbox v-model="checkedByBranch"  @change="showScreenRange()">按机构</el-checkbox>
                  <el-checkbox  v-model="checkedByDepart" @change="showScreenRange()">按部门</el-checkbox>
                  <el-checkbox  v-model="checkedByUserId" @change="showScreenRange()">按员工号</el-checkbox>
                  <el-checkbox  v-model="checkedByName" @change="showScreenRange()">按姓名</el-checkbox>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="6">
                        <el-form-item label="机构:" >
                          <el-select v-model="message.branchName" :disabled="ifSelectBranch" style="width: 150px" size="mini" @change="getBranch($event)" >
                            <el-option
                              v-for="item in branchNames"
                              :key="item.id"
                              :label="item.name"
                              :value="item.name">
                            </el-option>
                          </el-select>
                        </el-form-item>
              </el-col>
              <el-col :span="6">
                         <el-form-item label="部门:" >
                          <el-select v-model="message.departmentName"  :disabled="ifSelectDepart" style="width: 150px" size="mini" >
                            <el-option
                              v-for="item in departmentNames"
                              :key="item.id"
                              :label="item.name"
                              :value="item.name">
                            </el-option>
                          </el-select>
                        </el-form-item>
              </el-col>
                  <el-col :span="6">
                         <el-form-item label="员工号:" >
                          <el-input prefix-icon="el-icon-edit" v-model="message.userId" :disabled="ifSelectUserId"size="mini" style="width: 120px"
                          ></el-input>
                        </el-form-item>
                  </el-col> 
                  <el-col :span="6">
                         <el-form-item label="姓名:" >
                          <el-input prefix-icon="el-icon-edit" v-model="message.name"  :disabled="ifSelectName"size="mini" style="width: 100px"
                          ></el-input>
                        </el-form-item>
                  </el-col>
            </el-row>
            <el-row>
              <el-col offset="10">
                   <el-button size="mini" type="primary" @click="sure">确定选择范围</el-button>
              </el-col>
            </el-row>
          <el-row>
            <el-col :span="24">
                <el-form-item label="请选择发送对象:" prop="recipientId">
                  <el-checkbox  v-model="message.checkAll" @change="handleCheckAllChange">全选</el-checkbox>
                  <div style="margin:15px 0;"></div>
                  <el-checkbox-group v-model="message.checkedUsers" @change="handleCheckedCitiesChange">
                    <el-checkbox v-for="city in message.cities" :label="city" :key="city">{{city}}</el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
            </el-col>
         </el-row>
       </div>
          <el-row>
            <el-col :span="24">
                <el-form-item label="消息内容:" prop="messageContent">
                  <el-input type="textarea" v-model="message.messageContent"></el-input>
                </el-form-item>
            </el-col>
         </el-row>
         <span slot="footer" class="dialog-footer">
          <el-button size="mini" @click="cancelEidt">取 消</el-button>
          <el-button size="mini" type="primary" @click="addMessage('addMessageForm')">确 定</el-button>
        </span>
        </el-dialog>
      </div>
   </el-form>  
 </div>
</template>

<script>
  const cityOptions = ['上海', '北京', '广州', '深圳'];
export default {
  data () {
    return {
      dateScope:'',
      message:{
        checkAll: false,
        checkedUsers: ['上海', '北京'],
        cities:cityOptions,
        isIndeterminate: true,
        branchName:"",
        departmentName:'',
        checkList:[],
        departmentId:'',
        messageTitle:'',
        messageType:'',
        messageContent:'',
        beginTime:'',
        endTime:'',
        recipientId:'2',
      },
       messages:[
        {
          messageTitle:"ad123",
          messageContent:"123456sssdsds ",
          recipientId:"小明好的哈哈"
        }
      ],
      toUserIdButton: false,
      pulishButton: false,
      checkedByBranch:'',
      checkedByDepart:'',
      checkedByUserId:'',
      checkedByName:'',
      ifSelectBranch:'',
      ifSelectDepart:'',
      ifSelectUserId:'',
      ifSelectName:'',
       dialogMessageVisible: false,
      dialogToUserVisible: false,
      showRange:true,
      messageTypes:[{id:1,name:"一般消息"},{id:2,name:"紧急消息"}],
      dialogVisible: false,
      isDisabled:0,
      totalCount: -1,
      currentPage: 1,
      tableLoading:false,
      multipleSelection:[],
      show:false,
      branchNames:[],
      departmentNames:[],
      allPrincipals:[],
      pickerOptions: {
          shortcuts: [{
              text: '今天',
              onClick(picker) {
               const end = new Date();
               const start = new Date();
               var hours = start.getHours();
               var minutes = start.getMinutes();
               var seconds = start.getSeconds();
               var milliseconds = hours*3600*1000+minutes*60*1000+seconds*1000;
               start.setTime(start.getTime()-milliseconds);
               end.setTime(end.getTime()+3600*1000*24-milliseconds);
              picker.$emit('pick', [start, end]);
            }
          },{
            text: '本周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              var m = start.getDay();
              var hours = start.getHours();
              var minutes = start.getMinutes();
              var seconds = start.getSeconds();
              var milliseconds = hours*3600*1000+minutes*60*1000+seconds*1000;
              start.setTime(start.getTime() - 3600 * 1000 * 24 * m - milliseconds);
              end.setTime(end.getTime() + 3600 * 1000 * 24 * (7-m+1) - milliseconds);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '本月',
            onClick(picker) {
              var day;
              const end = new Date();
              const start = new Date();
              var hours = start.getHours();
              var minutes = start.getMinutes();
              var seconds = start.getSeconds();
              var milliseconds = hours*3600*1000+minutes*60*1000+seconds*1000;
              var month = start.getMonth()+1;
              if(month<=7){
                if(month%2==1){
                   day = 31;
                }
                else{
                  day = 30;
                }
              }
              else{
                if(month%2==0){
                  day = 30;
                }
                else{
                  day = 31;
                }
              }
              var today = start.getDate();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * (today-1)-milliseconds);
              end.setTime(end.getTime()+ 3600 * 1000 * 24 *(day-today)-milliseconds);
              picker.$emit('pick', [start, end]);
            }
          },]
        },
    }
  },
    mounted: function () {
      this.loadMessages();
      this.getAllBranchsInfo();
      this.getAlldepartmentsInfo(),
      this.disPlay();
    },
   methods:{
     // 机构下拉框的值改变，部门下拉框实现二级联动
    getBranch: function (value) {
      let tempValue = []
      this.branchNames = []
      this.departmentName = ''
      let allBranchSubDepart = this.departmentNames
      for (let val of allBranchSubDepart) {
        if (value === val.branchName) {
          tempValue.push({
            departmentName: val.departmentName,
            departmentId: val.departmentId
          })
        }
      }
      this.branchSubDepart = tempValue
    },
    // 发布消息
      pulish: function (row) {
      this.$confirm('消息发布后不可修改, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '发布成功!'
        })
        this.pulishButton = true
      }).catch(() => {})
    },
      // 显示消息内容详情
    showContentDetail: function (row) {
      this.dialogMessageVisible = true
      this.message.messageContent = row.messageContent
    },
    // 显示所有的发送对象
    showToUserId: function (row) {
      if (row.toUserId !== '所有人') {
        this.dialogToUserVisible = true
        this.message.recipientId = row.recipientId
      }
    },
    showScreenRange(){
       if (this.checkedByBranch) {
        this.ifSelectBranch = false
        this.ifSelectDepart = true
        this.ifSelectUserId = true
        this.ifSelectName = true
        this.checkedByDepart = false
        this.checkedByUserId = false
        this.checkedByName = false
      } else if (this.checkedByDepart) {
        this.ifSelectBranch = false
        this.ifSelectDepart = false
        this.ifSelectUserId = true
        this.ifSelectName = true
        this.checkedByBranch = false
        this.checkedByUserId = false
        this.checkedByName = false
      } else if (this.checkedByUserId) {
        this.ifSelectBranch = true
        this.ifSelectDepart = true
        this.ifSelectUserId = false
        this.ifSelectName = true
        this.checkedByBranch = false
        this.checkedByDepart = false
        this.checkedByName = false
      } else if (this.checkedByName) {
        this.ifSelectBranch = true
        this.ifSelectDepart = true
        this.ifSelectUserId = true
        this.ifSelectName = false
        this.checkedByBranch = false
        this.checkedByDepart = false
        this.checkedByUserId = false
      } else {
        this.ifSelectBranch = true
        this.ifSelectDepart = true
        this.ifSelectUserId = true
        this.ifSelectName = true
        this.checkedByBranch = false
        this.checkedByDepart = false
        this.checkedByUserId = false
        this.checkedByName = false
      }
    },
    // 显示筛选范围
    userChange(){
      this.showRange = !this.showRange;
    },
     handleCheckAllChange(val) {
        this.message.checkedUsers = val ? cityOptions : [];
        this.message.isIndeterminate = false;
      },
      handleCheckedCitiesChange(value) {
        let checkedCount = value.length;
        this.message.depacheckAll = checkedCount === this.message.cities.length;
        this.message.isIndeterminate = checkedCount > 0 && checkedCount < this.message.cities.length;
      },
        getAlldepartmentsInfo(){
        this.postRequest("/employee/basic/listEmpVo").then(resp=> {
          if (resp.data && resp.data.status == 200) {
            console.log("获取所有部门信息",resp);
            this.departmentNames = resp.data.obj;
          }
        });
        },    
        getAllBranchsInfo(){
          this.postRequest("/employee/basic/listEmpVo").then(resp=> {
          if (resp.data && resp.data.status == 200) {
            console.log("获取所有机构信息",resp);
            this.branchNames = resp.data.obj;
          }
        });
        },
        searchEmp(){
         this.loadMessages();
        },
        disPlay(){
          if(this.messages.length>0){
            this.show=true
          }
        },
        // 添加表单信息
       showAddMessageView(){
        this.dialogTitle = "添加新的消息";
        this.$refs['addMessageForm'].clearValidate() // 重置验证
        this.message.messageType = '';
        this.message.messageTitle = '';
        this.message.messageContent = '';
        this.message.beginTime = '';
        this.message.endTime = '';
        this.recipientIdButton =false;
        this.showRange =true;

        this.dialogVisible = true;
    
    },
     handleSelectionChange(val) {
        this.multipleSelection = val;
      },
     keywordsChange(val){
        if (val == '') {
          this.loadMessages();
        }
      },
     currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadMessages();
      },
       addMessage(formName){
        var _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.message.id) {
              //更新
              this.tableLoading = true;
              this.postRequest("/employee/prize/updateEmpById", this.emp).then(resp=> {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyMessageData();
                  _this.loadMessages();
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
                  _this.emptyMessageData();
                  _this.loadMessages();
                }
              })
            }
         
          } else {
            return false;
          }
        });
      },
     deleteManyMessage(){
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          for (var i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doDeleteMultiMessage(ids);
        }).catch(() => {
        });
      },
      doDeleteMultiMessage(ids){
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
            _this.loadMessages();
          }
        })
      },
       loadMessages(){
        var _this = this;
        var datas = {
          "id":"",
          "branchId": "",
          "messageType":"",
          "size": "10",
          "start": (this.currentPage-1)*10          
        };
        this.tableLoading = true;
        this.postRequest("/user/branch/list",datas).then(resp=> {
          this.tableLoading = false;
          console.log("haha",resp);
          if (resp.data && resp.status == 200) {
            var data = resp.data.obj.empPrizeVoList;
            _this.messages = data;
            _this.totalCount = resp.data.obj.listCount;
          }
        })
      },
      // 编辑表单信息
      showMessageView(row){
        if(this.pulishButton){
           this.$message({
          message: '消息已经发布不可以修改!',
          type: 'warning'
        })
        }
        else
        {
        this.dialogTitle = "编辑信息情况";
        this.$refs['addMessageForm'].clearValidate() // 重置验证
        this.dialogVisible = true;
        this.recipientIdButton = true
        this.showRange = false
        this.message.messageTitle = row.messageTitle;
        this.message.messageType = row.messageType;
        this.message.messageContent = row.messageContent;
        this.message.beginTime = row.beginTime;
        this.message.endTime = row.endTime;
      
        }
      },
      deleteMessage(){
          this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDeleteMessage(row.id);
        }).catch(() => {
        });
      },
      doDeleteMessage(ids){
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
            _this.loadMessages();
          }
        })
      },
      emptyMessageData(){
        this.branch = {
         id: '',
         messageType: '',
         messageTitle:'',
         departmentId:'',
         connectPhone:'',
         connectTelephone:'',
        }
      },
       cancelEidt(){
        this.recipientIdButton=false;
        this.dialogVisible = false;
        this.emptyMessageData();
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
.second{
  font-family: Georgia, Times, 'Times New Roman', serif;
  position:relative;
  top:140px;
  left:100px;

}
.first{
  font-family:  Georgia, Times, 'Times New Roman', serif;
  position: relative;
  width:1000px;
  top:150px;
  left:0px;
}
.main{
  position:relative;
  top:200px;
  left:50px;
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
