
// const baseUrl = 'http://106.53.113.158:8048';  //服务器地址
const baseUrl = 'http://192.168.10.29:8048';  //服务器地址

const get = (url, data) => {
    let httpDefaultOpts = {
        url: baseUrl+url,
        data: data,
        method: 'get',
        header: {
        'X-Requested-With': 'XMLHttpRequest',
        "Accept": "application/json",
        "Content-Type": "application/json; charset=UTF-8"
    },
        dataType: 'json',
    }
    let promise = new Promise(function(resolve, reject) {
        uni.request(httpDefaultOpts).then((res) => {
        	if (res[1].data.success === true && res[1].data.data !== null) {
        			resolve(res[1].data)
        		} else {
					uni.showToast({
						icon: 'none',
						title: '服务器出现异常'
					});
        		}
            }
        ).catch(
            (response) => {
                reject(response)
            }
        )
    })
    return promise
};
const post = (url, data) => {
    let httpDefaultOpts = {
        url: baseUrl+url,
        data: data,
        method: 'post',
		header: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json'
    },
        dataType: 'json',
    }
    let promise = new Promise(function(resolve, reject) {
        uni.request(httpDefaultOpts).then((res) => {
			if (res[1].data.success === true && res[1].data.data !== null) {
					resolve(res[1].data)
				} else {
					resolve({
						message: "服务器出现异常"
					})
				}
            }
        ).catch(
            (response) => {
                reject(response)
            }
        )
    })
    return promise
};
//带Token请求
const httpTokenRequest = (opts, data) => {
	let token = "";
	uni.getStorage({
		key: 'token',
		success: function(ress) {
			token = ress.data
		}
	});
    //此token是登录成功后后台返回保存在storage中的
    let httpDefaultOpts = {
        url: baseUrl+opts.url,
        data: data,
        method: opts.method,
        header: opts.method == 'get' ? {
		'Token': token,
        'X-Requested-With': 'XMLHttpRequest',
        "Accept": "application/json",
        "Content-Type": "application/json; charset=UTF-8"
    } : {
		'Token': token,
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
        dataType: 'json',
    }
    let promise = new Promise(function(resolve, reject) {
        uni.request(httpDefaultOpts).then(
            (res) => {
                resolve(res[1])
            }
        ).catch(
            (response) => {
                reject(response)
            }
        )
    })
    return promise
};
 
export default {
	    baseUrl,
		get,
		post,
		httpTokenRequest
}