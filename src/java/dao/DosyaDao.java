/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Dosya;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.Connector;

/**
 *
 * @author Furkan
 */
public class DosyaDao {

    private Connector connector;
    private Connection connection;

    public List<Dosya> findAll() {
        List<Dosya> dList = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from dosya");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Dosya d = new Dosya();
                d.setId(rs.getLong("id"));
                d.setAdi(rs.getString("adi"));
                d.setDosyauzanti(rs.getString("dosyauzanti"));
                d.setDosyayolu(rs.getString("dosyayolu"));

                dList.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dList;
    }

    public void insert(Dosya dosya) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into dosya (adi,dosyauzanti,dosyayolu) values (?,?,?)");
            pst.setString(1, dosya.getAdi());
            pst.setString(2, dosya.getDosyauzanti());
            pst.setString(3, dosya.getDosyayolu());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Dosya findById(Long id) {
        Dosya d = null;

        try {
            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from dosya where id =" + id);
            rs.next();

            d = new Dosya();
            d.setId(rs.getLong("id"));
            d.setAdi(rs.getString("adi"));
            d.setDosyauzanti(rs.getString("dosyauzanti"));
            d.setDosyayolu(rs.getString("dosyayolu"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return d;
    }

    public void remove(Dosya dosya) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from dosya where id=? ");
            pst.setLong(1, dosya.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connector getConnector() {
        if (this.connector == null) {
            this.connector = new Connector();
        }
        return connector;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }
}
