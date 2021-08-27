/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Gustavo
 */
public class SpringUtils {
    
    public static Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
}
