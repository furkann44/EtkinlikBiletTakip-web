/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.SponsorDao;
import entity.Sponsor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Furkan
 */
@FacesConverter(value = "sponsorConverter")
public class SponsorConverter implements Converter{

    private SponsorDao sponsorDao;
    
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
        return this.getSponsorDao().findById(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object o) {
        Sponsor s = (Sponsor) o;
        return s.getId().toString();
    }

    public SponsorDao getSponsorDao() {
        if(this.sponsorDao == null)
            this.sponsorDao = new SponsorDao();
        return sponsorDao;
    }

    
    
    
}
