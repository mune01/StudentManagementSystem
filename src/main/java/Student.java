public class Student {
    private int id;
    private String name;
    private String surname;
    private String city;
    private int age;

    public Student(String name, String surname, String city, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
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

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }
}
