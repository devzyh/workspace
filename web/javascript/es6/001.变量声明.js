var a = "a";
var a = 1;
console.log(a);

let b = "b";
// let b = 2; // SyntaxError: Identifier 'b' has already been declared
console.log(b);

// 变量提升
console.log(c);
var c = "c"; // undefined

// console.log(d); // ReferenceError: Cannot access 'd' before initialization
let d = "d";

const e = "e";
// e = "x"; // Attempt to assign to const or readonly variable
console.log(e);
