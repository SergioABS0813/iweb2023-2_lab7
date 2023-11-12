<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <title>Crear un nuevo departamento</title>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Crear un nuevo departamento</h1>

    <form method="post" action="<%=request.getContextPath()%>/DepartmentServlet">
        <div class="mb-3">
            <label>Dep ID</label>
            <input type="text" class="form-control" name="depId">
        </div>
        <div class="mb-3">
            <label>Dep Name</label>
            <input type="text" class="form-control" name="depName">
        </div>
        <div class="mb-3">
            <label>Dep Manager</label>
            <input type="text" class="form-control" name="depManager">
        </div>
        <div class="mb-3">
            <label>Dep Location</label>
            <input type="text" class="form-control" name="depLocation">
        </div>
        <a href="<%=request.getContextPath()%>/DepartmentServlet" class="btn btn-danger">Regresar</a>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>

