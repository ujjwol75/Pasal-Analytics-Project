import { DataItem } from '@antv/g2plot/esm/interface/config';

export { DataItem };

export interface VisitDataType {
  x: string;
  y: number;
}

export type SearchDataType = {
  index: number;
  keyword: string;
  count: number;
  range: number;
  status: number;
};

export type OfflineDataType = {
  name: string;
  cvr: number;
};

export interface OfflineChartData {
  date: number;
  type: number;
  value: number;
}

export type RadarData = {
  name: string;
  label: string;
  value: number;
};

export interface AllSalesDataType {
  id: number;
  customer: string;
  total: number;
  remarks: string;
}

export interface SalesRankDataType {
  id: number;
  title: string;
  total: number;
}

export interface IntroduceRowDataType {
  totalCustomers: number;
  totalProducts: number;
  totalSalesAmount: number;
}

export interface AnalysisData {
  visitData: DataItem[];
  visitData2: DataItem[];
  salesData: DataItem[];
  allSalesData: AllSalesDataType[];
  salesRankData: SalesRankDataType[];
  searchData: DataItem[];
  offlineData: OfflineDataType[];
  offlineChartData: DataItem[];
  salesTypeData: DataItem[];
  salesTypeDataOnline: DataItem[];
  salesTypeDataOffline: DataItem[];
  radarData: RadarData[];
  introduceRowData: IntroduceRowDataType;
}
// export interface AnalysisData {
//   salesData: DataItem[];
//   allSalesData: AllSalesDataType[];
//   salesRankData: SalesRankDataType[];
//   offlineData: OfflineDataType[];
//   introduceRowData: IntroduceRowDataType;
// }
