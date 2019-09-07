<template>
<el-container class="home-container">
   <div class="head">
    <p>{{this.$router.currentRoute.name}}</p>
    <el-form>
       <el-row style="margin-top: 10px">
          <el-col :span="6">
          <span>输入时间段:</span>
          </el-col>
          <el-col :span="10">
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
       </el-row>
       <hr>
          
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
           <el-col :span="5" :offset="8" >
             <el-button icon="el-icon-search" type="primary" size="mini" @click="searchEmp">统计</el-button>
           </el-col>
        </el-row>
       </el-form>
     </div>
     <div class="main">
          <el-table
            :data="messages"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%">
            <el-table-column
              prop="name"
              align="center"    
              label="姓名"
              width="150">
            </el-table-column>
            <el-table-column
              prop="singinTime"
              align="center"    
              label="出勤率"
              width="200">
            </el-table-column>
            <el-table-column
              prop="singinTag"
              align="center"    
              label="迟到次数"
              width="100">
            </el-table-column>
             <el-table-column
              prop="singinDesc"
              align="center"    
              label="早退次数"
              width="150">
            </el-table-column>
             <el-table-column
              prop="departmentName"
              align="center"    
              label="旷工次数"
              width="150">
            </el-table-column>
             <el-table-column
              prop="branchName"
              align="center"    
              label="所属机构"
              width="150">
            </el-table-column>
             <el-table-column
              prop="departmenName"
              align="center"    
              label="所属部门"
              width="150">
            </el-table-column>
          </el-table>
     </div>
   </el-container>
</template>
<script>
export default {
data () {
    return {
      dateScope:'',
      message:{
        name:'',
        singinTime:'',
        singinTag:'',
        singinDesc:'',
        branchName:'',
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
          singinTag:"gahahah",
          singinDesc:"真棒",
          name:"小明",
          messageTitle:"ad123",
          messageContent:"123456sssdsds ",
          recipientId:"小明好的哈哈"
        }
      ],
      tableLoading:false,
      show:false,
      branchNames:[],
      departmentNames:[],
      departments: [],
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
              start.setTime(start.getTime() - 3600 * 1000 * 24 * (m-1) - milliseconds);
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
      this.getAlldepartmentsInfo();
      // this.disPlay();
    },
    methods:{
      //  机构下拉框的值改变，部门下拉框实现二级联动
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
  },
}
</script>
<style scoped>
.head{
  position: absolute;
  width:1200px;
  top:30px;
  left:50px;
  font-size: 20px;
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
  top:350px;
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