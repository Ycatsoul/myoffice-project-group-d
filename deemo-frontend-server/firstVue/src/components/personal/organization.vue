<template>
<el-container class="home-container">
   <div class="head">
    <p>{{this.$router.currentRoute.name}}</p>
     <hr>
   </div>
   <div class="content">
     <div class="first">
       机构名称<el-input
            style="width: 200px;margin: 0px;padding: 0px;"
            size="mini">
            </el-input>
            机构简称<el-input
            style="width: 200px;margin: 0px;padding: 0px;"
            size="mini">
            </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px"  @click="searchEmp">
            添加
          </el-button>
          <el-button type="primary" size="mini" style="margin-left: 5px"  @click="searchEmp">
            保存修改
          </el-button>
     </div>
     <div class="main">
          <el-table
            :data="emps"
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
              prop="name"
              align="left"    
              label="机构名称"
              width="">
            </el-table-column>
            <el-table-column
              align="left"
              prop="prizeName"
              label="机构简称"
              width="">
            </el-table-column>
            <el-table-column
              align="left"
              label="修改"
              width="150">
              <template slot-scope="scope">
                <el-button @click="showPrizeEmpView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                 </template>
               </el-table-column>
              <el-table-column
              align="left"
              label="删除"
              width="150">
              <template slot-scope="scope">
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteEmpPrize(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
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
