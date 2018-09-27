package com.hyt.client.config.shiro;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IEmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private IEmployeeService employeeService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->ShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        JSONObject userInfo  = (JSONObject)principals.getPrimaryPrincipal();
        JSONArray roles = userInfo.getJSONArray("roles");
        JSONArray perissions = userInfo.getJSONArray("perissions");
        roles.forEach(r -> {
            JSONObject role = (JSONObject) r;
            System.out.println(role);
            authorizationInfo.addRole(role.getString("role"));
        });

        perissions.forEach(p -> {
            JSONObject perission = (JSONObject) p;
            System.out.println(perission);
            authorizationInfo.addStringPermission(perission.getString("perission"));
        });


//        roles.forEach(r -> {
//            JSONObject role = (JSONObject) r;
//            authorizationInfo.addRole(role.getString("role"));
//            JSONArray perissions = role.getJSONArray("perissions");
//            perissions.forEach(p -> {
//                JSONObject perission = (JSONObject) p;
//                authorizationInfo.addStringPermission(perission.getString("perission"));
//            });
//        });

        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String loginName = (String) token.getPrincipal();

        if(loginName == null) return null;
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        Map<String, Object> param = new HashMap<>();
        param.put("loginName", loginName);
        JSONObject employee = this.employeeService.login(param);

        if(employee == null) return null;

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("employee", employee);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                employee, //用户名
                employee.getString("loginPassword"), //密码
                ByteSource.Util.bytes(employee.getString("loginName")),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

}