import java.sql.*;

public class StudentRepository {
    // DB ye bağlanacak olan class oluşturalım...

    private Connection conn;
    private Statement st;
    private PreparedStatement prst;

    //5.Adım, connection oluşturmak için method yazalım.
    private void setConnection() {
        try {
            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/jdbc_db", "postgres", "12345");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //6- statement oluşturmak için methot oluştur...
    private void setStatement() {
        try {
            this.st = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //7- Preparedstatement oluşturmak için methot oluştur..
    private void setPreparedStatement(String sql) {
        try {
            this.prst = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    // 8- ogrenci bilgisini tabloya kaydedicez. bununiçin tablo oluştutalım.

    public void createTable() {
        setConnection();
        setStatement();
        try {
            st.execute("CREATE TABLE IF NOT EXISTS t_student(" +
                    "id SERIAL UNIQUE," +
                    "name VARCHAR(50) NOT NULL CHECK(LENGTH(name)>0)," +//empty ""
                    "lastname VARCHAR(50) NOT NULL CHECK(LENGTH(lastname)>0)," +
                    "city VARCHAR(50) NOT NULL CHECK(LENGTH(city)>0)," +
                    "age INT NOT NULL CHECK(age>0)" +
                    ")");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //12-tabloya kayıt ekleme
    public void save(Student student) {
        String sql="INSERT INTO t_student(name,lastname,city,age) VALUES(?,?,?,?)";
        setConnection();
        setPreparedStatement(sql);
        try {
            prst.setString(1,student.getName());
            prst.setString(2,student.getSurname());
            prst.setString(3,student.getCity());
            prst.setInt(4,student.getAge());
            prst.executeUpdate();
            System.out.println("Saved successfully...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


