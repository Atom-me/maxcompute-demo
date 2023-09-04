package com.atom.maxcompute.demo;

import com.aliyun.odps.*;
import com.aliyun.odps.account.Account;
import com.aliyun.odps.account.AliyunAccount;
import com.atom.maxcompute.Constant;

/**
 * @author Atom
 */
public class TableDemo {

    public static void main(String[] args) throws OdpsException {
        Account account = new AliyunAccount(Constant.accessId, Constant.accessKey);
        Odps odps = new Odps(account);
        odps.setEndpoint(Constant.endpoint);
        // 项目区分环境，开发环境/生产环境，这里获取开发环境内的表
        odps.setDefaultProject("atom_project_test_dev");
        for (Table t : odps.tables()) {
            System.err.println(t.getName());
            System.err.println(t.getLife());
            System.err.println(t.getCreatedTime());
            System.err.println(t.getLocation());
            System.err.println(t.getJsonSchema());

            // 获取普通列
            t.getSchema().getColumns().forEach(column -> System.err.println(column.getName()));


            // 获取分区列
            t.getSchema().getPartitionColumns().forEach(partitionColumn -> System.err.println(partitionColumn.getName()));
        }


        // 创建表，两种方式，一种通过 Tables，一种通过SQLTask
        TableSchema schema = new TableSchema();
        schema.addColumn(new Column("key", OdpsType.STRING));
        schema.addColumn(new Column("value", OdpsType.BIGINT));
        odps.tables().create("test_label", schema);
    }


}
