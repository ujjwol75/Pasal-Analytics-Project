import { getHeaders, SERVER_URL } from '@/utils/utils';
import { request } from 'umi';
import type { AnalysisData } from './data';

export async function fakeChartData(): Promise<{ data: AnalysisData }> {
  return request('/api/fake_analysis_chart_data');
}

export async function fetchAnalysisData(): Promise<{ data: AnalysisData }> {
  const headers = await getHeaders();
  return request(`${SERVER_URL}/analysis`, { headers, method: 'GET' });
}
