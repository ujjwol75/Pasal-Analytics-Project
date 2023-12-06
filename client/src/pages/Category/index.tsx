import { getDishHeaders, SERVER_URL } from '@/utils/utils';
import { useMatch } from '@@/exports';
import { request } from '@@/plugin-request/request';
import ProForm, { ProFormField, ProFormText,   ProFormSelect,ProFormTextArea } from '@ant-design/pro-form';
import { PageContainer } from '@ant-design/pro-layout';
import { Card, Form, message } from 'antd';
import { FC, useState } from 'react';
import { useEffect } from 'react';
import { FormattedMessage, history, useIntl, useRequest } from 'umi';
import { getCategories, submitNewCategory } from './service';

const NewCategoryForm: FC<Record<string, any>> = () => {
  const intl = useIntl();
  const [form] = Form.useForm();
  const [categories, setCategories] = useState();
  const match = useMatch('/category/edit/:id');
  const categoryId = match?.params.id;

  useEffect(() => {
    async function getDropDownData() {
      const categoriesJSON = await getCategories();
      setCategories(categoriesJSON.data);
      const headers = getDishHeaders();
      if (categoryId) {
        const response = await request(`${SERVER_URL}/category/${categoryId}`, {
          method: 'GET',
          headers,
        });
        const result = response.data;
        form.setFieldsValue({
          name: result.name,
          description: result.description,
          parentCategory: result.parentCategory,
        });
      }
    }
    getDropDownData().then();
  }, []);

  const { run } = useRequest(submitNewCategory, {
    manual: true,
    onSuccess: () => {
      message.success('Information submitted successfully');
      form.resetFields();
      history.push('/category');
    },
  });

  const onFinish = async (values: Record<string, any>) => {
    if (categoryId) {
      // eslint-disable-next-line no-param-reassign
      values.key = categoryId;
    }
    await run(values);
  };

  // @ts-ignore
  return (
    <PageContainer content="The New Category form page is used to collect or verify information about the category.">
      <Card bordered={false}>
        <ProForm
          hideRequiredMark
          style={{ margin: 'auto', marginTop: 8, maxWidth: 600 }}
          name="category"
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
              label=" Name of category "
              tooltip="The maximum length is 24 digits "
              placeholder=" Please enter a category name "
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.category.name.required"
                      defaultMessage="Category name is required!"
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
            placeholder=" Please enter a category description "
            rules={[
              {
                required: false,
                message: (
                  <FormattedMessage
                    id="pages.category.description.required"
                    defaultMessage="Category description is required!"
                  />
                ),
              },
            ]}
          />
          <ProForm.Group>

            {/*<ProFormField
              width="sm"
              name="parentCategory"
              label=" Parent Parent "
              tooltip="The maximum length is 24 digits "
              placeholder=" Please enter a  Parent Category "
            rules={[
              {
                required: false,
                message: (
                  <FormattedMessage
                    id="pages.category.parentCategory.required"
                    defaultMessage="Parent Category is required!"
                  />
                ),
              },
            ]}

            />*/}
             <ProFormSelect
              name="parentCategory"
              label="Parent Category"
              placeholder="Select a Category"
              // @ts-ignore
              optionFilterProps="children"
              fieldProps={{
                showSearch: true,
                optionFilterProp: 'label',
              }}
              options={categories}
              width="md"
            />
          </ProForm.Group>
        </ProForm>
      </Card>
    </PageContainer>
  );
};

export default NewCategoryForm;
