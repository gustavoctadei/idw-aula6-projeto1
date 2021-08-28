/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gustavo
 */

@Repository
@Transactional
public class UsuarioDao {
    
    @PersistenceContext
    private EntityManager em;
    
    public Usuario salvar(Usuario usuario) {
        return (Usuario) em.merge(usuario);
    }
    
    public Usuario atualizar(Usuario usuario) {
        return (Usuario) em.merge(usuario);
    }
    
    public void excluir(Usuario usuario) {
        em.remove( em.contains(usuario)? usuario : em.merge(usuario) );
    }
    
    public Usuario carregar(Integer idUsuario) {
        String sql = "select u from Usuario u where u.idUsuario = ?1";
        
        return em.createQuery(sql, Usuario.class).getSingleResult();
    }
    
    public Usuario buscarPorLogin(String login) {
        String sql = "select u from Usuario u where u.login = ?1";
        
        return em.createQuery(sql, Usuario.class).getSingleResult();
    }
    
    public List<Usuario> listar() {
        String sql = "select u from Usuario u";
        
        return em.createQuery(sql, Usuario.class).getResultList();
    }
    
}
