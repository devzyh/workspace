<script setup lang="ts">
import {ref} from "vue";
import ChildComponent from "@/components/ChildComponent.vue";

// 组件属性定义
interface Props {
  num?: number
}

const props = withDefaults(defineProps<Props>(), {
  num: 1
})

// 组件方法定义
// interface Emits {
//   (e: "plus", num: number, msg: string): void
// }
//
// const emits = defineEmits<Emits>()

// 最新语法，好像没上面的易懂
const emits = defineEmits<{
  "plus": [number, string]
}>()

// 内部变量
const count = ref(0)

function addCount() {
  // 不要尝试在组件里改变父级传过来的props值，而是通知父级去改变
  count.value += props.num

  emits("plus", 10, "@@@")
}
</script>

<template>
  <div>
    <p>count: {{ count }}</p>
    <button @click="addCount()">功德+{{ props.num }}</button>
  </div>

  <child-component/>
</template>

<style scoped>

</style>