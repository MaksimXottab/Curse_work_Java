package by.maksim.curse_work.util;

import by.maksim.curse_work.model.Device;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DeviceMapper {
    public static Device extractFieldsFromURIQueryAndDecode(HttpServletRequest req) {
        Device device = new Device();

        String id = req.getParameter("id"),
               number = req.getParameter("number"),
               designation = req.getParameter("designation"),
               condition = req.getParameter("condition");

        if (id != null && !id.isBlank())
            device.setId(Integer.valueOf(decode(id)));

        if (number != null && !number.isBlank())
            device.setNumber(Integer.valueOf(decode(number)));

        if (designation != null && !designation.isBlank())
            device.setDesignation(decode(designation));

        if (condition != null && !condition.isBlank())
            device.setCondition(decode(condition));

        return device;
    }

    private static Device mapResultSetToDevice(ResultSet resultSet) throws SQLException {
        Device device = new Device();

        device.setId(resultSet.getInt("id"));
        device.setDesignation(resultSet.getString("designation"));
        device.setNumber(resultSet.getInt("number"));
        device.setCondition(resultSet.getString("condition"));

        return device;
    }

    public static List<Device> extractFieldsFromResultSetList(ResultSet resultSet) {
        List<Device> devices = new ArrayList<>();
        try {
            while (resultSet.next())
                devices.add(mapResultSetDevice(resultSet));

            return devices;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static Optional<Device> extractFieldsFromResultSet(ResultSet resultSet) {
        try {
            if (resultSet.next())
                return Optional.of(mapResultSetDevice(resultSet));

            return Optional.empty();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    private static Device mapResultSetDevice(ResultSet resultSet) throws SQLException {
        Device device = new Device();

        device.setId(resultSet.getInt("id"));
        device.setDesignation(resultSet.getString("designation"));
        device.setNumber(resultSet.getInt("number"));
        device.setCondition(resultSet.getString("condition"));

        return device;
    }

    private static String decode(String encoded) {
        return URLDecoder.decode(encoded, StandardCharsets.UTF_8);
    }
}
