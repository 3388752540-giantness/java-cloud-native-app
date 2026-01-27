<template>
  <div class="careers-container">
    <!-- 顶部 Banner 区 -->
    <div class="banner-box">
      <el-image 
        src="https://iph.href.lu/1000x200?text=Join+Our+Cloud+Native+Team&fg=ffffff&bg=409eff" 
        class="banner-img"
      >
        <template #placeholder>
          <div class="image-slot">加载中<span class="dot">...</span></div>
        </template>
      </el-image>
      <div class="banner-text">选择云原生，实现你的价值！！</div>
    </div>

    <el-card class="job-card" shadow="never">
      <!-- 职位头部 -->
      <div class="job-header">
        <h2 class="job-title">UI设计师</h2>
      </div>

      <!-- 职位基础信息 -->
      <el-descriptions :column="1" border class="job-meta">
        <el-descriptions-item label="职位性质">全职</el-descriptions-item>
        <el-descriptions-item label="工作经验">两年以上</el-descriptions-item>
        <el-descriptions-item label="学历要求">本科及以上</el-descriptions-item>
        <el-descriptions-item label="招聘人数">3人</el-descriptions-item>
        <el-descriptions-item label="工作地点">北京</el-descriptions-item>
        <el-descriptions-item label="薪资范围">面议</el-descriptions-item>
      </el-descriptions>

      <!-- 职位描述 -->
      <div class="section">
        <h3 class="section-title">职位描述：</h3>
        <div class="section-content">
          <h4>技能要求</h4>
          <ol>
            <li>艺术设计专业背景，3年以上相关经验；</li>
            <li>精通UI设计、Flash动画，能够驾驭设计理论熟练完成界面设计工作；</li>
            <li>具备良好的审美能力和平面设计能力优先，有成功的界面设计、交互设计作品者优先；</li>
            <li>熟练应用Photoshop、Flash、Illustrator、Dreamweaver等设计软件。</li>
          </ol>

          <h4>素质要求</h4>
          <ol>
            <li>热爱IT职业教育行业，愿意这一行业长期发展，热爱技术，具有良好的基本技术素质；</li>
            <li>工作细心，有耐心；</li>
            <li>有较强的学习能力，沟通、表达能力强，有良好工作习惯；</li>
          </ol>
        </div>
      </div>

      <!-- 联系方式 -->
      <div class="section">
        <h3 class="section-title">联系方式：</h3>
        <div class="contact-info">
          <p><strong>公司名称：</strong> 北京阿博泰克北大青鸟信息技术有限公司</p>
          <p><strong>公司地址：</strong> 北京海淀成府路207号北大青鸟楼二层 B 座</p>
          <p><strong>传真：</strong> 62760088-6111</p>
          <p><strong>公司主页：</strong> <el-link type="primary" href="http://www.jb-aptech.com.cn" target="_blank">http://www.jb-aptech.com.cn</el-link></p>
        </div>
      </div>

      <!-- 招聘邮箱 -->
      <div class="section">
        <h3 class="section-title">招聘邮箱：</h3>
        <p class="email-text">zhaopin2@jb-aptech.com.cn</p>
      </div>

      <div class="apply-footer">
        <el-button type="primary" size="large" @click="handleApply">立即申请该职位</el-button>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ElMessage } from 'element-plus'

const handleApply = () => {
  ElMessage.success('简历上传模块正在开发中，请先发送邮件至招聘邮箱')
}
</script>

<style scoped>
.careers-container {
  max-width: 1000px;
  margin: 20px auto;
  background-color: #fff;
}
.banner-box {
  position: relative;
  margin-bottom: 20px;
}
.banner-img {
  width: 100%;
  height: 200px;
  border-radius: 4px;
}
.banner-text {
  margin-top: 10px;
  font-size: 14px;
  color: #666;
}
.job-card {
  border: 1px solid #ebeef5;
}
.job-title {
  font-size: 24px;
  color: #333;
  margin-bottom: 20px;
  border-bottom: 1px solid #eee;
  padding-bottom: 10px;
}
.job-meta {
  margin-bottom: 30px;
}
.section {
  margin-top: 25px;
}
.section-title {
  color: #0000ff; /* 还原原图的蓝色 */
  font-size: 18px;
  border-bottom: 2px solid #0000ff;
  display: inline-block;
  margin-bottom: 15px;
}
.section-content h4 {
  margin: 15px 0 10px;
  color: #333;
}
.section-content ol li {
  line-height: 1.8;
  color: #444;
  font-size: 15px;
  margin-bottom: 5px;
}
.contact-info p, .email-text {
  line-height: 2;
  font-size: 16px;
  color: #333;
}
.apply-footer {
  margin-top: 40px;
  text-align: center;
  padding-bottom: 20px;
}
</style>