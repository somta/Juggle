import fetch from 'node-fetch';

export async function get(url: string, headers = {}) {
  const response = await fetch(url, {
    method: 'GET',
    headers: { 'Content-Type': 'application/json', ...headers },
  });
  return response.json();
}

export async function post(
  url: string,
  data: Record<string, any> = {},
  headers = {}
) {
  const response = await fetch(url, {
    method: 'POST',
    body: JSON.stringify(data),
    headers: { 'Content-Type': 'application/json', ...headers },
  });

  return response.json();
}
