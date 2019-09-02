import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import home from '@/components/home'
import organization from '@/components/personal/organization'
import department from '@/components/personal/department'
import employee from '@/components/personal/employee'
import mySchedule from '@/components/schedule/mySchedule'
import departmentSchedule from '@/components/schedule/departmentSchedule'
import myNotes from '@/components/schedule/myNotes'
import docManage from '@/components/document/docManage'
import fileSearch from '@/components/document/fileSearch'
import recycle from '@/components/document/recycle'
import mailBox from '@/components/message/mailBox'
import msgManage from '@/components/message/msgManage'
import loginRecord from '@/components/system/loginRecord'
import operateRecord from '@/components/system/operateRecord'
import menuOrder from '@/components/system/menuOrder'
import roleManage from '@/components/system/roleManage'
import checkCount from '@/components/check/checkCount'
import checkInquiry from '@/components/check/checkInquiry'

Vue.use(Router)

export default new Router({
  // mode:'history',//history模式 前面没有#号  反之叫哈希模式
  routes: [
    {
      path: '/login',
      name: '登录',
      component: login,
      hidden: true,
    },
    {
      path: '/',
      name: '主页面',
      component: home,
      hidden: true,
      meta: {
        requireAuth: true
      },
      children:[
        {
          path: '/personal/organization',
          name: '机构管理',
          component: organization,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/personal/department',
          name: '部门管理',
          component: department,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/personal/employee',
          name: '员工管理',
          component: employee,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/schedule/mySchedule',
          name: '我的日程',
          component: mySchedule,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/schedule/departmentSchedule',
          name: '部门日程',
          component: departmentSchedule,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/schedule/myNotes',
          name: '我的便签',
          component: myNotes,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/document/docManage',
          name: '文档管理',
          component: docManage,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/document/fileSearch',
          name: '文件搜索',
          component: fileSearch,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/document/recycle',
          name: '回收站',
          component: recycle,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/message/mailBox',
          name: '信箱',
          component: mailBox,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/message/msgManage',
          name: '消息管理',
          component: msgManage,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/system/loginRecord',
          name: '登录日志',
          component: loginRecord,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/system/operateRecord',
          name: '操作日志',
          component: operateRecord,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/system/menuOrder',
          name: '菜单排序',
          component: menuOrder,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/system/roleManage',
          name: '角色管理',
          component: roleManage,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/check/checkCount',
          name: '考勤统计',
          component: checkCount,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
        {
          path: '/check/checkInquiry',
          name: '考勤历史记录查询',
          component: checkInquiry,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        },
      ]
    },
   
  ]
})
