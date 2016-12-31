package com.a.eye.by.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseClient {

    private static Configuration conf;

    private static Connection conn;

    static {
        try {
            conf = HBaseConfiguration.create();
            conn = ConnectionFactory.createConnection(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // create table
    public static void createTable(String name, String...columns) {
        try {
            Admin admin = conn.getAdmin();

            TableName table = TableName.valueOf(name);

            HTableDescriptor tableDescriptor = new HTableDescriptor(table);

            for (String column : columns) {
                HColumnDescriptor columnDescriptor = new HColumnDescriptor(column);
                tableDescriptor.addFamily(columnDescriptor);
            }
            admin.createTable(tableDescriptor);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // put data
    public static void putData(String name) {
        try {
            TableName tableName = TableName.valueOf(name);
            Table table = conn.getTable(tableName);

            Put put = new Put(Bytes.toBytes("key"));
            put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("a"), Bytes.toBytes("tom"));
            table.put(put);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // get data
    public static void getData(String name, String rowKey) {
        try {
            TableName tableName = TableName.valueOf(name);
            Table table = conn.getTable(tableName);

            Get get = new Get(Bytes.toBytes(rowKey));

            Result rs = table.get(get);
            System.out.println(rs);
        } catch (Exception e) {

        }
    }

    // scan data
    public static void scanTable(String name) {
        try {
            TableName tableName = TableName.valueOf(name);
            Table table = conn.getTable(tableName);

            Scan scan = new Scan();
            ResultScanner rs = table.getScanner(scan);
            for (Result result : rs) {
                System.out.println(new String(result.value()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
