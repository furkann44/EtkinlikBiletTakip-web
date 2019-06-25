/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BiletDao;
import entity.Bilet;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class BiletController implements Serializable {

    private List<Bilet> biletList;
    private BiletDao biletDao;
    private Bilet bilet;
    private List<Bilet> sorguList;

    String searchString = null;

    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    @Inject
    private MusteriController musController;
    private EtkinlikController etkController;

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

    public void deleteConfirm(Bilet bil) {
        this.bilet = bil;
    }

    public void updateForm(Bilet bil) {
        this.bilet = bil;
    }

    public void clearForm() {
        this.bilet = new Bilet();
    }

    public void delete() {
        this.getBiletDao().remove(this.bilet);
    }

    public void update() {
        this.biletDao.update(this.bilet);
    }

    public void create() {
        this.getBiletDao().insert(this.bilet);
        this.clearForm();
    }

    public void search() {

        if (searchString != null) {
            this.biletList = this.biletDao.search(searchString);
            setBiletList(this.biletList);
        }
    }

    public List<Bilet> getBiletList() {
        if (searchString == null) {
            this.biletList = this.getBiletDao().findAll(page, pageSize);
        }
        return biletList;
    }

    public void setBiletList(List<Bilet> biletList) {
        this.biletList = biletList;
    }

    public BiletDao getBiletDao() {
        if (this.biletDao == null) {
            this.biletDao = new BiletDao();
        }
        return biletDao;
    }

    public void setBiletDao(BiletDao biletDao) {
        this.biletDao = biletDao;
    }

    public Bilet getBilet() {
        if (this.bilet == null) {
            this.bilet = new Bilet();
        }
        return bilet;
    }

    public void setBilet(Bilet bilet) {
        this.bilet = bilet;
    }

    public MusteriController getMusController() {
        if (this.musController == null) {
            this.musController = new MusteriController();
        }
        return musController;
    }

    public void setMusController(MusteriController musController) {
        this.musController = musController;
    }

    public EtkinlikController getEtkController() {
        if (this.etkController == null) {
            this.etkController = new EtkinlikController();
        }
        return etkController;
    }

    public void setEtkController(EtkinlikController etkController) {
        this.etkController = etkController;
    }

    public void sorgu(Bilet bil) {
        this.sorguList = this.getBiletDao().findBilet(bil);
    }

    public List<Bilet> getSorguList() {
        return sorguList;
    }

    public void setSorguList(List<Bilet> sorguList) {
        this.sorguList = sorguList;
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
        this.pageCount = (int) Math.ceil(this.getBiletDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
