<template>
  <div class="menu-bar-top">
    <ul style="font-size: 24px">
      <li>
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-xiaoxi2"></use>
        </svg>
      </li>
      <li>
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-gerenzhongxin"></use>
        </svg>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>

</script>


<style scoped lang="less">
.menu-bar-top {
  flex: 1;

  ul {
    list-style: none;
    padding: 0;
    margin: 0;
    text-align: center;
  }

  li {
    margin: 20px 0;
    cursor: pointer;
    display: block;
  }
}
</style>
