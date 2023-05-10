import java.util.Scanner;

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
        String surName=scan.nextLine().trim();
        System.out.println("Ogrenci Sehir Giriniz:");
        String city=scan.nextLine().trim();
        System.out.println("Ogrenci Yas Giriniz:");
        int age= scan.nextInt();
        scan.nextLine();

        Student student=new Student(name,surName,city,age);
        repo.save(student);
    }
}
