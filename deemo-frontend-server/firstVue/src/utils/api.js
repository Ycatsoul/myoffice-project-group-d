import axios from 'axios'
import {Message} from 'element-ui'
axios.interceptors.request.use(config => {
  return config;
}, err => {
  Message.error({message: '请求超时!'});
  // return Promise.resolve(err);
})
axios.interceptors.response.use(data => {
  if (data.status && data.status == 200 && data.data.status == 500) {
    Message.error({message: data.data.msg});
    return;
  }
  if (data.data.msg) {
    Message.success({message: data.data.msg});
  }
  return data;
}, err => {
  if (err.response.status == 504 || err.response.status == 404) {
    Message.error({message: '服务器错误'});
  } else if (err.response.status == 403) {
    Message.error({message: '权限不足,请联系管理员!'});
  } else if (err.response.status == 401) {
    Message.error({message: err.response.data.msg});
  } else {
    if (err.response.data.msg) {
      Message.error({message: err.response.data.msg});
    }else{
      Message.error({message: '未知错误!'});
    }
  }
  // return Promise.resolve(err);
})
let base = '';
//登陆的post请求
export const postRequestLogin = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      // 'Content-Type': 'application/x-www-form-urlencoded'
      'Content-Type': 'application/json;charset=utf-8'
    }
  });
}
//非登陆的post
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    contentType: 'application/json; charset=UTF-8',
    async:false,
    dataType:'json',
    data: JSON.stringify(params),
    headers: {
      'Content-Type': 'application/json; charset=UTF-8'
    }
  });
}
export const uploadFileRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
}
export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/json'
    }
  });
}
export const deleteRequest = (url,params) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`,
    contentType: 'application/json; charset=UTF-8',
    dataType:'json',
    data: JSON.stringify(params),
  });
}
export const getRequest = (url) => {
  return axios({
    method: 'get',
    url: `${base}${url}`,
  });
}
