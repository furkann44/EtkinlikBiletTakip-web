/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.SponsorlukTip;
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
public class SponsorlukTipDao {
    
    Connector connector;
    Connection connection;
    
    public List<SponsorlukTip> findAll(int page, int pageSize) {
        List<SponsorlukTip> spnTipList = new ArrayList();
        
        int start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from sponsorluktip order by id asc limit " + pageSize + " offset " + start);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                SponsorlukTip s = new SponsorlukTip();
                s.setId(rs.getLong("id"));
                s.setAd(rs.getString("ad"));
                s.setAciklama(rs.getString("aciklama"));
                s.setOdememiktari(rs.getFloat("odememiktari"));
                
                spnTipList.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikTurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return spnTipList;
    }
    
    public void insert(SponsorlukTip spnTip) {
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into sponsorluktip (ad,aciklama,odememiktari) values(?,?,?)");
            pst.setString(1, spnTip.getAd());
            pst.setString(2, spnTip.getAciklama());
            pst.setDouble(3, spnTip.getOdememiktari());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SponsorlukTipDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(SponsorlukTip spnTip) {
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update sponsorluktip set ad=?, aciklama=?, odememiktari=? where id=?");
            pst.setString(1, spnTip.getAd());
            pst.setString(2, spnTip.getAciklama());
            pst.setDouble(3, spnTip.getOdememiktari());
            pst.setLong(4, spnTip.getId());
            
            pst.executeUpdate();
             
        } catch (SQLException ex) {
            Logger.getLogger(SponsorlukTipDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(SponsorlukTip spnTip) {
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from sponsorluktip where id = ?");
            pst.setLong(1, spnTip.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SponsorlukTipDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public SponsorlukTip findById(Long id) {
        SponsorlukTip s = null;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from sponsorluktip where id = ?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            
            s = new SponsorlukTip();
            s.setId(rs.getLong("id"));
            s.setAd(rs.getString("ad"));
            s.setAciklama(rs.getString("aciklama"));
            s.setOdememiktari(rs.getFloat("odememiktari"));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }
    
    public List<SponsorlukTip> search(String searchString) {
        List<SponsorlukTip> spnTipList = new ArrayList();
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from sponsorluktip where position (? in ad) > 0");
            
            pst.setString(1, searchString);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                SponsorlukTip s = new SponsorlukTip();
                s.setId(rs.getLong("id"));
                s.setAd(rs.getString("ad"));
                s.setAciklama(rs.getString("aciklama"));
                s.setOdememiktari(rs.getFloat("odememiktari"));
                
                spnTipList.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SponsorlukTipDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return spnTipList;
    }
    
    public int count() {
        int count = 0;

        try {
            PreparedStatement  pst = this.getConnection().prepareStatement("select count(id) as sponsor_count from sponsorluktip");
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
            this.connection = this.getConnector().connect();
        }
        return connection;
    }
    
}
