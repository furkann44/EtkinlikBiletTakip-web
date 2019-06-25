/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.MekanDao;
import entity.Mekan;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "mekanConverter")
public class MekanConverter implements Converter{

    private MekanDao mekanDao;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
        return this.getMekanDao().findById(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object o) {
        Mekan m = (Mekan) o;
        return m.getId().toString();
    }

    public MekanDao getMekanDao() {
        if(this.mekanDao == null)
            this.mekanDao = new MekanDao();
        return mekanDao;
    }
    
    
}
