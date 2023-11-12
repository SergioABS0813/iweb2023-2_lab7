package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Daos.DepartmentDao;
import com.example.webapphr1_2023.Daos.EmployeeDao;
import com.example.webapphr1_2023.Daos.JobDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DepartmentServlet", urlPatterns = {"/DepartmentServlet"})
public class DepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        RequestDispatcher view;
        EmployeeDao employeeDao = new EmployeeDao();
        JobDao jobDao = new JobDao();
        DepartmentDao departmentDao = new DepartmentDao();

        //LO MOVÍ
        //req.setAttribute("departmentList", departmentDao.lista());
        //view = req.getRequestDispatcher("department/list.jsp");
        //view.forward(req, resp);

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
                req.setAttribute("listaDepartamentos",departmentDao.lista());
                view = req.getRequestDispatcher("department/form_new.jsp");
                view.forward(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        resp.setContentType("text/html");

    }
}
