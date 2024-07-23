package org.example.repository.impl;

import org.example.db.DBConnector;
import org.example.db.DBConnectorImpl;
import org.example.entity.*;
import org.example.repository.Interfaces.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {
    private static final String INSERT = "INSERT INTO students (student_name, student_surname, group_id) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE students SET student_name = ?, student_surname = ?, group_id = ? WHERE student_id = ?";
    private static final String DELETE = "DELETE FROM students WHERE student_id = ?";
    private static final String FIND_BY_ID = "SELECT student_id, student_name, student_surname, group_id FROM students WHERE student_id = ? LIMIT 1";
    private static final String EXIST_BY_ID = "SELECT exists (SELECT 1 FROM students WHERE student_id = ? LIMIT 1)";
    private static StudentRepository instance;
    private final DBConnector connectionManager = DBConnectorImpl.getInstance();
    private final StudentToOptionalClassRepository studentToOptionalClassRepository = StudentToOptionalClassRepositoryImpl.getInstance();
    private final GroupRepository groupRepository = GroupRepositoryImpl.getInstance();
    private final OptionalClassRepository optionalClassRepository = OptionalClassRepositoryImpl.getInstance();

    private StudentRepositoryImpl() {
    }

    public static synchronized StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepositoryImpl();
        }
        return instance;
    }
    @Override
    public Student insert(Student student) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getSurname());
            if (student.getGroup() == null) {
                preparedStatement.setNull(3, Types.NULL);
            } else {
                preparedStatement.setLong(3, student.getGroup().getId());
            }
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                student = new Student(
                        resultSet.getLong("student_id"),
                        student.getFirstName(),
                        student.getSurname(),
                        student.getGroup(),
                        null
                );
            }
            student.getOptionalClassList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return student;
    }


    @Override
    public void update(Student student) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getSurname());
            if (student.getGroup() == null) {
                preparedStatement.setNull(3, Types.NULL);
            } else {
                preparedStatement.setLong(3, student.getGroup().getId());
            }
            preparedStatement.setLong(4, student.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        boolean deleteResult;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            studentToOptionalClassRepository.deleteById(id);
            preparedStatement.setLong(1, id);
            deleteResult = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleteResult;
    }

    @Override
    public Optional<Student> findById(Long id) {
        Student student = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = createStudent(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(student);
    }


    @Override
    public boolean exitsById(Long id) {
        boolean isExists = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EXIST_BY_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExists = resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExists;
    }

    private Student createStudent(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("student_id");
        Group group = groupRepository.findById(resultSet.getLong("group_id")).orElse(null);

        return new Student(
                userId,
                resultSet.getString("student_name"),
                resultSet.getString("student_surname"),
                group,
                null
        );
    }
}
