/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.SponsorlukTipDao;
import entity.SponsorlukTip;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "sponsorlukTipConverter")
public class SponsorlukTipConverter implements Converter{

    private SponsorlukTipDao spnTipDao;
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
        return this.getSpnTipDao().findById(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object o) {
        SponsorlukTip s = (SponsorlukTip) o;
        return s.getId().toString();
    }

    public SponsorlukTipDao getSpnTipDao() {
        if(this.spnTipDao == null)
            this.spnTipDao = new SponsorlukTipDao();
        return spnTipDao;
    }
    
    
}
