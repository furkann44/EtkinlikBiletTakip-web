/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List; 

/**
 *
 * @author Furkan
 */
public class Kullanici {
    private Long id;
    private String ad;
    private String sifre;
    private Long grupid;
    
    private Grup grup;
    
    private List<Role> roles;
    
    public boolean hasRole(Role role){
        return roles.contains(role);
    }
    
    public enum Role{
        ADMIN,KULLANICI
    }
    
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

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public Long getGrupid() {
        return grupid;
    }

    public void setGrupid(Long grupid) {
        this.grupid = grupid;
    }

    public Grup getGrup() {
        return grup;
    }

    public void setGrup(Grup grup) {
        this.grup = grup;
    }

    @Override
    public String toString() {
        return "Kullanici{" + "id=" + id + ", ad=" + ad + ", sifre=" + sifre + ", grupid=" + grupid + ", grup=" + grup + '}';
    }
    
    
}
