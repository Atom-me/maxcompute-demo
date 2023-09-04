package com.atom.maxcompute.demo;

import com.aliyun.odps.Odps;
import com.aliyun.odps.OdpsException;
import com.aliyun.odps.Project;
import com.aliyun.odps.account.Account;
import com.aliyun.odps.account.AliyunAccount;
import com.atom.maxcompute.Constant;

/**
 * Projects是MaxCompute中所有项目空间的集合。集合中的元素为项目（Project）。
 * Project是对项目信息的描述。您可以通过Projects获取相应的项目。
 *
 * @author Atom
 */
public class ProjectDemo {
    public static void main(String[] args) throws OdpsException {
        Account account = new AliyunAccount(Constant.accessId, Constant.accessKey);
        Odps odps = new Odps(account);
        odps.setEndpoint(Constant.endpoint);
        // 获取指定用户下所有的项目信息
        for (Project atomMe : odps.projects().iterable("atom_me")) {
            // 重新加载项目的属性值
            atomMe.reload();
            System.err.println(atomMe.getName());
        }
        System.out.println("===================================================================");
    }
}
