<template>
  <div class="dashboard-container">
    <el-row :gutter="20">
      <!-- 循环渲染节点，key 使用数据库 ID -->
      <el-col :span="8" v-for="node in nodes" :key="node.id">
        <el-card :body-style="{ padding: '20px' }" class="node-card">
          <template #header>
            <div class="card-header">
              <span class="node-name">
                <!-- 修改点1: 字段名改为 nodeName -->
                <el-icon><Platform /></el-icon> {{ node.nodeName }}
              </span>
              <el-tag :type="node.status === 'Ready' ? 'success' : 'danger'" effect="dark">
                {{ node.status }}
              </el-tag>
            </div>
          </template>
          
          <div class="node-body">
            <!-- 修改点2: 后端实体类暂时没有 IP 和 Role 字段，先改为显示 ID -->
            <div class="info-row">
              <span class="label">节点 ID:</span>
              <span class="value">#{{ node.id }}</span>
            </div>
            
            <el-divider content-position="left">资源占用</el-divider>
            
            <div class="progress-section">
              <div class="progress-label">CPU Usage</div>
              <!-- 修改点3: 字段名改为 cpuUsage -->
              <el-progress :percentage="node.cpuUsage" :color="customColors" />
            </div>
            
            <div class="progress-section">
              <div class="progress-label">Memory Usage</div>
              <!-- 修改点4: 字段名改为 memUsage -->
              <el-progress :percentage="node.memUsage" :color="customColors" />
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

const nodes = ref([])

// 确保这里的 IP 是你的 Master 节点 IP，端口是 NodePort (30080)
const API_BASE = 'http://192.168.37.100:30080/api'

const fetchNodeData = async () => {
  try {
    const response = await axios.get(`${API_BASE}/nodes`)
    // 后端已经开启驼峰映射，这里直接赋值即可
    nodes.value = response.data
    console.log('真实数据已加载:', response.data)
  } catch (error) {
    console.error('API 请求失败:', error)
    ElMessage.error('无法连接后端，请检查 K8s 服务状态')
  }
}

// 页面加载时执行
onMounted(() => {
  fetchNodeData()
  // 开启每 5 秒自动刷新，实现“实时监控”效果
  setInterval(fetchNodeData, 5000)
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