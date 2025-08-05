<script setup lang="ts">
import {inject} from "vue";

const msg = inject("msg", "默认信息")
</script>

<template>
  <div>
    <p>子级组件信息：{{ msg }}</p>
  </div>
</template>

<style scoped>

</style>