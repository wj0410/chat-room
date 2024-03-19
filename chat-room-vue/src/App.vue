<script setup lang="ts">
import User from './model/User';
import useUserStore from "@/stores/User";
import {onMounted} from 'vue'

const userStore = useUserStore()
let user1 = new User('{"id":1,"username":"admin","nickName":"管理员","avatar":"https://avatars.githubusercontent.com/u/43922975?v=4"}');
let user2 = new User('{"id":2,"username":"user","nickName":"用户","avatar":"https://avatars.githubusercontent.com/u/43922975?v=4"}');
userStore.addOnlineUser(user1)
userStore.addOnlineUser(user2)
console.log("onlineUserList",userStore.onlineUserList)
console.log("onlineUserTotal",userStore.onlineUserTotal)
console.log("getadminUser",userStore.getUser("admin"))
userStore.removeOnlineUser(user2)
console.log("删除用户后")
console.log("onlineUserList",userStore.onlineUserList)
console.log("onlineUserTotal",userStore.onlineUserTotal)

</script>

<template>
  <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125"/>

    <div class="wrapper">
      <HelloWorld msg="You did it!"/>

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView/>
</template>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>
