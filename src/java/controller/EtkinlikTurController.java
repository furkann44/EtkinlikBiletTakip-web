/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EtkinlikTurDao;
import entity.EtkinlikTur;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class EtkinlikTurController implements Serializable {

    private List<EtkinlikTur> etkTurList;
    private EtkinlikTurDao etkTurDao;
    private EtkinlikTur etkTur;
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

    public void updateForm(EtkinlikTur etkTur) {
        this.etkTur = etkTur;
    }

    public void clearForm() {
        this.etkTur = new EtkinlikTur();
    }

    public void deleteConfirm(EtkinlikTur etkTur) {
        this.etkTur = etkTur;
    }

    public void delete() {
        this.getEtkTurDao().remove(this.etkTur);
        this.etkTur = new EtkinlikTur();
    }

    public void update() {
        this.etkTurDao.update(this.etkTur);
        this.etkTur = new EtkinlikTur();
    }

    public void create() {
        this.getEtkTurDao().insert(this.etkTur);
        this.etkTur = new EtkinlikTur();
    }

    public void search() {

        if (searchString != null) {
            this.etkTurList = this.etkTurDao.search(searchString);
            setEtkTurList(this.etkTurList);
        }
    }

    public List<EtkinlikTur> getEtkTurList() {
        if (searchString == null) {
            this.etkTurList = this.getEtkTurDao().findAll(page, pageSize);
        }
        return etkTurList;
    }

    public void setEtkTurList(List<EtkinlikTur> etkTurList) {
        this.etkTurList = etkTurList;
    }

    public EtkinlikTurDao getEtkTurDao() {
        if (this.etkTurDao == null) {
            this.etkTurDao = new EtkinlikTurDao();
        }
        return etkTurDao;
    }

    public void setEtkTurDao(EtkinlikTurDao etkTurDao) {
        this.etkTurDao = etkTurDao;
    }

    public EtkinlikTur getEtkTur() {
        if (this.etkTur == null) {
            this.etkTur = new EtkinlikTur();
        }
        return etkTur;
    }

    public void setEtkTur(EtkinlikTur etkTur) {
        this.etkTur = etkTur;
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
        this.pageCount = (int) Math.ceil(this.getEtkTurDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
