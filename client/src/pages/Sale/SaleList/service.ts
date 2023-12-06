import { getHeaders, SERVER_URL } from '@/utils/utils';
import { request } from 'umi';

export async function queryRule(params?: API.SaleParams) {
  const headers = await getHeaders();
  const response = await request(`${SERVER_URL}/sale`, {
    method: 'GET',
    headers,
    params,
  });
  return response;
}

export async function removeRule(params: { key: number[] }) {
  return request('/api/rule', {
    method: 'POST',
    data: {
      ...params,
      method: 'delete',
    },
  });
}

export async function addRule(params: API.SaleParams) {
  return request('/api/rule', {
    method: 'POST',
    data: {
      ...params,
      method: 'post',
    },
  });
}

export async function updateRule(params: API.SaleParams) {
  return request('/api/rule', {
    method: 'POST',
    data: {
      ...params,
      method: 'update',
    },
  });
}
