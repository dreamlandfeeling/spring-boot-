package com.xin.manager.shiro;

import com.xin.manager.model.Authority;
import com.xin.manager.model.Role;
import com.xin.manager.model.User;
import com.xin.manager.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        User user = userService.findUserByUserName(username);
        return new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        User user=(User) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions=new ArrayList<>();
        List<Role> roles = user.getRoles();
        Set<String> roleSet = new HashSet<>();
        if(roles.size()>0) {
            for(Role role : roles) {
                Set<Authority> modules = new HashSet<>(role.getAuthorities());
                if(modules.size()>0) {
                    for(Authority authority : modules) {
                        permissions.add(authority.getName());
                    }
                }
                roleSet.add(role.getName());
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addRoles(roleSet);
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
    }

}