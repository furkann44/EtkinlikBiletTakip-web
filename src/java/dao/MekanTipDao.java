/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.MekanTip;
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
public class MekanTipDao {

    Connector connector;
    Connection connection;

    public List<MekanTip> findAll() {
        List<MekanTip> mTipList = new ArrayList();

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from mekantip");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                MekanTip m = new MekanTip();
                m.setId(rs.getLong("id"));
                m.setTip(rs.getString("tip"));
                m.setAciklama(rs.getString("aciklama"));

                mTipList.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MekanTipDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mTipList;
    }

    public void insert(MekanTip mekanTip) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into mekantip (tip,aciklama) values(?,?)");
            pst.setString(1, mekanTip.getTip());
            pst.setString(2, mekanTip.getAciklama());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MekanTipDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(MekanTip mekanTip) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update mekantip set tip=?, aciklama = ? where id = ?");
            pst.setString(1, mekanTip.getTip());
            pst.setString(2, mekanTip.getAciklama());
            pst.setLong(3, mekanTip.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MekanTipDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(MekanTip mekanTip) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from mekantip where id =?");
            pst.setLong(1, mekanTip.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MekanTipDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MekanTip find(Long id) {
        MekanTip r = null;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from mekantip where id=");
            ResultSet rs = pst.executeQuery();
            rs.next();

            r = new MekanTip();
            r.setId(rs.getLong("id"));
            r.setTip(rs.getString("tip"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
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
