/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MekanTipDao;
import entity.MekanTip;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped

public class MekanTipController implements Serializable{
    List<MekanTip> mekanTipList;
    MekanTipDao mekanTipDao;
    MekanTip mekanTip;
    
      public void updateForm(MekanTip mknTip) {
        this.mekanTip = mknTip;
    }

    public void clearForm() {
        this.mekanTip = new MekanTip();
    }

    public void deleteConfirm(MekanTip mknTip) {
        this.mekanTip = mknTip;
    }
    
    public void delete(){
        this.getMekanTipDao().remove(this.mekanTip);
        this.mekanTip = new MekanTip();
    }
    
    public void update(){
        this.mekanTipDao.update(this.mekanTip);
        this.mekanTip = new MekanTip();
    }
    
    public void create(){
        this.getMekanTipDao().insert(this.mekanTip);
        this.mekanTip = new MekanTip();
    }

    public List<MekanTip> getMekanTipList() {
        this.mekanTipList = this.getMekanTipDao().findAll();
        return mekanTipList;
    }

    public void setMekanTipList(List<MekanTip> mekanTipList) {
        this.mekanTipList = mekanTipList;
    }

    public MekanTipDao getMekanTipDao() {
        if(this.mekanTipDao == null)
            this.mekanTipDao = new MekanTipDao();
        return mekanTipDao;
    }

    public void setMekanTipDao(MekanTipDao mekanTipDao) {
        this.mekanTipDao = mekanTipDao;
    }

    public MekanTip getMekanTip() {
        if(this.mekanTip == null)
            this.mekanTip = new MekanTip();
        return mekanTip;
    }

    public void setMekanTip(MekanTip mekanTip) {
        this.mekanTip = mekanTip;
    }
    
    
}
