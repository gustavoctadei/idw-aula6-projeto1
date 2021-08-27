/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UsuarioPermissaoDao;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import model.Permissao;
import model.PermissaoEnum;
import model.Usuario;
import model.UsuarioPermissao;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Gustavo
 */

@ManagedBean
@Named
@ViewScoped
public class AdministrativoBean {
    
    private Usuario usuarioLogado;
    
    @ManagedProperty("#{usuarioPermissaoDao}")
    private UsuarioPermissaoDao usuarioPermissaoDao;

    public AdministrativoBean() {
        usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    public void atribuiPermissaoAdministrador() {
        atribuiPermissao(usuarioLogado, new Permissao(PermissaoEnum.ADMINISTRADOR));
    }
    
    public void atribuiPermissaoUsuarioVip() {
        atribuiPermissao(usuarioLogado, new Permissao(PermissaoEnum.USUARIO_VIP));
    }
    
    private void atribuiPermissao(Usuario usuario, Permissao permissao) {
        
        Collection<UsuarioPermissao> permissoes = usuario.getUsuarioPermissaoCollection();
        
        for (UsuarioPermissao up : permissoes) {
            if (up.getIdPermissao().equals(permissao)) return;
        }
        
        UsuarioPermissao up = usuarioPermissaoDao.salvar( new UsuarioPermissao(usuario, permissao) );
        usuario.getUsuarioPermissaoCollection().add(up);
    }

    public UsuarioPermissaoDao getUsuarioPermissaoDao() {
        return usuarioPermissaoDao;
    }

    public void setUsuarioPermissaoDao(UsuarioPermissaoDao usuarioPermissaoDao) {
        this.usuarioPermissaoDao = usuarioPermissaoDao;
    }
    
}
