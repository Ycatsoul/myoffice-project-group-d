<template>
<el-container class="home-container">
        <div class="header ">
         <p>欢迎：{{user.name}}</p>
         <p>角色：{{user.name}}</p>
         <p>部门：{{user.department}}</p>
         <p>您共有{{user.number}}条新消息</p>
        </div>
      <el-container>
      <div class="aside">
        <el-menu :default-active="this.$router.path" router mode="horizontal">
          <el-submenu index="1">
            <template slot="title">人事管理</template>
        <el-menu-item v-for="(item,i) in menu" :key="i" :index="item.path">
          {{ item.name }}
        </el-menu-item>
         </el-submenu>
         <el-submenu index="2">
          <template slot="title">日程管理</template>
        <el-menu-item v-for="(item,i) in menu2" :key="i" :index="item.path">
          {{ item.name }}
        </el-menu-item>
         </el-submenu>
          <el-submenu index="3">
          <template slot="title">文档管理</template>
        <el-menu-item v-for="(item,i) in menu3" :key="i" :index="item.path">
          {{ item.name }}
        </el-menu-item>
         </el-submenu>
          <el-submenu index="4">
          <template slot="title">消息传递</template>
        <el-menu-item v-for="(item,i) in menu4" :key="i" :index="item.path">
          {{ item.name }}
        </el-menu-item>
         </el-submenu>
          <el-submenu index="5">
          <template slot="title">系统管理</template>
        <el-menu-item v-for="(item,i) in menu5" :key="i" :index="item.path">
          {{ item.name }}
        </el-menu-item>
         </el-submenu>
          <el-submenu index="6">
          <template slot="title">考勤管理</template>
        <el-menu-item v-for="(item,i) in menu6" :key="i" :index="item.path">
          {{ item.name }}
        </el-menu-item>
         </el-submenu>
       </el-menu>   
      </div>     
        <div class="home-header">   
        <i class="el-icon-document size">主页</i>
        <i class="el-icon-user-solid size">重新登录</i>
        <i class="el-icon-edit size">修改密码</i>
        <i class="el-icon-news size">显示/隐藏通知栏</i>
        <i class="el-icon-help size">帮助</i>
        <i class="el-icon-date " align="right">今天是: {{today}}</i>
        </div>
        <div class="home-main">
           <el-main>   
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/home' }">当前位置</el-breadcrumb-item>
              <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
            </el-breadcrumb>
            <keep-alive>
              <router-view v-if="this.$route.meta.keepAlive"></router-view>
            </keep-alive>
            <router-view v-if="!this.$route.meta.keepAlive"></router-view>
          </el-main> 
      </div>
     </el-container>
  </el-container> 
</template>
<script>
  export default{
    mounted: function () {
    },
    methods: {
      
        handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
       handleCommand(cmd){
        var _this = this;
        if (cmd == 'logout') {
          this.$confirm('注销登录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            _this.getRequest("/logout");
            _this.$store.commit('logout');
            _this.$router.replace({path: '/'});
          }).catch(() => {
            _this.$message({
              type: 'info',
              message: '取消'
            });
          });
        }
      }
    },
    data(){
      return {
        isDot: false,
        menu:[{path:"/personal/organization",name: '机构管理',},
              {path:"/personal/department",name:"部门管理" },
              {path:"/personal/employee",name:"员工管理" },
        ],
        menu2:[{path: '/schedule/mySchedule',name:'我的日程',},
             { path: '/schedule/departmentSchedule', name: '部门日程', },
             {path: '/schedule/myNotes', name: '我的便签', }
        ],
        menu3:[{path:"/document/docManage",name: '文档管理',},
          {path:"/document/recycle",name:"回收站" },
          {path:"/document/fileSearch",name:"文件搜索" }
        ],
        menu4:[{path:"/message/msgManage",name: '信息管理',},
          {path:"/message/mailBox",name:"信箱" },
        ],
        menu5:[{path:"/system/loginRecord",name: '登录日志',},
          {path:"/system/roleManage",name:"角色管理" },
          {path:"/system/menuOrder",name:"菜单排序" },
          {path:"/system/operateRecord",name:"操作日志" },
        ],
         menu6:[{path:"/check/checkCount",name: '考勤统计',},
          {path:"/check/checkInquiry",name:"考勤历史记录查询" },
        ],
      }
    },
    computed: {
      user(){
        return this.$store.state.user;
      },
      routes(){
        return this.$store.state.routes;
      },
      today () {
      var date = new Date()
      var seperator1 = '-'
      var year = date.getFullYear()
      var month = date.getMonth() + 1
      var strDate = date.getDate()
      var week = date.getDay() // 返回表示星期的某一天的数字
      if (month >= 1 && month <= 9) {
        month = '0' + month
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = '0' + strDate
      }
      var weekday = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
      var currentdate = year + seperator1 + month + seperator1 + strDate + '  ' + weekday[week]
      return currentdate
    },
    }
  }
</script>
<style>
.header{
  position: relative;
  left:0;
  top:0;
  width:200px;
  height: 180px;
  background-color: rgb(0, 174, 255);
}
.aside {
  /* height: 520px; */
    text-align: center;
    width:200px;
    position: absolute;
    top:180px;
    left: 0px;
    /* background-color: rgb(0, 195, 255) */
  }
.home-header {
    background-color:rgb(0, 174, 255);
    color:whitesmoke;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-sizing: content-box;
    padding: 0px;
    position: relative;
    left: 0px;
    width: 1400px;
    height:50px;
  }
  .home-container {
    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
  }
  .home-main {
    position: absolute;
    top:50px;
    background-color:white;
    color: #000;
    text-align: center;
    margin: 0px;
    padding: 0px;;
  }

  .home_title {
    color: #fff;
    font-size: 22px;
    display: inline;
    margin-left: 8px;
  }

  .home_userinfo {
    color: #fff;
    cursor: pointer;
  }

  .home_userinfoContainer {
    display: inline;
    margin-right: 20px;
  }

  .el-submenu .el-menu-item {
    width: 180px;
    min-width: 175px;
    text-align:center;
  }
  .el-menu--horizontal>.el-submenu{
    float:none;
  }
</style>