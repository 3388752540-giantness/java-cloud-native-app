<template>
  <div class="register-container">
    <!-- 头部 Logo 区 -->
    <div class="reg-header">
      <div class="header-content">
        <div class="logo">
          <el-icon :size="30" color="#409EFF"><Monitor /></el-icon>
          <span>K8s 监控平台账号注册</span>
        </div>
        <div class="help-link">帮助</div>
      </div>
    </div>

    <!-- 顶部欢迎栏 -->
    <div class="welcome-bar">
      <el-icon><ChatDotRound /></el-icon>
      欢迎注册 K8s 云原生微服务管理平台！
    </div>

    <el-card class="register-card">
      <el-form :model="regForm" :rules="rules" ref="regFormRef" label-width="120px" label-position="left">
        
        <!-- 第一部分：创建账号 -->
        <div class="form-section-title">创建您的账号</div>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="regForm.username" placeholder="请输入用户名" style="width: 300px" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="regForm.password" type="password" show-password placeholder="请输入密码" style="width: 300px" />
        </el-form-item>
        <el-form-item label="再次输入密码" prop="checkPass">
          <el-input v-model="regForm.checkPass" type="password" show-password placeholder="确认密码" style="width: 300px" />
        </el-form-item>

        <!-- 第二部分：安全设置 -->
        <div class="form-section-title">安全信息设置</div>
        <el-form-item label="密码保护问题" prop="question">
          <el-select v-model="regForm.question" placeholder="请选择密码提示问题" style="width: 300px">
            <el-option label="您的出生地在哪里？" value="1" />
            <el-option label="您最喜欢的服务器架构是什么？" value="2" />
            <el-option label="您的第一台电脑主频是多少？" value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="保护问题答案" prop="answer">
          <el-input v-model="regForm.answer" style="width: 300px" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="regForm.gender">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="出生日期">
          <el-date-picker
            v-model="regForm.birthday"
            type="date"
            placeholder="选择日期"
            style="width: 300px"
          />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="regForm.phone" placeholder="请输入手机号" style="width: 300px" />
        </el-form-item>

        <!-- 第三部分：注册验证 -->
        <div class="form-section-title">注册验证</div>
        <el-form-item label="验证码">
          <div class="captcha-box">
            <!-- 使用 Element 的图片占位 -->
            <el-image 
              src="https://iph.href.lu/100x40?text=K8sA1" 
              class="captcha-img"
            />
            <el-link type="primary" :underline="false" style="margin-left: 10px">看不清楚，换一张</el-link>
          </div>
        </el-form-item>
        <el-form-item label="输入上边字符" prop="captcha">
          <el-input v-model="regForm.captcha" style="width: 200px" />
        </el-form-item>

        <!-- 第四部分：服务条款 -->
        <div class="terms-section">
          <el-checkbox v-model="regForm.agree">
            我已阅读并接受 <el-link type="primary">“服务条款”</el-link> 和 <el-link type="primary">“隐私权保护”</el-link>
          </el-checkbox>
          <div class="submit-btn-box">
            <el-button type="primary" size="large" @click="handleRegister" class="create-btn">创建账号</el-button>
          </div>
        </div>
      </el-form>
    </el-card>

    <!-- 页脚 -->
    <div class="reg-footer">
      <p>关于 K8s 监控平台 | 官方博客 | 财富邮 | 精美贺卡 | 举报违法信息 | 客户服务</p>
      <p>隐私政策 | 云原生有限公司版权所有 © 1997-2026</p>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { Monitor, ChatDotRound } from '@element-plus/icons-vue'

const regFormRef = ref(null)

const regForm = reactive({
  username: '',
  password: '',
  checkPass: '',
  question: '',
  answer: '',
  gender: '男',
  birthday: '',
  phone: '',
  captcha: '',
  agree: false
})

// 校验规则
const rules = reactive({
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  checkPass: [{ required: true, message: '请再次输入密码', trigger: 'blur' }],
  agree: [{ 
    validator: (rule, value, callback) => {
      if (!value) callback(new Error('请接受服务条款'))
      else callback()
    }, 
    trigger: 'change' 
  }]
})

const handleRegister = () => {
  if (!regForm.agree) {
    ElMessage.warning('请先勾选并接受服务条款')
    return
  }
  regFormRef.value.validate((valid) => {
    if (valid) {
      ElMessage.success('注册请求已发送，正在调用后端 K8s API...')
      // 以后在这里写 axios.post('/api/register', regForm)
    }
  })
}
</script>

<style scoped>
.register-container {
  background-color: #fff;
  min-height: 100vh;
  font-family: "Microsoft YaHei", sans-serif;
}
.header-content {
  width: 960px;
  margin: 0 auto;
  padding: 20px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.logo {
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 22px;
  font-weight: bold;
  color: #333;
}
.welcome-bar {
  width: 960px;
  margin: 0 auto 10px;
  background-color: #f2f8ff;
  border: 1px solid #d4e7ff;
  padding: 10px;
  font-size: 14px;
  color: #0055aa;
  display: flex;
  align-items: center;
  gap: 8px;
}
.register-card {
  width: 960px;
  margin: 0 auto;
  border-radius: 0;
  box-shadow: none;
  border: 1px solid #ddd;
}
.form-section-title {
  background-color: #f7f7f7;
  padding: 8px 15px;
  margin: 20px 0;
  font-weight: bold;
  color: #333;
  border-left: 4px solid #409EFF;
}
.captcha-box {
  display: flex;
  align-items: center;
}
.captcha-img {
  width: 100px;
  height: 40px;
  border: 1px solid #ccc;
}
.terms-section {
  margin-top: 30px;
  padding-left: 120px;
}
.submit-btn-box {
  margin-top: 20px;
  padding-bottom: 40px;
}
.create-btn {
  padding: 12px 40px;
  font-size: 16px;
}
.reg-footer {
  text-align: center;
  margin-top: 30px;
  padding-bottom: 50px;
  color: #666;
  font-size: 12px;
  line-height: 2;
}
</style>