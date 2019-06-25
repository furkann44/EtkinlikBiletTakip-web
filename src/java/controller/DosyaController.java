/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DosyaDao;
import entity.Dosya;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@SessionScoped
public class DosyaController implements Serializable {

    private Dosya dosya;
    private List<Dosya> dosyaList;
    private DosyaDao dosyaDao;

    private Part doc;
    private final String uploadTo = "/Users/Furkan/Documents/NetBeansProjects/BiletTakip/upload/";

    public void upload() {
        try {
            InputStream input = doc.getInputStream();
            File f = new File(uploadTo+doc.getSubmittedFileName());
            Files.copy(input, f.toPath());
            
            dosya = this.getDosya();
            dosya.setAdi(f.getName());
            dosya.setDosyauzanti(doc.getContentType());
            dosya.setDosyayolu(f.getParent());
            
            this.getDosyaDao().insert(dosya);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    
    public void delete(){
        this.getDosyaDao().remove(dosya);
    }
    
     public void deleteConfirm(Dosya dosya){
        this.dosya = dosya; 
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

    public List<Dosya> getDosyaList() {
        this.dosyaList = this.getDosyaDao().findAll();
        return dosyaList;
    }

    public void setDosyaList(List<Dosya> dosyaList) {
        this.dosyaList = dosyaList;
    }

    public DosyaDao getDosyaDao() {
        if (this.dosyaDao == null) {
            this.dosyaDao = new DosyaDao();
        }
        return dosyaDao;
    }

    public void setDosyaDao(DosyaDao dosyaDao) {
        this.dosyaDao = dosyaDao;
    }

    public Part getDoc() {
        return doc;
    }

    public void setDoc(Part doc) {
        this.doc = doc;
    }

    public String getUploadTo() {
        return uploadTo;
    }

}
