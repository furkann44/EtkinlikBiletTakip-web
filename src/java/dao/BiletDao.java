/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Bilet;
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
public class BiletDao {

    private Connector connector;
    private Connection connection;

    private MusteriDao musteriDao;
    private EtkinlikDao etkinlikDao;

    public List<Bilet> findAll(int page, int pageSize) {
        List<Bilet> bList = new ArrayList<>();

        int start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from bilet order by id asc limit " + pageSize + " offset " + start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Bilet b = new Bilet();

                b.setId(rs.getLong("id"));
                b.setMusteri(this.getMusteriDao().findById(rs.getLong("musteriid")));
                b.setEtkinlik(this.getEtkinlikDao().findById(rs.getLong("etkinlikid")));

                bList.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BiletDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bList;
    }

    public void insert(Bilet bilet) {

        int musteri_id = 0;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select id as musteri_id from musteri order by id desc limit 1 ");
            ResultSet rs = pst.executeQuery();
            rs.next();
            musteri_id = rs.getInt("musteri_id");
            pst = this.getConnection().prepareStatement("insert into bilet(musteriid,etkinlikid) values(?,?)");
            pst.setLong(1, musteri_id);
            pst.setLong(2, bilet.getEtkinlik().getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BiletDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Bilet bilet) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update bilet set musteriid=?, etkinlikid=? where id=?");
            pst.setLong(1, bilet.getMusteri().getId());
            pst.setLong(2, bilet.getEtkinlik().getId());
            pst.setLong(3, bilet.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BiletDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(Bilet bilet) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from bilet where id=?");
            pst.setLong(1, bilet.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(BiletDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Bilet> findBilet(Bilet bil) {
       List<Bilet> sorguList = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select bilet.id as bilet_id,musteri.id as musteri_id,etkinlikid as etkinlik_adi +"+
                    "from bilet left join  musteri on bilet.musteriid = musteri.id where musteri.tc =" + bil.getMusteri().getTc());
            ResultSet rs = pst.executeQuery();
            rs.next();
            Bilet b = new Bilet();
            b.setId(rs.getLong("bilet_id"));
            b.setMusteri(this.getMusteriDao().findById(rs.getLong("musteri_id")));
            b.setEtkinlik(this.getEtkinlikDao().findById(rs.getLong("etkinlik_id")));
            sorguList.add(b);
           
        } catch (SQLException ex) {
            Logger.getLogger(BiletDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sorguList;
    }
    
      public List<Bilet> search(String searchString) {
        List<Bilet> bList = new ArrayList();
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement(" select * from bilet inner join musteri on bilet.musteriid = musteri.id and position(? in adsoyad) > 0");
            
            pst.setString(1, searchString); 
            ResultSet rs = pst.executeQuery();
            
             while (rs.next()) {
                Bilet b = new Bilet();

                b.setId(rs.getLong("id"));
                b.setMusteri(this.getMusteriDao().findById(rs.getLong("musteriid")));
                b.setEtkinlik(this.getEtkinlikDao().findById(rs.getLong("etkinlikid")));

                bList.add(b);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bList;
    }
      
       public int count() {
        int count = 0;

        try {
            PreparedStatement  pst = this.getConnection().prepareStatement("select count(id) as sponsor_count from bilet");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("sponsor_count");
            
        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public MusteriDao getMusteriDao() {
        if (this.musteriDao == null) {
            this.musteriDao = new MusteriDao();
        }
        return musteriDao;
    }

    public EtkinlikDao getEtkinlikDao() {
        if (this.etkinlikDao == null) {
            this.etkinlikDao = new EtkinlikDao();
        }
        return etkinlikDao;
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
