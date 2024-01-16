package ua.klunniy.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.klunniy.spring.models.Person;
import ua.klunniy.spring.util.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Serhii Klunniy
 */
@Component
public class PersonDAO {
    private DbConnector dbConnector;

    @Autowired
    public PersonDAO(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public List<Person> index() {
        List<Person> personList = new ArrayList<>();
        final String sql = "select * from person order by person.id";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Person person = new Person(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"));
                personList.add(person);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return personList;
    }

    public Person show(int id) {
        final String sql = "select * from person where id = ?";
        Person person = null;

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                person = new Person(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {
        final String sql = "INSERT INTO PERSON (name, surname, age, email) values (?, ?, ?, ?) RETURNING id";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setInt(3, person.getAge());
            ps.setString(4, person.getEmail());

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                person.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person person) {
        final String sql = "UPDATE PERSON SET name=?, surname=?, age=?, email=? where id=?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setInt(3, person.getAge());
            ps.setString(4, person.getEmail());
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        final String sql = "delete from person where id=?";
        try (Connection connection = dbConnector.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
