package com.example.hou.service.impl;

/**
用于批量生成数据表

 sql语法
 INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('11', '2', '修改', NULL, 'sys:user:edit', '2', NULL, '0');
 INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('12', '2', '删除', NULL, 'sys:user:delete', '2', NULL, '0');

待定    未完成
 */
public class dataMake {

    String tableName;
    Integer loop;

    void printSQL (String tableName,Integer loop)
    {
        //生成一些随机数

        //拼接字符串然后输出
        for(int i=0;i<loop;i++)
        {
            String temp="INSERT INTO ";
            temp=temp+tableName+" (`ID`, `airline`, `depAirport`, `arrAirport`, `depTime`, `arrTime`, `price`, `availableSeats`, `model`, `status`) " +
                    "VALUES (";
        }

    }

    public static void main(String[] args) {
        System.out.println("test");
    }
}
