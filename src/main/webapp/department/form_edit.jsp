<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.example.webapphr1_2023.Beans.Employee" %>
<%@page import="com.example.webapphr1_2023.Beans.Location" %>
<%@page import="com.example.webapphr1_2023.Beans.Department" %>
<jsp:useBean id="listaJefes" type="ArrayList<Employee>" scope="request" />
<jsp:useBean id="listaLocaciones" type="ArrayList<Location>" scope="request" />
<jsp:useBean id="departamento" type="Department" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Editar departamento</title>
</head>
<body>
<div class='container mb-4'>
    <div class="row justify-content-center">
        <h1 class='mb-3'>Editar departamento</h1>
        <hr>
        <form method="POST" action="<%=request.getContextPath()%>/DepartmentServlet?action=actualizar" class="col-md-6 col-lg-6">
            <input type="hidden" name="department_id" value="<%= departamento.getDepartmentId()%>"/>
            <div class="mb-3">
                <label for="department_name">Dep Name</label>
                <input type="text" class="form-control form-control-sm" name="department_name"
                       value="<%= departamento.getDepartmentName() == null ? "" : departamento.getDepartmentName()%>">
            </div>

            <div class="mb-3">
                <label for="manager_id">Dep Manager</label>
                <select name="manager_id" class="form-select">
                    <option value="0">-- Sin jefe --</option>
                    <% for(Employee manager: listaJefes){ %>
                    <option value="<%=manager.getEmployeeId()%>" <%=manager.getEmployeeId() == departamento.getManager().getEmployeeId()?"selected":""%>  > <%=manager.getFullName()%> </option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="location_id">Dep Location</label>
                <select name="location_id" class="form-select">
                    <option value="0">-- Sin locación --</option>
                    <% for(Location location: listaLocaciones){ %>
                    <option value="<%=location.getLocationId()%>" <%=location.getLocationId() == departamento.getLocation().getLocationId()?"selected":""%>   > <%=location.getStreetAdd()%> </option>
                    <% } %>
                </select>
            </div>
            <a href="<%= request.getContextPath()%>/DepartmentServlet" class="btn btn-danger">Cancelar</a>
            <button type="submit" class="btn btn-primary">Actualizar</button>

        </form>
    </div>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>
