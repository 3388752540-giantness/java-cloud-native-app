<template>
  <div class="mall-container">
    <!-- 顶部状态栏 -->
    <div class="top-bar">
      <div class="inner">
        <div class="user-links">
          <span>你好，欢迎访问贵美商城！</span>
          <span class="time">{{ currentTime }}</span>
        </div>
        <div class="top-nav">
          <el-link :underline="false"><el-icon><ShoppingCart /></el-icon> 购物车</el-link>
          <el-divider direction="vertical" />
          <el-link :underline="false">帮助中心</el-link>
          <el-divider direction="vertical" />
          <el-link :underline="false">加入收藏</el-link>
          <el-divider direction="vertical" />
          <el-link :underline="false">设为首页</el-link>
          <el-divider direction="vertical" />
          <el-button size="small" type="primary" link @click="$router.push('/login')">登录</el-button>
          <el-button size="small" type="primary" link @click="$router.push('/register')">注册</el-button>
        </div>
      </div>
    </div>

    <!-- Logo 与 搜索栏 -->
    <div class="header-main">
      <div class="logo-box">
        <h1 class="logo-text">GM <span>贵美.商城</span></h1>
        <p class="sub-logo">GuiMei Shopping</p>
      </div>
      <div class="search-box">
        <el-input v-placeholder="'请输入商品名称'" class="input-with-select">
          <template #append>
            <el-button type="primary">搜 索</el-button>
          </template>
        </el-input>
      </div>
    </div>

    <!-- 主导航栏 -->
    <div class="main-nav">
      <el-menu mode="horizontal" default-active="1" background-color="#ff9900" text-color="#fff" active-text-color="#fff">
        <el-menu-item index="1">首 页</el-menu-item>
        <el-menu-item index="2">家用电器</el-menu-item>
        <el-menu-item index="3">手机数码</el-menu-item>
        <el-menu-item index="4">日用百货</el-menu-item>
        <el-menu-item index="5">书 籍</el-menu-item>
        <el-menu-item index="6">帮助中心</el-menu-item>
        <el-menu-item index="7">免费开店</el-menu-item>
        <el-menu-item index="8">全球咨询</el-menu-item>
      </el-menu>
    </div>

    <!-- 主体内容 -->
    <el-row :gutter="10" class="content-body">
      <!-- 左侧分类 -->
      <el-col :span="5">
        <div class="sidebar">
          <div class="side-title">商品分类 <span class="all">全部分类</span></div>
          <div class="category-list">
            <div v-for="cat in categories" :key="cat.name" class="cat-group">
              <div class="cat-hd">{{ cat.name }}</div>
              <div class="cat-bd">
                <span v-for="sub in cat.subs" :key="sub">{{ sub }}</span>
              </div>
            </div>
          </div>
        </div>
      </el-col>

      <!-- 中间核心区 -->
      <el-col :span="14">
        <!-- 轮播图 -->
        <el-carousel height="280px" class="main-banner">
          <el-carousel-item v-for="item in 4" :key="item">
            <div class="banner-placeholder">
              <h2>OUTLETS</h2>
              <p>商城品牌折扣区</p>
              <el-button type="danger">开张啦</el-button>
            </div>
          </el-carousel-item>
        </el-carousel>

        <!-- 疯狂抢购区 -->
        <div class="flash-sale">
          <div class="section-hd">疯狂抢购 <el-icon><ArrowRightBold /></el-icon></div>
          <el-row :gutter="10">
            <el-col :span="8" v-for="p in flashProducts" :key="p.id">
              <el-card class="product-card" :body-style="{ padding: '10px' }">
                <el-image src="https://iph.href.lu/120x120?text=Product" />
                <div class="price">￥{{ p.price }}</div>
                <p class="p-name">{{ p.name }}</p>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-col>

      <!-- 右侧辅助区 -->
      <el-col :span="5">
        <!-- 公告栏 -->
        <el-card class="side-card" header="公告栏">
          <ul class="news-list">
            <li>大学要求老师开网店</li>
            <li>安全锤网上大热销</li>
            <li>商城完成网购试衣间</li>
            <li>2万网上开十家连锁店</li>
          </ul>
        </el-card>

        <!-- 新品上架 -->
        <el-card class="side-card" header="新品上架" style="margin-top: 10px;">
          <div v-for="item in 4" :key="item" class="new-item">
            <el-image src="https://iph.href.lu/40x40?text=New" />
            <span>大牌狂降价，三折直送</span>
          </div>
        </el-card>

        <!-- 手机充值 (模拟 K8s 资源充值) -->
        <el-card class="recharge-card" style="margin-top: 10px;">
          <template #header>
            <div class="tabs"><span>笔记本</span> <span class="active">手机充值</span></div>
          </template>
          <el-form label-width="60px" size="small">
            <el-form-item label="运营商">
              <el-select v-model="recharge.provider"><el-option label="移动" value="1" /></el-select>
            </el-form-item>
            <el-form-item label="地区">
              <el-select v-model="recharge.region"><el-option label="浙江" value="1" /></el-select>
            </el-form-item>
            <el-form-item label="面值">
              <el-select v-model="recharge.amount"><el-option label="50元" value="50" /></el-select>
            </el-form-item>
            <el-button type="warning" style="width: 100%;">点击充值</el-button>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { ShoppingCart, ArrowRightBold } from '@element-plus/icons-vue'

const currentTime = ref('2026年1月21日 12点51分')

const categories = [
  { name: '家用电器', subs: ['大家电', '洗衣机', '平板电视', '热水器', '冷柜', 'DVD'] },
  { name: '书籍', subs: ['文学', '经管', '少儿', '励志', '科技', '生活'] },
  { name: '手机数码', subs: ['智能机', '单反', '笔记本', '平板', '配件'] }
]

const flashProducts = [
  { id: 1, name: '惠普商务移动笔记本', price: 1760 },
  { id: 2, name: '夏新 N6 GPS 导航手机', price: 1488 },
  { id: 3, name: '三星家庭影院套装', price: 2499 },
  { id: 4, name: '明基 MP512 投影仪', price: 60 },
  { id: 5, name: '1TB 移动鼠标', price: 80 },
  { id: 6, name: '海森那尔极品耳机', price: 148 }
]

const recharge = reactive({
  provider: '1',
  region: '1',
  amount: '50'
})
</script>

<style scoped>
.mall-container {
  background-color: #fff;
  font-size: 12px;
  color: #333;
}
.top-bar {
  background-color: #f7f7f7;
  border-bottom: 1px solid #ddd;
  height: 30px;
  line-height: 30px;
}
.top-bar .inner {
  max-width: 1000px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
}
.header-main {
  max-width: 1000px;
  margin: 15px auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.logo-text {
  font-size: 32px;
  color: #c00;
  margin: 0;
}
.logo-text span { color: #333; font-size: 24px; }
.sub-logo { font-size: 14px; color: #999; margin: 0; }
.search-box { width: 450px; }

.main-nav {
  max-width: 1000px;
  margin: 0 auto;
}
.main-nav :deep(.el-menu) {
  height: 35px;
  border-radius: 4px 4px 0 0;
}
.main-nav :deep(.el-menu-item) {
  height: 35px;
  line-height: 35px;
  font-weight: bold;
}

.content-body {
  max-width: 1000px;
  margin: 10px auto 0;
}
.sidebar {
  border: 1px solid #ffcc66;
}
.side-title {
  background-color: #ffcc66;
  padding: 8px;
  font-weight: bold;
  display: flex;
  justify-content: space-between;
}
.cat-hd {
  color: #f60;
  font-weight: bold;
  padding: 5px 10px;
  border-bottom: 1px dotted #ccc;
}
.cat-bd {
  padding: 5px 10px;
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}
.cat-bd span { cursor: pointer; color: #666; }
.cat-bd span:hover { color: #f60; }

.banner-placeholder {
  background: linear-gradient(135deg, #eee 0%, #ddd 100%);
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 1px solid #ccc;
}

.flash-sale {
  margin-top: 15px;
  border: 1px solid #ddd;
}
.section-hd {
  background: linear-gradient(to bottom, #ff9900, #ff6600);
  color: #fff;
  padding: 5px 10px;
  font-weight: bold;
}
.product-card {
  text-align: center;
  border: none;
  box-shadow: none !important;
}
.price {
  color: #fff;
  background-color: #ff6666;
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  margin: 5px 0;
}
.p-name { font-size: 11px; color: #444; }

.side-card :deep(.el-card__header) {
  background-color: #ffcc66;
  padding: 5px 10px;
  font-size: 12px;
  font-weight: bold;
}
.news-list { list-style: none; padding: 0; margin: 0; }
.news-list li { padding: 5px 0; border-bottom: 1px dotted #eee; }

.new-item {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 8px;
  font-size: 11px;
}
.recharge-card :deep(.el-card__header) { padding: 0; }
.tabs { display: flex; }
.tabs span { flex: 1; text-align: center; padding: 8px; cursor: pointer; }
.tabs span.active { background-color: #fff; font-weight: bold; }
</style>