import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import { useIntl } from '@umijs/max';
import React from 'react';

const Footer: React.FC = () => {
  const intl = useIntl();
  const defaultMessage = intl.formatMessage({
    id: 'app.copyright.produced',
    defaultMessage: 'Developed by Nuptse Technology Department',
  });

  const currentYear = new Date().getFullYear();

  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'nuptse',
          title: 'Nuptse',
          href: 'https://nuptse.com.np',
          blankTarget: true,
        },
        /*{
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/ant-design/ant-design-pro',
          blankTarget: true,
        },
        {
          key: 'Pasal Analytics',
          title: 'Ant Design',
          href: 'https://ant.design',
          blankTarget: true,
        },*/
      ]}
    />
  );
};

export default Footer;
