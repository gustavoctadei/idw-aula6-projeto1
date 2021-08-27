/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Gustavo
 */
public enum PermissaoEnum {
    
    ADMINISTRADOR(1),
    USUARIO(2),
    USUARIO_VIP(3);
    
    private final Integer id;
    private String descricao;
    
    private PermissaoEnum(Integer idPermissao) {
        this.id = idPermissao;
        
        switch (idPermissao) {
            case 1:
                this.descricao = "ROLE_ADMINISTRADOR";
                break;
            case 2:
                this.descricao = "ROLE_USUARIO";
                break;
            case 3:
                this.descricao = "ROLE_USUARIO_VIP";
                break;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
