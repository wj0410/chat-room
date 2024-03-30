<template>
    <div class="login-container">
        <div class="form">
            <h2>欢迎登录</h2>
            <input type="text" placeholder="用户名或邮箱" v-model="form.username" required>
            <input type="password" placeholder="密码" v-model="form.password" required>
            <button type="submit" @click="onSubmit">登录</button>
            <p>还没有账号？<a href="#" @click="jump('register')">立即注册</a></p>
        </div>
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
const onSubmit = () => {
    if (!(form.username && form.password)) {
        return;
    }
    console.log('submit!')
}
</script>
<style lang="scss" scoped>
.login-container {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;

    .form {
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