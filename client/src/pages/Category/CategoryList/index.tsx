import { addRule, getCategory, updateRule } from '@/services/ant-design-pro/api';
import { getHeaders, SERVER_URL } from '@/utils/utils';
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

const CategoryList: React.FC = () => {
console.log(getCategory)
  /**
   * @en-US Pop-up window of new window
   *  */
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  /**
   * @en-US The pop-up window of the distribution update window
   * */
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);

  const [showDetail, setShowDetail] = useState<boolean>(false);

  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState<API.RuleListItem>();
  const [selectedRowsState, setSelectedRows] = useState<API.RuleListItem[]>([]);

  /**
   * @en-US International configuration
   * */
  const intl = useIntl();

  const remove = async (key: string) => {
    request(`${SERVER_URL}/category/${key}`, {
      method: 'DELETE'
    }).then(function () {
      if (actionRef.current) {
        actionRef.current.reload();
      }
    });
  };

  // @ts-ignore
  const columns: ProColumns<TableListItem>[] = [
    {
      title: 'ID',
      dataIndex: 'id',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Id',
      },
      width: 20,
      render: (text: any) => {
        // const href = `/customer/${text.props.text}`;
        const href = `/category/edit/${text}`;
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
      title: 'Description',
      dataIndex: 'description',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Please input Description',
      },
    },
    {
      title: 'Parent Category',
      dataIndex: 'parentCategory',
      formItemProps: {
        // @ts-ignore
        placeholder: 'Input Parent Category',
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
      <ProTable<API.TableListItem, API.PageParams>
        headerTitle={intl.formatMessage({
          id: 'pages.category.list',
          defaultMessage: 'Category List',
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
              history.push('/category/new');
            }}
          >
            <PlusOutlined /> <FormattedMessage id="pages.searchTable.new" defaultMessage="New" />
          </Button>,
        ]}
        // @ts-ignore
        request={getCategory}
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
              <FormattedMessage id="pages.category.name" defaultMessage="Category" />
              &nbsp;&nbsp;
            </div>
          }
        >
        </FooterToolbar>
      )}
    </PageContainer>
  );
};

export default CategoryList;
