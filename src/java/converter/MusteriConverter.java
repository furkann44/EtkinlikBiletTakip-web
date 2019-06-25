/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.MusteriDao;
import entity.Musteri;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "musteriConverter")
public class MusteriConverter implements Converter{

    private MusteriDao musteriDao;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
        return this.getMusteriDao().findById(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object o) {
        Musteri m = (Musteri) o;
        return m.getId().toString();
    }

    public MusteriDao getMusteriDao() {
        if(this.musteriDao == null)
            this.musteriDao = new MusteriDao();
        return musteriDao;
    }
    
    
    
}
