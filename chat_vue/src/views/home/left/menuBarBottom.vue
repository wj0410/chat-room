<template>
  <div class="menu-bar-bottom">
    <ul>
      <li>
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-caidan-xian"></use>
        </svg>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>

</script>


<style scoped lang="less">
.menu-bar-bottom {
  flex: 0.11;

  ul {
    list-style: none;
    padding: 0;
    margin: 0;
    text-align: center;
  }

  li {
    margin: 20px 0;
    cursor: pointer;
  }
}

</style>
