/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SponsorlukTipDao;
import entity.SponsorlukTip;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SponsorlukTipController implements Serializable {

    List<SponsorlukTip> spnTipList;
    SponsorlukTipDao spnTipDao;
    SponsorlukTip spnTip;
    String searchString = null;
    
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


    public void updateForm(SponsorlukTip spnTip) {
        this.spnTip = spnTip;
    }

    public void clearForm() {
        this.spnTip = new SponsorlukTip();
    }

    public void deleteConfirm(SponsorlukTip spnTip) {
        this.spnTip = spnTip;
    }

    public void delete() {
        this.getSpnTipDao().remove(this.spnTip);
        this.spnTip = new SponsorlukTip();
    }

    public void update() {
        this.spnTipDao.update(this.spnTip);
        this.spnTip = new SponsorlukTip();
    }

    public void create() {
        this.getSpnTipDao().insert(this.spnTip);
        this.spnTip = new SponsorlukTip();
    }

    public void search() {
        
        if (searchString != null) {
            this.spnTipList = this.spnTipDao.search(searchString);
            setSpnTipList(this.spnTipList);
        }  
    }

    public List<SponsorlukTip> getSpnTipList() {
        
        if(searchString == null){
            this.spnTipList = this.getSpnTipDao().findAll(page,pageSize);
        }
        return spnTipList;
    }

    public void setSpnTipList(List<SponsorlukTip> spnTipList) {
        this.spnTipList = spnTipList;
    }

    public SponsorlukTipDao getSpnTipDao() {
        if (this.spnTipDao == null) {
            this.spnTipDao = new SponsorlukTipDao();
        }
        return spnTipDao;
    }

    public void setSpnTipDao(SponsorlukTipDao spnTipDao) {
        this.spnTipDao = spnTipDao;
    }

    public SponsorlukTip getSpnTip() {
        if (this.spnTip == null) {
            this.spnTip = new SponsorlukTip();
        }
        return spnTip;
    }

    public void setSpnTip(SponsorlukTip spnTip) {
        this.spnTip = spnTip;
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
        this.pageCount = (int) Math.ceil(this.getSpnTipDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    

}
