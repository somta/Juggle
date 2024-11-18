import { suiteAPI } from '../api';

export async function addSuite(params: Parameters<typeof suiteAPI.addSuite>[0]) {
  return suiteAPI.addSuite(params);
}

export async function deleteSuite(suiteId: number) {
  return suiteAPI.deleteSuite(suiteId);
}

export async function updateSuite(params: Parameters<typeof suiteAPI.updateSuite>[0]) {
  return suiteAPI.updateSuite(params);
}

export async function querySuitePage(params: Parameters<typeof suiteAPI.suitePage>[0]) {
  return suiteAPI.suitePage(params);
}

export async function querySuiteList() {
  return suiteAPI.suiteList();
}
