package br.univille.loginproject.service;

import br.univille.loginproject.dao.LoginDAO;
import br.univille.loginproject.entitys.Login;

public class LoginService {
    
    private LoginDAO dao = new LoginDAO();

    public boolean enter(Login login) {
        return dao.enter(login);
    }

    public void register(Login login) {
        dao.register(login);
    }

    public boolean compare(String user) {
        return dao.compare(user);
    }
}
