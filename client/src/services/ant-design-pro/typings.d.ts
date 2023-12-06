// @ts-ignore
/* eslint-disable */

declare namespace API {
  type CurrentUser = {
    name?: string;
    avatar?: string;
    userid?: string;
    email?: string;
    signature?: string;
    title?: string;
    group?: string;
    tags?: { key?: string; label?: string }[];
    notifyCount?: number;
    unreadCount?: number;
    country?: string;
    access?: string;
    geographic?: {
      province?: { label?: string; key?: string };
      city?: { label?: string; key?: string };
    };
    address?: string;
    phone?: string;
  };

  type LoginResult = {
    status?: string;
    type?: string;
    currentAuthority?: string;
  };

  type PageParams = {
    current?: number;
    pageSize?: number;
  };

  type RuleListItem = {
    key?: number;
    disabled?: boolean;
    href?: string;
    avatar?: string;
    name?: string;
    owner?: string;
    desc?: string;
    callNo?: number;
    status?: number;
    updatedAt?: string;
    createdAt?: string;
    progress?: number;
  };

  type RuleList = {
    data?: RuleListItem[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  };

  type FakeCaptcha = {
    code?: number;
    status?: string;
  };

  type LoginParams = {
    username?: string;
    password?: string;
    autoLogin?: boolean;
    type?: string;
  };

  type ErrorResponse = {
    /** The error code of the business agreement */
    errorCode: string;
    /** business misinformation */
    errorMessage?: string;
    /** Whether the business request is successful */
    success?: boolean;
  };

  type NoticeIconList = {
    data?: NoticeIconItem[];
    /** total number of items in the list */
    total?: number;
    success?: boolean;
  };

  type TableListItem = {
    key?: number;
    disabled?: boolean;
    href?: string;
    avatar?: string;
    name?: string;
    owner?: string;
    desc?: string;
    callNo?: number;
    status?: number;
    isCredit?: boolen;
    updatedAt?: string;
    createdAt?: string;
    progress?: number;
    pricePerUnit?: number;
  };

  type SaleParams = {
    customer: string;
    billDate: string;
    isCredit: boolean;
    items: string[];
    remarks: string;
    discount: string;
  };

  type LineItem = {};
  type Payment = {};

  type SaleItem = {
    id?: string;
    customer?: string;
    billDate: string;
    isCredit: boolean;
    total: number;
    discount: number;
    grossAmount: number;
    partialTotal: number;
    dues: number;
    remarks: string;
    lineItems: LineItem[];
    payments: Payment[];
  };
  type ClientItem ={
    key?: number;
    id?:string
    phone?: number
    name?: string
    description?: string
    address?: string
    code?: number
    url?: string
    displayName?: string
    pan?: number
    vat?: string

  };
}

type NoticeIconItemType = 'notification' | 'message' | 'event';

type NoticeIconItem = {
  id?: string;
  extra?: string;
  key?: string;
  read?: boolean;
  avatar?: string;
  title?: string;
  status?: string;
  datetime?: string;
  description?: string;
  type?: NoticeIconItemType;
};

type CategoryItem = {
  id?: string
  name?: string
  description?: string
  sortOrder?: number
  parentCategory?: number
  client?: number
}
