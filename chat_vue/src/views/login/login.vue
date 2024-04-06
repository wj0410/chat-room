<template>
  <div class="login_wrap">
    <div class="login_from_wrap">
      <el-form
          ref="ruleFormRef"
          :model="ruleForm"
          :rules="rules"
          label-width="120px"
          class="demo-ruleForm"
          :size="formSize"
          status-icon
      >
        <el-form-item label="用户名 :" prop="account">
          <el-input style="width: 300px" v-model="ruleForm.account"/>
        </el-form-item>
        <el-form-item label="密码 :" prop="password">
          <el-input type="password" style="width: 300px" v-model="ruleForm.password"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm(ruleFormRef)">
            登录
          </el-button>
          <el-button @click="resetForm(ruleFormRef)">重置</el-button>
          <el-button type="primary" @click="visitorLogin()">游客登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {reactive, ref} from 'vue'
import type {FormInstance, FormRules} from 'element-plus'
import {ElMessage} from "element-plus";
import router from "@/router";
import {setToken} from "../../utils/auth";

const accountList: Array<UserInfo> = [
  {
    account: 'lkw',
    nickName: '廖开威',
    password: '123',
    avatar: 'http://47.98.35.95/files/images/tx07.png'
  },
  {
    account: 'pwj',
    nickName: '彭文杰',
    password: '123',
    avatar: 'http://47.98.35.95/files/images/tx02.png'
  },
  {
    account: 'wj',
    nickName: '王杰',
    password: '123',
    avatar: 'http://47.98.35.95/files/images/tx09.png'
  },
  {
    account: 'zh',
    nickName: '张恒',
    password: '123',
    avatar: 'http://47.98.35.95/files/images/tx03.png'
  },
  {
    account: 'zhouhui',
    nickName: '周慧',
    password: '123',
    avatar: 'http://47.98.35.95/files/images/tx01.png'
  },
]

interface RuleForm {
  account: string,
  password: string
}

const formSize = ref('default')
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive<RuleForm>({
  account: '',
  password: ''
})

const rules = reactive<FormRules<RuleForm>>({
  account: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
  ]
})

const WEB_FILE_URL = import.meta.env.VITE_WEB_FILE_URL

const loginSuccess = (userInfo: UserInfo) => {
  localStorage.setItem('userInfo', JSON.stringify(userInfo))
  setToken(JSON.stringify(userInfo))
  setTimeout(() => {
    router.push(
        {
          path: '/home'
        }
    )
  }, 500)
}

const visitorLogin = () => {
  // 生成5位随机字符串,只保留5位
  let randomStr = Math.random().toString(36).slice(2, 7)
  let nickName = ruleForm.account.trim()!==''?ruleForm.account:randomStr

  let user: UserInfo = {
    account: randomStr,
    nickName: '游客-' + nickName,
    avatar: WEB_FILE_URL + '/images/default.png'
  }
  loginSuccess(user)
}

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      let user: UserInfo = accountList.find((item: UserInfo) => item.account === ruleForm.account)
      if (user) {
        loginSuccess(user)
      } else {
        ElMessage({
          showClose: true,
          duration: 1000,
          message: '用户名密码错误',
          type: 'error',
        })
      }
    } else {
      console.log('error submit!', fields)
    }
  })
}

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}

</script>

<style scoped lang="less">
.login_wrap {
  width: 100vw;
  height: 100vh;
  background-color: #f3f3f3;
  position: relative;

  .login_from_wrap {
    padding: 20px;
    width: 700px;
    height: 500px;
    background-color: white;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translateX(-50%) translateY(-50%);

    .demo-ruleForm {
      margin-top: 200px;
      margin-left: 120px;
    }
  }
}
</style>
