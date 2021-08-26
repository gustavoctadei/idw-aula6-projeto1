/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UsuarioDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import model.Usuario;
import util.FacesUtils;

/**
 *
 * @author Gustavo
 */

@ManagedBean
@Named
@ViewScoped
public class UsuarioBean {
    
    private Usuario usuario;
    private String confirmaSenha;
    
    private String login;
    private String senha;
    
    @ManagedProperty("#{usuarioDao}")
    private UsuarioDao usuarioDao;

    public UsuarioBean() {
        this.usuario = new Usuario();
    }
    
    public String novo() {
        return "/publico/usuario";
    }
    
    public String salvar() {
        
        if (!this.usuario.getSenha().equals(this.confirmaSenha)) {
            FacesUtils.mensagem("A senha não foi confirmada corretamente.", FacesMessage.SEVERITY_WARN);
            return null;
        }
        
        this.usuario.setAtivo(true);
        
        usuarioDao.salvar(this.usuario);
        
        return "usuarioSucesso";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
