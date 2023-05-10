import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        start();

    }

    private static void start() {
        Scanner scan = new Scanner(System.in);
        //10-servis ve tablo
        StudentService service =new StudentService();
        service.createStudentTable();
        int select;
        int id;

        do {
            System.out.println("===============StudentManagementSystem=============");
            System.out.println("1-Ogrenci Ekle");
            System.out.println("2- Ogrenci Listesi");
            System.out.println("3-Ogrenci Guncelle");
            System.out.println("4-Ogrenci Sil");
            System.out.println("0- Cikis");
            System.out.println("Lutfen bir islem Seciniz: ");
            select = scan.nextInt();
            scan.nextLine();

            switch (select) {
                case 1:
                    service.saveStudent();
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Ogrenci ID'si Giriniz...");
                    id=getId(scan);
                    break;
                case 4:
                    System.out.println("Silmek istediğiniz ogrencinin ID'sini giriniz..");
                    id=getId(scan);
                    break;
                case 0:
                    System.out.println("iyi günler dileriz...");
                    break;
                default:
                    System.out.println("hatali giriş taptınız, lutfen kontrol ediniz...");
                    break;
            }

        } while (select != 0);
    }

        private static int getId(Scanner scan){
            int id=scan.nextInt();
            scan.nextLine();
            return id;
        }
}
