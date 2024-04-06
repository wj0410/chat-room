<template>
  <router-view></router-view>
</template>


<script setup lang="ts">

</script>

<style lang="less">
* {
  margin: 0;
  padding: 0;
}

#app{
  display: flex;
  align-items: center;
  height: 100vh;
}

body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  overflow: hidden;
}

.avatar {
  width: 35px;
  height: 35px;
  background-color: #ccc;
}

svg{
  width: 25px; /* 设置图标的宽度 */
  height: 25px; /* 设置图标的高度 */
}

li {
  display: inline-block;
  cursor: pointer;
}
ul{
  list-style: none;
  padding: 0;
  margin: 0;
}

// 阿里巴巴图标库
.icon {
  width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
  font-size: 24px !important;
}
.iconfont{
  font-size: 24px;
}
// 富文本编辑器
.ql-toolbar{
  height: 0 !important;
  padding: 0 !important;
  border:0 !important;
}
.ql-snow{
  border:0 !important;
}
.ql-editor{
  padding:0 !important;
}

.no-copy {
  -webkit-user-select: none; /* Chrome 和 Safari */
  -moz-user-select: none; /* Firefox */
  -ms-user-select: none; /* Internet Explorer/Edge */
  user-select: none;
}

.offline {
  filter: grayscale(100%);
}
</style>
