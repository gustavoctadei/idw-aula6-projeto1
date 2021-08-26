/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import model.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Gustavo
 */

@ManagedBean
@Named
@SessionScoped
public class UsuarioBean {
    
    private Usuario usuarioLogado;

    public UsuarioBean() {
        usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
}
