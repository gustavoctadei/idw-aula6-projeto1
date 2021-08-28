/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.UsuarioPermissao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gustavo
 */

@Repository
@Transactional
public class UsuarioPermissaoDao {
    
    @PersistenceContext
    private EntityManager em;
    
    public UsuarioPermissao salvar(UsuarioPermissao usuarioPermissao) {
        return (UsuarioPermissao) em.merge(usuarioPermissao);
    }
    
//    public void excluir(UsuarioPermissao usuarioPermissao) {
//        em.remove( em.contains(usuarioPermissao)? usuarioPermissao : em.merge(usuarioPermissao) );
//    }
    
}
