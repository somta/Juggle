
import { userAPI } from '@/service/api';
import CryptoJS from 'crypto-js';

const authKey = 'Juggle-Authorization';

export async function login (data: Parameters<typeof userAPI.login>[0]) {
  const userName = data.userName;
  let password = CryptoJS.MD5(data.password).toString();
  password = CryptoJS.MD5(data.password).toString();
  const res = await userAPI.login({ userName, password });
  if (res.success) {
    window.localStorage.setItem(authKey, res.result);
  }
  return res;
}

export function logout () {
  window.localStorage.removeItem(authKey);
  return Promise.resolve(true);
}

export function check () {
  const auth = window.localStorage.getItem(authKey);
  if (auth) {
    return Promise.resolve(true);
  }
  return Promise.resolve(false);
}

export function getAuth () {
  return window.localStorage.getItem(authKey);
}
