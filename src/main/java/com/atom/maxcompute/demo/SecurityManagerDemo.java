package com.atom.maxcompute.demo;

import com.aliyun.odps.Odps;
import com.aliyun.odps.OdpsException;
import com.aliyun.odps.account.Account;
import com.aliyun.odps.account.AliyunAccount;
import  com.aliyun.odps.security.SecurityManager;
import com.atom.maxcompute.Constant;

/**
 * @author Atom
 */
public class SecurityManagerDemo {
    private static final String project = "atom_project_test_dev";


    public static void main(String[] args) throws OdpsException {
        Account account = new AliyunAccount(Constant.accessId, Constant.accessKey);
        Odps odps = new Odps(account);
        odps.setEndpoint(Constant.endpoint);
        // 项目区分环境，开发环境/生产环境，这里获取开发环境内的表
        odps.setDefaultProject(project);
        SecurityManager securityManager = odps.projects().get().getSecurityManager();
        // 查询当前用户是否具有创建表的权限，使用某个表的权限？
//        String res = securityManager.runQuery("list roles", false);

        // 查询项目的权限信息
        String res = securityManager.runQuery("show acl for atom_project_test_dev on type project;", false);

        // 查询表的权限信息
//        String res = securityManager.runQuery("show acl for user on type table", false);


        System.err.println(res);
    }
}
