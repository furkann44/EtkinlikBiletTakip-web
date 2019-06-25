/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.KullaniciDao;
import entity.Kullanici;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class KullaniciController implements Serializable {

    private List<Kullanici> kulList;
    private KullaniciDao kulDao;
    private Kullanici kullanici;

    String searchString = null;

    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    @Inject
    private GrupController grupController;

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }

    public void updateForm(Kullanici kul) {
        this.kullanici = kul;
    }

    public void deleteConfirm(Kullanici kul) {
        this.kullanici = kul;
    }

    public void clearForm() {
        this.kullanici = new Kullanici();
    }

    public void delete() {
        this.getKulDao().remove(this.kullanici);
        this.clearForm();
    }

    public void update() {
        this.kulDao.update(this.kullanici);
        this.clearForm();
    }

    public void create() {
        this.getKulDao().insert(this.kullanici);
        this.clearForm();
    }

    public void search() {

        if (searchString != null) {
            this.kulList = this.kulDao.search(searchString);
            setKulList(this.kulList);
        }
    }

    public List<Kullanici> getKulList() {
        if (searchString == null) {
            this.kulList = this.getKulDao().findAll(page,pageSize);
        }
        return kulList;
    }

    public void setKulList(List<Kullanici> kulList) {
        this.kulList = kulList;
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

    public Kullanici getKullanici() {
        if (this.kullanici == null) {
            this.kullanici = new Kullanici();
        }
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public GrupController getGrupController() {
        if (this.grupController == null) {
            this.grupController = new GrupController();
        }
        return grupController;
    }

    public void setGrupController(GrupController grupController) {
        this.grupController = grupController;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getKulDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
