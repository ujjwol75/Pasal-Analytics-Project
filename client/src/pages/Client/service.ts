import { getDishHeaders, getHeaders, SERVER_URL } from '@/utils/utils';
import { request } from 'umi';

export async function submitNewClient(params: any) {
  const headers = await getHeaders();
  return request(`${SERVER_URL}/client`, {
    headers,
    method: 'POST',
    data: params,
  });
}


export async function getClients() {
  // const headers = await getHeaders();
  const headers = getDishHeaders();
  return request(`${SERVER_URL}/client/list`, { headers });
}

