/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gustavo
 */

@Repository
public class AuthenticationDao implements UserDetailsService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public UserDetails loadUserByUsername(String login) {
        String sql = "select u from Usuario u where u.login = :login";
        
        try {
            return em.createQuery(sql, Usuario.class).setParameter("login", login).getSingleResult();
            
        } catch (Exception e) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }
}
