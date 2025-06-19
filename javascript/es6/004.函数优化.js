// 函数默认值
function defaultVal(a, b = 1) {
    console.log(a, b);
}

defaultVal(1);
defaultVal(1, 3);


// 不定参数
function moreArgs(...vals) {
    console.log("args:", vals)
}

moreArgs(1);
moreArgs(1, 2);
moreArgs(1, 2, 3);


// 箭头函数
// 无参
var test = () => console.log("Hello");
test();

// 一个参数
var test1 = t => console.log(t);
test1("Test1");

// 多个参数
var test2 = (a, b) => console.log(a + b);
test2(5, 4);

var test3 = (a) => {
    console.log(a);
}
test3("3333");
