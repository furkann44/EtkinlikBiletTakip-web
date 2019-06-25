/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.EtkinlikTur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Connector;

/**
 *
 * @author Furkan
 */
public class EtkinlikTurDao {

    Connector connector;
    Connection connection;

    public List<EtkinlikTur> findAll(int page, int pageSize) {
        List<EtkinlikTur> etkTList = new ArrayList();

        int start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from etkinliktur order by id asc limit " + pageSize + " offset " + start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                EtkinlikTur e = new EtkinlikTur();
                e.setId(rs.getLong("id"));
                e.setAd(rs.getString("ad"));
                e.setAciklama(rs.getString("aciklama"));

                etkTList.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikTurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return etkTList;
    }

    public void insert(EtkinlikTur etkTur) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into etkinliktur (ad,aciklama) values(?,?)");
            pst.setString(1, etkTur.getAd());
            pst.setString(2, etkTur.getAciklama());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikTurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(EtkinlikTur etkTur) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update etkinliktur set ad=?, aciklama=? where id=?");
            pst.setString(1, etkTur.getAd());
            pst.setString(2, etkTur.getAciklama());
            pst.setLong(3, etkTur.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikTurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(EtkinlikTur etkTur) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from etkinliktur where id= ?");
            pst.setLong(1, etkTur.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikTurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EtkinlikTur findById(Long id) {
        EtkinlikTur e = null;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from etkinliktur where id =?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();

            e = new EtkinlikTur();
            e.setId(rs.getLong("id"));
            e.setAd(rs.getString("ad"));
            e.setAciklama(rs.getString("aciklama"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }

     public List<EtkinlikTur> search(String searchString) {
        List<EtkinlikTur> etkTList = new ArrayList();
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from etkinliktur where position (? in ad) > 0");
            
            pst.setString(1, searchString);
            ResultSet rs = pst.executeQuery();
            
             while (rs.next()) {
                EtkinlikTur e = new EtkinlikTur();
                e.setId(rs.getLong("id"));
                e.setAd(rs.getString("ad"));
                e.setAciklama(rs.getString("aciklama"));

                etkTList.add(e);
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikTurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return etkTList;
    }
     
      public int count() {
        int count = 0;

        try {
            PreparedStatement  pst = this.getConnection().prepareStatement("select count(id) as sponsor_count from etkinliktur");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("sponsor_count");
            
        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
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
            this.connection = getConnector().connect();
        }
        return connection;
    }

}
