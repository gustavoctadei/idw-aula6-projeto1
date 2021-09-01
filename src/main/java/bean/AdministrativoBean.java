/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UsuarioDao;
import dao.UsuarioPermissaoDao;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import model.Permissao;
import model.PermissaoEnum;
import model.Usuario;
import model.UsuarioPermissao;
import util.FacesUtils;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@Named
@ViewScoped
public class AdministrativoBean {

    private List<Usuario> listaUsuario;
    private Usuario currentUsuario;

    @ManagedProperty("#{usuarioDao}")
    private UsuarioDao usuarioDao;

    @ManagedProperty("#{usuarioPermissaoDao}")
    private UsuarioPermissaoDao usuarioPermissaoDao;

    @PostConstruct
    public void postConstruct() {
        listaUsuario = usuarioDao.listar();
    }

    public void ativar() {
        currentUsuario.setAtivo(true);
        usuarioDao.salvar(currentUsuario);

        currentUsuario = new Usuario();
    }

    public void desativar() {
        currentUsuario.setAtivo(false);
        usuarioDao.salvar(currentUsuario);

        currentUsuario = new Usuario();
    }

    public String editar() {
        FacesUtils.putAtributoFlash("currentUsuario", currentUsuario);

        return "/publico/registre-se";
    }

    public void excluir() {
        usuarioDao.excluir(currentUsuario);
        listaUsuario.remove(currentUsuario);

        currentUsuario = new Usuario();
    }

    public void atribuiPermissaoAdministrador() {
        atribuiPermissao(currentUsuario, new Permissao(PermissaoEnum.ADMINISTRADOR));
    }

    public void atribuiPermissaoUsuarioVip() {
        atribuiPermissao(currentUsuario, new Permissao(PermissaoEnum.USUARIO_VIP));
    }

    public void removePermissaoAdministrador() {
        removePermissao(currentUsuario, new Permissao(PermissaoEnum.ADMINISTRADOR));
    }

    public void removePermissaoUsuarioVip() {
        removePermissao(currentUsuario, new Permissao(PermissaoEnum.USUARIO_VIP));
    }

    private void atribuiPermissao(Usuario usuario, Permissao permissao) {
        Collection<UsuarioPermissao> permissoes = usuario.getUsuarioPermissaoCollection();

        for (UsuarioPermissao up : permissoes) {
            if (up.getIdPermissao().equals(permissao)) return;
        }

        UsuarioPermissao up = usuarioPermissaoDao.salvar(new UsuarioPermissao(usuario, permissao));
        usuario.getUsuarioPermissaoCollection().add(up);

        currentUsuario = new Usuario();
    }

    private void removePermissao(Usuario usuario, Permissao permissao) {
        Collection<UsuarioPermissao> permissoes = usuario.getUsuarioPermissaoCollection();
        UsuarioPermissao remover = null;
        
        for (UsuarioPermissao up : permissoes) {
            if (up.getIdPermissao().equals(permissao)) {
                usuarioPermissaoDao.excluir(up);
                remover = up;
            }
        }
        
        if (remover != null) usuario.getUsuarioPermissaoCollection().remove(remover);
        
        currentUsuario = new Usuario();
    }

    ////////////////////////////////////////////////////////////////////////////
    
    public UsuarioPermissaoDao getUsuarioPermissaoDao() {
        return usuarioPermissaoDao;
    }

    public void setUsuarioPermissaoDao(UsuarioPermissaoDao usuarioPermissaoDao) {
        this.usuarioPermissaoDao = usuarioPermissaoDao;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Usuario getCurrentUsuario() {
        return currentUsuario;
    }

    public void setCurrentUsuario(Usuario currentUsuario) {
        this.currentUsuario = currentUsuario;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

}
