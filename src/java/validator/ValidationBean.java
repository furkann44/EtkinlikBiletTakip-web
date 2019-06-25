/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Furkan
 */
@FacesValidator("tcValidator")
public class ValidationBean implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uıc, Object o) throws ValidatorException {

         Long model = (Long) o;
        long x=1,y=1;
        while(model/x > 9){
            x = x *10;
            y++;
        }

        if (y <11 || y > 11 ) {
            FacesMessage msg = new FacesMessage(
                    " Tc 11 haneli olmalıdır.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);

        }
    }

}
