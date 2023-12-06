import { PlusOutlined } from '@ant-design/icons';

import type { ProDescriptionsItemProps } from '@ant-design/pro-descriptions';
import ProDescriptions from '@ant-design/pro-descriptions';
import { ModalForm, ProFormText, ProFormTextArea } from '@ant-design/pro-form';
import { FooterToolbar, PageContainer } from '@ant-design/pro-layout';
import type { ActionType, ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { Button, Divider, Drawer, message, Popconfirm, Tag } from 'antd';
import React, { useRef, useState } from 'react';
import { FormattedMessage,history, Link, useIntl } from 'umi';
import type { FormValueType } from './components/UpdateForm';
import UpdateForm from './components/UpdateForm';
import { getHeaders, SERVER_URL } from '@/utils/utils';
import { request } from '@@/plugin-request/request';
import { addRule, queryRule } from './service';


/**
 * add node
 * @param fields
 */
const handleAdd = async (fields: API.TableListItem) => {
  const hide = message.loading('Adding');
  try {
    await addRule({ ...fields });
    hide();
    message.success('Added successfully');
    return true;
  } catch (error) {
    hide();
    message.error('Add failed, please try again！');
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

const SaleList: React.FC = () => {
  /**
   * Pop-up window of new window
   */
  
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);

  const [showDetail, setShowDetail] = useState<boolean>(false);

  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState<API.TableListItem>();
  const [selectedRowsState, setSelectedRows] = useState<API.TableListItem[]>([]);

  /**
   * International configuration
   */
  const intl = useIntl();

  const remove = async (key: string) => {
    request(`${SERVER_URL}/sale/${key}`, {
      method: 'DELETE',
      headers: await getHeaders(),
    }).then(function () {
      console.log('headers');
      if (actionRef.current) {
        actionRef.current.reload();
      }
    });
  };

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
        // const href = `/sale/${text.props.text}`;
        const href = `/sale/view/${text}`;
        return <Link to={href}>{text}</Link>;
      },
    },
    {
      title: 'Customer',
      dataIndex: 'customer',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Please input name',
      },
    },
    {
      title: 'Sales Date',
      dataIndex: 'billDate',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Please select date',
      },
    },
    {
      title: 'Total',
      dataIndex: 'total',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Please input Description',
      },
    },
    {
      title: 'Particulars',
      dataIndex: 'remarks',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Please input Description',
      },
    },
    {
      title: 'Status',
      dataIndex: 'isCredit',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Please input Description',
      },
      render: (text, value) =>
        value.isCredit ? <Tag color={'red'}>{'Due'}</Tag> : <Tag color={'green'}>{'Paid'}</Tag>,
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
      <ProTable<API.TableListItem>
        headerTitle={intl.formatMessage({
          id: 'pages.searchTable.title',
          defaultMessage: 'Enquiry form',
        })}
        actionRef={actionRef}
        rowKey="id"
        size="small"
        search={{
          labelWidth: 120,
        }}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              // handleModalVisible(true);
              history.push('/sale/new');
            }}
          >
            <PlusOutlined /> <FormattedMessage id="pages.searchTable.new" defaultMessage="新建" />
          </Button>,
        ]}
        request={(params, sorter, filter) => queryRule({ ...params, sorter, filter })}
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
              <FormattedMessage id="pages.searchTable.chosen" defaultMessage="Selected" />{' '}
              <a style={{ fontWeight: 600 }}>{selectedRowsState.length}</a>{' '}
              <FormattedMessage id="pages.searchTable.item" defaultMessage="Item" />
              &nbsp;&nbsp;
            </div>
          }
        ></FooterToolbar>
      )}
      <ModalForm
        title={intl.formatMessage({
          id: 'pages.searchTable.createForm.newRule',
          defaultMessage: '新建规则',
        })}
        width="400px"
        open={createModalVisible}
        onOpenChange={handleModalVisible}
        onFinish={async (value) => {
          const success = await handleAdd(value as API.TableListItem);
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
                  defaultMessage="规则名称为必填项"
                />
              ),
            },
          ]}
          width="md"
          name="name"
        />
        <ProFormTextArea width="md" name="desc" />
      </ModalForm>
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
          <ProDescriptions<API.TableListItem>
            column={2}
            title={currentRow?.name}
            request={async () => ({
              data: currentRow || {},
            })}
            params={{
              id: currentRow?.name,
            }}
            columns={columns as ProDescriptionsItemProps<API.TableListItem>[]}
          />
        )}
      </Drawer>
    </PageContainer>
  );
};

export default SaleList;
