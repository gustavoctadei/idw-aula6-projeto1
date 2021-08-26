/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Gustavo
 */
public class Md5PasswordEncoder implements PasswordEncoder {
    
    MD5 md5 = new MD5();
    
    @Override
    public String encode(CharSequence cs) {
        return md5.getHashString(cs.toString());
    }

    @Override
    public boolean matches(CharSequence cs, String string) {
        String hashSenha = md5.getHashString(cs.toString());
        
        return hashSenha.equals(string);
    }
    
}
