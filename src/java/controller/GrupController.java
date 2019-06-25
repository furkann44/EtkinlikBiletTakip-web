/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GrupDao;
import entity.Grup;
import java.io.Serializable;
import java.util.List; 
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class GrupController implements Serializable {
    List<Grup> grupList;
    GrupDao grupDao;
    Grup grup;
    
    public void updateForm(Grup g){
        this.grup = g;
    }

    public void clearForm(){
        this.grup = new Grup();
    }
    
    public void deleteConfirm(Grup g){
        this.grup = g;
    }
    
    public void delete(){
        this.getGrupDao().remove(this.grup);
        this.clearForm();
    }
    
     public void update(){
         this.grupDao.update(this.grup);
         this.clearForm();
     }
    
    public void create(){
        this.getGrupDao().insert(this.grup);
        this.clearForm();
    }
    
    
    public List<Grup> getGrupList() {
        this.grupList = this.getGrupDao().findAll();
        return grupList;
    }

    public void setGrupList(List<Grup> grupList) {
        this.grupList = grupList;
    }

    public GrupDao getGrupDao() {
        if(this.grupDao == null)
            this.grupDao = new GrupDao();
        return grupDao;
    }

    public void setGrupDao(GrupDao grupDao) {
        this.grupDao = grupDao;
    }

    public Grup getGrup() {
        if(this.grup == null)
            this.grup = new Grup();
        return grup;
    }

    public void setGrup(Grup grup) {
        this.grup = grup;
    }
    
    
    
    
    
}
