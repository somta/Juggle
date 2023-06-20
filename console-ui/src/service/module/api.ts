
import { apiAPI } from '../api';

export async function domainQuery (params: Parameters<typeof apiAPI.domainQuery>[0]) {
  return apiAPI.domainQuery(params);
}

export async function domainAdd (params: Parameters<typeof apiAPI.domainAdd>[0]) {
  return apiAPI.domainAdd(params);
}

export async function domainUpdate (params: Parameters<typeof apiAPI.domainUpdate>[0]) {
  return apiAPI.domainUpdate(params);
}

export async function domainDelete (params: Parameters<typeof apiAPI.domainDelete>[0]) {
  return apiAPI.domainDelete(params);
}

export async function listQuery (params: Parameters<typeof apiAPI.listQuery>[0]) {
  return apiAPI.listQuery(params);
}

export async function listAdd (params: Parameters<typeof apiAPI.listAdd>[0]) {
  return apiAPI.listAdd(params);
}

export async function listUpdate (params: Parameters<typeof apiAPI.listUpdate>[0]) {
  return apiAPI.listUpdate(params);
}

export async function listDelete (params: Parameters<typeof apiAPI.listDelete>[0]) {
  return apiAPI.listDelete(params);
}
