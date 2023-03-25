import java.lang.reflect.Field;
import java.util.Scanner;

/**
 * 负责提示录入人员的相关属性，提示必须是注解@Label所标注的中文名称
 */
public class PersonInput implements PersonAction {


    @Override
    public Person process(Person person) throws IllegalAccessException {
        Scanner input=new Scanner(System.in);
        Class clazz=person.getClass();//获取Class 对象
        Field []fields=clazz.getDeclaredFields();//通过Class对象获取Field对象

        //遍历获取的person对象的所有字段
        for (Field field:fields) {
            Object obj=field.get(person);
            if(obj==null){
                String s=field.getAnnotation(Label.class).value();
                System.out.println("请输入"+s+":");
                String s1=input.next();
                if (field.getType().getName().contains("String")){          //获取的字段是String类型
                    field.set(person,s1);
                } else if (field.getType().getName().contains("Integer")) { //获取的字段或Integer
                    field.set(person,Integer.parseInt(s1));
                } else if (field.getType().getName().contains("Boolean")) { //获取的字段为Boolean
                    field.set(person,Boolean.parseBoolean(s1));
                }
            }
        }
        return person;
    }
}
