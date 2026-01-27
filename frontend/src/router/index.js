import { createRouter, createWebHistory } from 'vue-router';
import Dashboard from '../views/Dashboard.vue';
import Register from '../views/Register.vue'; // 引入新页面



const routes = [
  { path: '/', component: Dashboard },
  { path: '/register', component: Register }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
