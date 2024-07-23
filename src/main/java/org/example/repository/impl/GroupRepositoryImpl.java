package org.example.repository.impl;

import org.example.db.DBConnector;
import org.example.db.DBConnectorImpl;
import org.example.entity.Group;
import org.example.repository.Interfaces.GroupRepository;

import java.sql.*;
import java.util.Optional;

public class GroupRepositoryImpl implements GroupRepository {
    private static final String INSERT = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String UPDATE = "UPDATE groups SET group_name = ? WHERE group_id = ?";
    private static final String DELETE = "DELETE FROM groups WHERE group_id = ?";
    private static final String FIND_BY_ID = "SELECT group_id, group_name FROM groups WHERE group_id = ? LIMIT 1";
    private static final String EXIST_BY_ID = "SELECT exists (SELECT 1 FROM groups WHERE group_id = ? LIMIT 1)";
    private static GroupRepository instance;
    private final DBConnector connectionManager = DBConnectorImpl.getInstance();

    GroupRepositoryImpl() {
    }

    public static synchronized GroupRepository getInstance() {
        if (instance == null) {
            instance = new GroupRepositoryImpl();
        }
        return instance;
    }

    private static Group createGroup(ResultSet resultSet) throws SQLException {
        Group group;
        group = new Group(resultSet.getLong("group_id"),
                resultSet.getString("group_name"));
        return group;
    }

    @Override
    public Group insert(Group group) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, group.getName());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                group = new Group(
                        resultSet.getLong("group_id"),
                        group.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return group;
    }

    @Override
    public void update(Group group) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, group.getName());
            preparedStatement.setLong(2, group.getId());

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
    public Optional<Group> findById(Long id) {
        Group group = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                group = createGroup(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(group);
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
