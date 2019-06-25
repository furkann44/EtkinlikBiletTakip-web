/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SponsorDao;
import entity.Sponsor;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@Named
@SessionScoped

public class SponsorController implements Serializable {

    List<Sponsor> sponsorList;
    SponsorDao sponsorDao;
    Sponsor sponsor;
    String searchString = null;

    @Inject
    private SponsorlukTipController spnTipController;

    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

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
        this.pageCount = (int) Math.ceil(this.getSponsorDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void updateForm(Sponsor spn) {
        this.sponsor = spn;
    }

    public void clearForm() {
        this.sponsor = new Sponsor();
    }

    public void deleteConfirm(Sponsor spn) {
        this.sponsor = spn;
    }

    public void delete() {
        this.getSponsorDao().remove(this.sponsor);
        this.sponsor = new Sponsor();
    }

    public void update() {
        this.getSponsorDao().update(this.sponsor);
        this.sponsor = new Sponsor();
    }

    public void create() {
        this.getSponsorDao().insert(this.sponsor);
        this.sponsor = new Sponsor();
    }

    public void search() {

        if (searchString != null) {
            this.sponsorList = this.sponsorDao.search(searchString);
            setSponsorList(this.sponsorList); 
        }
    }

    public List<Sponsor> getSponsorList() {
        if (searchString == null) {
            this.sponsorList = this.getSponsorDao().findAll(page, pageSize);
        }
        return sponsorList;
    }

    public void setSponsorList(List<Sponsor> sponsorList) {
        this.sponsorList = sponsorList; 
    }

    public SponsorDao getSponsorDao() {
        if (this.sponsorDao == null) {
            this.sponsorDao = new SponsorDao();
        }
        return sponsorDao;
    }

    public void setSponsorDao(SponsorDao sponsorDao) {
        this.sponsorDao = sponsorDao;
    }

    public Sponsor getSponsor() {
        if (this.sponsor == null) {
            this.sponsor = new Sponsor();
        }
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public SponsorlukTipController getSpnTipController() {
        if (this.spnTipController == null) {
            this.spnTipController = new SponsorlukTipController();
        }
        return spnTipController;
    }

    public void setSpnTipController(SponsorlukTipController spnTipController) {
        this.spnTipController = spnTipController;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    
}
