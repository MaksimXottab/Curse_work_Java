package by.maksim.curse_work.service;

import by.maksim.curse_work.exception.EntityNotFoundException;
import by.maksim.curse_work.model.Device;
import by.maksim.curse_work.storage.DeviceStorage;
import by.maksim.curse_work.util.DeviceMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeviceService {
    private final DeviceStorage deviceStorage;

    public DeviceService() {
        deviceStorage = new DeviceStorage();
    }

    public Device create(HttpServletRequest request) {
        Device device = DeviceMapper.extractFieldsFromURIQueryAndDecode(request);
        device = deviceStorage.add(device);

        return device;
    }

    public Device change(HttpServletRequest request) {
        Device device = DeviceMapper.extractFieldsFromURIQueryAndDecode(request);
        deviceStorage.update(device);
        return checkIfDeviceExists(device.getId());
    }

    public Device getById(Integer id) {
        return checkIfDeviceExists(id);
    }

    public List<Device> getALL(HttpServletRequest request) {
        Device device = DeviceMapper.extractFieldsFromURIQueryAndDecode(request);
        List<Device> devices = deviceStorage.findAll(device);

        if (devices.isEmpty())
            throw new EntityNotFoundException(("Не найдено ни одного устройства"));

        return devices;
    }

    public Device turn(HttpServletRequest request) {
        Device device = DeviceMapper.extractFieldsFromURIQueryAndDecode(request);
        deviceStorage.turn(device);

        return checkIfDeviceExists(device.getId());
    }
    public void deleteById(Integer id) {
        checkIfDeviceExists(id);
        deviceStorage.deleteById(id);
    }

    private Device checkIfDeviceExists(Integer id) {
        return deviceStorage.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Устройство с id = " + id + " не найдено"));
    }
}
