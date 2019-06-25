/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.EtkinlikDao;
import entity.Etkinlik;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Furkan
 */
@FacesConverter(value = "etkinlikConverter")
public class EtkinlikConverter implements Converter{

    EtkinlikDao etkinlikDao;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
        return this.getEtkinlikDao().findById(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object o) {
        Etkinlik e = (Etkinlik) o;
        return e.getId().toString();
    }

    public EtkinlikDao getEtkinlikDao() {
        if(this.etkinlikDao == null)
            this.etkinlikDao = new EtkinlikDao();
        return etkinlikDao;
    }
    
    
}
