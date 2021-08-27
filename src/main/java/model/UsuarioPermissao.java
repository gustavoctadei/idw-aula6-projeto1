/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "usuario_permissao", catalog = "mvc", schema = "")
@NamedQueries({
    @NamedQuery(name = "UsuarioPermissao.findAll", query = "SELECT u FROM UsuarioPermissao u")})
public class UsuarioPermissao implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_permissao", nullable = false)
    private Integer idUsuarioPermissao;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "id_permissao", referencedColumnName = "id_permissao", nullable = false)
    @ManyToOne(optional = false)
    private Permissao idPermissao;

    public UsuarioPermissao() {
    }
    
    public UsuarioPermissao(Usuario usuario, Permissao permissao) {
        this.idUsuario = usuario;
        this.idPermissao = permissao;
    }

    public UsuarioPermissao(Integer idUsuarioPermissao) {
        this.idUsuarioPermissao = idUsuarioPermissao;
    }

    public Integer getIdUsuarioPermissao() {
        return idUsuarioPermissao;
    }

    public void setIdUsuarioPermissao(Integer idUsuarioPermissao) {
        this.idUsuarioPermissao = idUsuarioPermissao;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Permissao getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(Permissao idPermissao) {
        this.idPermissao = idPermissao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioPermissao != null ? idUsuarioPermissao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioPermissao)) {
            return false;
        }
        UsuarioPermissao other = (UsuarioPermissao) object;
        if ((this.idUsuarioPermissao == null && other.idUsuarioPermissao != null) || (this.idUsuarioPermissao != null && !this.idUsuarioPermissao.equals(other.idUsuarioPermissao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UsuarioPermissao[ idUsuarioPermissao=" + idUsuarioPermissao + " ]";
    }

    @Override
    public String getAuthority() {
        return this.getIdPermissao().getDescricao();
    }
    
}
