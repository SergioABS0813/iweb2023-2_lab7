package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Country;
import com.example.webapphr1_2023.Beans.Location;
import com.example.webapphr1_2023.Daos.LocationDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "LocationServlet", urlPatterns = {"/LocationServlet"})
public class LocationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view;
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        LocationDao locationDao = new LocationDao();

        switch (action){

            case "lista":
                request.setAttribute("locationList", locationDao.obtenerListaLocations());
                view = request.getRequestDispatcher("location/list.jsp");
                view.forward(request, response);
                break;
            case "new":
                String vistaNuevoLocation = "location/nuevoLocation.jsp";
                request.getRequestDispatcher(vistaNuevoLocation).forward(request,response);

                break;
            case "editar":

                String idRec = request.getParameter("id");

                Location location = locationDao.buscarPorId(Integer.parseInt(idRec));

                if (location != null){
                    request.setAttribute("location", location); // enviamos el job a la vista
                    request.getRequestDispatcher("location/editLocation.jsp").forward(request,response);

                }else{
                    response.sendRedirect( request.getContextPath() + "/LocationServlet");
                }
                break;

            case "borrar":

                String idRecB = request.getParameter("id"); // recibimos el ID enviado desde el botón
                Location LocationD = locationDao.buscarPorId(Integer.parseInt(idRecB));

                if(LocationD != null){
                    try{
                        locationDao.borrar(Integer.parseInt(idRecB));
                    }catch (SQLException e){
                        System.out.println("Log: exception: " + e.getMessage());
                    }
                    response.sendRedirect( request.getContextPath() + "/LocationServlet"); //lo redirecciono a la página misma
                }else{
                    response.sendRedirect( request.getContextPath() + "/LocationServlet"); //lo redirecciono a la página misma
                }
                break;

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null?"crear":request.getParameter("action");
        LocationDao locationDao = new LocationDao();

        switch (action){

            case "crear":
                String locationId = request.getParameter("locationId");
                String streetAdd = request.getParameter("streetAdd");
                String postalCode = request.getParameter("postalCode");
                String city = request.getParameter("city");
                String stateProvince = request.getParameter("stateProvince");
                String countryId = request.getParameter("countryId");

                // Creamos el LOCATION
                locationDao.crearLocation(Integer.parseInt(locationId),streetAdd,postalCode,city,stateProvince,countryId); //Realizamos los cambios
                response.sendRedirect(request.getContextPath() + "/LocationServlet");
                break;

            case "editar":

                String locationIdEd = request.getParameter("locationId");
                String streetAddEd = request.getParameter("streetAdd");
                String postalCodeEd = request.getParameter("postalCode");
                String cityEd = request.getParameter("city");
                String stateProvinceEd = request.getParameter("stateProvince");
                String countryIdEd = request.getParameter("countryId");

                locationDao.actualizar(Integer.parseInt(locationIdEd), streetAddEd, postalCodeEd, cityEd, stateProvinceEd, countryIdEd);


                response.sendRedirect(request.getContextPath() + "/LocationServlet");

                break;

        }


    }
}