// 模拟登录

export async function checkToken () {
  await new Promise(resolve => setTimeout(() => { resolve(true); }, 100));

  const lastLogin = window.localStorage.getItem('juggle-login-timestamp');
  // 12个小时
  const duration = 12 * 60 * 60 * 1000;
  return Number(lastLogin) + duration > Date.now();
}

export async function doLogin (username: string, password: string) {

  await new Promise(resolve => setTimeout(() => { resolve(true); }, 500));

  if (username === 'admin' && (password === 'admin' || password === '123456')) {
    const timestamp = Date.now();
    window.localStorage.setItem('juggle-login-timestamp', timestamp + '');
    return true;
  }

  return false;
}
