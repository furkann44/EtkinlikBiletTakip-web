
package dao;

import entity.Mekan; 
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
public class MekanDao {

    Connector connector;
    Connection connection;

    private MekanTipDao mekanTipDao;

  
    public List<Mekan> findAll(int page, int pageSize) {
        List<Mekan> mList = new ArrayList();
        int start = (page - 1) * pageSize;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from mekan order by id asc limit " + pageSize + " offset " + start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Mekan m = new Mekan();
                m.setId(rs.getLong("id"));
                m.setAd(rs.getString("ad"));
                m.setAdres(rs.getString("adres"));
                m.setKapasite(rs.getInt("kapasite"));
                m.setStantsayisi(rs.getInt("stantsayisi"));

                m.setMekanTip(this.getMekanTipDao().find(rs.getLong("mekantipid")));
                
                mList.add(m);
            }

        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
        return mList;
    }

     public void insert(Mekan mekan, Long selectedTip) {
          
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("insert into mekan (ad,kapasite,stantsayisi,adres,salonsayisi,mekantipid) values(?,?,?,?,?,?)");
            pst.setString(1, mekan.getAd());
            pst.setInt(2, mekan.getKapasite());
            pst.setInt(3, mekan.getStantsayisi());
            pst.setString(4, mekan.getAdres());
            pst.setInt(5, mekan.getSalonsayisi());
            pst.setLong(6, selectedTip);
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MekanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Mekan mekan, Long selectedTip) {
        
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("update mekan set ad=?, kapasite=?, stantsayisi=?, adres=?,salonsayisi=?,mekantipid=? where id=?");
            pst.setString(1, mekan.getAd());
            pst.setInt(2, mekan.getKapasite());
            pst.setInt(3, mekan.getStantsayisi());
            pst.setString(4, mekan.getAdres());
            pst.setInt(5, mekan.getSalonsayisi());
            pst.setLong(6, selectedTip);
            pst.setLong(7, mekan.getId());
            
            pst.executeUpdate();
            
        }   catch(SQLException ex){
            Logger.getLogger(MekanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(Mekan mekan) {
      
         try{
             PreparedStatement pst = this.getConnection().prepareStatement("delete from mekan where id =?");
             pst.setLong(1, mekan.getId());
            Statement st = this.getConnection().createStatement();
            st.executeUpdate("delete from mekan where id ="+mekan.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MekanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
   public Mekan findById(Long id) {
         Mekan m = null;
         
         try{
             PreparedStatement pst = this.getConnection().prepareStatement("select * from mekan where id = ?");
             pst.setLong(1, id);
             ResultSet rs = pst.executeQuery();
             rs.next();
             
             m = new Mekan();
             m.setId(rs.getLong("id"));
             m.setAd(rs.getString("ad"));
             m.setAdres(rs.getString("adres"));
             m.setKapasite(rs.getInt("kapasite"));
             m.setStantsayisi(rs.getInt("stantsayisi"));
             m.setSalonsayisi(rs.getInt("salonsayisi"));
             
             m.setMekanTip(this.getMekanTipDao().find(rs.getLong("mekantipid")));
             
         }  catch(SQLException ex){
              Logger.getLogger(MekanDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         return m;
    }
   
    public List<Mekan> search(String searchString) {
        List<Mekan> mList = new ArrayList();
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from mekan where position (? in ad) > 0");
            
            pst.setString(1, searchString);
            ResultSet rs = pst.executeQuery();
            
           while (rs.next()) {
                Mekan m = new Mekan();
                m.setId(rs.getLong("id"));
                m.setAd(rs.getString("ad"));
                m.setAdres(rs.getString("adres"));
                m.setKapasite(rs.getInt("kapasite"));
                m.setStantsayisi(rs.getInt("stantsayisi"));

                m.setMekanTip(this.getMekanTipDao().find(rs.getLong("mekantipid")));
                
                mList.add(m);
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(MekanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mList;
    }
    
    public int count() {
        int count = 0;

        try {
            PreparedStatement  pst = this.getConnection().prepareStatement("select count(id) as sponsor_count from mekan");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("sponsor_count");
            
        } catch (SQLException ex) {
            Logger.getLogger(SponsorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }  
    
    
      public MekanTipDao getMekanTipDao() {
          if(this.mekanTipDao == null)
              this.mekanTipDao = new MekanTipDao();
        return mekanTipDao;
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
