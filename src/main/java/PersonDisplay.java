import java.lang.reflect.Field;

/**
 * 负责显示人员信息，显示时的属性名称必须为注解@Label所标注的中文名称
 */
public class PersonDisplay implements PersonAction{

    @Override
    public Person process(Person person) throws IllegalAccessException {
        Class clazz=person.getClass();
        Field []fields=clazz.getDeclaredFields();
        for (Field field:fields) {
            //获取Label注解的value值
            if (field.isAnnotationPresent(Label.class)) {
                String value = field.getAnnotation(Label.class).value();
                System.out.println(value + ":");
            }
            //获取属性的值
            Object obj=field.get(person);
            System.out.println(obj);
        }

        return person;
    }
}
