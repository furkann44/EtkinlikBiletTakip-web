/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.KullaniciDao;
import entity.Kullanici;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private Kullanici kullanici;
    private Kullanici Kontrol;
    private KullaniciDao kulDao;

    public String login() {

        if (this.kullanici.getAd().equals(getKontrol().getAd()) && this.kullanici.getSifre().equals(getKontrol().getSifre())) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", this.kullanici);
            return "shared/admin/index?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hatalı kullanıcı adı veya şifre"));
            return "/login";
        }

    }

    public Kullanici getKullanici() {
        if (this.kullanici == null) {
            this.kullanici = new Kullanici();
        }
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public KullaniciDao getKulDao() {
        if (this.kulDao == null) {
            this.kulDao = new KullaniciDao();
        }
        return kulDao;
    }

    public void setKulDao(KullaniciDao kulDao) {
        this.kulDao = kulDao;
    }

    public Kullanici getKontrol() {
        this.Kontrol = this.getKulDao().Kontrol(kullanici);
        return Kontrol;
    }

    public void setKontrol(Kullanici Kontrol) {
        this.Kontrol = Kontrol;
    }

}
