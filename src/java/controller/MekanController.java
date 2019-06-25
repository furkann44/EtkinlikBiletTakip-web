/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MekanDao;
import dao.MekanTipDao;
import entity.Mekan;
import entity.MekanTip;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class MekanController implements Serializable {

    List<Mekan> mekanList;
    MekanDao mekanDao;
    Mekan mekan;

    private List<MekanTip> mekanTipList;
    private MekanTipDao mekanTipDao;
    private Long selectedTip;

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

    public void updateForm(Mekan mekan) {
        this.mekan = mekan;
        this.selectedTip = this.mekan.getMekanTip().getId();
    }

    public void clearForm() {
        this.mekan = new Mekan();
    }

    public void deleteConfirm(Mekan mekan) {
        this.mekan = mekan;
    }

    public void delete() {
        this.getMekanDao().remove(this.mekan);
        this.mekan = new Mekan();
    }

    public void update() {
        this.mekanDao.update(this.mekan, selectedTip);
        this.mekan = new Mekan();
    }

    public void create() {
        this.getMekanDao().insert(this.mekan, selectedTip);
        this.mekan = new Mekan();
    }

    public void search() {

        if (searchString != null) {
            this.mekanList = this.mekanDao.search(searchString);
            setMekanList(this.mekanList);
        }
    }

    public List<Mekan> getMekanList() {
        if (searchString == null) {
            this.mekanList = this.getMekanDao().findAll(page,pageSize);
        }
        return mekanList;
    }

    public void setMekanList(List<Mekan> mekanList) {
        this.mekanList = mekanList;
    }

    public MekanDao getMekanDao() {
        if (this.mekanDao == null) {
            this.mekanDao = new MekanDao();
        }
        return mekanDao;
    }

    public void setMekanDao(MekanDao mekanDao) {
        this.mekanDao = mekanDao;
    }

    public Mekan getMekan() {
        if (this.mekan == null) {
            this.mekan = new Mekan();
        }
        return mekan;
    }

    public void setMekan(Mekan mekan) {
        this.mekan = mekan;
    }

    public Long getSelectedTip() {
        return selectedTip;
    }

    public void setSelectedTip(Long selectedTip) {
        this.selectedTip = selectedTip;
    }

    public MekanTipDao getMekanTipDao() {
        if (this.mekanTipDao == null) {
            this.mekanTipDao = new MekanTipDao();
        }
        return mekanTipDao;
    }

    public List<MekanTip> getMekanTipList() {
        if (this.mekanTipList == null) {
            this.mekanTipList = this.getMekanTipDao().findAll();
        }
        return mekanTipList;
    }

    public void setMekanTipList(List<MekanTip> mekanTipList) {
        this.mekanTipList = mekanTipList;
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
        this.pageCount = (int) Math.ceil(this.getMekanDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
