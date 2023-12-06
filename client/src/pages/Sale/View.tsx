import { getHeaders, SERVER_URL } from '@/utils/utils';
import { useMatch } from '@@/exports';
import { request } from 'umi';
import ProCard from '@ant-design/pro-card';
import { PageContainer, RouteContext } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { Alert, Button, Col, Descriptions, Form, Input, message, Row, Tag } from 'antd';
import React from 'react';
import { useIntl } from 'umi';

export default (): React.ReactNode => {
  const intl = useIntl();
  const match = useMatch('/sale/view/:id');
  const saleId = match?.params?.id;

  //@ts-ignore
  const [sale, setSale] = React.useState<API.SaleItem>({});
  const [discountAmount, setDiscount] = React.useState();
  const [partialAmount, setPartial] = React.useState();
  const [discountForm] = Form.useForm();
  const [partialForm] = Form.useForm();
  const [payments, setPayments] = React.useState([]);

  async function getSale() {
    const headers = await getHeaders();
    const result = await request(`${SERVER_URL}/sale/${saleId}`, { headers });
    setSale(result);
    setPayments(result.payments);
  }

  React.useEffect(() => {
    getSale().then();
  }, []);

  console.log('sale ', sale);
  console.log('payments ', payments);

  function showStatus(isCredit: boolean) {
    if (!isCredit) {
      return <Tag color={'green'}>{'Paid'}</Tag>;
    }
    return <Tag color={'red'}>{'Due'}</Tag>;
  }

  const checkPartial = (rule: any, value: any) => {
    // console.log("validating ... ", value, viewSale.id)
    // @ts-ignore
    if (value > sale.dues) {
      // eslint-disable-next-line prefer-promise-reject-errors
      return Promise.reject('Must be less than dues!');
    }
    if (value > 0) {
      return Promise.resolve();
    }
    // eslint-disable-next-line prefer-promise-reject-errors
    return Promise.reject('Must be greater than zero!');
  };

  // console.log("sale data", sale)
  async function payPartial(values: any) {
    console.log('Received values from form: ', values, saleId);
    const postData = {
      id: saleId,
      discountAmount: '',
      partialAmount: '',
    };
    if (values.discount) {
      // this.setState({ discountAmount: values.discount });
      setDiscount(values.discount);
      postData['discountAmount'] = values.discount;
    } else {
      // this.setState({ partialAmount: values.partial });
      setPartial(values.partial);
      postData['partialAmount'] = values.partial;
    }
    console.log('the postData ', postData);
    const headers = await getHeaders();
    request(`${SERVER_URL}/sale/${saleId}`, {
      method: 'PUT',
      data: JSON.stringify(postData),
    })
      .then((response) => {
        console.log('pay partial completed');
        console.log(response);
        getSale();
        // TODO: add conditionals here too.
        if (values.discount) {
          message.success('discount initiated.', 5);
          discountForm.resetFields();
          // @ts-ignore
          setDiscount(0);
        } else {
          message.success('Partial payment completed.', 5);
          // @ts-ignore
          partialForm.resetFields();
          // @ts-ignore
          setPartial(0);
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  const columns = [
    {
      title: 'S.No',
      key: 'index',
      render: (text: any, record: any, index: any) => index + 1,
    },
    {
      title: 'Id',
      dataIndex: 'key',
      key: 'key',
    },
    {
      title: 'Product Name',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: 'Particulars',
      dataIndex: 'particulars',
      key: 'particulars',
    },
    {
      title: 'Price',
      dataIndex: 'price',
      key: 'price',
    },
    {
      title: 'Quantity',
      dataIndex: 'quantity',
      key: 'quantity',
    },
    {
      title: 'Total',
      dataIndex: 'total',
      key: 'total',
    },
  ];
  const partialColumns = [
    {
      title: 'S.No',
      key: 'index',
      render: (text: any, record: any, index: any) => index + 1,
    },
    {
      title: 'Id',
      dataIndex: 'key',
      key: 'key',
    },
    {
      title: 'Bill Date',
      dataIndex: 'billDate',
      key: 'billDate',
    },
    {
      title: 'Amount',
      dataIndex: 'amount',
      key: 'amount',
    },
  ];

  return (
    <PageContainer>
      <ProCard>
        <Alert
          message={intl.formatMessage(
            {
              id: 'pages.sale.alertMessage',
              defaultMessage: 'View the details of the sale.',
            },
            { no: saleId },
          )}
          type="success"
          showIcon
          banner
          style={{
            margin: -12,
            marginBottom: 24,
          }}
        />
        {/* <GridContent> */}
        <RouteContext.Consumer>
          {/* eslint-disable-next-line @typescript-eslint/no-unused-vars */}
          {({ isMobile }) => (
            <Descriptions
            /* size="small"
              bordered={false} */
            // column = {4}
            >
              <Descriptions.Item label="Customer">{sale.customer}</Descriptions.Item>
              <Descriptions.Item label="Bill Date">{sale.billDate}</Descriptions.Item>
              <Descriptions.Item label="Status">{showStatus(sale.isCredit)}</Descriptions.Item>
              <Descriptions.Item label="Amount">{sale.total}</Descriptions.Item>
              <Descriptions.Item label="Discount">{sale.discount}</Descriptions.Item>
              <Descriptions.Item label="Gross">{sale.grossAmount}</Descriptions.Item>
              <Descriptions.Item label="Partial">{sale.partialTotal}</Descriptions.Item>
              <Descriptions.Item label="Dues">{sale.dues}</Descriptions.Item>
              <Descriptions.Item label="Remarks">{sale.remarks}</Descriptions.Item>
            </Descriptions>
          )}
        </RouteContext.Consumer>
      </ProCard>
      <ProCard
        size={'small'}
        title="Line Items"
        style={{ marginBottom: 24, marginTop: 10 }}
        bordered={true}
      >
        <ProTable
          pagination={false}
          toolBarRender={false}
          search={false}
          // loading={loading}
          dataSource={sale.lineItems}
          columns={columns}
          size="small"
        />
      </ProCard>
      {/* {((Object.keys(sale).payments.length >= 1) && (sale.partialTotal > 1)) ? (
        <ProCard size={"small"} title="Partial payments" style={{marginBottom: 24}}>
          <ProTable
            pagination={false}
            toolBarRender={false}
            search={false}
            dataSource={sale.payments}
            columns={partialColumns}
            size="small"
          />
        </ProCard>
      ):"" } */}
      {payments.length >= 1 && sale.partialTotal > 1 ? (
        <ProCard size={'small'} title="Partial payments" style={{ marginBottom: 24 }}>
          <ProTable
            pagination={false}
            toolBarRender={false}
            search={false}
            dataSource={sale.payments}
            columns={partialColumns}
            size="small"
          />
        </ProCard>
      ) : (
        ''
      )}

      {sale.isCredit === true ? (
        <ProCard size={'small'} title="Update Payments" bordered={true}>
          <Row>
            <Col offset={18}>
              <Form
                name="partial_payment_form"
                form={partialForm}
                layout="inline"
                onFinish={payPartial}
                initialValues={{
                  partial: {
                    number: 0,
                  },
                }}
                size={'small'}
              >
                <Form.Item
                  name="partial"
                  label="Partial Payment"
                  rules={[{ validator: checkPartial }]}
                >
                  <Input
                    type="number"
                    style={{ width: 100 }}
                    // @ts-ignore
                    value={partialAmount}
                  />
                </Form.Item>
                <Form.Item>
                  <Button
                    type="primary"
                    htmlType="submit"
                    disabled={
                      // !(this.state.partialAmount === 0)
                      // @ts-ignore
                      !partialAmount === 0
                    }
                  >
                    Pay Partial
                  </Button>
                </Form.Item>
              </Form>
            </Col>

            <Col offset={18}>
              <Form
                name="discount_form"
                form={discountForm}
                layout="inline"
                onFinish={payPartial}
                initialValues={{
                  price: {
                    number: 0,
                    currency: 'rmb',
                  },
                }}
                size={'small'}
                style={{ margin: '20px 0 0 45px' }}
              >
                <Form.Item name="discount" label="Discount" rules={[{ validator: checkPartial }]}>
                  <Input
                    type="number"
                    style={{ width: 100 }}
                    // value={this.state.discountAmount}
                    value={discountAmount}
                  />
                </Form.Item>
                <Form.Item>
                  <Button
                    type="primary"
                    htmlType="submit"
                    disabled={
                      // !(this.state.discountAmount === 0)
                      !(discountAmount === 0)
                    }
                  >
                    Discount
                  </Button>
                </Form.Item>
              </Form>
            </Col>
          </Row>
        </ProCard>
      ) : (
        ''
      )}
    </PageContainer>
  );
};
