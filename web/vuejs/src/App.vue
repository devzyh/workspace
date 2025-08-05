<template>
  <div>
    <h1>Hello World</h1>
    <div>
      <p>{{ msg }}</p>
      <button @click="changeMsg">改变内容</button>
    </div>

    <h1>模板语法</h1>
    <div>
      <p v-text="msg"></p>
      <p v-html="html"></p>
    </div>

    <h1>动态属性</h1>
    <div>
      <div v-bind="bindAttr" v-bind:class="dynamicClass">
        <p>完全语法</p>
      </div>
      <div :class="dynamicClass">
        <p>简写语法</p>
      </div>
      <button @click="dynamicClass.colorRed=true">改变字体颜色</button>
    </div>

    <h1>插值表达式</h1>
    <div>
      <p>{{ msg.startsWith("测试") ? "测试内容开头" : "非测试内容开头" }}</p>
      <button @click="msg='测试'">改变内容</button>
    </div>

    <h1>条件渲染</h1>
    <div>
      <div class="colorRed">
        <p v-if="status==1">v-if 使用演示1</p>
        <p v-else-if="status==2">v-else-if 使用演示2</p>
        <p v-else>v-else 使用演示3</p>
      </div>

      <div class="backgroundGreen">
        <p v-show="status==1">v-show 使用演示</p>
      </div>
      <button @click="changeStatus" v-text="status==1?'隐藏':'显示'"></button>
    </div>

    <h1>循环</h1>
    <div>
      <h3>循环列表</h3>
      <ul>
        <li v-for="({name, age}, index) in eachList" :key="name">
          {{ index }} {{ name }} {{ age }}
        </li>
      </ul>

      <h3>循环对象</h3>
      <ul>
        <li v-for="(value, key, index) in eachObj" :key="key">
          {{ index }} {{ key }} {{ value }}
        </li>
      </ul>
    </div>

    <h1>事件</h1>
    <div>
      <h3>绑定事件</h3>
      <p>
        count: {{ count }}
        <button v-on:click="count++">完全语法</button>
        <button @click="addCount(10,$event)">缩写语法</button>
      </p>

      <h3>阻止默认事件</h3>
      <a href="https://devzyh.cn" @click.prevent="showAlert('不准去')">我要访问网站</a>

      <h3>阻止事件冒泡</h3>
      <div @click="showAlert('我是父级容器')">
        <button @click.stop="showAlert('我是按钮')">父级不准弹框</button>
      </div>

      <h3>按键修饰符</h3>
      <input placeholder="请输入内容" v-model="input" @keydown.enter="showAlert(input)">
    </div>

    <h1>计算属性</h1>
    <div>
      <p>num1: {{ num1 }}</p>
      <p>num1: {{ num1 }}</p>
      <p>num1: {{ num1 }}</p>
      <p>num2: {{ num2(10) }}</p>
      <p>num2: {{ num2(10) }}</p>
      <p>num2: {{ num2(10) }}</p>
    </div>
  </div>

  <h1>侦听器</h1>
  <div>
    <h3>ref 监听</h3>
    input: {{ input }} <input v-model="input"/>

    <h3>reactive 监听</h3>
    msg: {{ data.msg }} <input v-model="data.msg"/>

    <h3>深度监听</h3>
    _msg: {{ data._data.__data.msg }} <input v-model="data._data.__data.msg"/>
  </div>

  <h1>模板引用</h1>
  <div>
    <input placeholder="默认选中输入框" id="test" ref="refInput"></input>
  </div>

  <hr/>

  <h1><a href="#component">组件演示</a></h1>
  <div>
    <h3>基础使用</h3>
    <hello-component/>

    <h3>父传子</h3>
    <hello-component :num="10"/>

    <h3>子通知父</h3>
    <hello-component :num="15" @plus="plus"/>

    <h3>父传孙（发送接收模式）</h3>
    <hello-component/>

  </div>

  <h1>组件插槽</h1>

  <h3>默认插槽</h3>
  <div>
    <slot-demo></slot-demo>
    <slot-demo>我是新的身体</slot-demo>
  </div>

  <h3>具名插槽</h3>
  <div>
    <slot-demo>
      <!--完全语法-->
      <template v-slot:header>
        我是新的头部
      </template>

      覆盖默认身体

      <!--缩写语法-->
      <template #footer>
        我是新的尾部
      </template>
    </slot-demo>

    <h3>作用域插槽</h3>
    <div>
      <slot-demo>
        <!--完全语法-->
        <template v-slot:header="msg">
          我是新的头部 {{ msg }}
        </template>

        <template v-slot="msg">
          覆盖默认身体 {{ msg }}
        </template>

        <!--缩写语法-->
        <template #footer="{level,age}">
          我是新的尾部 {{level}} {{ age}}
        </template>
      </slot-demo>
    </div>
  </div>
</template>

<script setup lang="ts">
// 导入依赖
import {computed, onBeforeMount, onMounted, provide, reactive, ref, watch} from "vue";

// 响应式
const msg = ref("测试内容")

function changeMsg() {
  msg.value = "改变后的内容"
}

// 模板语法
const html = ref("<a href='https://devzyh.cn' target='_blank'>网页链接</a>");

// 动态属性
const bindAttr = {
  attr1: "属性1",
  attr2: "属性2"
}

const dynamicClass = reactive({
  colorRed: false,
  backgroundGreen: true
})

// 条件渲染
const status = ref(1)

function changeStatus() {
  if (status.value == 1) {
    status.value = 0
  } else {
    status.value = 1
  }
}

// 循环
const eachList = reactive([
  {name: "zhangsan", age: 22},
  {name: "lisi", age: 23},
  {name: "wangwu", age: 24}
])

const eachObj = reactive({name: "zhangsan", age: 22})

// 事件
const count = ref(0)
const input = ref("")

function addCount(num: number, e: any) {
  console.log(e)
  count.value += num
}

function showAlert(msg: string) {
  window.alert(msg)
}

// 计算属性
const num = ref(0)
const num1 = computed(function () {
  console.log("num1")
  return num.value + 10
})

const num2 = computed(() => {
  return (inNumber: number) => {
    console.log("num2")
    return num.value + inNumber
  }
})

// 侦听器
watch(input, (newValue, oldValue) => {
  console.log(newValue)
  console.log(oldValue)
})

const data = reactive({
  msg: "",
  _data: {
    __data: {
      msg: ""
    }
  }
})

// 对象属性监听，推荐这种
watch(() => data.msg, (newValue, oldValue) => {
  console.log(newValue)
  console.log(oldValue)
})

// 深度监听对象
watch(data, (newValue, oldValue) => {
  console.log(newValue)
  console.log(oldValue)
}, {deep: true})

// 生命周期
// setup
console.log("setup 1")

onBeforeMount(() => {
  console.log("onBeforeMount")
})

onMounted(() => {
  console.log("onMounted")
  focusInput()
})

console.log("setup 2")

// 模板引用
const refInput = ref()

function focusInput() {
  // 方法1
  // let testInput: any = document.getElementById("test")
  // testInput.focus()

  // 方法2
  refInput.value.focus()
}

// 组件使用
function plus(num: number, msg: string) {
  window.alert(num + ":" + msg)
}

provide("msg", "发往组件的信息")
</script>

<style scoped>
.colorRed {
  color: red;
}

.backgroundGreen {
  background-color: green;
}
</style>
