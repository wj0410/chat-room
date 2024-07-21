<template>
    <div class="register-container">
        <form @submit="onSubmit">
            <h2>注册新账号</h2>
            <input type="text" placeholder="用户名" required>
            <input type="password" placeholder="密码" required>
            <input type="password" placeholder="确认密码" required>
            <button type="submit">注册</button>
            <p>已有账号？<a href="#" @click="jump('/login')">立即登录</a></p>
        </form>
    </div>

</template>
<script setup lang="ts">
import { reactive } from 'vue'
import { useRouter } from "vue-router";
const router = useRouter();
const jump = (path: string) => {
    router.push(path);
};
const form = reactive({
    username: '',
    password: ''
})
const onSubmit = (event: any) => {
    console.log('submit!')
    event.preventDefault()
}
</script>
<style lang="scss" scoped>
.register-container {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;

    form {
        max-width: 500px;
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
    }

    input[type="text"],
    input[type="password"],
    button {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ddd;
        border-radius: 3px;
        box-sizing: border-box;
    }

    button {
        background-color: #007bff;
        color: #fff;
        cursor: pointer;
    }

}
</style>