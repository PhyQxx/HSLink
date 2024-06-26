import request from '@/util/request'

// 注册
export function register(query) {
  return request({
    url: '/hs/register',
    method: 'post',
    data: query
  })
}

// 编辑人员信息
export function editPerson(query) {
  return request({
    url: '/hs/updatePersonalInfo',
    method: 'post',
    data: query
  })
}

// 删除人员
export function delPerson(id) {
  return request({
    url: '/admin/person/' + id,
    method: 'delete'
  })
}
// 获取班级列表
export function getClassList(query) {
  return request({
    url: '/admin/class/list',
    method: 'post',
    data: query
  })
}

// 获取职务列表
export function getPostList(query) {
  return request({
    url: '/admin/post/list',
    method: 'post',
    data: query
  })
}


