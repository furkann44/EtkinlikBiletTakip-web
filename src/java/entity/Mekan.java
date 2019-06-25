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
public class Mekan {
    private Long id;
    private String ad;
    private int kapasite;
    private int stantsayisi;
    private String adres;
    private int salonsayisi;
    
    private MekanTip mekanTip;

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

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public int getStantsayisi() {
        return stantsayisi;
    }

    public void setStantsayisi(int stantsayisi) {
        this.stantsayisi = stantsayisi;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public MekanTip getMekanTip() {
        return mekanTip;
    }

    public void setMekanTip(MekanTip mekanTip) {
        this.mekanTip = mekanTip;
    }
  
    public int getSalonsayisi() {
        return salonsayisi;
    }

    public void setSalonsayisi(int salonsayisi) {
        this.salonsayisi = salonsayisi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mekan other = (Mekan) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
