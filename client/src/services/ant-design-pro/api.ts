// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';
import {getHeaders, SERVER_URL} from "@/utils/utils";

/** Get the current user GET /api/currentUser */
export async function currentUser(options?: { [key: string]: any }) {
  const headers = await getHeaders();
  return request<{
    data: API.CurrentUser;
  }>(`${SERVER_URL}/currentUser`, {
    headers,
    method: 'GET',
    ...(options || {}),
  });
}

/** logout interface POST /api/login/outLogin */
export async function outLogin(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/login/outLogin', {
    method: 'POST',
    ...(options || {}),
  });
}

/** login interface POST /api/login/account */
export async function login(body: API.LoginParams, options?: { [key: string]: any }) {
  return request<API.LoginResult>(`${SERVER_URL}/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}


/** There are no annotations provided by the backend here GET /api/notices */
export async function getNotices(options?: { [key: string]: any }) {
  return request<API.NoticeIconList>('/api/notices', {
    method: 'GET',
    ...(options || {}),
  });
}

/** Get the list of products GET /api/product */
export async function getProducts(
  params: {
    // query
    /** Current page number */
    current?: number;
    /** Page capacity */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  console.log('inside getProduct ???');
  const data = await request<API.TableListItem>(`${SERVER_URL}/product`, {
    method: 'GET',
    headers: await getHeaders(),
    params: {
      ...params,
    },
    ...(options || {}),
  });
  return data;
}

/** Get the list of vendors GET /api/vendor */
export async function getVendors(
  params: {
    // query
    /** Current page number */
    current?: number;
    /** Page capacity */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  const data = await request<API.TableListItem>(`${SERVER_URL}/vendor`, {
    method: 'GET',
    headers: await getHeaders(),
    params: {
      ...params,
    },
    ...(options || {}),
  });
  return data;
}

/** Get the list of customers GET /api/customer */
export async function getCustomers(
  params: {
    // query
    /** Current page number */
    current?: number;
    /** Page capacity */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  const data = await request<API.TableListItem>(`${SERVER_URL}/customer`, {
    method: 'GET',
    headers: await getHeaders(),
    params: {
      ...params,
    },
    ...(options || {}),
  });
  return data;
}

/** Get the list of the client GET /api/client */
export async function getClients(
  params: {
    // query
    /** Current page number */
    current?: number;
    /** Page capacity */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  return request<API.ClientItem>(`${SERVER_URL}/client`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** Get the list of users GET /api/user */
export async function getUsers(
  params: {
    // query
    /** Current page number */
    current?: number;
    /** Page capacity */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  const data = await request<API.TableListItem>(`${SERVER_URL}/user`, {
    method: 'GET',
    headers: await getHeaders(),
    params: {
      ...params,
    },
    ...(options || {}),
  });
  return data;
}

/** Get the list of category GET /api/customer */
export async function getCategory(
  params: {
    // query
    /** Current page number */
    current?: number;
    /** Page capacity */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  const data = await request<API.TableListItem>(`${SERVER_URL}/category`, {
    method: 'GET',
    headers: await getHeaders(),
    params: {
      ...params,
    },
    ...(options || {}),
  });
  return data;
}

/** get list of rules GET /api/rule */
export async function rule(
  params: {
    // query
    /** current page number */
    current?: number;
    /** Page capacity */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  return request<API.RuleList>('/api/rule', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** new rule PUT /api/rule */
export async function updateRule(options?: { [key: string]: any }) {
  return request<API.RuleListItem>('/api/rule', {
    method: 'PUT',
    ...(options || {}),
  });
}

/** new rule POST /api/rule */
export async function addRule(options?: { [key: string]: any }) {
  return request<API.RuleListItem>('/api/rule', {
    method: 'POST',
    ...(options || {}),
  });
}

/** delete rule DELETE /api/rule */
export async function removeRule(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/rule', {
    method: 'DELETE',
    ...(options || {}),
  });
}
