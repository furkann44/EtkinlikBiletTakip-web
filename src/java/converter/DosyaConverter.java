/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.DosyaDao;
import entity.Dosya;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "dosyaConverter")
public class DosyaConverter implements Converter{

     DosyaDao dosyaDao;
            
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
        return this.getDosyaDao().findById(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object o) {
        Dosya d = (Dosya) o;
        return d.getId().toString();
    }

    public DosyaDao getDosyaDao() {
        if(this.dosyaDao == null)
            this.dosyaDao = new DosyaDao();
        return dosyaDao;
    }
    
    
    
}
