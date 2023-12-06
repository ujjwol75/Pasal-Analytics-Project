import { getDishHeaders, getHeaders, SERVER_URL } from '@/utils/utils';
import { request } from 'umi';

export async function submitNewUser(params: any) {
  return request(`${SERVER_URL}/user`, {
    method: 'POST',
    data: params,
  });
}

export async function getClients() {
  return request(`${SERVER_URL}/client/list`);
}

