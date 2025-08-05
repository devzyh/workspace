// Promise 语法
const promise = new Promise(function (resolve, reject) {
    // 执行异步操作
    if (true/* 异步操作成功 */) {
        resolve(value);// 调用 resolve，代表 Promise 将返回成功的结果
    } else {
        reject(error);// 调用 reject，代表 Promise 会返回失败结果
    }
});

// 使用箭头函数可以简写为：
const promise = new Promise((resolve, reject) => {
// 执行异步操作
    if (true/* 异步操作成功 */) {
        resolve(value);// 调用 resolve，代表 Promise 将返回成功的结果
    } else {
        reject(error);// 调用 reject，代表 Promise 会返回失败结果
    }
});

// 处理异步结果
promise.then(function (value) {
    // 异步执行成功后的回调
}).catch(function (error) {
    // 异步执行失败后的回调
})
