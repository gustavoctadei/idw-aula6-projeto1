/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import util.SingletonConnection;

/**
 *
 * @author Gustavo
 */
public class GenericDao {
    EntityManager em = SingletonConnection.getInstance().getEm();
    EntityTransaction tx = em.getTransaction();
    
    public <T extends Serializable> T getPojo(String str_query, Object... params) {
        T t = null;
        
        try {
            if (!tx.isActive()) {
                tx.begin();
            }
            
            Query qr = em.createQuery(str_query);
            qr.setHint("javax.persistence.cache.storeMode", "REFRESH");
            
            for (int i = 1; i <= params.length; i++) {
                qr.setParameter(i, params[i - 1]);
            }
            
            List<T> results = qr.getResultList();
            if (!results.isEmpty()) {
                t = results.get(0);
            }
            
        } catch (Exception e) {
            System.out.println("Erro na transação com o banco: " + e.getMessage());
        }
        
        return t;
    }

    public Serializable savePojo(Serializable obj) {
        try {
            if (!tx.isActive()) {
                tx.begin();
            }
            
            obj = em.merge(obj);
            tx.commit();
            
            return obj;
            
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro na transação com o banco: " + e.getMessage());
            return null;
        }
    }
    
    public void removePojo(Serializable obj) {
        try {
            if (!tx.isActive()) {
                tx.begin();
            }
            
            em.remove(em.merge(obj));
            tx.commit();
            
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro na transação com o banco: " + e.getMessage());
        }
    }
    
    public <T extends Serializable> List<T> getPureList(Class<T> classToCast, String query, Object... params) {
        List<T> toReturn = null;
        
        try {
            
            if (!tx.isActive()) {
                tx.begin();
            }
            
            Query qr = em.createQuery(query);
            qr.setHint("javax.persistence.cache.storeMode", "REFRESH");
            
            for (int i = 1; i <= params.length; i++) {
                qr.setParameter(i, params[i - 1]);
            }
            
            toReturn = qr.getResultList();
            
        } catch (Exception e) {
            System.out.println("Erro na transação com o banco: " + e.getMessage());
        }
        
        return toReturn;
    }
    
}