
import AboutView from './AboutView.vue';
import SettingView from './SettingView.vue';
import UserView from './UserView.vue';

export const CommonRoutes = [
  {
    path: 'about',
    name: 'about',
    component: AboutView,
  },
  {
    path: 'setting',
    name: 'setting',
    component: SettingView,
  },
  {
    path: 'user',
    name: 'user',
    component: UserView,
  },
];
