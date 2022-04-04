package DAO.classes;

import DAO.SingletonConnection;
import model.Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DAO.SingletonConnection.getConnection;

public class ClassDAO implements IClassDAO {

//    public Connection getConnection(){
//        Connection  connection = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/demoC12",
//                    "root",
//                    "123456@Abc"
//            );
//            System.out.println("Thanh cong");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//return connection;
//    }

    public static void main(String[] args) {
        ClassDAO c = new ClassDAO();
        System.out.println(c.findAll());
    }

    @Override
    public List<Classes> findAll() {
        List<Classes> classesList = new ArrayList<>();
        Connection connection = getConnection();
        try (
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM classes");
            )
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id= resultSet.getInt("id");
                String name= resultSet.getString("name");
                Classes classes = new Classes(id, name);
                classesList.add(classes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classesList;
    }

    @Override
    public Classes findById(int id) {
        Classes classes = null;
        try(
                Connection c = getConnection();
                PreparedStatement preparedStatement = c.prepareStatement("SELECT  * FROM  classes where  id =?")
                ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                classes = new Classes(id, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public boolean update(Classes classes) {
        return false;
    }

    @Override
    public boolean save(Classes classes) {
        return false;
    }
}
