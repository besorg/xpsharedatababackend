package com.sdb.xp.service;

import com.sdb.xp.model.Login;
import com.sdb.xp.repository.LoginR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginS implements LoginIS {
    
    @Autowired
    public LoginR loginR;
    

    @Override
    public void add(Login login) {
        loginR.save(login);
    }

    @Override
    public void del(Long id) {
        loginR.deleteById(id);
    }

    @Override
    public Boolean checkNombreExist(String nombre) {
        return loginR.findByNombre(nombre) != null;
        //loginR.findByNombreContainingAndPasscodeContaining(nombre, password);
    }

    @Override
    public Login findByNombreAndPasscode(String nombre, String passcode) {
        return loginR.findByNombreAndPasscode(nombre, passcode);
    }

    @Override
    public Login findById(Long id) {
        return loginR.getById(id);
    }
}