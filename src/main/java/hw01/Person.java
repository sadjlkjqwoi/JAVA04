package hw01;
@Column(label = @Label("Person"))
public class Person {
    @Column(label = @Label("姓名"))
    String name;        //姓名
    @Column(label = @Label("性别"))
    String sex;         //性别
    @Column(label = @Label("年龄"))
    Integer age;        //年龄
    @Column(label = @Label("身份证号"))
    String idNo;        //身份证号
    @Column(label = @Label("是否已婚"))
    Boolean isMerries;      //是否已婚

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Boolean getMerries() {
        return isMerries;
    }

    public void setMerries(Boolean merries) {
        isMerries = merries;
    }

}
