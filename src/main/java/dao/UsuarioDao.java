/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Usuario;

/**
 *
 * @author Gustavo
 */
public class UsuarioDao extends GenericDao {
    
    public void salvar(Usuario usuario) {
        savePojo(usuario);
    }
    
    public void atualizar(Usuario usuario) {
        savePojo(usuario);
    }
    
    public void excluir(Usuario usuario) {
        removePojo(usuario);
    }
    
    public Usuario carregar(Integer idUsuario) {
        String sql = "select u from Usuario u where u.idUsuario = ?1";
        
        return (Usuario) getPojo(sql, idUsuario);
    }
    
    public Usuario buscarPorLogin(String login) {
        String sql = "select u from Usuario u where u.login = ?1";
        
        return (Usuario) getPojo(sql, login);
    }
    
    public List<Usuario> listar() {
        String sql = "select u from Usuairo u";
        
        return getPureList(Usuario.class, sql);
    }
    
}
