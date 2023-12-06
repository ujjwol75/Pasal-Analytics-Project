import { getHeaders, SERVER_URL } from '@/utils/utils';
import ProForm, {
  ProFormDatePicker,
  ProFormDigit,
  ProFormSelect,
  ProFormSwitch,
  ProFormTextArea,
} from '@ant-design/pro-form';
import { PageContainer } from '@ant-design/pro-layout';
import { Button, Card, Divider, Form, Input, message, Popconfirm, Table } from 'antd';
import moment from 'moment';
import React, { useEffect, useState } from 'react';
import { request, useIntl } from 'umi';
import { Col, Row } from 'antd';

const FormItem = Form.Item;

/* const waitTime = (time: number = 1000) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true);
    }, time);
  });
}; */

export default (): React.ReactNode => {
  const intl = useIntl();
  const [form] = Form.useForm();
  const [products, setProducts] = useState();
  const [customers, setCustomers] = useState();
  const [lineItems, setLineItems] = useState([]);
  const [isCredit, setIsCredit] = useState(false);

  // console.log(billDate, " isCredit", isCredit, " customer => ", customer);

  useEffect(() => {
    async function getDropDownData() {
      const headers = await getHeaders();
      const customersJSON = await request(`${SERVER_URL}/customer/list`, { headers });
      console.log('customersJSON', customersJSON);
      setCustomers(customersJSON.data);
      const productsJSON = await request(`${SERVER_URL}/product/list`, { headers });
      setProducts(productsJSON.data);
    }
    getDropDownData().then();
  }, []);

  async function addSale(params: API.SaleParams) {
    form.resetFields();
    console.log('add/edit product data', params);
    const headers = await getHeaders();
    return request(`${SERVER_URL}/sale`, {
      headers,
      method: 'POST',
      data: params,
    });
  }

  function calculateTotal() {
    const pPrice = form.getFieldValue('price');
    console.log('pPrice is', pPrice);
    let pQuantity = form.getFieldValue('quantity');
    console.log('pQuantity is', pQuantity);
    if (pQuantity === undefined) {
      pQuantity = 0;
    }
    const pTotal = pPrice * pQuantity;
    form.setFieldsValue({ total: pTotal });
  }

  async function handleProductChange(id: any, value: any) {
    const headers = await getHeaders();
    const data = await request(`${SERVER_URL}/product/price/${id}`, {
      headers,
    });
    console.log('product Data ', data);
    form.setFieldsValue({ price: data.price });
    form.setFieldsValue({ quantity: 1 });
    form.setFieldsValue({ productName: value.label });
    calculateTotal();
  }

  const resetProductItems = () => {
    form.resetFields(['product', 'price', 'quantity', 'total', 'particulars']);
  };

  async function handleAddItem(event: { preventDefault: () => void }) {
    event.preventDefault();
    const id = (+new Date() + Math.floor(Math.random() * 999999)).toString(36);

    const newItemData = {
      key: id,
      product: form.getFieldValue('product'),
      productName: form.getFieldValue('productName'),
      particulars: form.getFieldValue('particulars'),
      price: form.getFieldValue('price'),
      quantity: form.getFieldValue('quantity'),
      total: form.getFieldValue('total'),
    };

    // console.log("the new Data ", newItemData)

    // @ts-ignore
    setLineItems([...lineItems, newItemData]);
    console.log('println the list --> ', lineItems);
    // console.log("println states --> ", customer, billDate, isCredit)
    resetProductItems();
  }

  /* function onDateChange(date: any, dateString: any) {
    setBillDate(dateString)
  } */

  const remove = (key: string) => {
    console.log('key', key);
    // @ts-ignore
    const newData = lineItems?.filter((item) => item.key !== key) as any;
    setLineItems(newData);
  };

  const columns = [
    {
      title: 'S.No',
      key: 'index',
      render: (text: any, record: any, index: any) => index + 1,
    },
    {
      title: 'Product Id',
      dataIndex: 'product',
      key: 'product',
      hidden: true,
    },
    {
      title: 'Product Name',
      dataIndex: 'productName',
      key: 'productName',
    },
    {
      title: 'Particulars',
      dataIndex: 'particulars',
      key: 'particulars',
    },
    {
      title: 'Rate',
      dataIndex: 'price',
      key: 'price',
    },
    {
      title: 'Quantity',
      dataIndex: 'quantity',
      key: 'quantity',
    },
    {
      title: 'Total Amount',
      dataIndex: 'total',
      key: 'total',
    },
    {
      title: 'Operation',
      key: 'action',
      render: (text: string, record: any) => {
        return (
          <span>
            <Divider type="vertical" />
            <Popconfirm
              title="Do you want to delete this item？"
              onConfirm={() => remove(record.key)}
            >
              <a>Delete</a>
            </Popconfirm>
          </span>
        );
      },
    },
  ];
           <ProFormDigit
              name="total"
              label={intl.formatMessage({ id: 'new-sale-form.total.label' })}
              rules={[
                {
                  type: 'number',
                  min: 0,
                  max: 999999,
                  // message: 'Discount must be between 0 and 999999',
                },
              ]}
              placeholder={intl.formatMessage({ id: 'new-sale-form.total.placeholder' })}
            />

  return (
    <PageContainer>
      <ProForm
        form={form}
        // layout={"horizontal"}
        // initialValues={{isCredit: false, billDate: moment()}}
        initialValues={{
          billDate: moment(),
          price: 0,
          quantity: 1,
          total: 0,
          isCredit: false,
          discount: 0,
        }}
        // @ts-ignore
        onFinish={async (values: API.SaleParams) => {
          // setError([]);
          // await waitTime(2000);
          console.log('on finish values', values);
          // await handleSubmit(values);
          const msg = await addSale({
            ...values,
            // billDate: billDate,
            billDate: form.getFieldValue('billDate').format('YYYY-MM-DD'),
            items: lineItems,
            remarks: form.getFieldValue('remarks'),
            discount: form.getFieldValue('discount'),
            isCredit,
          });
          console.log('the return message', msg.status);
          // message . success ( 'Submission successful' ) ;
          if (msg.status === 'ok') {
            message.error('Failed to add new Sale!');
          } else {
            message.success('New sale created successfully！');
            form.resetFields();
            setLineItems([]);
          }
        }}
        submitter={{
          searchConfig: {
            submitText: '',
          },
          render: (_, dom) => dom.pop(),
          submitButtonProps: {
            size: 'large',
            style: {
              width: '0%',
              display: 'none',
            },
          },
        }}
        // size='medi'
      >
        <Card>
          <ProForm.Group>
            <ProFormSelect
              name="customer"
              label={intl.formatMessage({ id: 'new-sale-form.customer.label' })}
              placeholder="Select a Customer"
              // onFocus={getCustomers}
              // @ts-ignore
              optionFilterProps="children"
              disabled={lineItems.length > 0}
              fieldProps={{
                showSearch: true,
                // onSelect: (value) => setCustomer(value),
                optionFilterProp: 'label',
              }}
              // @ts-ignore
              options={customers}
              width="md"
            />
            <ProForm.Group style={{ margin: '0px 0px 0px 32px' }}>
              <ProFormDatePicker
                name="billDate"
                label={intl.formatMessage({ id: 'new-sale-form.date.label' })}
                width="sm"
                disabled={lineItems.length > 0}
                /* fieldProps={{
                  onChange: onDateChange,
                }} */
              />
            </ProForm.Group>
            <ProFormSwitch
              name="isCredit"
              label={intl.formatMessage({ id: 'new-sale-form.is-credit.label' })}
              disabled={lineItems.length > 0}
              fieldProps={{
                onChange: (value) => setIsCredit(value),
              }}
            />
          </ProForm.Group>
          <ProForm.Group>
            <ProFormSelect
              name="product"
              showSearch
              label={intl.formatMessage({ id: 'new-sale-form.product.label' })}
              placeholder="Select a Product"
              // @ts-ignore
              options={products}
              fieldProps={{
                onSelect: (e, v) => handleProductChange(e, v),
                // filterOption: true,
                optionFilterProp: 'label',
              }}
              width="md"
            />
            <Input style={{ display: 'none' }} name="productName"></Input>

            <ProFormDigit
              name="price"
              label={intl.formatMessage({ id: 'new-sale-form.price.label' })}
              placeholder={intl.formatMessage({ id: 'new-sale-form.price.placeholder' })}
              fieldProps={{
                onStep: calculateTotal,
                onKeyUp: calculateTotal,
              }}
              rules={[
                {
                  type: 'number',
                  min: 0,
                  max: 999999,
                  message: 'Between 1 and 999999',
                },
              ]}
            />
            <ProFormDigit
              name="quantity"
              label={intl.formatMessage({ id: 'new-sale-form.quantity.label' })}
              placeholder={intl.formatMessage({ id: 'new-sale-form.price.placeholder' })}
              rules={[
                {
                  type: 'number',
                  min: 0,
                  max: 999999,
                  message: 'Quantity must be between 0 and 999999',
                },
              ]}
              fieldProps={{
                onStep: calculateTotal,
                onKeyUp: calculateTotal,
              }}
            />
            
            <ProFormDigit
              name="total"
              label={intl.formatMessage({ id: 'new-sale-form.total.label' })}
            />

          </ProForm.Group>
          <ProFormTextArea
            name="particulars"
            label={intl.formatMessage({ id:'new-sale-form.particulars.label' })}
            placeholder={intl.formatMessage({ id:'new-sale-form.particulars.placeholder' })}
            width="md"
          />

          <FormItem shouldUpdate={true}>
            {() => (
              <React.Fragment>
                <Button
                  type="primary"
                  htmlType="submit"
                  disabled={
                    form.getFieldsError().filter(({ errors }) => errors.length).length > 0 ||
                    !form.isFieldTouched('product')
                  }
                  onClick={handleAddItem}
                >
                  {intl.formatMessage({ id: 'new-sale-form.form.submit' })}
                </Button>

                <Button
                  style={{ marginLeft: 8 }}
                  onClick={resetProductItems}
                  disabled={
                    form.getFieldsError().filter(({ errors }) => errors.length).length > 0 ||
                    !form.isFieldTouched('product')
                  }
                >
                  {intl.formatMessage({ id:'new-sale-form.form.reset' })}
                </Button>
              </React.Fragment>
            )}
          </FormItem>
        </Card>
        <Card title="Item List" bordered={false}>
          <FormItem name="members">
            <Table
              size="small"
              key="product"
              dataSource={lineItems}
              columns={columns}
        pagination={false}
        />
        {/* <ProFormDigit
              name="total"
              label={intl.formatMessage({ id: 'new-sale-form.total.label' })}
            /> */}
          
          <Row justify="end">
      <Col span={6} offset={6}>
       <ProFormDigit
              name="total"
              label={intl.formatMessage({ id: 'new-sale-form.total.label' })}
            />
      </Col>
    </Row>
          
    
          </FormItem>
        </Card>
        
        
        <Card bordered={false}>
          <ProForm.Group>
            <ProFormTextArea
              label={intl.formatMessage({ id: 'new-sale-form.remarks.label' })}
              name="remarks"
              placeholder={intl.formatMessage({ id: 'new-sale-form.remarks.placeholder' })}
              width="md"
            />

            <ProFormDigit
              name="discount"
              label={intl.formatMessage({ id: 'new-sale-form.discount.label' })}
              rules={[
                {
                  type: 'number',
                  min:0,
                  max: 999999,
                  message: 'Discount must be between 0 and 999999',
                },
              ]}
              placeholder={intl.formatMessage({ id: 'new-sale-form.discount.placeholder' })}
            />
            {/* <ProFormDigit
              name="total"
              label={intl.formatMessage({ id: 'new-sale-form.total.label' })}
              rules={[
                {
                  type: 'number',
                  min: 0,
                  max: 999999,
                  // message: 'Discount must be between 0 and 999999',
                },
              ]}
              placeholder={intl.formatMessage({ id: 'new-sale-form.total.placeholder' })}
            /> */}
          </ProForm.Group>
        </Card>
        <Card>
          <FormItem shouldUpdate={true}>
            {() => (
              <Button
                type="primary"
                htmlType="submit"
                // loading={submitting}
                disabled={lineItems.length < 1}
              >
                Submit
              </Button>
            )}
          </FormItem>
        </Card>
      </ProForm>
    </PageContainer>
  );
};
