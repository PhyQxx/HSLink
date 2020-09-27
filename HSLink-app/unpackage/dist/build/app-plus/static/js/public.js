/* 一些公共方法 */

import request from '@/util/request.js';

/**
 * 获取当前时间
 */
const getNowDate = () => {
	const myDate = new Date();
	let year = myDate.getFullYear();
	let month = myDate.getMonth()+1 < 10 ? '0'+(myDate.getMonth()+1) : myDate.getMonth()+1;
	let date = myDate.getDate() < 10 ? '0'+myDate.getDate() : myDate.getDate();
	return year+"-"+month+"-"+date
};
export { getNowDate }

/**
 * 格式化时间方法
 * @param {Object} fmt 格式
 * @param {Object} date 时间对象
 */
const dateFormat = (fmt, date) => {
    let ret;
    const opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        };
    };
    return fmt;
};
export { dateFormat }

/**
 * 排序方法
 * @param {Object} attr 数组
 * @param {Object} rev 升序/降序
 */
const  sortBy = (attr,rev) => {
	//第二个参数没有传递 默认升序排列
	if(rev ===  undefined){
		rev = 1;
	}else{
		rev = (rev) ? 1 : -1;
	}
	return function(a,b){
		a = a[attr];
		b = b[attr];
		if(a < b){
		  return rev * -1;
		}
		if(a > b){
		  return rev * 1;
		}
		return 0;
    }
};
export { sortBy }

const updateUserInfo = () => {
	request.post('/hs/login',{
		role: uni.getStorageSync("userInfo").user_type,
		username: uni.getStorageSync("userInfo").real_name,
		password: uni.getStorageSync("userInfo").pass_word
		}).then(res => {
			console.log("刷新个人信息",res.data);
			uni.setStorageSync("userInfo", res.data);
		},err => {
		})
}
export { updateUserInfo }