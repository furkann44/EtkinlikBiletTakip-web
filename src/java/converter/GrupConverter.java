/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.GrupDao;
import entity.Grup;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "grupConverter")
public class GrupConverter implements Converter {

    private GrupDao grupDao;
            
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
        return this.getGrupDao().findById(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object o) {
        Grup g = (Grup) o;
        return g.getId().toString();
    }

    public GrupDao getGrupDao() {
        if(this.grupDao == null)
            this.grupDao = new GrupDao();
        return grupDao;
    }

   
    
}
