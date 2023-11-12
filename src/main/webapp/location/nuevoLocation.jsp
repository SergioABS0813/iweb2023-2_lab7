<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
              crossorigin="anonymous">
        <title>Crear un nuevo Location</title>
    </head>
    <body>
        <div class='container'>
            <h1 class='mb-3'>Crear un nuevo Location</h1>

            <form method="post" action="<%=request.getContextPath()%>/LocationServlet">
                <div class="mb-3">
                    <label for="locationId">Location ID</label>
                    <input type="text" class="form-control" name = "locationId">
                </div>
                <div class="mb-3">
                    <label for="streetAdd">Street Address </label>
                    <input type="text" class="form-control" name = "streetAdd">
                </div>
                <div class="mb-3">
                    <label for="postalCode">Postal Code </label>
                    <input type="text" class="form-control" name = "postalCode">
                </div>
                <div class="mb-3">
                    <label for="city">City </label>
                    <input type="text" class="form-control" name = "city">
                </div>
                <div class="mb-3">
                    <label for="stateProvince">State Province </label>
                    <input type="text" class="form-control" name = "stateProvince">
                </div>
                <div class="mb-3">
                    <label for="countryId">Country ID </label>
                    <input type="text" class="form-control" name = "countryId">
                </div>

                <a href="<%=request.getContextPath()%>/LocationServlet" class="btn btn-danger">Regresar</a>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>



        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
                crossorigin="anonymous"></script>
    </body>
</html>
