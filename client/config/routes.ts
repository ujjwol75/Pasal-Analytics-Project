/**
 * @name umi routing configuration for
 * @description Only support the configuration of path, component, routes, redirect, wrappers, name, icon
 * @param path  path Only two placeholder configurations are supported,
 *              the first is the form of the dynamic parameter :id, the second is the * wildcard,
 *              and the wildcard can only appear at the end of the routing string.
 * @param component Configure the path of the React component to be rendered after matching location and path.
 *        It can be an absolute path or a relative path. If it is a relative path, it will start from src/pages.
 * @param routes Configure sub-routes, usually used when you need to add layout components for multiple paths.
 * @param redirect Configure routing jump
 * @param wrappers Configure the packaging component of the routing component. Through the packaging component,
 *            more functions can be combined into the current routing component.
 *            For example, it can be used for permission verification at the routing level
 * @param name Configure the title of the route. By default, the value of menu.xxxx in the internationalization file menu.ts is read.
 *            If the name is configured as login, the value of menu.login in menu.ts is read as the title
 * @param icon Configure the icon of the route, refer to https://ant.design/components/icon-cn for the value,
 *            pay attention to remove the style suffix and capitalization,
 *            if you want to configure the icon as <StepBackwardOutlined />, the value should be stepBackward or StepBackward,
 *            such as If you want to configure the icon as <UserOutlined />, the value should be user or User
 * @doc https://umijs.org/docs/guides/routes
 */
export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        name: 'login',
        path: '/user/login',
        component: './User/Login',
      },
    ],
  },
  {
    path: '/analysis',
    name: 'dashboard',
    icon: 'dashboard',
    access: 'canAdmin',
    component: './Dashboard/Analysis',
  },
  /*{
    path: '/welcome',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
  },
  {
    path: '/admin',
    name: 'admin',
    icon: 'crown',
    access: 'canAdmin',
    routes: [
      {
        path: '/admin',
        redirect: '/admin/sub-page',
      },
      {
        path: '/admin/sub-page',
        name: 'sub-page',
        component: './Admin',
      },
    ],
  },*/
  {
    path: '/vendor',
    name: 'vendor',
    icon: 'ShopOutlined',
    access: 'canAdmin',
    routes: [
      { path: '/vendor', redirect: '/vendor/list' },
      {
        path: '/vendor/list',
        name: 'list-vendor',
        icon: 'crown',
        component: './Vendor/VendorList',
      },
      {
        path: '/vendor/new',
        name: 'new-vendor',
        icon: 'crown',
        component: './Vendor',
      },
      {
        path: '/vendor/edit/:id',
        name: 'edit-vendor',
        icon: 'crown',
        component: './Vendor',
        hideInMenu: true,
      },
    ],
  },
  {
    path: '/product',
    name: 'product',
    icon: 'AppstoreOutlined',
    access: 'canAdmin',
    routes: [
      { path: '/product', redirect: '/product/list' },
      {
        path: '/product/list',
        name: 'list-product',
        icon: 'smile',
        component: './Product/ProductList',
      },
      {
        path: '/product/new',
        name: 'new-product',
        icon: 'smile',
        component: './Product',
      },
      {
        path: '/product/edit/:id',
        name: 'edit-product',
        icon: 'smile',
        component: './Product',
        hideInMenu: true,
      },
    ],
  },
  {
    path: '/customer',
    name: 'customer',
    icon: 'AimOutlined',
    access: 'canAdmin',
    routes: [
      { path: '/customer', redirect: '/customer/list' },
      {
        path: '/customer/list',
        name: 'list-customer',
        icon: 'crown',
        component: './Customer/CustomerList',
      },
      {
        path: '/customer/new',
        name: 'new-customer',
        icon: 'crown',
        component: './Customer',
      },
      {
        path: '/customer/edit/:id',
        name: 'edit-customer',
        icon: 'crown',
        component: './Customer',
        hideInMenu: true,
      },
    ],
  },
  {
    path: '/sale',
    name: 'sale',
    icon: 'ShoppingCartOutlined',
    access: 'canAdmin',
    routes: [
      { path: '/sale', redirect: '/sale/list' },
      {
        path: '/sale/list',
        name: 'list-sale',
        icon: 'unordered-list',
        component: './Sale/SaleList',
      },
      {
        path: '/sale/new',
        name: 'new-sale',
        icon: 'plus',
        component: './Sale',
      },
      {
        path: '/sale/view/:id',
        name: 'view-sale',
        icon: 'smile',
        component: './Sale/View',
        hideInMenu: true,
      },
    ],
  },
  // category
  {
    path: '/category',
    name: 'category',
    icon: 'ShoppingCartOutlined',
    access: 'canAdmin',

    routes: [
      { path: '/category', redirect: '/category/list' },
      {
        path: '/category/list',
        name: 'list-category',
        icon: 'smile',
        component: './Category/CategoryList',
      },
      {
        path: '/category/new',
        name: 'new-category',
        icon: 'GroupOutlined',
        component: './Category',
      },
      {
        path: '/category/edit/:id',
        name: 'edit-category',
        icon: 'smile',
        component: './Category',
        hideInMenu: true,
      },
    ]
  },
  //client
  {
    path: '/client',
    name: 'client',
    icon: 'AppstoreOutlined',
    access: 'canAdmin',
    routes: [
      { path: '/client', redirect: '/client/list' },
      {
        path: '/client/list',
        name: 'list-client',
        icon: 'smile',
        component: './Client/ClientList',
      },
      {
        path: '/client/new',
        name: 'new-client',
        icon: 'smile',
        component: './Client',
      },
      {
        path: '/client/edit/:id',
        name: 'edit-client',
        icon: 'smile',
        component: './Client',
        hideInMenu: true,
      },
    ],
  },
  // user
  {
    path: '/user',
    name: 'user',
    icon: 'AimOutlined',
    access: 'canAdmin',
    routes: [
      { path: '/user', redirect: '/user/list' },
      {
        path: '/user/list',
        name: 'list-user',
        icon: 'crown',
        component: './User/UserList',
      },
      {
        path: '/user/new',
        name: 'new-user',
        icon: 'crown',
        component: './User',
      },
      {
        path: '/user/edit/:id',
        name: 'edit-user',
        icon: 'crown',
        component: './User',
        hideInMenu: true,
      },
    ],
  },
  {
    name: 'list.table-list',
    icon: 'table',
    path: '/list',
    component: './TableList',
  },
  {
    path: '/',
    redirect: '/analysis',
  },
  {
    path: '*',
    layout: false,
    component: './404',
  },
];
