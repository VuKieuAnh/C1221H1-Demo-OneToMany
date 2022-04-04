package DAO.student;

import model.Classes;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {

    public Connection getConnection(){
        Connection  connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demoC12",
                    "root",
                    "123456@Abc"
            );
            System.out.println("Thanh cong");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        Student student = new Student("Duong", new Classes(1, "fasjfghs"));
        System.out.println(studentDAO.save(student));
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select student.id as id, student.name as name, student.id_class as id_class, c.name as classname from student join classes c on c.id = student.id_class;");
                ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int id_class = resultSet.getInt("id_class");
                String nameClass = resultSet.getString("classname");
                Classes classes = new Classes(id_class, nameClass);
                Student student = new Student(id, name, classes);
                studentList.add(student);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean save(Student student) {
        try(
                Connection c = getConnection();
                PreparedStatement preparedStatement = c.prepareStatement(
                        "insert into student(name, id_class) VALUE (?, ?);")

        ){
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getClasses().getId());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
return false;

    }
}
