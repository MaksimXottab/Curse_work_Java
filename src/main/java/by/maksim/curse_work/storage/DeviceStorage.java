package by.maksim.curse_work.storage;

import by.maksim.curse_work.model.Device;
import by.maksim.curse_work.util.DeviceMapper;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class DeviceStorage {
    public Device add(Device device) {
        try (Connection connection = getNewConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO devices (designation, number, condition) VALUES (?, ?, ?)",
                          Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, device.getDesignation());
            statement.setInt(2, device.getNumber());
            statement.setString(3, device.getCondition());

            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();

            if (key.next()) {
                device.setId(key.getInt(1));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return device;
    }

    public Device update(Device device) {
        StringBuilder query = new StringBuilder("UPDATE devices SET ");
        List<Object> values = new ArrayList<>();

        if (device.getDesignation() != null) {
            query.append("designation = ?, ");
            values.add(device.getDesignation());
        }

        if (device.getNumber() != null) {
            query.append("number = ?, ");
            values.add(device.getNumber());
        }

        if (values.isEmpty()) {
            return device;
        }

        query.delete(query.length() - 2, query.length());

        query.append(" WHERE id = ?");
        values.add(device.getId());

        try (Connection connection = getNewConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return device;
    }

    public Device turn(Device device) {
        try (Connection connection = getNewConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE devices SET condition = ? WHERE id = ?")) {
            statement.setString(1, device.getCondition());
            statement.setInt(2, device.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return device;
    }

    public List<Device> findAll(Device device) {
        StringBuilder query = new StringBuilder("SELECT * FROM devices ");
        List<Object> values = new ArrayList<>();

        boolean whereFound = false;

        if ( device.getDesignation() != null && ! device.getDesignation().isBlank()) {
            query.append(whereFound ? " AND designation = ?" : "WHERE designation = ?");
            values.add( device.getDesignation());
            whereFound = true;
        }

        if ( device.getNumber() != null) {
            query.append(whereFound ? " AND number = ?" : "WHERE number = ?");
            values.add( device.getNumber());
            whereFound = true;
        }

        query.append(whereFound ? " AND condition = ?" : "WHERE condition = ?");
        values.add(device.getCondition());

        try (Connection connection = getNewConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            return new ArrayList<Device>(DeviceMapper.extractFieldsFromResultSetList(resultSet));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<Device> findById(Integer id) {
        Optional<Device> optionalDevice = null;

        try (Connection connection = getNewConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM devices WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            optionalDevice = DeviceMapper.extractFieldsFromResultSet(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return optionalDevice;
    }

    public void deleteById(Integer id) {
        try (Connection connection = getNewConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM devices WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    protected Connection getNewConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    protected DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setPortNumbers(new int[]{5432});
        dataSource.setDatabaseName("curse_work");
        dataSource.setUser("maksim");
        dataSource.setPassword("admin");
        return dataSource;
    }
}

