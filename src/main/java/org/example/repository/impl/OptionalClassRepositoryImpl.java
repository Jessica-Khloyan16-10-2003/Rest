package org.example.repository.impl;

import org.example.db.DBConnector;
import org.example.db.DBConnectorImpl;
import org.example.entity.OptionalClass;
import org.example.repository.Interfaces.OptionalClassRepository;

import java.sql.*;
import java.util.Optional;

public class OptionalClassRepositoryImpl implements OptionalClassRepository {
    private static final String INSERT = "INSERT INTO optionalclasses (optionalclass_name) VALUES (?)";
    private static final String UPDATE = "UPDATE optionalclasses SET optionalclass_name = ? WHERE optionalclass_id = ?";
    private static final String DELETE = "DELETE FROM optionalclasses WHERE optionalclass_id = ?";
    private static final String FIND_BY_ID = "SELECT optionalclass_id, optionalclass_name FROM optionalclasses WHERE optionalclass_id = ? LIMIT 1";
    private static final String EXIST_BY_ID = "SELECT exists (SELECT 1 FROM optionalclasses WHERE optionalclass_id = ? LIMIT 1)";
    private static OptionalClassRepository instance;
    private final DBConnector connectionManager = DBConnectorImpl.getInstance();

    private OptionalClassRepositoryImpl() {
    }

    public static synchronized OptionalClassRepository getInstance() {
        if (instance == null) {
            instance = new OptionalClassRepositoryImpl();
        }
        return instance;
    }

    private static OptionalClass createOptionalClass(ResultSet resultSet) throws SQLException {
        OptionalClass optionalClass;
        optionalClass = new OptionalClass(
                resultSet.getLong("optionalclass_id"),
                resultSet.getString("optionalclass_name"),
                null);
        return optionalClass;
    }

    @Override
    public OptionalClass insert(OptionalClass optionalClass) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, optionalClass.getName());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                optionalClass = new OptionalClass(
                        resultSet.getLong("optionalclass_id"),
                        optionalClass.getName(),
                        null
                );
                optionalClass.getStudentListList();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalClass;
    }

    @Override
    public void update(OptionalClass optionalClass) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {

            preparedStatement.setString(1, optionalClass.getName());
            preparedStatement.setLong(2, optionalClass.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        boolean deleteResult;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {

            preparedStatement.setLong(1, id);

            deleteResult = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return deleteResult;
    }

    @Override
    public Optional<OptionalClass> findById(Long id) {
        OptionalClass optionalClass = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optionalClass = createOptionalClass(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(optionalClass);
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
}
