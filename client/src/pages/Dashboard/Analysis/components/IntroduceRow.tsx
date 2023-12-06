import { InfoCircleOutlined } from '@ant-design/icons';
// import { TinyArea, TinyColumn, Progress } from '@ant-design/charts';
import { Col, Row, Tooltip } from 'antd';

import numeral from 'numeral';
import type { IntroduceRowDataType } from '../data.d';
import { ChartCard, Field } from './Charts';
// import Trend from './Trend';
import Yuan from '../utils/Yuan';
// import styles from '../style.less';
import { FormattedMessage } from '@@/plugin-locale/localeExports';

const topColResponsiveProps = {
  xs: 24,
  sm: 12,
  md: 12,
  lg: 12,
  xl: 6,
  style: { marginBottom: 24 },
};

const IntroduceRow = ({
  loading,
  introduceRowData,
}: {
  loading: boolean;
  introduceRowData: IntroduceRowDataType;
}) => (
  <Row gutter={24}>
    <Col {...topColResponsiveProps}>
      <ChartCard
        bordered={false}
        title={
          <FormattedMessage id="dashboard.analysis.total-sales" defaultMessage="Total Sales" />
        }
        action={
          <Tooltip
            title={
              <FormattedMessage id="dashboard.analysis.introduce" defaultMessage="Total Sales" />
            }
          >
            <InfoCircleOutlined />
          </Tooltip>
        }
        loading={loading}
        total={() => <Yuan>{introduceRowData.totalSalesAmount}</Yuan>}
        footer={
          <Field
            label={
              <FormattedMessage id="dashboard.analysis.day-sales" defaultMessage="Daily Sales" />
            }
            value={`Rs ${numeral(12423).format('0,0')}`}
          />
        }
        contentHeight={46}
      ></ChartCard>
    </Col>

    <Col {...topColResponsiveProps}>
      <ChartCard
        bordered={false}
        loading={loading}
        title={
          <FormattedMessage id="dashboard.analysis.customers" defaultMessage="Total Customers" />
        }
        action={
          <Tooltip
            title={
              <FormattedMessage
                id="dashboard.analysis.customers-tooltip"
                defaultMessage="Introduce"
              />
            }
          >
            <InfoCircleOutlined />
          </Tooltip>
        }
        total={numeral(introduceRowData.totalCustomers).format('0,0')}
        footer={
          <Field
            label={
              <FormattedMessage
                id="dashboard.analysis.new-customers"
                defaultMessage="Daily Visits"
              />
            }
            value={numeral(1234).format('0,0')}
          />
        }
        contentHeight={46}
      ></ChartCard>
    </Col>
    <Col {...topColResponsiveProps}>
      <ChartCard
        bordered={false}
        loading={loading}
        title={<FormattedMessage id="dashboard.analysis.products" defaultMessage="Products" />}
        action={
          <Tooltip
            title={
              <FormattedMessage
                id="dashboard.analysis.products-tooltip"
                defaultMessage="Total number of products"
              />
            }
          >
            <InfoCircleOutlined />
          </Tooltip>
        }
        total={numeral(introduceRowData.totalProducts).format('0,0')}
        footer={
          <Field
            label={
              <FormattedMessage
                id="dashboard.analysis.conversion-rate"
                defaultMessage="Conversion Rate"
              />
            }
            value="60%"
          />
        }
        contentHeight={46}
      >
        {/* <TinyColumn xField="x" height={46} forceFit yField="y" data={visitData} /> */}
      </ChartCard>
    </Col>
    <Col {...topColResponsiveProps}>
      <ChartCard
        loading={loading}
        bordered={false}
        title={
          <FormattedMessage
            id="dashboard.analysis.expenditures"
            defaultMessage="Operational Expenditures"
          />
        }
        action={
          <Tooltip
            title={
              <FormattedMessage
                id="dashboard.analysis.expenditures-tooltip"
                defaultMessage="Introduce"
              />
            }
          >
            <InfoCircleOutlined />
          </Tooltip>
        }
        total="78%"
        footer={
          <div style={{ whiteSpace: 'nowrap', overflow: 'hidden' }}>
            {/*<Trend flag="up" style={{ marginRight: 16 }}>
              Weekly
              <span className={styles.trendText}>12%</span>
            </Trend>
            <Trend flag="down">
              Yearly
              <span className={styles.trendText}>11%</span>
            </Trend>*/}
          </div>
        }
        contentHeight={46}
      ></ChartCard>
    </Col>
  </Row>
);

export default IntroduceRow;
