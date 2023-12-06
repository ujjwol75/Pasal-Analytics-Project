import { Card, Col, DatePicker, Row, Tabs } from 'antd';
import type { RangePickerProps } from 'antd/es/date-picker/generatePicker';
// import type moment from 'moment';
import { Column } from '@ant-design/charts';

import { FormattedMessage } from '@@/plugin-locale/localeExports';
import dayjs from 'dayjs';
import numeral from 'numeral';
import type { DataItem, SalesRankDataType } from '../data.d';
import styles from '../style.less';

// type RangePickerValue = RangePickerProps<moment.Moment>['value'];
type RangePickerValue = RangePickerProps<dayjs.Dayjs>['value'];
export type TimeType = 'today' | 'week' | 'month' | 'year';

const { RangePicker } = DatePicker;
const { TabPane } = Tabs;

const SalesCard = ({
  rangePickerValue,
  salesData,
  salesRankData,
  isActive,
  handleRangePickerChange,
  loading,
  selectDate,
}: {
  rangePickerValue: RangePickerValue;
  isActive: (key: TimeType) => string;
  salesData: DataItem[];
  salesRankData: SalesRankDataType[];
  loading: boolean;
  handleRangePickerChange: (dates: RangePickerValue, dateStrings: [string, string]) => void;
  selectDate: (key: TimeType) => void;
}) => (
  <Card loading={loading} bordered={false} bodyStyle={{ padding: 0 }}>
    <div className={styles.salesCard}>
      <Tabs
        tabBarExtraContent={
          <div className={styles.salesExtraWrap}>
            <div className={styles.salesExtra}>
              <a className={isActive('today')} onClick={() => selectDate('today')}>
                <FormattedMessage id="dashboard.analysis.all-day" defaultMessage="All Day" />
              </a>
              <a className={isActive('week')} onClick={() => selectDate('week')}>
                <FormattedMessage id="dashboard.analysis.all-week" defaultMessage="All Week" />
              </a>
              <a className={isActive('month')} onClick={() => selectDate('month')}>
                <FormattedMessage id="dashboard.analysis.all-month" defaultMessage="All Month" />
              </a>
              <a className={isActive('year')} onClick={() => selectDate('year')}>
                <FormattedMessage id="dashboard.analysis.all-year" defaultMessage="All Year" />
              </a>
            </div>
            <RangePicker
              value={rangePickerValue}
              onChange={handleRangePickerChange}
              style={{ width: 256 }}
            />
          </div>
        }
        size="large"
        tabBarStyle={{ marginBottom: 24 }}
      >
        <TabPane
          tab={<FormattedMessage id="dashboard.analysis.sales" defaultMessage="Sales" />}
          key="sales"
        >
          <Row>
            <Col xl={16} lg={12} md={12} sm={24} xs={24}>
              <div className={styles.salesBar}>
                <Column
                  height={300}
                  forceFit
                  data={salesData as any}
                  xField="x"
                  yField="y"
                  xAxis={{
                    visible: true,
                    title: {
                      // @ts-ignore
                      visible: false,
                    },
                  }}
                  yAxis={{
                    visible: true,
                    title: {
                      // @ts-ignore
                      visible: false,
                    },
                  }}
                  title={{
                    visible: true,
                    text: 'Sales trend',
                    style: {
                      fontSize: 14,
                    },
                  }}
                  meta={{
                    y: {
                      alias: 'Sales volume',
                    },
                  }}
                />
              </div>
            </Col>
            <Col xl={8} lg={12} md={12} sm={24} xs={24}>
              <div className={styles.salesRank}>
                <h4 className={styles.rankingTitle}>Store Sales Ranking</h4>
                <ul className={styles.rankingList}>
                  {salesRankData.map((item, i) => (
                    <li key={item.id}>
                      <span className={`${styles.rankingItemNumber} ${i < 3 ? styles.active : ''}`}>
                        {i + 1}
                      </span>
                      <span className={styles.rankingItemTitle} title={item.title}>
                        {item.title}
                      </span>
                      <span className={styles.rankingItemValue}>
                        {numeral(item.total).format('0,0')}
                      </span>
                    </li>
                  ))}
                </ul>
              </div>
            </Col>
          </Row>
        </TabPane>
        <TabPane tab="Expenses" key="expenses">
          <Row>
            <Col xl={16} lg={12} md={12} sm={24} xs={24}>
              <div className={styles.salesBar}>
                <Column
                  height={300}
                  forceFit
                  data={salesData as any}
                  xField="x"
                  yField="y"
                  xAxis={{
                    visible: true,
                    title: {
                      // @ts-ignore
                      visible: false,
                    },
                  }}
                  yAxis={{
                    visible: true,
                    title: {
                      // @ts-ignore
                      visible: false,
                    },
                  }}
                  title={{
                    visible: true,
                    text: 'Expense trend',
                    style: {
                      fontSize: 14,
                    },
                  }}
                  meta={{
                    y: {
                      alias: 'Expenditures',
                    },
                  }}
                />
              </div>
            </Col>
            <Col xl={8} lg={12} md={12} sm={24} xs={24}>
              <div className={styles.salesRank}>
                <h4 className={styles.rankingTitle}>Store Expense Ranking</h4>
                <ul className={styles.rankingList}>
                  {salesRankData.map((item, i) => (
                    <li key={item.title}>
                      <span className={`${styles.rankingItemNumber} ${i < 3 ? styles.active : ''}`}>
                        {i + 1}
                      </span>
                      <span className={styles.rankingItemTitle} title={item.title}>
                        {item.title}
                      </span>
                      <span>{numeral(item.total).format('0,0')}</span>
                    </li>
                  ))}
                </ul>
              </div>
            </Col>
          </Row>
        </TabPane>
      </Tabs>
    </div>
  </Card>
);

export default SalesCard;
