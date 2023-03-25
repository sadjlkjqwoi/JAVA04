package hw01;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CreateSQL {
    static PreparedStatement pd=null;
    static ResultSet rs;
    static Scanner input=new Scanner(System.in);
    public String CreateTable(Person person){
        Class<?extends Person>clazz=person.getClass();
        Field []fields=clazz.getDeclaredFields();
        Label name=clazz.getAnnotation(Column.class).label();
        StringBuilder sql=new StringBuilder("create table if not exists "+name.value()+"(\n");
        for (Field field: fields) {
            String str=field.getAnnotation(Column.class).label().value();
            int maxLength=field.getAnnotation(Column.class).MaxLength();

            //如果是Integer类型
            if(field.getType().getName().contains("Integer")){
                sql.append(" int,\n");
            }
            //如果是Boolean或者String类型
            if (field.getType().getName().contains("String")||field.getType().getName().contains("Boolean")){
                sql.append(str).append(" varchar(").append(maxLength).append("),\n");
            }

            //添加主键
            if (str.equals("身份证号")){
                sql=new StringBuilder(sql.substring(0,sql.length()-2));
                sql.append("primary key,\n");
            }
        }
        sql=new StringBuilder(sql.substring(0,sql.length()));
        sql.append("\n);");
        System.out.println(sql);
        return sql.toString();

    }
    public String InsertInf(Person person){
        Class<?extends Person> clazz=person.getClass();
        Label name=clazz.getAnnotation(Column.class).label();
        StringBuilder sql=new StringBuilder("insert into "+name.value()+"values(");
        Field[] fields=clazz.getDeclaredFields();

        for (int i = 0; i <fields.length ; i++) {
            Field field=fields[i];
            String value=field.getAnnotation(Column.class).label().value();
            System.out.println("请输入"+value+":");
            String str=input.next();

            //根据注解@Column的配置进行校验
            if(!isTrue(field,str)){
                i--;
                continue;
            }
            //判断性别输入是否正确
            if(value.equals("性别")){
                if(!str.equals("男")&&str.equals("女")){
                    System.out.println("您输入的性别不符合规范请重新输入！");
                    i--;
                    continue;
                }
            }
            //判断是否结婚输入正确
            if (value.equals("是否结婚")) {
                if (value.equals("是") && !str.equals("否")) {
                    System.out.println("您输入的结婚状况不符合规范请重新输入！");
                    i--;
                    continue;
                }
            }
            if(!isContainNum(str)){
                sql.append("'").append(str).append(" ").append(",");
            }else {
                sql.append(str).append(",");
            }

        }
        sql=new StringBuilder(sql.substring(0,sql.length()-2));
        sql.append(");");
        System.out.println(sql);
        return sql.toString();
    }
    public String deleteInf(Person person){
        Class<?extends Person>clazz=person.getClass();
        Label name=clazz.getAnnotation(Column.class).label();
        StringBuilder sql=new StringBuilder("delete from "+name.value()+" where ");
        Field[] fields=clazz.getDeclaredFields();
        String value=fields[0].getAnnotation(Column.class).label().value();
        System.out.println("请输入你要删除的"+value+":");
        String str=input.next();
        if(!isContainNum(str)){
            sql.append(value).append("=").append("'").append(str).append("'");
        }else
            sql.append(value).append(str).append(";");
        System.out.println(sql);
        return sql.toString();
    }
    //更新操作
    public String updateInf(Person person) {
        Class<? extends Person> clazz = person.getClass();
        Label tableName = clazz.getAnnotation(Column.class).label();
        String sql = "update " + tableName.value() + " set ";
        Field[] fields = clazz.getDeclaredFields();
        String[] values = new String[fields.length];

        System.out.println("请输入要修改的姓名:");
        String str2 = input.nextLine();
        System.out.println("请选择您要修改的属性:");
        for (int i = 0; i < values.length; i++) {
            values[i] = fields[i].getAnnotation(Column.class).label().value();
            System.out.println((i + 1) + "--" + values[i]);
        }
        String choice = input.nextLine();
        int choiceNum = Integer.parseInt(choice);

        String str1;
        while (true) {
            System.out.println("请输入修改后的值:");
            str1 = input.nextLine();
            if (isTrue(fields[choiceNum - 1], str1)) {
                break;
            }
        }

        switch (choiceNum) {
            case 1:
                if (!isContainNum(str1)) {
                    sql += values[0] + " = " + "\"" + str1 + "\"";
                } else {
                    sql += values[0] + " = " + str1;
                }
                break;
            case 2:
                if (!isContainNum(str1)) {
                    sql += values[1] + " = " + "\"" + str1 + "\"";
                } else {
                    sql += values[1] + " = " + str1;
                }
                break;
            case 3:
                if (!isContainNum(str1)) {
                    sql += values[2] + " = " + "\"" + str1 + "\"";
                } else {
                    sql += values[2] + " = " + str1;
                }
                break;
            case 4:
                if (!isContainNum(str1)) {
                    sql += values[3] + " = " + "\"" + str1 + "\"";
                } else {
                    sql += values[3] + " = " + str1;
                }
                break;
            case 5:
                if (!isContainNum(str1)) {
                    sql += values[4] + " = " + "\"" + str1 + "\"";
                } else {
                    sql += values[4] + " = " + str1;
                }
                break;
            default:
                break;
        }

        if (!isContainNum(str2)) {
            sql += " where " + fields[0].getAnnotation(Column.class).label() + " = " + "\"" + str2 + "\"";
        } else {
            sql += " where " + fields[0].getAnnotation(Column.class).label() + " = " + str2;
        }
        System.out.println(sql);
        return sql;
    }


    //根据注解@Column的配置进行校验
    public boolean isTrue(Field f, String str) {
        String value = f.getAnnotation(Column.class).label().value();
        boolean nullable = f.getAnnotation(Column.class).Nullable();
        int maxLength = f.getAnnotation(Column.class).MaxLength();
        int minLength = f.getAnnotation(Column.class).MinLength();
        int maxValue = f.getAnnotation(Column.class).MaxValue();
        int minValue = f.getAnnotation(Column.class).MinValue();
        if (!nullable && str.isEmpty()) {
            System.out.println("输入的信息不能为空！请重新输入");
            return false;
        }
        if (maxLength == 0) {
            int num;
            try {
                num = Integer.parseInt(str);
            } catch (Exception e) {
                System.out.println("您输入的" + value + "不符合规范！请重新输入");
                return false;
            }

            if (num < minValue || num > maxValue) {
                System.out.println("您输入的" + value + "不符合规范！请重新输入");
                return false;
            }
        } else {
            int len = str.length();
            if (len > maxLength || len < minLength) {
                System.out.println("您输入的" + value + "不符合规范！请重新输入");
                return false;
            }
        }
        return true;
    }

    public static boolean isContainNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > '0' && str.charAt(i) < '9') {
                return true;
            }
        }
        return false;
    }


}
