const OSS = require('ali-oss');
export function client() {
  var client = new OSS({
    region: '',
    accessKeyId: '',
    accessKeySecret: '',
    bucket: ''
  }); //后端提供数据
  return client
}
