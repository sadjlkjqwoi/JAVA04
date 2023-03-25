package hw01;

import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

/**
 * 把注解@Column加在Person类的每个属性上，在输入Person时根据注解@Column的配置进行校验。
 * 第一题的@Label只标注在类上。根据注解生成Person类对应的数据库表创建语句，以及生成数据库表的删除、新增、修改SQL语句。
 * 并利用JDBC，实现数据库操作。
 */

public class ExecuteSQL {
    static PreparedStatement ps=null;
    static  CreateSQL sql=new CreateSQL();
    static Scanner input=new Scanner(System.in);
    static  Person person=new Person();

    public static void main(String[] args) throws Exception {
        mainMenu();
        closeConnection();

    }
    public static void mainMenu() throws Exception {
        System.out.println("请选择您要进行的操作：");
        System.out.println("1--自动生成表Person");
        System.out.println("2--增加数据");
        System.out.println("3--删除数据");
        System.out.println("4--修改数据");
        System.out.println("5--退出程序");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                createTable();
                mainMenu();
                break;
            case 2:
                insert();
                mainMenu();
                break;
            case 3:
                delete();
                mainMenu();
                break;
            case 4:
                update();
                mainMenu();
                break;
            case 5:
                break;
            default:
                System.out.println("请输入正确的选项！");
                mainMenu();
                break;
        }
    }

    //建表
    public static void createTable() throws Exception {
        System.out.println("生成表中......");
        String create = sql.CreateTable(person);
        ps = MySqlDAO.preparedStatement(create);
        ps.executeUpdate();
        System.out.println("成功生成");
    }

    //向表中添加信息
    public static void insert() throws Exception {
        try {
            String insertSQL = sql.InsertInf(person);
            ps = MySqlDAO.preparedStatement(insertSQL);
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("您输入的身份证号码有重复，请重新输入");
            insert();
        }
    }

    //删除信息1
    public static void delete() throws Exception {
        String deleteSQL = sql.deleteInf(person);
        ps = MySqlDAO.preparedStatement(deleteSQL);
        ps.executeUpdate();
    }

    //更新信息
    public static void update() throws Exception {
        String updateSQL = sql.updateInf(person);
        ps = MySqlDAO.preparedStatement(updateSQL);
        ps.executeUpdate();
    }

    public static void closeConnection() throws Exception {
        ps.close();
        MySqlDAO.getConnection().close();
    }



}
