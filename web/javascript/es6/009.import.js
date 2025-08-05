// 指定名称导入
import {name, util} from '007.export.js'
// 任意名称导入
import {test} from './008.export.default'

console.log(util.sum(1, 3));
console.log(name);

console.log(test.sum(1, 2));
