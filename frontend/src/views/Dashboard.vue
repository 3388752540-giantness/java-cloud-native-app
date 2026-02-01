<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <!-- 遍历三个节点 -->
      <el-col :span="24" v-for="node in ['k8s-master', 'k8s-worker1', 'k8s-worker2']" :key="node">
        <el-card shadow="always" style="margin-bottom: 20px">
          <template #header>
            <div class="card-header">
              <b style="font-size: 18px">{{ node }} 实时指标</b>
              <el-tag type="success">采集频率: 5s</el-tag>
            </div>
          </template>
          <!-- 这里放折线图的容器 -->
          <div :id="'chart-' + node" style="width: 100%; height: 300px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { onMounted, onUnmounted } from 'vue'
import axios from 'axios'
import * as echarts from 'echarts'

const API_BASE = 'https://api.k8s-dja.xyz/api'
let timers = [] // 存放定时器

const initChart = async (nodeName) => {
  const chartDom = document.getElementById('chart-' + nodeName)
  const myChart = echarts.init(chartDom)
  
  const updateData = async () => {
    try {
      const res = await axios.get(`${API_BASE}/metrics/${nodeName}`)
      const data = res.data
      
      const times = data.map(item => item.time)
      // 处理 CPU 数据：截取数值（例如把 105165780n 转为容易看懂的数字）
      const cpuValues = data.map(item => parseInt(item.cpu) / 1000000) 

      myChart.setOption({
        title: { text: 'CPU 负载趋势 (m)' },
        tooltip: { trigger: 'axis' },
        xAxis: { data: times },
        yAxis: { type: 'value' },
        series: [{
          name: 'CPU',
          type: 'line',
          data: cpuValues,
          smooth: true,
          areaStyle: {},
          itemStyle: { color: '#409EFF' }
        }]
      })
    } catch (e) { console.error("数据加载失败", e) }
  }

  updateData() // 立即执行一次
  const timer = setInterval(updateData, 5000) // 每5秒拉取一次
  timers.push(timer)
}

onMounted(() => {
  ['k8s-master', 'k8s-worker1', 'k8s-worker2'].forEach(node => initChart(node))
})

// 页面关闭时销毁定时器，防止内存泄漏
onUnmounted(() => {
  timers.forEach(t => clearInterval(t))
})
</script>