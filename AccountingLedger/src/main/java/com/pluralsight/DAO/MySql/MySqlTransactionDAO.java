package com.pluralsight.DAO.MySql;
import com.pluralsight.DAO.TransactionDAO;
import com.pluralsight.models.Transaction;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTransactionDAO implements TransactionDAO {
    private final Connection connection;

    public MySqlTransactionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO Transactions (customer_id, amount, transaction_date) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transaction.getCustomerId());
            statement.setBigDecimal(2, transaction.getAmount());
            statement.setTimestamp(3, Timestamp.valueOf(transaction.getTransactionDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction getTransaction(int id) {
        String sql = "SELECT * FROM Transactions WHERE transaction_id = ?";
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
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transactions";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                transactions.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        String sql = "UPDATE Transactions SET customer_id = ?, amount = ?, transaction_date = ? WHERE transaction_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transaction.getCustomerId());
            statement.setBigDecimal(2, transaction.getAmount());
            statement.setTimestamp(3, Timestamp.valueOf(transaction.getTransactionDate()));
            statement.setInt(4, transaction.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransaction(int id) {
        String sql = "DELETE FROM Transactions WHERE transaction_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Transaction mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("transaction_id");
        int customerId = resultSet.getInt("customer_id");
        BigDecimal amount = resultSet.getBigDecimal("amount");
        Timestamp transactionDate = resultSet.getTimestamp("transaction_date");
        return new Transaction(id, customerId, amount, transactionDate.toLocalDateTime());
    }
}