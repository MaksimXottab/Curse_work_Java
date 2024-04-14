package by.maksim.curse_work.servlet;

import by.maksim.curse_work.model.Device;
import by.maksim.curse_work.model.Employee;
import by.maksim.curse_work.service.DeviceService;
import by.maksim.curse_work.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/security_tech")
public class SecurityTechServlet extends HttpServlet {

    private final DeviceService deviceService;
    private final EmployeeService employeeService;

    public SecurityTechServlet() {
        deviceService = new DeviceService();
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/data/menu.jsp").forward(request, response);
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("action");

        if (param == null) {
            request.getRequestDispatcher("/data/error.jsp").forward(request, response);
            return;
        }

        switch (param) {
            case "add_device" -> request.getRequestDispatcher("/data/add_device.jsp").forward(request, response);
            case "change_device" -> request.getRequestDispatcher("/data/change_device.jsp").forward(request, response);
            case "get_device" -> request.getRequestDispatcher("/data/get_device.jsp").forward(request, response);
            case "get_list_device" -> request.getRequestDispatcher("/data/get_list_device.jsp").forward(request, response);
            case "switch_device" -> request.getRequestDispatcher("/data/switch_device.jsp").forward(request, response);
            case "delete_device" -> request.getRequestDispatcher("/data/delete_device.jsp").forward(request, response);

            case "add_employee" -> request.getRequestDispatcher("/data/add_employee.jsp").forward(request, response);
            case "change_employee" -> request.getRequestDispatcher("/data/change_employee.jsp").forward(request, response);
            case "get_employee" -> request.getRequestDispatcher("/data/get_employee.jsp").forward(request, response);
            case "get_list_employee" -> request.getRequestDispatcher("/data/get_list_employee.jsp").forward(request, response);
            case "switch_employee" -> request.getRequestDispatcher("/data/switch_employee.jsp").forward(request, response);
            case "delete_employee" -> request.getRequestDispatcher("/data/delete_employee.jsp").forward(request, response);

            case "addDeviceForm" -> {
                try {
                    Device device = deviceService.create(request);
                    request.setAttribute("devices", List.of(device));
                    request.setAttribute("titleText", "add_device");
                    request.getRequestDispatcher("/data/display_device.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("errorMessage", exception.getMessage());
                    request.getRequestDispatcher("/data/add_device.jsp").forward(request, response);
                }
            }

            case "changeDeviceForm" -> {
                try {
                    Device device = deviceService.change(request);
                    request.setAttribute("devices", List.of(device));
                    request.setAttribute("titleText", "change_device");
                    request.getRequestDispatcher("/data/display_device.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("errorMessage", exception.getMessage());
                    request.getRequestDispatcher("/data/change_device.jsp").forward(request, response);
                }
            }

            case "getDeviceForm" -> {
                try {
                    Device device = deviceService.getById(Integer.valueOf(request.getParameter("id")));
                    request.setAttribute("devices", List.of(device));
                    request.setAttribute("titleText", "get_device");
                    request.getRequestDispatcher("/data/display_device.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("errorMessage", exception.getMessage());
                    request.getRequestDispatcher("/data/get_device.jsp").forward(request, response);
                }
            }

            case "getListDeviceForm" -> {
                try {
                    List<Device> devices = deviceService.getALL(request);
                    request.setAttribute("devices", devices);
                    request.setAttribute("titleText", "get_list_device");
                    request.getRequestDispatcher("/data/display_device.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("errorMessage", exception.getMessage());
                    request.getRequestDispatcher("/data/get_list_device.jsp").forward(request, response);
                }
            }

            case "switchDeviceForm" -> {
                try {
                    Device device = deviceService.turn(request);
                    request.setAttribute("devices", List.of(device));
                    request.setAttribute("titleText", "switch_device");
                    request.getRequestDispatcher("/data/display_device.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("errorMessage", exception.getMessage());
                    request.getRequestDispatcher("/data/switch_device.jsp").forward(request, response);
                }
            }

            case "deleteDeviceForm" -> {
                try {
                    deviceService.deleteById(Integer.valueOf(request.getParameter("id")));
                    request.setAttribute("titleText", "delete_device");
                    request.getRequestDispatcher("/data/display_device.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("errorMessage", exception.getMessage());
                    request.getRequestDispatcher("/data/delete_device.jsp").forward(request, response);
                }
            }

            case "addEmployeeForm" -> {
                try {
                    Employee employee = employeeService.create(request);
                    request.setAttribute("devices", List.of(employee));
                    request.setAttribute("titleText", "add_employee");
                    request.getRequestDispatcher("/data/display_employee.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("errorMessage", exception.getMessage());
                    request.getRequestDispatcher("/data/add_employee.jsp").forward(request, response);
                }
            }

            case "changeEmployeeForm" -> {
                try {
                    Employee employee = employeeService.change(request);
                    request.setAttribute("devices", List.of(employee));
                    request.setAttribute("titleText", "change_employee");
                    request.getRequestDispatcher("/data/display_employee.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("errorMessage", exception.getMessage());
                    request.getRequestDispatcher("/data/change_employee.jsp").forward(request, response);
                }
            }

            case "getEmployeeForm" -> {
                try {
                    Employee employee = employeeService.getById(Integer.valueOf(request.getParameter("id")));
                    request.setAttribute("devices", List.of(employee));
                    request.setAttribute("titleText", "get_employee");
                    request.getRequestDispatcher("/data/display_employee.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("errorMessage", exception.getMessage());
                    request.getRequestDispatcher("/data/get_employee.jsp").forward(request, response);
                }
            }

            case "getListEmployeeForm" -> {
                try {
                    List<Employee> employes = employeeService.getALL(request);
                    request.setAttribute("devices", employes);
                    request.setAttribute("titleText", "get_list_employee");
                    request.getRequestDispatcher("/data/display_employee.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("errorMessage", exception.getMessage());
                    request.getRequestDispatcher("/data/get_list_employee.jsp").forward(request, response);
                }
            }

            case "switchEmployeeForm" -> {
                try {
                    Employee employee = employeeService.turn(request);
                    request.setAttribute("devices", List.of(employee));
                    request.setAttribute("titleText", "switch_employee");
                    request.getRequestDispatcher("/data/display_employee.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("errorMessage", exception.getMessage());
                    request.getRequestDispatcher("/data/switch_employee.jsp").forward(request, response);
                }
            }

            case "deleteEmployeeForm" -> {
                try {
                    employeeService.deleteById(Integer.valueOf(request.getParameter("id")));
                    request.setAttribute("titleText", "delete_employee");
                    request.getRequestDispatcher("/data/display_employee.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("errorMessage", exception.getMessage());
                    request.getRequestDispatcher("/data/delete_employee.jsp").forward(request, response);
                }
            }
            default -> request.getRequestDispatcher("/data/error.jsp").forward(request, response);
        }
    }
}
