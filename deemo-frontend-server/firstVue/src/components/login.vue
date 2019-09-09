<template>
<div>
  <el-form :rules="rules" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">My Office</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="USERNAME"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="PASSWORD"></el-input>
    </el-form-item>
    <el-checkbox class="login_remember" v-model="checked"
                 label-position="left">记住账号</el-checkbox>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%" @click="submitClick">登录</el-button>
       <el-button type="primary" style="margin-top:5px;margin-left:0px;width: 100%" @click="submitClick2">管理员登录</el-button>
    </el-form-item>
  </el-form>
</div>
</template>
<script>
  export default{
    data(){
      return {
        rules: {
          account: [{required: false, message: '请输入用户名', trigger: 'blur'}],
          checkPass: [{required: false, message: '请输入密码', trigger: 'blur'}]
        },
        checked: true,
        loginForm: {
          username: '',
          password: ''
        },
        loading: false
      }
    },
    methods:{
       submitClick() {
        var _this = this;
        this.loading = true;
        this.postRequestLogin('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        }).then(resp=> {
          _this.loading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            var userInfo = data.obj.userInfoVo;
            var routes = data.obj.sysFuns;
            this.initUserStore(userInfo)
            this.initRoutesStore(routes)
            var path = _this.$route.query.redirect;
            //管理员
            if(this.$store.status.user.roleName===""){
               _this.$router
             .replace({path: path == '/' || path == undefined ? '/home' : path});
            }//普通员工
            else if(this.$store.status.user.roleName===""){
               _this.$router
             .replace({path: path == '/' || path == undefined ? '/check/employeeCheck' : path});
            }
            
          }
        });
      },
      initUserStore(){
      this.$store.state.user.departName = userInfo.departName
      this.$store.state.user.gender = userInfo.gender
      this.$store.state.user.passWord = userInfo.passWord
      this.$store.state.user.name = userInfo.name
      this.$store.state.user.userStateName = userInfo.userStateName
      },
      initRoutesStore(routes){
        this.$store.state.routes=[];
        for(let i in routes){
          name=i.displayName,
          displayOrder= i.displayOrder,
          nodeId= i.nodeId,
          children=i.subSysFunList
        }
      }
    }
  }
</script>

<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 150px auto;
    width: 350px;
    height:320px;
    padding: 35px 35px 15px 35px;
    background: rgba(255, 255, 255, 0.979);
    border: 1px solid rgba(255, 255, 255, 0.541);
    box-shadow: 0 0 25px #cac6c6;
    position: relative;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }

</style>
