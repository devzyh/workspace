const stu = {
    id: 1000,
    name: "张三",
    age: 22
}

// 获取对象的所有 key 形成的数组
console.log(Object.keys(stu));

// 获取对象的所有 value 形成的数组
console.log(Object.values(stu));

// 获取对象的所有 key 和 value 形成的二维数组
console.log(Object.entries(stu));

// 将多个 src 对象的值 拷贝到 dest 中。
const dest = {a: 1};
const src1 = {b: 2};
const src2 = {c: 3};
console.log(Object.assign(dest, src1, src2))


// 声明对象简写

// 属性名和属性值变量名一样，可以省略
const id = 1000;
const score = 99;
const p = {id, score};
console.log(p);

// 对象的函数属性简写
let person = {
    name: "jack",
    // 以前：
    eat: function (food) {
        console.log(this.name + "在吃" + food);
    },
    // 箭头函数版：这里拿不到 this
    eat2: food => console.log(person.name + "在吃" + food),
    // 简写版：
    eat3(food) {
        console.log(this.name + "在吃" + food);
    }
}
person.eat("apple");


// 拓展运算符（...）用于取出参数对象所有可遍历属性然后拷贝到当前对象
// 1、拷贝对象（深拷贝）
let person1 = {name: "Amy", age: 15}
let someone = {...person1}
console.log(someone) //{name: "Amy", age: 15}

// 2、合并对象
let age = {age: 15}
let name = {name: "Amy"}
let person2 = {...age, ...name} //如果两个对象的字段名重复，后面对象字段值会覆盖前面对象的字段值
console.log(person2) //{age: 15, name: "Amy"}


// map()：接收一个函数，将原数组中的所有元素用这个函数处理后放入新数组返回。
let arr = ['1', '20', '-5', '3'];
console.log(arr)
arr = arr.map(s => parseInt(s));
console.log(arr)


// reduce 为数组中的每一个元素依次执行回调函数，不包括数组中被删除或从未被赋值的元素
// 接受四个参数：初始值（或者上一次回调函数的返回值），当前元素值，当前索引，调用 reduce 的数组。
const arr1 = [1, 20, -5, 3];
//没有初始值：
console.log(arr1.reduce((a, b) => a + b));//19
console.log(arr1.reduce((a, b) => a * b));//-300

//指定初始值：
console.log(arr1.reduce((a, b) => a + b, 1));//20
console.log(arr1.reduce((a, b) => a * b, 0));//-0
