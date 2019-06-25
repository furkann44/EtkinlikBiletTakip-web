/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Etkinlik;
import entity.Sponsor;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Connector;

/**
 *
 * @author Furkan
 */
public class EtkinlikDao {

    Connector connector;
    Connection connection;

    private EtkinlikTurDao etkTurDao;
    private SponsorDao sponsorDao;
    private MekanDao mekanDao;
    private DosyaDao dosyaDao;

    public List<Etkinlik> findAll(int page, int pageSize) {
        List<Etkinlik> eList = new ArrayList<>();

        int start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from etkinlik order by id asc limit " + pageSize + " offset " + start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Etkinlik e = new Etkinlik();
                e.setId(rs.getLong("id"));
                e.setAdi(rs.getString("adi"));
                e.setKitle(rs.getString("kitle"));
                e.setBaslangic(rs.getDate("baslangic"));
                e.setBitis(rs.getDate("bitis"));

                e.setEtkinlikTur(this.getEtkTurDao().findById(rs.getLong("etkinlikturid")));
                
                e.setEtkinlikSponsor(this.getSponsorDao().getEtkinlikSponsor(e.getId()));
                
                e.setMekan(this.getMekanDao().findById(rs.getLong("mekanid")));
                
                e.setDosya((this.getDosyaDao().findById(rs.getLong("dosyaid"))));
                eList.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eList;
    }

    public void insert(Etkinlik etkinlik) {
        try {
             java.util.Date utilBaslangic =  etkinlik.getBaslangic();
             java.sql.Date sqlBaslangic = new java.sql.Date(utilBaslangic.getTime());
             java.util.Date utilBitis =  etkinlik.getBaslangic();
             java.sql.Date sqlBitis = new java.sql.Date(utilBitis.getTime());
            
            PreparedStatement pst = this.getConnection().prepareStatement("insert into etkinlik (adi,kitle,etkinlikturid,baslangic,bitis,mekanid,dosyaid) values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, etkinlik.getAdi());
            pst.setString(2, etkinlik.getKitle());
            pst.setLong(3, etkinlik.getEtkinlikTur().getId());
            pst.setDate(4, sqlBaslangic);
            pst.setDate(5,  sqlBitis);
            pst.setLong(6, etkinlik.getMekan().getId());
            pst.setLong(7, etkinlik.getDosya().getId());
            pst.executeUpdate();

            Long id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id = gk.getLong(1);
            }

            for (Sponsor s : etkinlik.getEtkinlikSponsor()) {
                pst = this.getConnection().prepareStatement("insert into etkinliksponsor (etkinlikid, sponsorid) values(?,?)");
                pst.setLong(1, id);
                pst.setLong(2, s.getId());
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikTurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Etkinlik etkinlik) {
        try {
             java.util.Date utilBaslangic =  etkinlik.getBaslangic();
             java.sql.Date sqlBaslangic = new java.sql.Date(utilBaslangic.getTime());
             java.util.Date utilBitis =  etkinlik.getBaslangic();
             java.sql.Date sqlBitis = new java.sql.Date(utilBitis.getTime());
             
            PreparedStatement pst = this.getConnection().prepareStatement("update etkinlik set adi=?, kitle=?, etkinlikturid=?, baslangic=?, bitis=?, mekanid=?, dosyaid=? where id=?");
            pst.setString(1, etkinlik.getAdi());
            pst.setString(2, etkinlik.getKitle());
            pst.setLong(3, etkinlik.getEtkinlikTur().getId());
            pst.setDate(4, sqlBaslangic);
            pst.setDate(5, sqlBitis);
            pst.setLong(6, etkinlik.getMekan().getId());
            pst.setLong(7, etkinlik.getDosya().getId());
            pst.setLong(8, etkinlik.getId());
            
            pst.executeUpdate();

            pst = this.getConnection().prepareStatement("delete from etkinliksponsor where etkinlikid=?");
            pst.setLong(1, etkinlik.getId());
            pst.executeUpdate();

            for (Sponsor s : etkinlik.getEtkinlikSponsor()) {
                pst = this.getConnection().prepareStatement("insert into etkinliksponsor (etkinlikid, sponsorid) values(?,?)");
                pst.setLong(1, etkinlik.getId());
                pst.setLong(2, s.getId());
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikTurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(Etkinlik etkinlik) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from etkinliksponsor where etkinlikid=?");
            pst.setLong(1, etkinlik.getId());
            pst.executeUpdate();

            pst = this.getConnection().prepareStatement("delete from etkinlik where id=?");
            pst.setLong(1, etkinlik.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikTurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Etkinlik findById(Long id) {
        Etkinlik e = null;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from etkinlik where id = ?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();

            e = new Etkinlik();
            e.setId(rs.getLong("id"));
            e.setAdi(rs.getString("adi"));
            e.setKitle(rs.getString("kitle"));
            e.setEtkinlikturid(rs.getLong("etkinlikturid"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }
    
     public List<Etkinlik> search(String searchString) {
        List<Etkinlik> mList = new ArrayList();
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from etkinlik where position (? in adi) > 0");
            
            pst.setString(1, searchString);
            ResultSet rs = pst.executeQuery();
            
           while (rs.next()) {
                Etkinlik e = new Etkinlik();
                e.setId(rs.getLong("id"));
                e.setAdi(rs.getString("adi"));
                e.setKitle(rs.getString("kitle"));
                e.setBaslangic(rs.getDate("baslangic"));
                e.setBitis(rs.getDate("bitis"));

                e.setEtkinlikTur(this.getEtkTurDao().findById(rs.getLong("etkinlikturid")));
                
                e.setEtkinlikSponsor(this.getSponsorDao().getEtkinlikSponsor(e.getId()));
                
                e.setMekan(this.getMekanDao().findById(rs.getLong("mekanid")));
                
                e.setDosya((this.getDosyaDao().findById(rs.getLong("dosyaid"))));
                mList.add(e);
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(EtkinlikDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mList;
    }

      public int count() {
        int count = 0;

        try {
            PreparedStatement  pst = this.getConnection().prepareStatement("select count(id) as sponsor_count from etkinlik");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("sponsor_count");
            
        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
     
    public SponsorDao getSponsorDao() {
        if (this.sponsorDao == null) {
            this.sponsorDao = new SponsorDao();
        }
        return sponsorDao;
    }

    public EtkinlikTurDao getEtkTurDao() {
        if (this.etkTurDao == null) {
            this.etkTurDao = new EtkinlikTurDao();
        }
        return etkTurDao;
    }

    public MekanDao getMekanDao() {
        if(this.mekanDao == null){
            this.mekanDao = new MekanDao();
        }
        return mekanDao;
    }

    public DosyaDao getDosyaDao() {
        if(this.dosyaDao == null)
            this.dosyaDao = new DosyaDao();
        return dosyaDao;
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
