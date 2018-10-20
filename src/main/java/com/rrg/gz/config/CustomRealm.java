package com.rrg.gz.config;

import com.rrg.gz.dao.UserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 要继承AuthorizingRealm 类来自定义我们自己的realm
 * 以进行我们自定义的身份，权限认证操作。
 * 记得要Override 重写 doGetAuthenticationInfo 和 doGetAuthorizationInfo
 * 两个方法（两个方法名很相似，不要搞错）
 *
 *  doGetAuthorizationInfo 方法只有在需要权限认证时才会进去，
 *  比如前面配置类中配置了 filterChainDefinitionMap.put("/admin/**", "roles[admin]");
 *  的管理员角色，这时进入 /admin 时就会进入 doGetAuthorizationInfo 方法来检查权限；
 *  而 doGetAuthenticationInfo 方法则是需要身份认证时（比如前面的 Subject.login() 方法）才会进入
 * @author huangsz  2018/10/19 0019
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * Authorization是 认证，授权。
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限认证开始了咯----------！");
        // 获取登录用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取用户角色
        String role = userDao.getRoleByUserName(username);
        // 需要将 role 封装到 Set 作为 info.setRoles() 的参数
        Set<String> set = new HashSet<>();
        set.add(role);
        // 设置用户拥有的角色
        info.setRoles(set);
        return info;
    }

    /**
     * Authentication是 鉴定；证实
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份认证开始了-------");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        String password = userDao.getPasswordByUserName(token.getUsername());
        // 获取用户登录密码
        String loginPassword = new String((char[]) token.getCredentials());
        if (password == null) {
            throw new AccountException("用户名不正确");
        } else if (!password.equals(loginPassword)) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(),password,getName());
    }
}
