package ua.klunniy.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.klunniy.spring.models.Book;
import ua.klunniy.spring.models.BookRowMapper;
import ua.klunniy.spring.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Serhii Klunniy
 */
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    private static final String URL = "jdbc:postgresql://localhost:5432/zero_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "test";

    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        final String SQL = "SELECT * from Person";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("address")
                );
                people.add(person);
            }
            return people;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //return PeopleStorage.getPeople();
    }

    public Person show(int id) {
        String SQL = "SELECT * FROM Person where id=?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("address"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //return PeopleStorage.getPeople().stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public Optional<Person> showByEmail(String email) {
        return jdbcTemplate.query("SELECT * from Person where email=?", new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Optional<Person> showByAddress(String address) {
        return jdbcTemplate.query("SELECT * from Person where address=?", new Object[]{address},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person) {
//        String SQL = "INSERT into Person(name, surname, age, email) VALUES ('" + person.getName() + "','" +
//        person.getSurname() +
//                "'," + person.getAge() + ",'" + person.getEmail() + "')";
        String SQL = "INSERT into Person(name, surname, age, email, address) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setInt(3, person.getAge());
            ps.setString(4, person.getEmail());
            ps.setString(5, person.getAddress());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                // Получение сгенерированных ключей
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        person.setId(id);
                        System.out.println("Запись успешно вставлена. ID: " + id);
                    } else {
                        System.out.println("Не удалось получить ID записи.");
                    }
                }
            } else {
                System.out.println("Не удалось вставить запись.");
            }
            //PeopleStorage.save(person);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void save(Person person) {
//        final String sql = "INSERT INTO PERSON (name, surname, age, email) values (?, ?, ?, ?) RETURNING id";
//        try (Connection connection = dbConnector.getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setString(1, person.getName());
//            ps.setString(2, person.getSurname());
//            ps.setInt(3, person.getAge());
//            ps.setString(4, person.getEmail());
//
//            ResultSet resultSet = ps.executeQuery();
//            if (resultSet.next()) {
//                person.setId(resultSet.getInt("id"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void update(int id, Person updatePerson) {
        String SQL = "UPDATE Person SET name=?, surname=?, age=?, email=?, address=? where id=?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, updatePerson.getName());
            ps.setString(2, updatePerson.getSurname());
            ps.setInt(3, updatePerson.getAge());
            ps.setString(4, updatePerson.getEmail());
            ps.setString(5, updatePerson.getAddress());
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String SQL = "DELETE  from Person where id=?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
