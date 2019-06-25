/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Grup;
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
public class GrupDao {

    Connector connector;
    Connection connection;

    public List<Grup> findAll() {
        List<Grup> grupList = new ArrayList<>();

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from grup");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Grup g = new Grup();

                g.setId(rs.getLong("id"));
                g.setAdi(rs.getString("adi"));  

                grupList.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GrupDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grupList;
    }

    public void insert(Grup grup) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into grup (adi) values (?)"); 
            pst.setString(1, grup.getAdi());
            
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GrupDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Grup grup) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update grup set adi=? where id=?");
            pst.setString(1, grup.getAdi());
            pst.setLong(2, grup.getId());
            
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GrupDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(Grup grup) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from grup where id= ?");
            pst.setLong(1, grup.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GrupDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public Grup findById(Long id) {
        Grup g = null;
        
        try{
        PreparedStatement pst = this.getConnection().prepareStatement("select * from grup where id = ?");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        
        rs.next();
        
        g = new Grup();
        g.setId(rs.getLong("id"));
        g.setAdi(rs.getString("adi"));
         
        }   catch (SQLException ex) {
            Logger.getLogger(GrupDao.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return g;
    }

}
