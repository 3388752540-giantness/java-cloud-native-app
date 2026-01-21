<template>
  <div class="dashboard-container">
    <el-row :gutter="20">
      <el-col :span="8" v-for="node in nodes" :key="node.name">
        <el-card :body-style="{ padding: '20px' }" class="node-card">
          <template #header>
            <div class="card-header">
              <span class="node-name">
                <el-icon><Platform /></el-icon> {{ node.name }}
              </span>
              <el-tag :type="node.status === 'Ready' ? 'success' : 'danger'" effect="dark">
                {{ node.status }}
              </el-tag>
            </div>
          </template>
          
          <div class="node-body">
            <div class="info-row">
              <span class="label">内网 IP:</span>
              <span class="value">{{ node.ip }}</span>
            </div>
            <div class="info-row">
              <span class="label">节点角色:</span>
              <el-tag size="small" type="warning">{{ node.role }}</el-tag>
            </div>
            
            <el-divider content-position="left">资源占用</el-divider>
            
            <div class="progress-section">
              <div class="progress-label">CPU Usage</div>
              <el-progress :percentage="node.cpu" :color="customColors" />
            </div>
            
            <div class="progress-section">
              <div class="progress-label">Memory Usage</div>
              <el-progress :percentage="node.mem" :color="customColors" />
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'

// 初始化为空数组
const nodes = ref([])

// 后端 API 地址：指向你的 Master IP + Java 后端的 NodePort
// 注意：如果在生产环境，这里通常会用相对路径或环境变量
const API_BASE = 'http://192.168.37.100:30080/api'

const fetchNodeData = async () => {
  try {
    const response = await axios.get(`${API_BASE}/nodes`)
    nodes.value = response.data
    console.log('成功获取节点数据:', response.data)
  } catch (error) {
    console.error('获取数据失败:', error)
    ElMessage.error('无法连接到后端监控接口，请检查服务状态')
  }
}

// 页面加载时自动执行一次
onMounted(() => {
  fetchNodeData()
  
  // 可选：开启自动刷新，每 10 秒刷新一次数据（实现实时监控效果）
  setInterval(fetchNodeData, 10000)
})

const customColors = [
  { color: '#67c23a', percentage: 20 },
  { color: '#e6a23c', percentage: 40 },
  { color: '#f56c6c', percentage: 80 }
]
</script>

<style scoped>
.dashboard-container { padding: 10px; }
.node-card { margin-bottom: 20px; transition: all 0.3s; border-radius: 8px; }
.node-card:hover { transform: translateY(-5px); }
.card-header { display: flex; justify-content: space-between; align-items: center; }
.node-name { font-weight: bold; display: flex; align-items: center; gap: 5px; }
.info-row { display: flex; justify-content: space-between; margin-bottom: 10px; font-size: 14px; }
.label { color: #909399; }
.value { font-family: monospace; font-weight: bold; }
.progress-section { margin-top: 15px; }
.progress-label { font-size: 12px; color: #606266; margin-bottom: 5px; }
</style>