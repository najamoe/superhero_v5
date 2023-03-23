package com.example.superhero_v5.Repository;

import com.example.superhero_v5.DTO.PowerTypeDTO;
import com.example.superhero_v5.DTO.SuperheroFormDTO;
import com.example.superhero_v5.Model.Superhero;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("HeroRepository_db")
public class HeroRepository {

    public List<Superhero> getAllHeroes() {
        String SQL = "SELECT * FROM superherov5.superhero";

        List<Superhero> superheroes = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/superherov5", "root", "Barthur2254!")) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int heroid = rs.getInt("heroid");
                String hero_name = rs.getString("hero_name");
                String real_name = rs.getString("real_name");
                int creation_year = rs.getInt("creation_year");
                String superpower = rs.getString("superpower");
                superheroes.add(new Superhero(heroid, hero_name,real_name, creation_year, superpower));
            }
            return superheroes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//Modificér endpointet: /superpower/{name} fra superheltev4, sådan at controlleren returnerer powers.html der renderer powers:
    public List<PowerTypeDTO> getPowerTypeByName(String name) {
        List<PowerTypeDTO> superheroes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/superherov5", "root", "Barthur2254!")) {
            String SQL = "SELECT real_name, superpower FROM superherov5.superhero where hero_name = ?;";
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, name);
            ResultSet rst= pst.executeQuery();
            while (rst.next()) {
                String real_name = rst.getString("real_name");
                String superpower = rst.getString("superpower");
                superheroes.add(new PowerTypeDTO(real_name, superpower));
            }
            return superheroes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addSuperHero(SuperheroFormDTO form) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/superherov4", "root", "Barthur2254!")) {
            // ID's
            int cityId = 0;
            int heroId = 0;
            List<Integer> powerIDs = new ArrayList<>();

            // find city_id
            String SQL1 = "select city_id from city where name = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL1);
            pstmt.setString(1, form.getCity());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cityId = rs.getInt("city_id");
            }

            // insert row in superhero table
            String SQL2 = "insert into superhero (hero_name, real_name, creation_year, city_id) " +
                    "values(?, ?, ?, ?);";
            pstmt = con.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS); // return autoincremented key
            pstmt.setString(1, form.getHeroName());
            pstmt.setString(2, form.getRealName());
            pstmt.setInt(3, form.getCreationYear());
            pstmt.setInt(4, cityId);
            int rows = pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                heroId = rs.getInt(1);
            }


            // find power_ids
            String SQL3 = "select power_id from superpower where name = ?;";
            pstmt = con.prepareStatement(SQL3);

            for (String power : form.getPowerList()) {
                pstmt.setString(1, power);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    powerIDs.add(rs.getInt("power_id"));
                }
            }

            // insert entries in superhero_powers join table
            String SQL4 = "insert into superhero_powers values (?,?,'high');";
            pstmt = con.prepareStatement(SQL4);

            for (int i = 0; i < powerIDs.size(); i++) {
                pstmt.setInt(1, heroId);
                pstmt.setInt(2, powerIDs.get(i));
                rows = pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

/*
    //Select- og Checkboxe skal udfyldes dynamisk, dvs data skal hentes fra backenden. Til dette formål skal der skrives to metoder i repositoriet:
    public List<String> getCities() {
// hent cities fra City tabel
    }

    public List<String> getPowers() {
// hent powers fra Superpower tabel
    }
*/
}

