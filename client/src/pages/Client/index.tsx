import { getDishHeaders, SERVER_URL } from '@/utils/utils';
import { useMatch } from '@@/exports';
import { request } from '@@/plugin-request/request';
import ProForm, { ProFormField, ProFormText, ProFormTextArea } from '@ant-design/pro-form';
import { PageContainer } from '@ant-design/pro-layout';
import { Card, Form, message } from 'antd';
import type { FC } from 'react';
import { useEffect } from 'react';
import { FormattedMessage, history, useIntl, useRequest } from 'umi';
import { submitNewClient} from './service';

const NewClientForm: FC<Record<string, any>> = () => {
  const intl = useIntl();
  const [form] = Form.useForm();
  const match = useMatch('/client/edit/:id');
  const clientId = match?.params.id;

  useEffect(() => {
    async function getDropDownData() {
      const headers = getDishHeaders();
      if (clientId) {
        const response = await request(`${SERVER_URL}/client/${clientId}`, {
          method: 'GET',
          headers,
        });
        const result = response.data;
        form.setFieldsValue({
          code:result.code,
          url :result.url,
          name: result.name,
          email: result.email,
          displayName:result.displayName,
          phone: result.phone,
          vat: result.vat,
          pan:result.pan,
          description: result.description,
          address: result.address,
        });
      }
    }
    getDropDownData().then();
  }, []);

  const { run } = useRequest(submitNewClient, {
    manual: true,
    onSuccess: () => {
      message.success('Information submitted successfully');
      form.resetFields();
      history.push('/client');
    },
  });

  const onFinish = async (values: Record<string, any>) => {
    if (clientId) {
      // eslint-disable-next-line no-param-reassign
      values.key = clientId;
    }
    await run(values);
  };

  // @ts-ignore
  return (
    <PageContainer content="The New Client form page is used to collect or verify information about the client.">
      <Card bordered={false}>
        <ProForm
          hideRequiredMark
          style={{ margin: 'auto', marginTop: 8, maxWidth: 600 }}
          name="client"
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
              label=" Name of client "
              tooltip="The maximum length is 24 digits "
              placeholder=" Please enter a client name "
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.client.name.required"
                      defaultMessage="Client name is required!"
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
              placeholder=" Please enter client's email "
              rules={[
                {
                  required: true,
                  type: 'email',
                  message: (
                    <FormattedMessage
                      id="pages.client.email.required"
                      defaultMessage="Client email is required in an appropriate format!"
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
              placeholder=" Please enter a client phone number "
              rules={[
                {
                  required: true,
                  message: intl.formatMessage({ id: 'pages.new-client-form.phone.required' }),
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
              placeholder=" Please enter a client address "
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.client.address.required"
                      defaultMessage="Client address is required!"
                    />
                  ),
                },
              ]}
            />

          </ProForm.Group>
          <ProForm.Group>
            <ProFormField
              width="sm"
              name="code"
              label="Code"
              tooltip="The maximum length is 10 digits"
              placeholder="Please enter a client code"
              rules={[
                { required: true, message: 'Client code is required' },
                { pattern: /^\d{1,10}$/, message: 'Please enter a valid code with maximum 10 digits' },
              ]}
            />
            <ProFormField
              width="sm"
              name="url"
              label="URL"
              tooltip="Please enter a valid URL"
              placeholder="Please enter a client URL"
              rules={[
                { required: true, message: 'Client URL is required' },
                { type: 'url', message: 'Please enter a valid URL' },
              ]}
            />
          </ProForm.Group>
          <ProForm.Group>
            <ProFormField
              width="sm"
              name="pan"
              label="PAN"
              tooltip="Please enter a valid PAN"
              placeholder="Please enter a PAN"
              rules={[
                { required: true, message: 'PAN is required' },
                { pattern: /^\d{9}$/, message: 'Please enter a valid PAN with 9 digit unique number' },
              ]}
            />
            <ProFormField
              width="sm"
              name="vat"
              label="VAT"
              tooltip="Please enter a valid VAT"
              placeholder="Please enter a VAT"
              rules={[
                { required: true, message: 'VAT is required' },
                { pattern: /^[A-Za-z\d]{15}$/, message: 'Please enter a valid VAT' },
              ]}
            />
          </ProForm.Group>
          <ProForm.Group>
            <ProFormText
              width="sm"
              name="displayName"
              label=" Display name of client "
              tooltip="The maximum length is 24 digits "
              placeholder=" Please enter a client  display name "
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.client.displayName.required"
                      defaultMessage="Client  display name is required!"
                    />
                  ),
                },
              ]}
            />
            <ProFormTextArea
              width="sm"
              name="description"
              label=" Description "
              tooltip="The maximum length is 200 digits "
              placeholder=" Please enter a client description "
              rules={[
                {
                  required: false,
                  message: (
                    <FormattedMessage
                      id="pages.client.description.required"
                      defaultMessage="Client description is required!"
                    />
                  ),
                },
              ]}
            />
          </ProForm.Group>

        </ProForm>
      </Card>
    </PageContainer>
  );
};

export default NewClientForm;
