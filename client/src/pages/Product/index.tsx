import { getDishHeaders, SERVER_URL } from '@/utils/utils';
import { request } from '@@/plugin-request/request';
import ProForm, {
  ProFormDigit,
  ProFormSelect,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-form';
import { PageContainer } from '@ant-design/pro-layout';
import { Card, Form, message } from 'antd';
import type { FC } from 'react';
import { useEffect, useState } from 'react';
import { FormattedMessage, history, useIntl, useMatch, useRequest } from 'umi';
import { getVendors, submitNewProduct } from './service';
import {getCategories} from "@/pages/Category/service";

const NewProductForm: FC<Record<string, any>> = () => {
  // console.log('props match params ===> ', props.match.params.id);
  const intl = useIntl();
  const [form] = Form.useForm();

  const [vendors, setVendors] = useState();
  const [categories, setCategories] = useState();
  const match = useMatch('/product/edit/:id');
  console.log(match?.pathname, match?.params.id);
  const productId = match?.params.id;

  useEffect(() => {
    async function getDropDownData() {
      const vendorsJSON = await getVendors();
      const categoriesJSON = await getCategories();
      setVendors(vendorsJSON.data);
      setCategories(categoriesJSON.data);
      console.log(categoriesJSON.data);
      if (productId) {
        const response = await request(`${SERVER_URL}/product/${productId}`, {
          method: 'GET'
        });
        const result = response.data;
        console.log('result', result);
        form.setFieldsValue({
          name: result.name,
          pricePerUnit: result.pricePerUnit,
          description: result.description,
          vendor: result.vendorId,
          category: result.categoryId,
        });
      }
    }
    getDropDownData().then();
  }, []);

  const { run } = useRequest(submitNewProduct, {
    manual: true,
    onSuccess: () => {
      message.success('Information submitted successfully');
      form.resetFields();
      history.push('/product');
    },
  });

  const onFinish = async (values: Record<string, any>) => {
    if (productId) {
      // eslint-disable-next-line no-param-reassign
      values.key = productId;
    }
    await run(values);
  };

  // @ts-ignore
  return (
    <PageContainer content="The New Product form page is used to collect or verify information about the product.">
      <Card bordered={false}>
        <ProForm
          // hideRequiredMark
          style={{ margin: 'auto', marginTop: 8, maxWidth: 600 }}
          name="basic"
          layout="vertical"
          initialValues={{ pricePerUnit: 0 }}
          onFinish={onFinish}
          form={form}
          size="middle"
        >
          <ProFormText
            width="sm"
            name="name"
            label="Name of product "
            tooltip="Name of your product."
            placeholder="Enter product name"
            rules={[
              {
                required: true,
                message: (
                  <FormattedMessage
                    id="pages.product.name.required"
                    defaultMessage="Product name is required!"
                  />
                ),
              },
            ]}
          />
          <ProFormSelect
            name="category"
            label={intl.formatMessage({ id: 'pages.new-product-form.category.label' })}
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
          <ProFormDigit
            name="pricePerUnit"
            width="md"
            label={intl.formatMessage({ id: 'pages.new-product-form.price.label' })}
            rules={[
              {
                type: 'number',
                min: 1,
                max: 999999,
                message: 'Price per unit must be greater than 0',
              },
              {
                required: true,
                message: intl.formatMessage({ id: 'pages.new-product-form.price.required' }),
              },
            ]}
          />
          <ProFormTextArea
            width="md"
            name="description"
            rules={[
              {
                required: false,
                message: intl.formatMessage({ id: 'pages.new-product-form.product.required' }),
              },
            ]}
            label={intl.formatMessage({ id: 'pages.new-product-form.product.label' })}
            placeholder={intl.formatMessage({
              id: 'pages.new-product-form.product.placeholder',
              defaultMessage: 'Enter product description',
            })}
          />
          <ProFormSelect
            name="vendor"
            label={intl.formatMessage({ id: 'pages.new-product-form.vendor.label' })}
            placeholder="Select a Vendor"
            // @ts-ignore
            optionFilterProps="children"
            fieldProps={{
              showSearch: true,
              optionFilterProp: 'label',
            }}
            options={vendors}
            width="md"
          />
        </ProForm>
      </Card>
    </PageContainer>
  );
};

export default NewProductForm;
