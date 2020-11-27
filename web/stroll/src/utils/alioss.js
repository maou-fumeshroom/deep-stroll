const OSS = require('ali-oss');
export function client() {
  var client = new OSS({
    region: 'oss-cn-beijing',
    accessKeyId: 'LTAI4G8VhKAYhSRZrKVnZgho',
    accessKeySecret: 'tMaTBo8wp6w3TcMpUbKCk5agJ8AOrh',
    bucket: 'bai111111'
  }); //后端提供数据
  return client
}
