import AboutView from './AboutView.vue'
import SettingView from './SettingView.vue'
import UserView from './UserView.vue'

export const CommonRoutes = [
  {
    path: 'about',
    name: 'about',
    component: AboutView,
    meta: { name: '关于' },
  },
  {
    path: 'setting',
    name: 'setting',
    component: SettingView,
    meta: { name: '设置' },
  },
  {
    path: 'user',
    name: 'user',
    component: UserView,
    meta: { name: '用户' },
  },
]
