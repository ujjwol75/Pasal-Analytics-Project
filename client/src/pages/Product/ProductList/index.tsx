import { addRule, getProducts, updateRule } from '@/services/ant-design-pro/api';
import { getHeaders, SERVER_URL } from '@/utils/utils';
import { request } from '@@/plugin-request/request';
import { PlusOutlined } from '@ant-design/icons';
import type { ProDescriptionsItemProps } from '@ant-design/pro-descriptions';
import ProDescriptions from '@ant-design/pro-descriptions';
import { ModalForm, ProFormText, ProFormTextArea } from '@ant-design/pro-form';
import { FooterToolbar, PageContainer } from '@ant-design/pro-layout';
import type { ActionType, ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { Button, Divider, Drawer, message, Popconfirm, Tag } from 'antd';
import React, { useRef, useState } from 'react';
import { FormattedMessage, history, Link, useIntl } from 'umi';
import type { FormValueType } from './components/UpdateForm';
import UpdateForm from './components/UpdateForm';

/**
 * @en-US Add node
 * @zh-CN add node
 * @param fields
 */
const handleAdd = async (fields: API.RuleListItem) => {
  const hide = message.loading('正在添加');
  try {
    await addRule({ ...fields });
    hide();
    message.success('Added successfully');
    return true;
  } catch (error) {
    hide();
    message.error('Adding failed, please try again!');
    return false;
  }
};

/**
 * @en-US Update node
 * @zh-CN 更新节点
 *
 * @param fields
 */
const handleUpdate = async (fields: FormValueType) => {
  const hide = message.loading('Configuring');
  try {
    await updateRule({
      name: fields.name,
      desc: fields.desc,
      key: fields.key,
    });
    hide();

    message.success('Configuration is successful');
    return true;
  } catch (error) {
    hide();
    message.error('Configuration failed, please try again!');
    return false;
  }
};

/**
 *  Delete node
 * @zh-CN delete node
 *
 * @param selectedRows
 */
/* const handleRemove = async (selectedRows: API.RuleListItem[]) => {
  const hide = message.loading('正在删除');
  if (!selectedRows) return true;
  try {
    await removeRule({
      key: selectedRows.map((row) => row.key),
    });
    hide();
    message.success('Deleted successfully and will refresh soon');
    return true;
  } catch (error) {
    hide();
    message.error('Delete failed, please try again');
    return false;
  }
}; */

const ProductList: React.FC = () => {
  /**
   * @en-US Pop-up window of new window
   * @zh-CN Pop-up window of new window
   *  */
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  /**
   * @en-US The pop-up window of the distribution update window
   * @zh-CN Popup window for distribution update window
   * */
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);

  const [showDetail, setShowDetail] = useState<boolean>(false);

  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState<API.RuleListItem>();
  const [selectedRowsState, setSelectedRows] = useState<API.RuleListItem[]>([]);

  /**
   * @en-US International configuration
   * @zh-CN Internationalization configuration
   * */
  const intl = useIntl();

  const remove = async (key: string) => {
    request(`${SERVER_URL}/product/${key}`, {
      method: 'DELETE',
      headers: await getHeaders(),
    }).then(function () {
      if (actionRef.current) {
        actionRef.current.reload();
      }
    });
  };

  // @ts-ignore
  const columns: ProColumns<API.TableListItem>[] = [
    {
      title: 'ID',
      dataIndex: 'id',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Please input Id',
      },
      width: 20,
      render: (text: any) => {
        // const href = `/product/${text.props.text}`;
        const href = `/product/edit/${text}`;
        return <Link to={href}>{text}</Link>;
      },
    },
    {
      title: 'Product Name',
      dataIndex: 'name',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Please input name',
      },
    },
    {
      title: 'Price',
      dataIndex: 'pricePerUnit',
      // @ts-ignore
      sorter: (a, b) => a.price - b.price,
      formItemProps: {
        // @ts-ignore
        placeholder: 'Please input price',
      },
    },
    {
      title: 'Description',
      dataIndex: 'description',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Please input Description',
      },
    },
    {
      title: 'Category',
      dataIndex: 'category',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Input Category name',
      },
    },
    {
      title: 'Vendor',
      dataIndex: 'vendor',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Input Vendor name',
      },
    },
    {
      title: (
        <FormattedMessage id="pages.searchTable.titleUpdatedAt" defaultMessage="Last updated" />
      ),
      sorter: true,
      dataIndex: 'lastUpdated',
      valueType: 'date',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Input Vendor name',
      },
    },
    {
      title: 'Operation',
      key: 'action',
      render: (record: any) => {
        return (
          <span>
            <Divider type="vertical" />
            <Popconfirm
              title="Do you want to delete this item？"
              onConfirm={() => remove(record.id)}
            >
              <Tag color={'red'}>
                {' '}
                <span>Delete</span>{' '}
              </Tag>
            </Popconfirm>
          </span>
        );
      },
      hideInSearch: true,
    },
  ];

  return (
    <PageContainer>
      <ProTable<API.TableListItem, API.PageParams>
        headerTitle={intl.formatMessage({
          id: 'pages.searchTable.title',
          defaultMessage: 'Enquiry form',
        })}
        actionRef={actionRef}
        rowKey="id"
        search={{
          labelWidth: 120,
        }}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              // handleModalVisible(true);
              history.push('/product/new');
            }}
          >
            <PlusOutlined /> <FormattedMessage id="pages.searchTable.new" defaultMessage="New" />
          </Button>,
        ]}
        request={getProducts}
        columns={columns}
        rowSelection={{
          onChange: (_, selectedRows) => {
            setSelectedRows(selectedRows);
          },
        }}
      />
      {selectedRowsState?.length > 0 && (
        <FooterToolbar
          extra={
            <div>
              <span style={{ fontWeight: 600 }}>{selectedRowsState.length}</span>{' '}
              <FormattedMessage id="menu.product" defaultMessage="Product" />
              {selectedRowsState.length >1 && <span>s</span>}
              &nbsp;
              <FormattedMessage id="pages.searchTable.chosen" defaultMessage="Chosen" />{' '}
              {/* <span>
                <FormattedMessage
                  id="pages.searchTable.totalServiceCalls"
                  defaultMessage="Total number of service calls"
                />{' '}
                {selectedRowsState.reduce((pre, item) => pre + item.callNo!, 0)}{' '}
                <FormattedMessage id="pages.searchTable.tenThousand" defaultMessage="Ten thousand" />
              </span> */}
            </div>
          }
        >
          {/* <Button
            onClick={async () => {
              await handleRemove(selectedRowsState);
              setSelectedRows([]);
              actionRef.current?.reloadAndRest?.();
            }}
          >
            <FormattedMessage
              id="pages.searchTable.batchDeletion"
              defaultMessage="Batch deletion"
            />
          </Button>
          <Button type="primary">
            <FormattedMessage
              id="pages.searchTable.batchApproval"
              defaultMessage="Batch approval"
            />
          </Button> */}
        </FooterToolbar>
      )}
      <ModalForm
        title={intl.formatMessage({
          id: 'pages.searchTable.createForm.newRule',
          defaultMessage: 'New rule',
        })}
        width="400px"
        visible={createModalVisible}
        onVisibleChange={handleModalVisible}
        onFinish={async (value) => {
          const success = await handleAdd(value as API.RuleListItem);
          if (success) {
            handleModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
      >
        <ProFormText
          rules={[
            {
              required: true,
              message: (
                <FormattedMessage
                  id="pages.searchTable.ruleName"
                  defaultMessage="Rule name is required"
                />
              ),
            },
          ]}
          width="md"
          name="name"
        />
        <ProFormTextArea width="md" name="desc" />
      </ModalForm>
      <UpdateForm
        onSubmit={async (value) => {
          const success = await handleUpdate(value);
          if (success) {
            handleUpdateModalVisible(false);
            setCurrentRow(undefined);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
        onCancel={() => {
          handleUpdateModalVisible(false);
          if (!showDetail) {
            setCurrentRow(undefined);
          }
        }}
        updateModalVisible={updateModalVisible}
        values={currentRow || {}}
      />

      <Drawer
        width={600}
        open={showDetail}
        onClose={() => {
          setCurrentRow(undefined);
          setShowDetail(false);
        }}
        closable={false}
      >
        {currentRow?.name && (
          <ProDescriptions<API.RuleListItem>
            column={2}
            title={currentRow?.name}
            request={async () => ({
              data: currentRow || {},
            })}
            params={{
              id: currentRow?.name,
            }}
            columns={columns as ProDescriptionsItemProps<API.RuleListItem>[]}
          />
        )}
      </Drawer>
    </PageContainer>
  );
};

export default ProductList;
