package com.pluralsight.DAO.MySql;

import com.pluralsight.DAO.TransactionTypeDAO;
import com.pluralsight.models.TransactionType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTransactionTypeDAO implements TransactionTypeDAO {
    private final Connection connection;

    public MySqlTransactionTypeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addTransactionType(TransactionType transactionType) {
        String sql = "INSERT INTO TransactionTypes (type) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, transactionType.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TransactionType getTransactionType(int id) {
        String sql = "SELECT * FROM TransactionTypes WHERE transaction_type_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRow(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TransactionType> getAllTransactionTypes() {
        List<TransactionType> transactionTypes = new ArrayList<>();
        String sql = "SELECT * FROM TransactionTypes";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                transactionTypes.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionTypes;
    }

    @Override
    public void updateTransactionType(TransactionType transactionType) {
        String sql = "UPDATE TransactionTypes SET type = ? WHERE transaction_type_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, transactionType.getType());
            statement.setInt(2, transactionType.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransactionType(int id) {
        String sql = "DELETE FROM TransactionTypes WHERE transaction_type_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TransactionType mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("transaction_type_id");
        String type = resultSet.getString("type");
        return new TransactionType(id, type);
    }
}