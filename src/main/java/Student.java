public class Student {
    private int id;
    private String name;
    private String lastname;
    private String city;
    private int age;

    public Student(String name, String lastname, String city, int age) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.age = age;
    }

    public Student() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String lastname) {
        this.lastname = lastname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
