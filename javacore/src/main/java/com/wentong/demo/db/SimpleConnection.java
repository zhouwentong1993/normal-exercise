package com.wentong.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class SimpleConnection {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/canal-test";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "12345678";

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            for (int i = 0; i < 10000; i++) {
                sql = "insert into single_table (key1, key2, key3, key_part1, key_part2, key_part3, common_field) values (" + "'" + UUID.randomUUID().toString() + "'" + "," + i + "," + "'" + UUID.randomUUID().toString() + "'" + "," + "'" + UUID.randomUUID().toString() + "'" + "," + "'" + UUID.randomUUID().toString() + "'" + "," + "'" + UUID.randomUUID().toString() + "'" + "," + "'" + UUID.randomUUID().toString() + "'" + ");";
                System.out.println(sql);
                stmt.execute(sql);
            }
            // 完成后关闭
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
