/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gustavo
 */
public class SingletonConnection {
    private static SingletonConnection instance;
    private EntityManagerFactory emf = null;
    private EntityManager em = null;
    
    public static SingletonConnection getInstance() {
        if (instance == null) {
            instance = new SingletonConnection();
        }
        
        return instance;
    }
    
    public EntityManager getEm() {
        try {
            if (emf == null || em == null) {
                emf = Persistence.createEntityManagerFactory("mvcPU");
                em = emf.createEntityManager();
                em.getTransaction().begin();
            
            } else if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            
            return em;
        
        } catch (Exception e) {
            System.out.println("Erro ao criar conexão com o banco de dados: " + e.getMessage());
            return null;
        }
    }
}