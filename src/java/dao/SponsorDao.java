/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Sponsor;
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
public class SponsorDao {

    private Connector connector;
    private Connection connection;
    
    private SponsorlukTipDao spnTipDao;

    public List<Sponsor> findAll(int page, int pageSize) {
        List<Sponsor> sList = new ArrayList<>();
        int start = (page - 1) * pageSize;

        try {
          PreparedStatement pst = this.getConnection().prepareStatement("select * from sponsor order by id asc limit " + pageSize  + " offset " + start);
          ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Sponsor s = new Sponsor();
                s.setId(rs.getLong("id"));
                s.setAd(rs.getString("ad"));
                s.setVerginumarasi(rs.getLong("verginumarasi"));
                
                s.setSpnTip(this.getSpnTipDao().findById(rs.getLong("sponsorluktipid")));

                sList.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sList;
    }

    public void insert(Sponsor sponsor) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into sponsor (ad,verginumarasi,sponsorluktipid) values(?,?,?)");
            pst.setString(1, sponsor.getAd());
            pst.setLong(2, sponsor.getVerginumarasi());
            pst.setLong(3, sponsor.getSpnTip().getId());
            pst.executeUpdate();
             
        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Sponsor sponsor) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update sponsor set ad=?, verginumarasi=?, sponsorluktipid=? where id=?");
            pst.setString(1, sponsor.getAd());
            pst.setLong(2, sponsor.getVerginumarasi());
            pst.setLong(3, sponsor.getSpnTip().getId());
            pst.setLong(4, sponsor.getId());
            pst.executeUpdate(); 
            
        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(Sponsor sponsor) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from sponsor where id = ?");
            pst.setLong(1, sponsor.getId());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Sponsor> getEtkinlikSponsor(Long id) {
        List<Sponsor> etkinlikSponsor = new ArrayList<>();

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from etkinliksponsor where etkinlikid =?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                etkinlikSponsor.add(this.findById(rs.getLong("sponsorid")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return etkinlikSponsor;
    }

    public Sponsor findById(Long id) {
        Sponsor s = null;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from sponsor where id = ?");
            pst.setLong(1, id);
            
            ResultSet rs = pst.executeQuery();
            rs.next();

            s = new Sponsor();
            s.setAd(rs.getString("ad"));
            s.setId(rs.getLong("id"));
            s.setVerginumarasi(rs.getLong("verginumarasi"));

        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return s;
    }

    public int count() {
        int count = 0;

        try {
            PreparedStatement  pst = this.getConnection().prepareStatement("select count(id) as sponsor_count from sponsor");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("sponsor_count");
            
        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

      public List<Sponsor> search(String searchString) {
        List<Sponsor> spnList = new ArrayList();
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from sponsor where position (? in ad) > 0");
            
            pst.setString(1, searchString);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Sponsor s = new Sponsor();
                s.setId(rs.getLong("id"));
                s.setAd(rs.getString("ad"));
                s.setVerginumarasi(rs.getLong("verginumarasi"));
                
                s.setSpnTip(this.getSpnTipDao().findById(rs.getLong("sponsorluktipid")));

                spnList.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return spnList;
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

    public SponsorlukTipDao getSpnTipDao() {
        if(this.spnTipDao == null)
            this.spnTipDao = new SponsorlukTipDao();
        return spnTipDao;
    }

}
