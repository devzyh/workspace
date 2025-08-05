// 数组解构
let arr = [1, 2, 3]
const [a, b, c] = arr;
console.log(a, b, c);

// 对象解构
let obj = {
    id: 1,
    name: "张三",
    age: 22
}
const {id, name, age: newAge} = obj;
console.log(id, name, newAge);
