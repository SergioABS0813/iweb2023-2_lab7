package com.example.webapphr1_2023.Daos;

import com.example.webapphr1_2023.Beans.*;

import java.sql.*;
import java.util.ArrayList;

public class LocationDao extends DaoBase{

    public ArrayList<Location> obtenerListaLocations() {
        ArrayList<Location> listaLocations = new ArrayList<>();

        String sql = "SELECT * FROM hr.locations l\n" +
                "left join countries c on l.country_id = c.country_id;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                Location location = fetchEmployeeData(rs);
                listaLocations.add(location);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLocations;
    }


    private Location fetchEmployeeData(ResultSet rs) throws SQLException {
        Location location = new Location();
        location.setLocationId(rs.getInt(1));
        location.setStreetAdd(rs.getString(2));
        location.setPostalCode(rs.getString(3));
        location.setCity(rs.getString(4));
        location.setStateProvince(rs.getString(5));

        Country country = new Country();
        country.setCountryId(rs.getString(6));
        country.setCountryName(rs.getString(8));
        location.setCountry(country);

        return location;
    }

    public void crearLocation(int locationId, String streetAdd, String postalCode ,String city, String stateprovince, String countryId){


        String sql = "insert into locations (location_id,street_address, postal_code, city, state_province, country_id) values (?,?,?,?,?,?)";

        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, locationId);
            pstmt.setString(2, streetAdd);
            pstmt.setString(3,postalCode);
            pstmt.setString(4, city);
            pstmt.setString(5,stateprovince);
            pstmt.setString(6, countryId);

            pstmt.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void actualizar(int locationId, String streetAdd, String postalCode ,String city, String stateprovince, String countryId){

        String sql = "update locations set street_address = ?, postal_code = ? , city = ? , state_province = ?, country_id = ? where location_id = ?";

        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, streetAdd);
            pstmt.setString(2, postalCode);
            pstmt.setString(3,city);
            pstmt.setString(4, stateprovince);
            pstmt.setString(5, countryId);
            pstmt.setInt(6, locationId);

            pstmt.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrar (String locationId) throws SQLException {

        String sql = "delete from locations where location_id = ?";

        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, locationId);
            pstmt.executeUpdate();
        }


    }

    public Location buscarPorId (int locationId){ //por mientrasssssssssssssssssssssssssssssss

        String sql = "select * from locations where location_id = ?";

        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, locationId);

            try(ResultSet rs = pstmt.executeQuery()){
                Location location = new Location();
                Country country = new Country();

                while(rs.next()){
                    location.setLocationId(rs.getInt(1));
                    location.setStreetAdd(rs.getString(2));
                    location.setPostalCode(rs.getString(3));
                    location.setCity(rs.getString(4));
                    location.setStateProvince(rs.getString(5));

                    country.setCountryId(rs.getString(6));
                    location.setCountry(country);


                }
                return location;
            }


        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }









}


