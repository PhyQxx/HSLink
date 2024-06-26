import request from '@/util/request'

// 查询获奖列表
export function listWinners(query) {
  return request({
    url: '/admin/winners/list',
    method: 'get',
    params: query
  })
}

// 新增获奖
export function addWinners(data) {
  return request({
    url: '/admin/winners',
    method: 'post',
    data: data
  })
}

// 修改获奖
export function updateWinners(data) {
  return request({
    url: '/admin/winners',
    method: 'put',
    data: data
  })
}

// 删除获奖
export function delWinners(id) {
  return request({
    url: '/admin/winners/' + id,
    method: 'delete'
  })
}
