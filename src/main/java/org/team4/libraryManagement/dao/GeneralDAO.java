package org.team4.libraryManagement.dao;

import org.team4.libraryManagement.ConnectionFactory;
import org.team4.libraryManagement.model.Book;
import org.team4.libraryManagement.model.Student;
import org.team4.libraryManagement.validator.BookValidator;
import org.team4.libraryManagement.validator.StudentValidator;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralDAO<T>
{
    protected static final Logger LOGGER = Logger.getLogger(GeneralDAO.class.getName());
    public Class<T> type;
    public static BookValidator bookValidator;
    public static StudentValidator studentValidator;

    public GeneralDAO(Class<T> classT)
    {
        this.type=classT;
        bookValidator = new BookValidator();
        studentValidator = new StudentValidator();
    }

    public BookValidator getBookValidator() {
        return bookValidator;
    }

    public StudentValidator getStudentValidator() {
        return studentValidator;
    }

    private String deleteQuery(String field)
    {
        return "DELETE FROM " + type.getSimpleName() + " WHERE "  + field + " = ?";
    }

    private String selectAllQuery()
    {
        return "SELECT * FROM " + type.getSimpleName();
    }

    private String selectById()
    {
        return "SELECT * FROM " + type.getSimpleName() + " WHERE uuid = ?";
    }
    private String selectByParameterQuery(String field)
    {
        return "SELECT * FROM " + type.getSimpleName() + " WHERE " + field + " = ?";
    }

    private String insertStudentQuery()
    {   //TODO
        return "INSERT INTO  Student (uuid,firstName,lastName,blackListed,email) VALUES (?,?,?,?,?)";
    }

    private String insertBookQuery()
    {   //TODO
        return "INSERT INTO  Book (uuid,title,author,genre,isbn,borrowedBy,borrowedDate) VALUES (?,?,?,?,?,?,?)";
    }

   private String updateStudentQuery()
   {
       return "UPDATE Student SET firstName = ?, lastName = ?, blackListed = ?, email = ? "  + "WHERE uuid = ?";
   }

    private String updateBookQuery()
    {
        return "UPDATE Book SET title = ?, author = ?, genre = ?, isbn = ?, borrowedBy = ?, borrowedDate = ? "  + "WHERE uuid = ?";
    }

    public int  insertBook(Book book) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {

            insertStatement = dbConnection.prepareStatement(insertBookQuery(), Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1,book.getTitle());
            insertStatement.setString(2,book.getAuthor());
            insertStatement.setString(3,book.getGenre());
            insertStatement.setString(4,book.getIsbn());
            insertStatement.setString(5,book.getBorrowedBy());
            insertStatement.setDate(6, (java.sql.Date) book.getBorrwedDate());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getSimpleName() + "GeneralDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public int  insertStudent(Student student) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStudentQuery(), Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1,student.getUuid());
            insertStatement.setString(2,student.getFirstName());
            insertStatement.setString(3,student.getLastName());
            insertStatement.setBoolean(4,student.isBlacklisted());
            insertStatement.setString(5,student.getEmail());
            insertStatement.executeUpdate();
            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"GeneralDAO:insertStudent " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public void delete(String uuid)
    {
        Connection connection=null;
        PreparedStatement statement=null;

        String query = deleteQuery("uuid");
        try {
            connection = ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            statement.setString(1, uuid);
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName() + "AbstractDAO: delete " + e.getMessage());
        }
        finally {

            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);

        }

    }

    public List<T> selectAll()
    {
        Connection connection= null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query = selectAllQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();
            return createObjects(resultSet);
        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName() + "GeneralDAO: selectAll " + e.getMessage());
        }
        finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);

        }
        return null;
    }

    public T selectById(String uuid)
    {
        Connection connection= null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;


        try {
            connection = ConnectionFactory.getConnection();
            statement=connection.prepareStatement(selectById());
            statement.setString(1,uuid);

            return (T) createObjects(resultSet);

        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName() + "GeneralDAO: selectAll " + e.getMessage());
        }
        finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);

        }
        return null;
    }

    public List<T> selectByParameter(String parameter, String searchBar)
    {
        Connection connection= null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query = selectByParameterQuery(parameter);

        try {
            connection = ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            statement.setString(1,searchBar);

            resultSet = statement.executeQuery();
            return createObjects(resultSet);

        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName() + "GeneralDAO: selectAll " + e.getMessage());
        }
        finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);

        }
        return null;
    }


    public void updateStudent(Student student)
    {
        Connection connection= (Connection) ConnectionFactory.getConnection();
        PreparedStatement statement=null;

        String query = updateStudentQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setBoolean(3, student.isBlacklisted());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getUuid());

            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName() + "GeneralDAO: delete " + e.getMessage());
        }
        finally {

            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);

        }

    }


    public void updateBook(Book book)
    {
        Connection connection=null;
        PreparedStatement statement=null;

        String querry = updateBookQuery()   ;
        try {
            connection = ConnectionFactory.getConnection();
            statement=connection.prepareStatement(querry);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setString(4, book.getIsbn());
            statement.setString(5, book.getBorrowedBy());
            statement.setDate(6, (java.sql.Date) book.getBorrwedDate());
            statement.setString(7, book.getBorrowedBy());
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName() + "AbstractDAO: delete " + e.getMessage());
        }
        finally {

            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);

        }

    }

    private List<T> createObjects(ResultSet resultSet)
    {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        }
        catch(InstantiationException | IllegalAccessException | SQLException | IntrospectionException | InvocationTargetException | NoSuchMethodException e)
        {
            LOGGER.log(Level.WARNING, "GeneralDAO:createObjects " +  e.getMessage());
        }
        return list;
    }


}
