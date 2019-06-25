/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
   
import dao.EtkinlikDao;
import entity.Dosya;
import entity.Etkinlik;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@SessionScoped
public class EtkinlikController implements Serializable {

    List<Etkinlik> etkinlikList;
    EtkinlikDao etkinlikDao;
    Etkinlik etkinlik;
    Dosya dosya;

    String searchString = null;

    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    @Inject
    private EtkinlikTurController etkTurController;
    private SponsorController sponsorController;
    private MekanController mekanController;
    private DosyaController dosyaController;

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

    public void goPage(int count) {
        this.page = count;
    }

    public void updateForm(Etkinlik etk) {
        this.etkinlik = etk;
    }

    public void clearForm() {
        this.etkinlik = new Etkinlik();
    }

    public void deleteConfirm(Etkinlik etk) {
        this.etkinlik = etk;
        this.clearForm();
    }

    public void delete() {
        this.getEtkinlikDao().remove(this.etkinlik);
        this.clearForm();
    }

    public void update() {
        this.etkinlikDao.update(this.etkinlik);
        this.clearForm();
    }

    public void create() {
        this.getEtkinlikDao().insert(this.etkinlik);
        this.clearForm();
    }

    public void search() {

        if (searchString != null) {
            this.etkinlikList = this.etkinlikDao.search(searchString);
            setEtkinlikList(this.etkinlikList);
        }
    }

    public List<Etkinlik> getEtkinlikList() {
        if (searchString == null) {
            this.etkinlikList = this.getEtkinlikDao().findAll(page,pageSize);
        }
        return etkinlikList;
    }

    public void setEtkinlikList(List<Etkinlik> etkinlikList) {
        this.etkinlikList = etkinlikList;
    }

    public EtkinlikDao getEtkinlikDao() {
        if (this.etkinlikDao == null) {
            this.etkinlikDao = new EtkinlikDao();
        }
        return etkinlikDao;
    }

    public void setEtkinlikDao(EtkinlikDao etkinlikDao) {
        this.etkinlikDao = etkinlikDao;
    }

    public Etkinlik getEtkinlik() {
        if (this.etkinlik == null) {
            this.etkinlik = new Etkinlik();
        }
        return etkinlik;
    }

    public void setEtkinlik(Etkinlik etkinlik) {
        this.etkinlik = etkinlik;
    }

    public EtkinlikTurController getEtkTurController() {
        if (this.etkTurController == null) {
            this.etkTurController = new EtkinlikTurController();
        }
        return etkTurController;
    }

    public void setEtkTurController(EtkinlikTurController etkTurController) {
        this.etkTurController = etkTurController;
    }

    public SponsorController getSponsorController() {
        if (this.sponsorController == null) {
            this.sponsorController = new SponsorController();
        }
        return sponsorController;
    }

    public void setSponsorController(SponsorController sponsorController) {
        this.sponsorController = sponsorController;
    }

    public MekanController getMekanController() {
        if (this.mekanController == null) {
            this.mekanController = new MekanController();
        }
        return mekanController;
    }

    public void setMekanController(MekanController mekanController) {
        this.mekanController = mekanController;
    }

    public Dosya getDosya() {
        if (this.dosya == null) {
            this.dosya = new Dosya();
        }
        return dosya;
    }

    public void setDosya(Dosya dosya) {
        this.dosya = dosya;
    }

    public DosyaController getDosyaController() {
        if (this.dosyaController == null) {
            this.dosyaController = new DosyaController();
        }
        return dosyaController;
    }

    public void setDosyaController(DosyaController dosyaController) {
        this.dosyaController = dosyaController;
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
        this.pageCount = (int) Math.ceil(this.getEtkinlikDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
