package com.sdb.xp.service;

import com.sdb.xp.model.Login;

public interface LoginIS {
    
    public void add(Login login);
    public void del(Long id);
    
    public Boolean checkNombreExist(String nombre);
    
    public Login findByNombreAndPasscode(String nombre, String passcode);
    public Login findById(Long id);
}
