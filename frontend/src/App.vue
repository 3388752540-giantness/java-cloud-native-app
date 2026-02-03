<template>
  <el-container class="layout-container">
    <el-header class="header">
      <div class="logo">
        <el-icon><Monitor /></el-icon>
        <span>K8s 云原生微服务管理平台1</span>
      </div>
      <div class="user-info">
        <el-tag type="info">管理员: dja</el-tag>
      </div>
    </el-header>
    
    <el-main>
      <router-view></router-view>
    </el-main>
    
    <el-footer class="footer">Cloud Native Graduation Project ©2025</el-footer>
  </el-container>
</template>

<style>
body { margin: 0; background-color: #f0f2f5; }
.layout-container { min-height: 100vh; }
.header {
  background-color: #001529;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 8px rgba(0,0,0,0.15);
}
.logo { display: flex; align-items: center; gap: 10px; font-size: 18px; font-weight: bold; }
.footer { text-align: center; color: #909399; font-size: 12px; }
</style>