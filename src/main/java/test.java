public class test {
    public static void main(String[] args) throws IllegalAccessException {
        Person person=new Person();
        PersonInput personInput=new PersonInput();
        personInput.process(person);
        PersonDisplay personDisplay=new PersonDisplay();
        personDisplay.process(person);
    }
}
