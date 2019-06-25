/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Musteri;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Connector;

/**
 *
 * @author Furkan
 */
public class MusteriDao {

    private Connector connector;
    private Connection connection;

    public List<Musteri> findAll(int page, int pageSize) {
        List<Musteri> musteriList = new ArrayList();
        int start = (page - 1) * pageSize;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from musteri order by id asc limit " + pageSize + " offset " + start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Musteri m = new Musteri();
                m.setId(rs.getLong("id"));
                m.setTc(rs.getLong("tc"));
                m.setAdsoyad(rs.getString("adsoyad"));
                m.setCinsiyet(rs.getString("cinsiyet"));
                m.setTelefon(rs.getLong("telefon"));
                m.setAdres(rs.getString("adres"));
                musteriList.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MusteriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return musteriList;
    }

    public void insert(Musteri musteri) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into musteri (adsoyad,tc,adres,telefon,cinsiyet) values(?,?,?,?,?)");
            pst.setString(1, musteri.getAdsoyad());
            pst.setLong(2, musteri.getTc());
            pst.setString(3, musteri.getAdres());
            pst.setLong(4, musteri.getTelefon());
            pst.setString(5, musteri.getCinsiyet());
            
            pst.executeUpdate();
             

        } catch (SQLException ex) {
            Logger.getLogger(MusteriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(Musteri musteri) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from musteri where id =?");
            pst.setLong(1, musteri.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MusteriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Musteri musteri) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update musteri set adsoyad=?, tc=?, adres=?, telefon=?, cinsiyet=? where id = ?");
             pst.setString(1, musteri.getAdsoyad());
            pst.setLong(2, musteri.getTc());
            pst.setString(3, musteri.getAdres());
            pst.setLong(4, musteri.getTelefon());
            pst.setString(5, musteri.getCinsiyet());
            pst.setLong(6, musteri.getId());
            
            pst.executeUpdate();
             

        } catch (SQLException ex) {
            Logger.getLogger(MusteriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Musteri findById(Long id) {
        Musteri m = null;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from musteri where id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();

            m = new Musteri();
            m.setId(rs.getLong("id"));
            m.setTc(rs.getLong("tc"));
            m.setAdsoyad(rs.getString("adsoyad"));
            m.setCinsiyet(rs.getString("cinsiyet"));
            m.setTelefon(rs.getLong("telefon"));
            m.setAdres(rs.getString("adres"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return m;
    }

     public List<Musteri> search(String searchString) {
        List<Musteri> musteriList = new ArrayList();
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from musteri where position (? in adsoyad) > 0");
            
            pst.setString(1, searchString);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Musteri m = new Musteri();
                m.setId(rs.getLong("id"));
                m.setTc(rs.getLong("tc"));
                m.setAdsoyad(rs.getString("adsoyad"));
                m.setCinsiyet(rs.getString("cinsiyet"));
                m.setTelefon(rs.getLong("telefon"));
                m.setAdres(rs.getString("adres"));
                musteriList.add(m);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MusteriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return musteriList;
    }
    
    public double count() {
        int count = 0;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(id) as musteri_count from musteri");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("musteri_count");

        } catch (SQLException ex) {
            Logger.getLogger(MusteriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
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
