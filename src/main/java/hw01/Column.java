package hw01;

import java.awt.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.TYPE})
public @interface Column {
    Label label();//  表示类的属性的显示名称
    boolean Nullable() default  false;  //Nullable 表示是否允许属性值为空
    int MaxLength() default  100;   //MaxLength 表示文本属性的最大长度
    int MinLength() default 0;  //MinLength 表示文本属性的最小长度
    int MaxValue() default 999999999;   //MaxValue 表示最大值
    int MinValue() default 0; //MinValue 表示最小值

}
