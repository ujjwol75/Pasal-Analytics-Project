import {addRule, getClients, getCustomers, getProducts, updateRule} from '@/services/ant-design-pro/api';
import { SERVER_URL } from '@/utils/utils';
import { request } from '@@/plugin-request/request';
import { PlusOutlined } from '@ant-design/icons';
import { FooterToolbar, PageContainer } from '@ant-design/pro-layout';
import type { ActionType, ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { Button, Divider, Drawer, message, Popconfirm, Tag } from 'antd';
import React, { useRef, useState } from 'react';
import { FormattedMessage, history, Link, useIntl } from 'umi';
import type { FormValueType } from './components/UpdateForm';

/**
 * @en-US Add node
 * @zh-CN 添加节点
 * @param fields
 */
const handleAdd = async (fields: API.RuleListItem) => {
  const hide = message.loading('Adding');
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
 * @zh-CN 删除节点
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
   *  */
  // const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  /**
   * @en-US The pop-up window of the distribution update window
   * */
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);

  const [showDetail, setShowDetail] = useState<boolean>(false);

  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState<API.RuleListItem>();
  const [selectedRowsState, setSelectedRows] = useState<API.RuleListItem[] | any>([]);

  /**
   * @en-US International configuration
   * */
  const intl = useIntl();

  const remove = async (key: string) => {
    request(`${SERVER_URL}/client/${key}`, {
      method: 'DELETE',
      // headers: await getHeaders(),
    }).then(function () {
      if (actionRef.current) {
        actionRef.current.reload();
      }
    });
  };

  // @ts-ignore
  const columns: ProColumns<API.ClientItem>[] = [
    {
      title: 'ID',
      dataIndex: 'id',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Id',
      },
      width: 20,
      render: (text: any) => {
        const href = `/client/edit/${text}`;
        return <Link to={href}>{text}</Link>;
      },
    },
    {
      title: 'Name',
      dataIndex: 'name',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Name',
      },
    },
    {
      title: 'Email',
      dataIndex: 'email',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Please input email',
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
      title: 'Phone',
      dataIndex: 'phone',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Input Customer phone',
      },
    },
    {
      title: 'Address',
      dataIndex: 'address',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Input Customer address',
      },
    },
    {
        title: 'Code',
        dataIndex: 'code',
        formItemProps: {
          // @ts-ignore
          placeholder: 'Input Client code',
        },
    },
    {
        title: 'URL',
        dataIndex: 'url',
        formItemProps: {
          // @ts-ignore
          placeholder: 'Input Client Url',
        },
      },
    {
        title: 'DisplayName',
        dataIndex: 'displayName',
        formItemProps: {
          // @ts-ignore
          placeholder: 'Input Client Display Name',
        },
    },
    {
        title: 'Pan',
        dataIndex: 'pan',
        formItemProps: {
          // @ts-ignore
          placeholder: 'Input Client Pan',
        },
    },
    {
        title: 'Vat',
        dataIndex: 'vat',
        formItemProps: {
          // @ts-ignore
          placeholder: 'Input Client Vat',
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
                <a>Delete</a>{' '}
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
      <ProTable<API.ClientItem, API.PageParams>
        headerTitle={intl.formatMessage({
          id: 'dashboard.analysis.clients',
          defaultMessage: 'Client List',
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
              history.push('/client/new');
            }}
          >
            <PlusOutlined /> <FormattedMessage id="pages.searchTable.new" defaultMessage="New" />
          </Button>,
        ]}
        // @ts-ignore
        request={getClients}
        
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
              <FormattedMessage id="pages.searchTable.chosen" defaultMessage="Chosen" />{' '}
              <a style={{ fontWeight: 600 }}>{selectedRowsState.length}</a>{' '}
              <FormattedMessage id="pages.client.name" defaultMessage="Clients" />
              &nbsp;&nbsp;
            </div>
          }
        >
        </FooterToolbar>
      )}
    </PageContainer>
  );
};

export default ProductList;
