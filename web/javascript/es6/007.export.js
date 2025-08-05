const util = {
    sum(a, b) {
        return a + b;
    }
}

var name = "javar";
var age = 21;

// 使用 export 将这个对象导出
// export不仅可以导出对象，一切JS变量都可以导出。比如：基本类型变量、函数、数组、对象。
export {util, name, age};
