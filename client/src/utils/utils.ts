const prod = process.env.NODE_ENV === 'production';

const fullHost = window.location.host;
const parts = fullHost.split('.');

export const CLIENT = parts[0];
export const SERVER_URL = prod ? `https://${CLIENT}.pasal.io:8443/api` : `http://${CLIENT}.local:8080/api`;
export const SERVER_URL_OAUTH = prod ? `https://${CLIENT}.pasal.io:8443/oauth` : `http://${CLIENT}.local:8080/oauth`;

export const asyncLocalStorage = {
  async setItem (key: any, value: any) {
    await null;
    return localStorage.setItem(key, value);
  },
  async getItem (key: any) {
    await null;
    return localStorage.getItem(key);
  }
};

export async function getHeaders() {
  const authToken = await asyncLocalStorage.getItem('auth');
  return {
    'Content-Type': 'application/json',
    Authorization: `Bearer ${authToken ? JSON.parse(authToken).access_token : null}`,
  };
}

export function getDishHeaders() {
  const authToken = localStorage.getItem('auth')
  return {
    'Content-Type': 'application/json',
    Authorization: `Bearer ${authToken ? JSON.parse(authToken).access_token : null}`,
  }
}
