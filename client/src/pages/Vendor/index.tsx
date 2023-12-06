import { getDishHeaders, SERVER_URL } from '@/utils/utils';
import { useMatch } from '@@/exports';
import { request } from '@@/plugin-request/request';
import ProForm, { ProFormField, ProFormText, ProFormTextArea } from '@ant-design/pro-form';
import { PageContainer } from '@ant-design/pro-layout';
import { Card, Form, message } from 'antd';
import type { FC } from 'react';
import { useEffect } from 'react';
import { FormattedMessage, history, useIntl, useRequest } from 'umi';
import { submitNewVendor } from './service';

const NewVendorForm: FC<Record<string, any>> = () => {
  const intl = useIntl();
  const [form] = Form.useForm();
  const match = useMatch('/vendor/edit/:id');
  const vendorId = match?.params?.id;

  useEffect(() => {
    async function getDropDownData() {
      const headers = getDishHeaders();
      if (vendorId) {
        const response = await request(`${SERVER_URL}/vendor/${vendorId}`, {
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

  const { run } = useRequest(submitNewVendor, {
    manual: true,
    onSuccess: () => {
      message.success('Information submitted successfully');
      form.resetFields();
      history.push('/vendor');
    },
  });

  const onFinish = async (values: Record<string, any>) => {
    if (vendorId) {
      // eslint-disable-next-line no-param-reassign
      values.key = vendorId;
    }
    await run(values);
  };

  // @ts-ignore
  return (
    <PageContainer content="The New Vendor form page is used to collect or verify information about the vendor.">
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
              label=" Name of vendor "
              tooltip="The maximum length is 24 digits "
              placeholder=" Please enter a vendor name "
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.vendor.name.required"
                      defaultMessage="Vendor name is required!"
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
              placeholder=" Please enter vendor's email "
              rules={[
                {
                  required: true,
                  type: 'email',
                  message: (
                    <FormattedMessage
                      id="pages.vendor.email.required"
                      defaultMessage="Vendor email is required in an appropriate format!"
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
              label=" Phone no. of vendor "
              tooltip="The maximum length is 10 digits "
              placeholder=" Please enter a vendor phone number "
              rules={[
                {
                  required: true,
                  message: intl.formatMessage({ id: 'pages.edit-vendor-form.phone.required' }),
                },
                {
                  pattern: /^(\d{10})$/,
                  message: 'Please enter a valid phone number',
                },
              ]}
            />
            <ProFormText
              width="md"
              name="address"
              label=" Address"
              tooltip="The maximum length is 24 digits "
              placeholder=" Please enter a vendor address "
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.vendor.address.required"
                      defaultMessage="Vendor address is required!"
                    />
                  ),
                },
              ]}
            />
          </ProForm.Group>
          <ProFormTextArea
            width="sm"
            name="description"
            label=" Description of vendor "
            tooltip="The maximum length is 200 digits "
            placeholder=" Please enter a vendor description "
            rules={[
              {
                required: false,
                message: (
                  <FormattedMessage
                    id="pages.vendor.description.required"
                    defaultMessage="Vendor description is required!"
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

export default NewVendorForm;
