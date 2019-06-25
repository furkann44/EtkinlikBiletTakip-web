/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Furkan
 */
public class Etkinlik {

    private Long id;
    private String adi;
    private String kitle;
    private Long dosyaid;
    private Long etkinlikturid;
    private Long kullaniciid;
    private Date baslangic;
    private Date bitis;
    private Long mekanid;
    

    private EtkinlikTur etkinlikTur;
    private List<Sponsor> etkinlikSponsor;
    private Mekan mekan;
    private Dosya dosya;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getKitle() {
        return kitle;
    }

    public void setKitle(String kitle) {
        this.kitle = kitle;
    }

    public Long getDosyaid() {
        return dosyaid;
    }

    public void setDosyaid(Long dosyaid) {
        this.dosyaid = dosyaid;
    }

    public Long getEtkinlikturid() {
        return etkinlikturid;
    }

    public void setEtkinlikturid(Long etkinlikturid) {
        this.etkinlikturid = etkinlikturid;
    }

    public Long getKullaniciid() {
        return kullaniciid;
    }

    public void setKullaniciid(Long kullaniciid) {
        this.kullaniciid = kullaniciid;
    }

    public Date getBaslangic() {
        return baslangic;
    }

    public void setBaslangic(Date baslangic) {
        this.baslangic = baslangic;
    }

    public Date getBitis() {
        return bitis;
    }

    public void setBitis(Date bitis) {
        this.bitis = bitis;
    }

    public EtkinlikTur getEtkinlikTur() {
        return etkinlikTur;
    }

    public void setEtkinlikTur(EtkinlikTur etkinlikTur) {
        this.etkinlikTur = etkinlikTur;
    }

    public List<Sponsor> getEtkinlikSponsor() {
        return etkinlikSponsor;
    }

    public void setEtkinlikSponsor(List<Sponsor> etkinlikSponsor) {
        this.etkinlikSponsor = etkinlikSponsor;
    }

    public Dosya getDosya() {
        return dosya;
    }

    public void setDosya(Dosya dosya) {
        this.dosya = dosya;
    }

    public Long getMekanid() {
        return mekanid;
    }

    public void setMekanid(Long mekanid) {
        this.mekanid = mekanid;
    }

    public Mekan getMekan() {
        return mekan;
    }

    public void setMekan(Mekan mekan) {
        this.mekan = mekan;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Etkinlik other = (Etkinlik) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
