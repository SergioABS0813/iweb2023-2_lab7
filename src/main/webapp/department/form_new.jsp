<%@page import="java.util.ArrayList" %>
<%@page import="com.example.webapphr1_2023.Beans.Employee" %>
<%@page import="com.example.webapphr1_2023.Beans.Location" %>
<%@page import="com.example.webapphr1_2023.Beans.Department" %>
<jsp:useBean id="listaJefes" type="ArrayList<Employee>" scope="request" />
<jsp:useBean id="listaLocations" type="ArrayList<Location>" scope="request" />
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Nuevo departamento</title>
</head>
<body>
<div class='container'>
    <div class="row justify-content-center">
        <form method="POST" action="DepartmentServlet?action=guardar" class="col-md-6 col-lg-6">
            <h1 class='mb-3'>Nuevo departamento</h1>
            <hr>
            <div class="mb-3">
                <label for="department_name">Dep Name</label>
                <input type="text" class="form-control form-control-sm" name="depName" >
            </div>

            <div class="mb-3">
                <label for="manager_id">Dep Manager</label>
                <select name="manager_id" class="form-select" >
                    <option value="0">-- Sin jefe --</option>
                    <% for(Employee manager: listaJefes){ %>
                    <option value="<%=manager.getEmployeeId()%>"> <%=manager.getFullName()%> </option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="location_id">Dep Location</label>
                <select name="location_id" class="form-select">
                    <option value="0">-- Sin locación --</option>
                    <% for(Location location: listaLocations){ %>
                    <option value="<%=location.getLocationId()%>"> <%=location.getStreetAdd()%> </option>
                    <% } %>
                </select>
            </div>
            <a href="<%= request.getContextPath()%>/DepartmentServlet" class="btn btn-danger">Cancelar</a>
            <input type="submit" value="Guardar" class="btn btn-primary"/>
        </form>
    </div>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>
