/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gustavo
 */
public class FacesUtils {
    
    public static void putAtributoFlash(String chave, Object objeto) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put(chave, objeto);
        
        } catch (Exception e) {}
    }
    
    public static Object getAtributoFlash(String chave) {
        try {
            return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(chave);
            
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void mensagem(String mensagem, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, mensagem, null));
    }
    
}
