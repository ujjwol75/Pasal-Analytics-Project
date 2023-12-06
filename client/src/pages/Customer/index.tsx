import { getDishHeaders, SERVER_URL } from '@/utils/utils';
import { useMatch } from '@@/exports';
import { request } from '@@/plugin-request/request';
import ProForm, { ProFormField, ProFormText, ProFormTextArea } from '@ant-design/pro-form';
import { PageContainer } from '@ant-design/pro-layout';
import { Card, Form, message } from 'antd';
import type { FC } from 'react';
import { useEffect } from 'react';
import { FormattedMessage, history, useIntl, useRequest } from 'umi';
import { submitNewCustomer } from './service';

const NewCustomerForm: FC<Record<string, any>> = () => {
  const intl = useIntl();
  const [form] = Form.useForm();
  const match = useMatch('/customer/edit/:id');
  const customerId = match?.params.id;

  useEffect(() => {
    async function getDropDownData() {
      const headers = getDishHeaders();
      if (customerId) {
        const response = await request(`${SERVER_URL}/customer/${customerId}`, {
          method: 'GET',
          headers,
        });
        const result = response.data;
        form.setFieldsValue({
          name: result.name,
          email: result.email,
          phone: result.phone,
          description: result.description,
          address: result.address,
        });
      }
    }
    getDropDownData().then();
  }, []);

  const { run } = useRequest(submitNewCustomer, {
    manual: true,
    onSuccess: () => {
      message.success('Information submitted successfully');
      form.resetFields();
      history.push('/customer');
    },
  });

  const onFinish = async (values: Record<string, any>) => {
    if (customerId) {
      // eslint-disable-next-line no-param-reassign
      values.key = customerId;
    }
    await run(values);
  };

  // @ts-ignore
  return (
    <PageContainer content="The New Customer form page is used to collect or verify information about the customer.">
      <Card bordered={false}>
        <ProForm
          hideRequiredMark
          style={{ margin: 'auto', marginTop: 8, maxWidth: 600 }}
          name="customer"
          layout="vertical"
          initialValues={{ price: 0 }}
          onFinish={onFinish}
          form={form}
          size="middle"
        >
          <ProForm.Group>
            <ProFormText
              width="sm"
              name="name"
              label=" Name of customer "
              tooltip="The maximum length is 24 digits "
              placeholder=" Please enter a customer name "
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.customer.name.required"
                      defaultMessage="Customer name is required!"
                    />
                  ),
                },
              ]}
            />
            <ProFormField
              width="md"
              name="email"
              label=" Email"
              tooltip="The maximum length is 64 digits "
              placeholder=" Please enter customer's email "
              rules={[
                {
                  required: true,
                  type: 'email',
                  message: (
                    <FormattedMessage
                      id="pages.customer.email.required"
                      defaultMessage="Customer email is required in an appropriate format!"
                    />
                  ),
                },
              ]}
            />
          </ProForm.Group>
          <ProForm.Group>
            <ProFormField
              width="sm"
              name="phone"
              label=" Phone No. "
              tooltip="The maximum length is 10 digits "
              placeholder=" Please enter a customer phone number "
              rules={[
                {
                  required: true,
                  message: intl.formatMessage({ id: 'pages.new-customer-form.phone.required' }),
                },
                {
                  pattern: /^(\d{10})$/,
                  message: 'Please enter a valid phone number',
                },
              ]}
            />
            <ProFormField
              width="sm"
              name="address"
              label=" Address "
              tooltip="The maximum length is 24 digits "
              placeholder=" Please enter a customer address "
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.customer.address.required"
                      defaultMessage="Customer address is required!"
                    />
                  ),
                },
              ]}
            />
          </ProForm.Group>
          <ProFormTextArea
            width="sm"
            name="description"
            label=" Description "
            tooltip="The maximum length is 200 digits "
            placeholder=" Please enter a customer description "
            rules={[
              {
                required: false,
                message: (
                  <FormattedMessage
                    id="pages.customer.description.required"
                    defaultMessage="Customer description is required!"
                  />
                ),
              },
            ]}
          />
        </ProForm>
      </Card>
    </PageContainer>
  );
};

export default NewCustomerForm;
