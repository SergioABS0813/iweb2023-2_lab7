package com.example.webapphr1_2023.Daos;


import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Job;
import com.example.webapphr1_2023.Beans.Location;

import java.sql.*;
import java.util.ArrayList;

public class DepartmentDao extends DaoBase {

    public ArrayList<Department> lista() {

        ArrayList<Department> list = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM departments d\n" +
                     "left join employees m on d.manager_id = m.employee_id \n" +
                     "left join locations l on d.location_id = l.location_id\n")) {

            while (rs.next()) {
                //Department department = new Department();
                //department.setDepartmentId(rs.getInt(1));
                //department.setDepartmentName(rs.getString(2));
                Department department = fetchDepartmentData(rs);
                list.add(department);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return list;
    }

    public Department obtenerDep(int departmentId) {

        Department department = null;

        String sql = "SELECT * FROM departments d\n" +
                "left join employees m on d.manager_id = m.employee_id \n" +
                "left join locations l on d.location_id = l.location_id\n" +
                "where d.department_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    department = fetchDepartmentData(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return department;
    }
    public void guardarDep(Department department){

        String sql = "INSERT INTO departments (department_id, department_name, manager_id,location_id)"
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setDepartmentData(department, pstmt);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarDep(Department department) {

        String sql = "UPDATE departments "
                + "SET department_name = ?, "
                + "manager_id = ?, "
                + "location_id = ? "
                + "WHERE department_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setDepartmentData(department, pstmt);
            pstmt.setInt(4, department.getDepartmentId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void borrarDep(int departmentId) {

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM employees WHERE employee_id = ?")) {
            pstmt.setInt(1, departmentId);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Department fetchDepartmentData(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(rs.getInt(1));
        department.setDepartmentName(rs.getString(2));


        Employee manager = null;
        if(rs.getInt("m.employee_id") != 0){
            manager = new Employee();
            manager.setEmployeeId(rs.getInt("m.employee_id"));
            manager.setFirstName(rs.getString("m.first_name"));
            manager.setLastName(rs.getString("m.last_name"));
            department.setManager(manager);
        }

        Location location = new Location();
        location.setLocationId(rs.getInt("d.location_id"));
        location.setStreetAdd(rs.getString("street_address"));
        department.setLocation(location);

        return department;
    }

    private void setDepartmentData(Department department, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, department.getDepartmentName());

        if (department.getManager().getEmployeeId() == 0) {
            pstmt.setNull(2, Types.INTEGER);
        } else {
            pstmt.setInt(2, department.getManager().getEmployeeId());
        }

        if (department.getLocation().getLocationId() == 0) {
            pstmt.setNull(3, Types.INTEGER);
        } else {
            pstmt.setInt(3, department.getLocation().getLocationId());
        }
    }
}