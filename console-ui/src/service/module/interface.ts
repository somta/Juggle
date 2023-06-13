
import { interfaceAPI } from '../api';

export async function domainQuery (params: Parameters<typeof interfaceAPI.domainQuery>[0]) {
  return interfaceAPI.domainQuery(params);
}

export async function domainAdd (params: Parameters<typeof interfaceAPI.domainAdd>[0]) {
  return interfaceAPI.domainAdd(params);
}

export async function domainUpdate (params: Parameters<typeof interfaceAPI.domainUpdate>[0]) {
  return interfaceAPI.domainUpdate(params);
}

export async function domainDelete (params: Parameters<typeof interfaceAPI.domainDelete>[0]) {
  return interfaceAPI.domainDelete(params);
}
