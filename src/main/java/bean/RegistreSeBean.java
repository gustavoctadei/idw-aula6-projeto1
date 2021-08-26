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
import util.MD5;

/**
 *
 * @author Gustavo
 */

@ManagedBean
@Named
@ViewScoped
public class RegistreSeBean {
    
    private Usuario usuario;
    private String confirmaSenha;
    
    @ManagedProperty("#{usuarioDao}")
    private UsuarioDao usuarioDao;

    public RegistreSeBean() {
        usuario = (Usuario) FacesUtils.getAtributoFlash("usuarioCadastrado");
        
        if (usuario == null) {
            usuario = new Usuario();
        }
    }
    
    public String salvar() {
        usuario.setSenha( MD5.getHashString(usuario.getSenha()) );
        confirmaSenha = MD5.getHashString(confirmaSenha);
        
        if (!usuario.getSenha().equals(this.confirmaSenha)) {
            FacesUtils.mensagem("A senha não foi confirmada corretamente.", FacesMessage.SEVERITY_WARN);
            return null;
        }
        
        this.usuario.setAtivo(true);
        
        usuario = usuarioDao.salvar(this.usuario);
        
        FacesUtils.putAtributoFlash("usuarioCadastrado", usuario);
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
    
}