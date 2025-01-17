package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Job;
import com.example.webapphr1_2023.Beans.Location;
import com.example.webapphr1_2023.Daos.DepartmentDao;
import com.example.webapphr1_2023.Daos.EmployeeDao;
import com.example.webapphr1_2023.Daos.JobDao;
import com.example.webapphr1_2023.Daos.LocationDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "DepartmentServlet", urlPatterns = {"/DepartmentServlet"})
public class DepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        RequestDispatcher view;
        EmployeeDao employeeDao = new EmployeeDao();
        LocationDao locationDao = new LocationDao();
        DepartmentDao departmentDao = new DepartmentDao();


        // recibimos el parámetro action
        String action = req.getParameter("action") == null ? "departmentList" : req.getParameter("action");


        switch (action){
            case "departmentList":
                req.setAttribute("departmentList", departmentDao.lista());
                view = req.getRequestDispatcher("department/list.jsp");
                view.forward(req, resp);
                break;

            case "formCrear":
                req.setAttribute("listaJefes",employeeDao.listarEmpleados());
                req.setAttribute("listaLocaciones",locationDao.obtenerListaLocations());
                view = req.getRequestDispatcher("department/form_new.jsp");
                view.forward(req, resp);
                break;

            case "editar":
                if (req.getParameter("id") != null) {
                    String departmentIdString = req.getParameter("id");
                    int departmentId = 0;
                    try {
                        departmentId = Integer.parseInt(departmentIdString);
                    } catch (NumberFormatException ex) {
                        resp.sendRedirect("DepartmentServlet");

                    }

                    Department dep = departmentDao.obtenerDep(departmentId);

                    if (dep != null) {
                        req.setAttribute("departamento", dep);
                        req.setAttribute("listaJefes",employeeDao.listarEmpleados());
                        req.setAttribute("listaLocaciones",locationDao.obtenerListaLocations());
                        view = req.getRequestDispatcher("department/form_edit.jsp");
                        view.forward(req, resp);
                    } else {
                        resp.sendRedirect("DepartmentServlet");
                    }

                } else {
                    resp.sendRedirect("DepartmentServlet");
                }

                break;
            case "borrar":
                if (req.getParameter("id") != null) {
                    String departmentIdString = req.getParameter("id");
                    int departmentId = 0;
                    try {
                        departmentId = Integer.parseInt(departmentIdString);
                    } catch (NumberFormatException ex) {
                        resp.sendRedirect("DepartmentServlet");
                    }

                    Department dep = departmentDao.obtenerDep(departmentId);

                    if (dep != null) {
                        departmentDao.borrarDep(departmentId);
                    }
                }

                resp.sendRedirect("DepartmentServlet");
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String action = req.getParameter("action") == null ? "lista" : req.getParameter("action");

        DepartmentDao departmentDao = new DepartmentDao();

        Department department = new Department();

        switch (action) {
            case "guardar":
                String department_id = req.getParameter("department_id");
                String department_name = req.getParameter("department_name");
                String manager_id = req.getParameter("manager_id");
                String location_id = req.getParameter("location_id");

                department.setDepartmentId(Integer.parseInt(department_id));
                department.setDepartmentName(department_name);

                Employee manager = new Employee();
                manager.setEmployeeId(Integer.parseInt(manager_id));

                department.setManager(manager);

                Location location1 = new Location();
                location1.setLocationId(Integer.parseInt(location_id));

                department.setLocation(location1);


                departmentDao.guardarDep(department);
                resp.sendRedirect("DepartmentServlet");
                break;

            case "actualizar":
                String department_idAc = req.getParameter("department_id");
                String department_nameAc = req.getParameter("department_name");
                String manager_idAc = req.getParameter("manager_id");
                String location_idAc = req.getParameter("location_id");

                department.setDepartmentId(Integer.parseInt(department_idAc));
                department.setDepartmentName(department_nameAc);

                Employee manager1 = new Employee();
                manager1.setEmployeeId(Integer.parseInt(manager_idAc));

                department.setManager(manager1);

                Location location = new Location();
                location.setLocationId(Integer.parseInt(location_idAc));

                department.setLocation(location);

                departmentDao.actualizarDep(department);

                resp.sendRedirect("DepartmentServlet");

                break;
        }

    }
}
