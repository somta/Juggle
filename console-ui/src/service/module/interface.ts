
import { interfaceAPI } from '../api';

export async function queryDomain (params: Parameters<typeof interfaceAPI.queryDomain>[0]) {
  return interfaceAPI.queryDomain(params);
}

export async function addDomain (params: Parameters<typeof interfaceAPI.addDomain>[0]) {
  return interfaceAPI.addDomain(params);
}

export async function editDomain (params: Parameters<typeof interfaceAPI.editDomain>[0]) {
  return interfaceAPI.editDomain(params);
}

export async function deleteDomain (params: Parameters<typeof interfaceAPI.deleteDomain>[0]) {
  return interfaceAPI.deleteDomain(params);
}
