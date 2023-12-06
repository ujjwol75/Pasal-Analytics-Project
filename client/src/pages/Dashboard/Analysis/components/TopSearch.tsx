// import { InfoCircleOutlined } from '@ant-design/icons';
import { Card, Table, Tag } from 'antd';
// import { TinyArea } from '@ant-design/charts';
import React from 'react';
// import numeral from 'numeral';
import type { AllSalesDataType } from '../data.d';

// import NumberInfo from './NumberInfo';
// import Trend from './Trend';
import { FormattedMessage } from '@@/plugin-locale/localeExports';
import { Link } from '@umijs/preset-dumi/lib/theme';
import styles from '../style.less';

const columns = [
  {
    title: <FormattedMessage id="dashboard.analysis.table.id" defaultMessage="Id" />,
    dataIndex: 'id',
    key: true,
    render: (text: React.ReactNode) => {
      const href = `/sale/${text}`;
      return <Link to={href}>{text}</Link>;
    },
  },
  {
    title: <FormattedMessage id="dashboard.analysis.table.remarks" defaultMessage="Remarks" />,
    dataIndex: 'remarks',
    key: 'remarks',
    // sorter: (a: { count: number }, b: { count: number }) => a.count - b.count,
    className: styles.alignRight,
  },
  {
    title: (
      <FormattedMessage id="dashboard.analysis.table.total-amount" defaultMessage="Total Amount" />
    ),
    dataIndex: 'total',
    key: 'total',
  },
  {
    title: <FormattedMessage id="dashboard.analysis.table.sale.type" defaultMessage="Sale Type" />,
    dataIndex: 'isCredit',
    key: 'isCredit',
    render: (text: any, value: { isCredit: any }) =>
      value.isCredit ? <Tag color={'red'}>{'Due'}</Tag> : <Tag color={'green'}>{'Paid'}</Tag>,
  },
];

const TopSearch = ({
  loading,
  // visitData2,
  searchData,
  dropdownGroup,
}: {
  loading: boolean;
  // visitData2: DataItem[];
  dropdownGroup: React.ReactNode;
  searchData: AllSalesDataType[];
}) => (
  <Card
    loading={loading}
    bordered={false}
    title="All Sales List"
    extra={dropdownGroup}
    style={{
      height: '100%',
    }}
  >
    {/* <Row gutter={68}>
      <Col sm={12} xs={24} style={{ marginBottom: 24 }}>
        <NumberInfo
          subTitle={
            <span>
              搜索用户数
              <Tooltip title="指标说明">
                <InfoCircleOutlined style={{ marginLeft: 8 }} />
              </Tooltip>
            </span>
          }
          gap={8}
          total={numeral(12321).format('0,0')}
          status="up"
          subTotal={17.1}
        />
        <TinyArea xField="x" height={45} forceFit yField="y" smooth data={visitData2} />
      </Col>
      <Col sm={12} xs={24} style={{ marginBottom: 24 }}>
        <NumberInfo
          subTitle={
            <span>
              人均搜索次数
              <Tooltip title="指标说明">
                <InfoCircleOutlined style={{ marginLeft: 8 }} />
              </Tooltip>
            </span>
          }
          total={2.7}
          status="down"
          subTotal={26.2}
          gap={8}
        />
        <TinyArea xField="x" height={45} forceFit yField="y" smooth data={visitData2} />
      </Col>
    </Row> */}
    <Table<any>
      rowKey={(record) => record.id}
      size="small"
      // @ts-ignore
      columns={columns}
      dataSource={searchData}
      pagination={{
        style: { marginBottom: 0 },
        pageSize: 5,
      }}
    />
  </Card>
);

export default TopSearch;
