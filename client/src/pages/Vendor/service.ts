import { getDishHeaders, getHeaders, SERVER_URL } from '@/utils/utils';
import { request } from 'umi';

export async function submitNewVendor(params: any) {
  const headers = await getHeaders();
  return request(`${SERVER_URL}/vendor`, {
    headers,
    method: 'POST',
    data: params,
  });
}

export async function getVendors() {
  // const headers = await getHeaders();
  const headers = getDishHeaders();
  return request(`${SERVER_URL}/vendor/list`, { headers });
}
