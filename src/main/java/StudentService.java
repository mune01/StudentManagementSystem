import java.util.Scanner;

    //3-student ile ilgili metodlar
    //4-DB e bağlanma, DB işlemleri için yeni bir repo class oluşturma

public class StudentService {
    Scanner scan = new Scanner(System.in);
    // repositoey clasındaki methotlar ulaşabilmek için obje oluşturulur
    private StudentRepository repo=new StudentRepository();

    // 9- student için tablo oluşturma
    public void createStudentTable(){
        repo.createTable();
    }
    //11-ogrenci kaydetme
    public void saveStudent (){
        System.out.println("Ogrenci Adi Giriniz:");
        String name=scan.nextLine().trim();
        System.out.println("Ogrenci Soyadi Giriniz:");
        String lastname=scan.nextLine().trim();
        System.out.println("Ogrenci Sehir Giriniz:");
        String city=scan.nextLine().trim();
        System.out.println("Ogrenci Yas Giriniz:");
        int age= scan.nextInt();
       // scan.nextLine();

        Student student=new Student(name,lastname,city,age);
        repo.save(student);
    }

    //13- tum ogrencileri listleleme
    public void getAllStudent() {
        repo.finAll ();

    }
    // 15- tablodan kayıt silmek için methot oluştur
    public void deleteStudent(int id) {
        repo.delete(id);
    }

    // 17- id si verilen ögrenciyi bulma
    public Student getStudentByID (int id){
        Student student= repo.findByID(id);
        return student;
    }
    //21- id si verilen ögrenciyi görüntüleme;
    public void displayStudent(int id) {
        Student student=getStudentByID(id);
        if (student==null){
            System.out.println("Student does not exist by id:"+id);
        }else{
            System.out.println(student);
        }

    }


    //19- ID si verilen ogrenciyi güncelleme
    public void updateStudent (int id){
        // kulanııcının girmiş olduğu id ile mevcut id var mı
        Student foundStudent= getStudentByID(id);
        if(foundStudent==null){
            System.out.println("Student Does not Exist by id: " + id);
        }else{
            System.out.println("Ogrenci Adi Giriniz:");
            String name=scan.nextLine().trim();
            System.out.println("Ogrenci Soyadi Giriniz:");
            String lastname=scan.nextLine().trim();
            System.out.println("Ogrenci Sehir Giriniz:");
            String city=scan.nextLine().trim();
            System.out.println("Ogrenci Yas Giriniz:");
            int age= scan.nextInt();
            //scan.nextLine();
            // bulunan öğrencinin özelliklerini güncelleyeceğiz
            foundStudent.setName(name);
            foundStudent.setSurname(lastname);
            foundStudent.setCity(city);
            foundStudent.setAge(age);

            repo.update(foundStudent);
        }

    }


}
