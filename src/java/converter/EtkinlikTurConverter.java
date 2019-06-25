/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;


import dao.EtkinlikTurDao;
import entity.EtkinlikTur;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Furkan
 */
@FacesConverter(value = "etkinlikTurConverter")
public class EtkinlikTurConverter implements Converter{

    private EtkinlikTurDao etkTurDao;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
        return this.getEtkTurDao().findById(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object o) {
        EtkinlikTur e = (EtkinlikTur) o;
        return e.getId().toString();
    }

    public EtkinlikTurDao getEtkTurDao() {
        if(this.etkTurDao == null)
            this.etkTurDao = new EtkinlikTurDao();
        return etkTurDao;
    }
    
    
    
}
