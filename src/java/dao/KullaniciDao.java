/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Kullanici;
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
public class KullaniciDao {

    Connector connector;
    Connection connection;

    private GrupDao grupDao;

    public List<Kullanici> findAll(int page, int pageSize) {
        List<Kullanici> kulList = new ArrayList<>();

        int start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from kullanici order by id asc limit " + pageSize + " offset " + start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Kullanici k = new Kullanici();
                k.setId(rs.getLong("id"));
                k.setAd(rs.getString("ad"));
                k.setSifre(rs.getString("sifre"));
                k.setGrup(this.getGrupDao().findById(rs.getLong("grupid")));
                
                kulList.add(k);
            }

        } catch (SQLException ex) {
            Logger.getLogger(KullaniciDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kulList;
    }

    public void insert(Kullanici kullanici) {
        
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("insert into kullanici(ad,sifre,grupid) values(?,?,?)");
            pst.setString(1, kullanici.getAd());
            pst.setString(2, kullanici.getSifre());
            pst.setLong(3, kullanici.getGrup().getId());
            pst.executeUpdate();
        }   catch(SQLException ex){
            Logger.getLogger(KullaniciDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Kullanici kullanici) {
        
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("update kullanici set ad=?, sifre=?, grupid=?");
            pst.setString(1, kullanici.getAd());
            pst.setString(2, kullanici.getSifre());
            pst.setLong(3, kullanici.getGrup().getId());
            
            pst.executeUpdate();
        }   catch(SQLException ex){
            Logger.getLogger(KullaniciDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(Kullanici kullanici) {
        
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("delete from kullanici where id=? ");
            pst.setLong(1, kullanici.getId());
            pst.executeUpdate();
        }   catch(SQLException ex){
            Logger.getLogger(KullaniciDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Kullanici Kontrol(Kullanici k){
       
        Kullanici kul = null;
        try{
           PreparedStatement pst = this.getConnection().prepareStatement("select ad,sifre,grupid from kullanici where ad = ? and sifre = ?");
           pst.setString(1, k.getAd());
           pst.setString(2, k.getSifre());
           
           ResultSet rs = pst.executeQuery();
           
           rs.next();
           
           kul = new Kullanici();
           kul.setAd(rs.getString("ad"));
           kul.setSifre(rs.getString("sifre"));
           kul.setGrup(this.getGrupDao().findById(rs.getLong("grupid")));
           
        }   catch(SQLException ex){
            Logger.getLogger(KullaniciDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         return kul;
    }
    
    public List<Kullanici> search(String searchString) {
        List<Kullanici> kulList = new ArrayList();
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from kullanici where position (? in ad) > 0");
            
            pst.setString(1, searchString);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Kullanici k = new Kullanici();
                k.setId(rs.getLong("id"));
                k.setAd(rs.getString("ad"));
                k.setSifre(rs.getString("sifre"));
                k.setGrup(this.getGrupDao().findById(rs.getLong("grupid")));
                
                kulList.add(k);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kulList;
    }
    
      public int count() {
        int count = 0;

        try {
            PreparedStatement  pst = this.getConnection().prepareStatement("select count(id) as sponsor_count from kullanici");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("sponsor_count");
            
        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
     

     public GrupDao getGrupDao() {
        if(this.grupDao == null)
            this.grupDao = new GrupDao();
        return grupDao;
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
