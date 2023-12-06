import { EllipsisOutlined } from '@ant-design/icons';
import { GridContent } from '@ant-design/pro-layout';
import { Col, Dropdown, Row } from 'antd';
import type { RangePickerProps } from 'antd/es/date-picker/generatePicker';
import type { RadioChangeEvent } from 'antd/es/radio';
import type moment from 'moment';
import type { FC } from 'react';
import { Suspense, useEffect, useState } from 'react';
import IntroduceRow from './components/IntroduceRow';
import ProportionSales from './components/ProportionSales';
import SalesCard from './components/SalesCard';
import TopSearch from './components/TopSearch';
// import OfflineData from './components/OfflineData';
import { useRequest } from 'umi';

import PageLoading from './components/PageLoading';
import type { TimeType } from './components/SalesCard';
import type { AnalysisData } from './data.d';
import { fetchAnalysisData } from './service';
import styles from './style.less';
import { getTimeDistance } from './utils/utils';

type RangePickerValue = RangePickerProps<moment.Moment>['value'];

type AnalysisProps = {
  dashboardAnalysis: AnalysisData;
  loading: boolean;
};

type SalesType = 'all' | 'online' | 'stores';

const Analysis: FC<AnalysisProps> = () => {
  const [salesType, setSalesType] = useState<SalesType>('all');
  // const [currentTabKey, setCurrentTabKey] = useState<string>('');
  const [rangePickerValue, setRangePickerValue] = useState<RangePickerValue>(
    getTimeDistance('year'),
  );
  // @ts-ignore
  const [data, setData] = useState<AnalysisData>({});

  const { loading } = useRequest(fetchAnalysisData);
  useEffect(() => {
    async function getDropDownData() {
      const analysisJSON = await fetchAnalysisData();
      console.log('analysisJSON', analysisJSON);
      // @ts-ignore
      setData(analysisJSON);
    }
    getDropDownData().then();
  }, []);

  const selectDate = (type: TimeType) => {
    setRangePickerValue(getTimeDistance(type));
  };

  const handleRangePickerChange = (value: RangePickerValue) => {
    setRangePickerValue(value);
  };

  const isActive = (type: TimeType) => {
    if (!rangePickerValue) {
      return '';
    }
    const value = getTimeDistance(type);
    if (!value) {
      return '';
    }
    if (!rangePickerValue[0] || !rangePickerValue[1]) {
      return '';
    }
    if (
      rangePickerValue[0].isSame(value[0] as moment.Moment, 'day') &&
      rangePickerValue[1].isSame(value[1] as moment.Moment, 'day')
    ) {
      return styles.currentDate;
    }
    return '';
  };

  // let salesPieData;
  // if (salesType === 'all') {
  //   salesPieData = data?.salesTypeData;
  // } else {
  //   salesPieData = salesType === 'online' ? data?.salesTypeDataOnline : data?.salesTypeDataOffline;
  // }

  /*const menu = (
    <Menu>
      <Menu.Item>Item 1</Menu.Item>
      <Menu.Item>Item 2</Menu.Item>
    </Menu>
  );*/

  const items = [
    { label: 'Item 1', key: 'item-1' }, // Menu items must be filled in key
    { label: 'Item 2', key: 'item-2' },
  ];

  const dropdownGroup = (
    <span className={styles.iconGroup}>
      <Dropdown menu={{ items }} placement="bottomRight">
        <EllipsisOutlined />
      </Dropdown>
    </span>
  );

  const handleChangeSalesType = (e: RadioChangeEvent) => {
    setSalesType(e.target.value);
  };
  //
  // const handleTabChange = (key: string) => {
  //   setCurrentTabKey(key);
  // };

  // const activeKey = currentTabKey || (data?.offlineData[0] && data?.offlineData[0].name) || '';

  return (
    <GridContent>
      <>
        <Suspense fallback={<PageLoading />}>
          <IntroduceRow loading={loading} introduceRowData={data?.introduceRowData || []} />
        </Suspense>

        <Suspense fallback={null}>
          <SalesCard
            // @ts-ignore
            rangePickerValue={rangePickerValue}
            salesData={data?.salesData || []}
            salesRankData={data?.salesRankData || []}
            isActive={isActive}
            // @ts-ignore
            handleRangePickerChange={handleRangePickerChange}
            loading={loading}
            selectDate={selectDate}
          />
        </Suspense>

        <Row
          gutter={24}
          style={{
            marginTop: 24,
          }}
        >
          <Col xl={12} lg={24} md={24} sm={24} xs={24}>
            <Suspense fallback={null}>
              <TopSearch
                loading={loading}
                // @ts-ignore
                visitData2={data?.visitData2 || []}
                searchData={data?.allSalesData || []}
                dropdownGroup={dropdownGroup}
              />
            </Suspense>
          </Col>
          <Col xl={12} lg={24} md={24} sm={24} xs={24}>
            <Suspense fallback={null}>
              <ProportionSales
                dropdownGroup={dropdownGroup}
                salesType={salesType}
                loading={loading}
                salesPieData={[]}
                handleChangeSalesType={handleChangeSalesType}
              />
            </Suspense>
          </Col>
        </Row>

        {/* <Suspense fallback={null}>
          <OfflineData
            activeKey={activeKey}
            loading={loading}
            offlineData={data?.offlineData || []}
            offlineChartData={data?.offlineChartData || []}
            handleTabChange={handleTabChange}
          />
        </Suspense> */}
      </>
    </GridContent>
  );
};

export default Analysis;
