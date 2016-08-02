/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ac.pdn.co328.studentSystem.dbimplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ac.pdn.co328.studentSystem.Student;
import lk.ac.pdn.co328.studentSystem.StudentRegister;
import java.sql.*;
/**
 *
 * @author himesh
 */
public class DerbyStudentRegister extends StudentRegister  {

    Connection connection = null;
    public DerbyStudentRegister() throws SQLException
    {
            String dbURL1 = "jdbc:derby:codejava/studentDB;create=true";
            connection = DriverManager.getConnection(dbURL1);
            if (connection != null)
            {


                DatabaseMetaData dbm = connection.getMetaData();

                ResultSet tables = dbm.getTables(null, null, "STUDENTS", null);
                if (tables.next()) {

                }
                else {


                    String SQL_CreateTable = "CREATE TABLE Students(id INT , name VARCHAR(24) , lastname VARCHAR(24))";
                    System.out.println("Creating table addresses...");

                    Statement stmnt = connection.createStatement();
                    stmnt.executeUpdate(SQL_CreateTable);
                    stmnt.close();
                    System.out.println("Table created");

                    System.out.println("Connected to database");
                }
            }
            else
            {
                throw new SQLException("Connection Failed");
            }
    }

    @Override
    public void addStudent(Student st) throws Exception {
        if (connection != null)
        {
            String SQL_AddStudent = "INSERT INTO Students VALUES (" + st.getId() + ",'" + st.getFirstName()  + "'" + ",'" + st.getLastName()  + "')";
            System.out.println ( "Adding the student..." + SQL_AddStudent);

            Statement stmnt = connection.createStatement();
            stmnt.execute(SQL_AddStudent );
            stmnt.close();
            System.out.println("Student Added");

        }
        else
        {
            throw new Exception("Database Connection Error");
        }
    }

    @Override
    public void removeStudent(int regNo) throws Exception {


        if (connection != null)
        {
            String SQL_remove_Student = "DELETE FROM students WHERE id="+regNo;

            Statement stmnt = connection.createStatement();

            stmnt.execute(SQL_remove_Student);
            stmnt.close();


        }
        else
        {
            throw new Exception("Database Connection Error");
        }

    }

    @Override
    public Student findStudent(int regNo) throws Exception {

        if (connection != null)
        {
            String SQL_Find_Student = "SELECT * FROM Students WHERE id= "+regNo;
            System.out.println ( "Adding the student..." + SQL_Find_Student);

            Statement stmnt = connection.createStatement();

            ResultSet res = stmnt.executeQuery(SQL_Find_Student);
            Student s=null;
            while (res.next()) {
                s = new Student(res.getInt("id"), res.getString("name"), res.getString("lastname"));
            }

            stmnt.close();
            return s;

        }
        else
        {
            throw new Exception("Database Connection Error");
        }


    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Student> findStudentsByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> getAllRegistrationNumbers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
