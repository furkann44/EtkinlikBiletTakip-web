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
public class Sponsor {
    private Long id;
    private Long verginumarasi;
    private String ad;
    private Long sponsorluktipid;
    
    private SponsorlukTip spnTip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVerginumarasi() {
        return verginumarasi;
    }

    public void setVerginumarasi(Long verginumarasi) {
        this.verginumarasi = verginumarasi;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Long getSponsorluktipid() {
        return sponsorluktipid;
    }

    public void setSponsorluktipid(Long sponsorluktipid) {
        this.sponsorluktipid = sponsorluktipid;
    }

    public SponsorlukTip getSpnTip() {
        return spnTip;
    }

    public void setSpnTip(SponsorlukTip spnTip) {
        this.spnTip = spnTip;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Sponsor other = (Sponsor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
