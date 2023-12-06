import { getDishHeaders, SERVER_URL } from '@/utils/utils';
import { request } from 'umi';

export async function submitNewProduct(params: any) {
  return request(`${SERVER_URL}/product`, {
    method: 'POST',
    data: params,
  });
}

export async function getVendors() {
  return request(`${SERVER_URL}/vendor/list`);
}
