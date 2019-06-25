/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author Furkan
 */
public class SponsorlukTip {
    private Long id;
    private String ad;
    private String aciklama;
    private double odememiktari;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public double getOdememiktari() {
        return odememiktari;
    }

    public void setOdememiktari(double odememiktari) {
        this.odememiktari = odememiktari;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SponsorlukTip other = (SponsorlukTip) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    @Override
    public String toString() {
        return "Musteri{" + "id=" + id + ", ad=" + ad + ", aciklama=" + aciklama + '}';
    }
    
    
}
