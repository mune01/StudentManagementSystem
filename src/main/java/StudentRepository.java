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
        String sql = "INSERT INTO t_student(name,lastname,city,age) VALUES(?,?,?,?)";
        setConnection();
        setPreparedStatement(sql);
        try {
            prst.setString(1, student.getName());
            prst.setString(2, student.getLastname());
            prst.setString(3, student.getCity());
            prst.setInt(4, student.getAge());
            prst.executeUpdate();
            System.out.println("Saved successfully...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 14- tablodaki kayıtları tüm bilgileriyle getirip yazdırma
    public void finAll() {
        setConnection();
        setStatement();
        String query = "Select * from t_student";
        System.out.println("========== All Students =================");
        try {
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                System.out.print("id: " + resultSet.getInt("id"));
                System.out.print("    -name: " + resultSet.getString("name"));
                System.out.print("    -lastname: " + resultSet.getString("lastname"));
                System.out.print("    -city: " + resultSet.getString("city"));
                System.out.print("    -age: " + resultSet.getInt("age"));
                System.out.println();
            }
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

    //16-tablodan idsi verilen kaydı silme
    public void delete(int id) {
        setConnection();
//        String query="DELETE FROM t_student WHERE id="+id;
//        st.executeUpdate(query);
        String query = "DELETE FROM t_student WHERE id=?";
        setPreparedStatement(query);
        try {
            prst.setInt(1, id);
            int deleted = prst.executeUpdate();
            //kayıt bulunursa deleted=1 olur
            if (deleted > 0) {
                System.out.println("Student is deleted successfully by id:" + id);
            } else {//böyle bir kayıt yoksa deleted=0 olur
                System.out.println("Student could not found by id:" + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    // 18- ID si verilen kaydı tablodan getirme.
    public Student findByID(int id) {
        Student student = null;
        setConnection();
        String query = "Select * from t_student where id=?";
        setPreparedStatement(query);
        try {
            prst.setInt(1, id);
            ResultSet resultSet = st.executeQuery(query);

            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("lastname"));
                student.setCity(resultSet.getString("city"));
                student.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return student;
    }

    // 20- tablodaki id si verilen kaydı güncelleme
    public void update(Student foundStudent) {
        setConnection();
        String query = "UPDATE t_student SET name=?, lastname=?, city=?, age=?";
        setPreparedStatement(query);
        try {
            prst.setString(1, foundStudent.getName());
            prst.setString(2,foundStudent.getLastname());
            prst.setString(3,foundStudent.getCity());
            prst.setInt(4,foundStudent.getAge());
            prst.executeUpdate();

            if(prst.executeUpdate()>0) {
                System.out.println("updated successfully...");
            }

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}





