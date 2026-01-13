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
import { ref } from 'vue'

const nodes = ref([
  { name: 'k8s-master', ip: '192.168.37.100', role: 'Control-Plane', status: 'Ready', cpu: 24, mem: 58 },
  { name: 'k8s-worker1', ip: '192.168.37.101', role: 'Worker', status: 'Ready', cpu: 12, mem: 35 },
  { name: 'k8s-worker2', ip: '192.168.37.102', role: 'Worker', status: 'Ready', cpu: 8, mem: 30 }
])

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