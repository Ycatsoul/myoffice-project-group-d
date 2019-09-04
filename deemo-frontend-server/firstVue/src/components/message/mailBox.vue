<template>
<div>
<el-container>
<div class="head">
  <div class="first">
   <p>个人消息管理区:</p>
  </div>
  <el-table
    :data="mailBox"
    border
    style="width: 50%">
    <el-table-column
      alige="center"
      prop="file"
      label="本地文件夹"
      width="180">
       <template slot-scope="scope">
                  <i class="el-icon-message" @click="showDetail(scope.row)"></i>{{scope.row.file}}
                </template>
    </el-table-column>
    <el-table-column
      alige="center"
      prop="fileNum"
      label="文件个数"
      width="180">
    </el-table-column>
    <el-table-column
      alige="center"
      prop="notRead"
      label="未读邮件"
     >
    </el-table-column>
   </el-table>
  </div>
</el-container> 
 <!-- 显示收件箱信息的模态框 -->
        <el-dialog title="收件箱" :visible.sync="dialogInboxVisible" width="60%">
          <div>
          <el-table :data="inboxInfo" border @selection-change="handleSelectionChange">
            <el-table-column type="selection"></el-table-column>
            <el-table-column  prop="newMessage" align="center">
              <template slot-scope="scope">
                <i class="colorYellow" @click="showMessageDetail(scope.row)">{{scope.row.newMessage}}</i>
              </template>
            </el-table-column>
            <el-table-column prop="userId" label="发件人" width="150px" align="center">
              <template slot-scope="scope">
                <span @click="showMessageDetail(scope.row)">{{scope.row.userId}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="messageTitle" label="主题" width="250px" align="center">
              <template slot-scope="scope">
                <span @click="showMessageDetail(scope.row)">{{scope.row.messageTitle}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="publishTime" label="发送时间" width="200px" align="center">
              <template slot-scope="scope">
                <span @click="showMessageDetail(scope.row)">{{scope.row.publishTime}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="messageType" label="紧急程度" align="center">
              <template slot-scope="scope">
                <span @click="showMessageDetail(scope.row)">{{scope.row.messageType}}</span>
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
          </el-table>
           <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini"  :disabled="multipleSelection.length==0"
                       @click="deleteManyMessage">批量删除
            </el-button>
            <el-button @click="dialogInboxVisible = false">返 回</el-button>
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
          <!-- 显示消息详情的模态框 -->
          <el-dialog title="消息详情" :visible.sync="dialogMessageDetail" >
              <el-form :model="messageDetail">
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="消息主题">
                      <span>{{messageDetail.messageTitle}}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="紧急程度">
                      <span>{{messageDetail.messageType}}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="消息内容">
                      <span>{{messageDetail.messageContent}}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="发/收件人">
                      <span>{{messageDetail.userId}}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="发送时间">
                      <span>{{messageDetail.publishTime}}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
              <div slot="footer" class="dialog-footer" align="center">
                <el-button @click="editMessageDetail()">关 闭</el-button>
              </div>
          </el-dialog>
        </el-dialog>
         <!-- 已发送信息的模态框 -->
        <el-dialog title="已发送" :visible.sync="dialogSendedVisible"  width="60%">
          <el-table :data="sendedInfo" border>
            <el-table-column type="selection"></el-table-column>
            <el-table-column prop="userId" label="收件人" width="150px" align="center">
              <template slot-scope="scope">
                <span @click="showMessageDetail(scope.row)">{{scope.row.userId}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="messageTitle" label="主题" width="250px" align="center">
              <template slot-scope="scope">
                <span @click="showMessageDetail(scope.row)">{{scope.row.messageTitle}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="publishTime" label="发送时间" width="200px" align="center">
              <template slot-scope="scope">
                <span @click="showMessageDetail(scope.row)">{{scope.row.publishTime}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="messageType" label="紧急程度" align="center">
              <template slot-scope="scope">
                <span @click="showMessageDetail(scope.row)">{{scope.row.messageType}}</span>
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
          </el-table>
           <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini"  :disabled="multipleSelection.length==0"
                       @click="deleteManyMessage">批量删除
            </el-button>
            <el-button @click="dialogSendedVisible = false">返 回</el-button>
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage"
              @current-change="currentChange"
              layout="prev, pager, next"
              :total="totalCount">
            </el-pagination>
          </div>
        </el-dialog>
  </div>
</template>
<script>
  export default {
    data() {
      return {
      multipleSelection:[],
      messageDetail: [],
      dialogInboxVisible: false,
      dialogSendedVisible: false,
      dialogMessageDetail: false,
      newMessgae: true,
        mailBox: [
        { file: "收件箱", fileNum: 0, notRead: 0}, 
        { file: '草稿箱', fileNum: 0, notRead: 0}, 
        { file: '已发送', fileNum: 0, notRead: 0}, 
        { file: '已删除', fileNum: 0,  notRead: 0}],
         inboxInfo: [
        { userId: '杨佳丽1', messageTitle: '关于会议信息的名单', publishTime: '2019-08-31 12:00:00', messageType: '一般', messageContent: '每个部门必须有一个人出席', newMessage: 'new!' },
        { userId: '杨佳丽2', messageTitle: '关于会议信息的名单', publishTime: '2019-08-31 12:00:00', messageType: '一般', messageContent: '每个部门必须有一个人出席', newMessage: 'new!' },
        { userId: '杨佳丽3', messageTitle: '关于会议信息的名单', publishTime: '2019-08-31 12:00:00', messageType: '一般', messageContent: '每个部门必须有一个人出席', newMessage: 'new!' },
        { userId: '杨佳丽4', messageTitle: '关于会议信息的名单', publishTime: '2019-08-31 12:00:00', messageType: '一般', messageContent: '每个部门必须有一个人出席', newMessage: 'new!' },
        { userId: '杨佳丽5', messageTitle: '关于会议信息的名单', publishTime: '2019-08-31 12:00:00', messageType: '一般', messageContent: '每个部门必须有一个人出席', newMessage: 'new!' }
      ],
      // 已发送信息
      sendedInfo: [
        { userId: '所有人', messageTitle: '关于会议信息的名单1', publishTime: '2019-08-31 12:00:00', messageType: '一般1', messageContent: '每个部门必须有一个人出席' },
        { userId: '所有人', messageTitle: '关于会议信息的名单2', publishTime: '2019-08-31 12:00:00', messageType: '一般2', messageContent: '每个部门必须有一个人出席' },
        { userId: '所有人', messageTitle: '关于会议信息的名单3', publishTime: '2019-08-31 12:00:00', messageType: '一般3', messageContent: '每个部门必须有一个人出席' },
        { userId: '所有人', messageTitle: '关于会议信息的名单4', publishTime: '2019-08-31 12:00:00', messageType: '一般4', messageContent: '每个部门必须有一个人出席' },
        { userId: '所有人', messageTitle: '关于会议信息的名单5', publishTime: '2019-08-31 12:00:00', messageType: '一般5', messageContent: '每个部门必须有一个人出席' }
      ],
      }
    },
    methods:{
       handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      showDetail: function (row) {
      // 收件箱页面
      if (row.file === '收件箱') {
        this.dialogInboxVisible = true
      } else if (row.file === '已发送') {
        this.dialogSendedVisible = true
      }
     },
     // 显示消息信息详情
    showMessageDetail: function (row) {
      this.dialogMessageDetail = true
      this.messageDetail = row
      row.newMessage = ''
    },
      editMessageDetail: function () {
      this.dialogMessageDetail = false
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
   }
     
  }
</script>
<style>
.head{
  position: absolute;
  width:1200px;
  top:80px;
  left:600px;
  font-size: 30px;
}
.first{
  position: relative;
  right:1000px;
  top:-50px;
  font-size:20px;
}
</style>