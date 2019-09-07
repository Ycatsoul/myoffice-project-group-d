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
                <el-col :span="5" offset="8"  style="margin-top:25px;">
                  <el-button icon="el-icon-search" type="primary" size="mini" @click="searchEmp">搜索</el-button>
                </el-col>
              </el-row>
     </div>
     <div class="main" >
          <el-table
            :data="operationRecords"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%">
            <el-table-column
              prop="loginId"
              type="selection"
              align="left"
              width="30">
            </el-table-column>
              <el-table-column
              label="序号"
              type="index"
              align="center"
              width="50">
            </el-table-column>
            <el-table-column
              prop="name"
              align="center"    
              label="操作用户"
              width="">
            </el-table-column>
            <el-table-column
              prop="operationName"
              align="center"    
              label="事件"
              width="">
            </el-table-column>
            <el-table-column
              prop="operationDesc"
              align="center"    
              label="操作描述"
              width="">
            </el-table-column>
             <el-table-column
              prop="operationTime"
              align="center"    
              label="操作时间"
              width="">
            </el-table-column>
            <el-table-column
              align="center"
              label="删除"
              width="80">
              <template slot-scope="scope">
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteOperationRecord(scope.row)">
                           <i class="el-icon-delete"></i>
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini"  :disabled="multipleSelection.length==0"
                       @click="deleteManyOperationRecord">批量删除
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
 </div>
</template>

<script>
export default {
  data () {
    return {
      dateScope:'',
      operationRecord:{
        operationName:'',
        operationId:'',
        operationDesc:'',
        operationTime:'',
        name:'',
      },

       operationRecords:[
        {
          operationRecordTitle:"ad123",
          operationRecordContent:"123456sssdsds ",
          recipientId:"小明好的哈哈"
        }
      ],
      multipleSelection: [],
      isDisabled:0,
      totalCount: -1,
      currentPage: 1,
      tableLoading:false,
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
      this.loadOperationRecords();
      this.disPlay();
    },
   methods:{
       searchEmp(){
         this.loadOperationRecords();
       },
        disPlay(){
          if(this.departments.length>0){
            this.show=true
          }
        },
      
     handleSelectionChange(val) {
        this.multipleSelection = val;
      },
     keywordsChange(val){
        if (val == '') {
          this.loadOperationRecords();
        }
      },
     currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadOperationRecords();
      },
      

     deleteManyOperationRecord(){
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          for (var i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doDeleteMultiOperationRecord(ids);
        }).catch(() => {
        });
      },
      doDeleteMultiOperationRecord(ids){
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
            this.$operationRecord({type: 'success', operationRecord: "删除成功！"});
            _this.loadOperationRecords();
          }
        })
      },
       loadOperationRecords(){
        var _this = this;
        var datas = {
          "loginId": "",
          "size": "10",
          "start": (this.currentPage-1)*10          
        };
        this.tableLoading = true;
        this.postRequest("/user/branch/list",datas).then(resp=> {
          this.tableLoading = false;
          console.log("haha",resp);
          if (resp.data && resp.status == 200) {
            var data = resp.data.obj.empPrizeVoList;
            _this.operationRecords = data;
            _this.totalCount = resp.data.obj.listCount;
          }
        })
      },
  
      deleteOperationRecord(){
          this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDeleteOperationRecord(row.id);
        }).catch(() => {
        });
      },
      doDeleteOperationRecord(ids){
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
            this.$operationRecord({type: 'success', operationRecord: "删除成功！"});
            _this.loadOperationRecords();
          }
        })
      },
      emptyOperationRecordData(){
        this.branch = {
         loginId:'',
         loginTime:'',
         loginIp:'',
         isSuccess:'',
         loginDesc:'',
         name:'',
        }
      },
       cancelEidt(){
        this.recipientIdButton=false;
        this.dialogVisible = false;

        this.emptyOperationRecordData();
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
