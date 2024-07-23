package org.example.repository.impl;

import org.example.db.DBConnector;
import org.example.db.DBConnectorImpl;
import org.example.entity.StudentToOptionalClass;
import org.example.repository.Interfaces.StudentToOptionalClassRepository;

import java.sql.*;
import java.util.Optional;

public class StudentToOptionalClassRepositoryImpl implements StudentToOptionalClassRepository {
    private static final DBConnector DBConnector = DBConnectorImpl.getInstance();
    private static final String INSERT = "INSERT INTO students_optionalclasses (student_id, optionalclass_id) VALUES (?,?)";
    private static final String UPDATE = "UPDATE students_optionalclasses SET student_id = ?, optionalclass_id = ? WHERE students_optionalclasses_id = ?";
    private static final String DELETE = "DELETE FROM students_optionalclasses WHERE students_optionalclasses_id = ?";
    private static final String FIND_BY_ID = "SELECT students_optionalclasses_id, student_id, optionalclass_id FROM students_optionalclasses WHERE students_optionalclasses_id = ? LIMIT 1";
    private static final String EXIST_BY_ID = "SELECT exists (SELECT 1 FROM students_optionalclasses WHERE students_optionalclasses_id = ? LIMIT 1)";
    private static StudentToOptionalClassRepository instance;

    private StudentToOptionalClassRepositoryImpl() {
    }

    public static synchronized StudentToOptionalClassRepository getInstance() {
        if (instance == null) {
            instance = new StudentToOptionalClassRepositoryImpl();
        }
        return instance;
    }

    private static StudentToOptionalClass createStudentToOptionalClass(ResultSet resultSet) throws SQLException {
        StudentToOptionalClass studentToOptionalClass;
        studentToOptionalClass = new StudentToOptionalClass(
                resultSet.getLong("students_optionalclasses_id"),
                resultSet.getLong("student_id"),
                resultSet.getLong("optionalclass_id")
        );
        return studentToOptionalClass;
    }

    @Override
    public StudentToOptionalClass insert(StudentToOptionalClass studentToOptionalClass) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, studentToOptionalClass.getStudentId());
            preparedStatement.setLong(2, studentToOptionalClass.getOptionalClassId());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                studentToOptionalClass = new StudentToOptionalClass(
                        resultSet.getLong("students_optionalclasses_id"),
                        studentToOptionalClass.getStudentId(),
                        studentToOptionalClass.getOptionalClassId()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentToOptionalClass;
    }

    @Override
    public void update(StudentToOptionalClass studentToOptionalClass) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {

            preparedStatement.setLong(1, studentToOptionalClass.getStudentId());
            preparedStatement.setLong(2, studentToOptionalClass.getOptionalClassId());
            preparedStatement.setLong(3, studentToOptionalClass.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        boolean deleteResult;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {

            preparedStatement.setLong(1, id);

            deleteResult = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return deleteResult;
    }

    @Override
    public Optional<StudentToOptionalClass> findById(Long id) {
        StudentToOptionalClass studentToOptionalClass = null;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                studentToOptionalClass = createStudentToOptionalClass(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(studentToOptionalClass);
    }

    @Override
    public boolean exitsById(Long id) {
        boolean isExists = false;
        try (Connection connection = DBConnector.getConnection();
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

}
