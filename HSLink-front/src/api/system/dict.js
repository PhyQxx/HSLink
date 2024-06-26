import request from '@/util/request'

// 获取字典列表
export function getDict(query) {
  return request({
    url: '/hs/getDictionariesData',
    method: 'post',
    data: {
      code: query
    }
  })
}
