package com.atom.maxcompute.demo;

import com.aliyun.odps.Instance;
import com.aliyun.odps.Odps;
import com.aliyun.odps.OdpsException;
import com.aliyun.odps.account.Account;
import com.aliyun.odps.account.AliyunAccount;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.task.SQLTask;
import com.atom.maxcompute.Constant;

import java.util.List;


/**
 * 如果想创建表，则需要通过SQLTask接口，而不是Table接口(可以通过Tables类)。需要将创建表（CREATE TABLE）的语句传入SQLTask。
 * 每次只能提交运行一个SQL语句。
 *
 * @author Atom
 */
public class SqlTaskDemo {

    private static final String project = "atom_project_test_dev";
    private static final String sql = "CREATE TABLE IF NOT EXISTS user(\n" +
            "\tid BIGINT COMMENT 'id',\n" +
            "\tname STRING COMMENT '姓名',\n" +
            "\tphone STRING COMMENT '手机号',\n" +
            "\taddr STRING COMMENT '住址'\n" +
            ") \n" +
            "PARTITIONED BY (ds STRING COMMENT '分区字段') \n" +
            "STORED AS ALIORC  \n" +
            "LIFECYCLE 100;";


    public static void main(String[] args) {
        Account account = new AliyunAccount(Constant.accessId, Constant.accessKey);
        Odps odps = new Odps(account);
        odps.setEndpoint(Constant.endpoint);
        // 项目区分环境，开发环境/生产环境，这里获取开发环境内的表
        odps.setDefaultProject(project);
        Instance i;
        try {
            i = SQLTask.run(odps, sql);
            i.waitForSuccess();
            List<Record> records = SQLTask.getResult(i);
            for (Record r : records) {
                System.out.println(r.get(0).toString());
            }
        } catch (OdpsException e) {
            e.printStackTrace();
        }
    }
}
