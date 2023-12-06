import { getDishHeaders, SERVER_URL } from '@/utils/utils';
import { useMatch } from '@@/exports';
import { request } from '@@/plugin-request/request';
import ProForm, { ProFormField, ProFormText, ProFormTextArea, ProFormSelect } from '@ant-design/pro-form';
import { PageContainer } from '@ant-design/pro-layout';
import { Card, Form, message } from 'antd';
import type { FC } from 'react';
import { useEffect, useState } from 'react';
import { FormattedMessage, history, useIntl, useRequest } from 'umi';

import { getClients, submitNewUser } from './service';

const NewUserForm: FC<Record<string, any>> = () => {
  const intl = useIntl();
  const [form] = Form.useForm();
  const match = useMatch('/user/edit/:id');
  const userId = match?.params.id;

  const [clients, setClients] = useState();

  useEffect(() => {
    async function getDropDownData() {
      const headers = getDishHeaders();
      const clientsJSON = await getClients();
      setClients(clientsJSON.data);

      if (userId) {
        const response = await request(`${SERVER_URL}/user/${userId}`, {
          method: 'GET',
          headers,
        });
        const result = response.data;
        form.setFieldsValue({
          username: result.username,
          password: result.password,
          avatarUrl: result.avatarUrl,
          firstName: result.firstName,
          lastName: result.lastName,
          country: result.country,
          address: result.address,
          description: result.description,
          phone: result.phone,
          email: result.email,
          client: result.client,
        });
      }
    }
    getDropDownData().then();
  }, []);

  const { run } = useRequest(submitNewUser, {
    manual: true,
    onSuccess: () => {
      message.success('Information submitted successfully');
      form.resetFields();
      history.push('/user');
    },
  });

  const onFinish = async (values: Record<string, any>) => {
    if (userId) {
      // eslint-disable-next-line no-param-reassign
      values.key = userId;
    }
    await run(values);
  };

  // @ts-ignore
  return (
    <PageContainer content="The New User form page is used to collect or verify information about the user.">
      <Card bordered={false}>
        <ProForm
          hideRequiredMark
          style={{ margin: 'auto', marginTop: 8, maxWidth: 600 }}
          name="user"
          layout="vertical"
          initialValues={{ price: 0 }}
          onFinish={onFinish}
          form={form}
          size="middle"
        >
          {/* Group-1: username and password */}
          <ProForm.Group>
            <ProFormText
              width="sm"
              name="username"
              label="Username"
              tooltip="The maximum length is 400 characters."
              placeholder="Please enter a username"
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.user.name.required"
                      defaultMessage="Username is required!"
                    />
                  ),
                },
              ]}
            />
            <ProFormText.Password
              width="md"
              name="password" // try input-password if not working
              label="Password"
              tooltip="Include numbers, letters, and special symbols."
              placeholder="Please enter user password"
              rules={[
                {
                  required: true,
                  type: 'password',
                  message: (
                    <FormattedMessage
                      id="pages.user.password.required"
                      defaultMessage="Password's minimum length is 8!"
                    />
                  ),
                },
              ]}
            />
          </ProForm.Group>

          {/* Group-2: avatarUrl and description */}
          <ProForm.Group>
            <ProFormField
             width="sm"
             name="url"
             label="URL"
             tooltip="Valid url should look like www.example.com"
             placeholder="Please enter user's URL"
             rules={[
             { required: true, message: 'User URL is required' },
             { type: 'url', message: 'Please enter a valid URL' },
             ]}
            />
            <ProFormTextArea
              width="sm"
              name="description"
              label="Description"
              tooltip="The maximum length is 1000 characters."
              placeholder="Please enter description for user"
              rules={[
                {
                  required: false,
                  message: (
                    <FormattedMessage
                      id="pages.user.description.required"
                      defaultMessage="User's description is required!"
                    />
                  ),
                },
              ]}
            />
          </ProForm.Group>

          {/* Group-3: firstName and lastName */}
          <ProForm.Group>
            <ProFormText
              width="sm"
              name="firstName"
              label="First Name"
              tooltip="The maximum length is 200 characters."
              placeholder="Please enter first name"
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.user.firstName.required"
                      defaultMessage="User's first name is required!"
                    />
                  ),
                },
              ]}
            />
            <ProFormText
              width="sm"
              name="lastName"
              label="Last Name"
              tooltip="The maximum length is 200 characters."
              placeholder="Please enter last name"
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.user.lastName.required"
                      defaultMessage="User's last name is required!"
                    />
                  ),
                },
              ]}
            />
          </ProForm.Group>

          {/* Group-4: country and address */}
          <ProForm.Group>
            <ProFormField
              width="sm"
              name="address"
              label="Address"
              tooltip="The maximum length is 1000 characters."
              placeholder="Please enter user's address"
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.user.address.required"
                      defaultMessage="User's address is required!"
                    />
                  ),
                },
              ]}
            />
            <ProFormField
              width="sm"
              name="country"
              label="Country"
              tooltip="The maximum length is 100 characters."
              placeholder="Please enter user's country"
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.user.country.required"
                      defaultMessage="User's country is required!"
                    />
                  ),
                },
              ]}
            />
          </ProForm.Group>

          {/* Group-5: phone and email */}
          <ProForm.Group>
            <ProFormField
              width="sm"
              name="phone"
              label="Phone No."
              tooltip="The maximum length is 10 digits."
              placeholder="Please enter user's phone number"
              rules={[
                {
                  required: true,
                  message: intl.formatMessage({ id: 'pages.new-user-form.phone.required' }),
                },
                {
                  pattern: /^(\d{10})$/,
                  message: 'Please enter a valid phone number',
                },
              ]}
            />
            <ProFormField
              width="md"
              name="email"
              label="Email"
//               tooltip="The maximum length is 64 digits"
              placeholder="Please enter user's email"
              rules={[
                {
                  required: true,
                  type: 'email',
                  message: (
                    <FormattedMessage
                      id="pages.user.email.required"
                      defaultMessage="User email is required in an appropriate format!"
                    />
                  ),
                },
              ]}
            />
          </ProForm.Group>

          {/* Group 6: Client */}
          <ProForm.Group>
            <ProFormSelect
              name="client"
              label={intl.formatMessage({ id: 'pages.new-user-form.client.label' })}
              placeholder="Select a Client"
              // @ts-ignore
              optionFilterProps="children"
              fieldProps={{
                showSearch: true,
                optionFilterProp: 'label',
              }}
              options={clients}
              width="md"
            />
          </ProForm.Group>
        </ProForm>
      </Card>
    </PageContainer>
  );
};

export default NewUserForm;
