import { createRouter, createWebHistory } from 'vue-router';
import Dashboard from '../views/Dashboard.vue';
import Register from '../views/Register.vue'; // 引入新页面
import Careers from '../views/Careers.vue';//作业删除
import Marketplace from '../views/Marketplace.vue';


const routes = [
  { path: '/', component: Dashboard },
  { path: '/register', component: Register }, // 添加路径
  { path: '/careers', component: Careers },
  { path: '/mall', component: Marketplace }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
