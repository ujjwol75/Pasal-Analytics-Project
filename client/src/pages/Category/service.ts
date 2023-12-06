import { getDishHeaders, getHeaders, SERVER_URL } from '@/utils/utils';
import { request } from 'umi';

export async function submitNewCategory(params: any) {
  const headers = await getHeaders();
  return request(`${SERVER_URL}/category`, {
    headers,
    method: 'POST',
    data: params,
  });
}

export async function getCategories() {
  return request(`${SERVER_URL}/category/list`);
}
