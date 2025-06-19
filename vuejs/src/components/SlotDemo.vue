<script setup lang="ts">

</script>

<template>
  <div class="demo">
    <div>
      <slot name="header" msg="我是头部插槽消息">我是插槽头部</slot>
    </div>
    <div>
      <slot msg="默认插槽数据">我是默认身体</slot>
    </div>
    <div>
      <slot name="footer" level="111" age="22">我是插槽尾部</slot>
    </div>
  </div>
</template>

<style scoped>
.demo {
  margin: 10px;
  border: 1px solid red;
}
</style>