package br.univille;

import br.univille.loginproject.view.LoginView;

public class Application {
    public static void main(String[] args) {
        LoginView view = new LoginView();
        System.out.println(view.getAlignmentX());
        System.out.println(view.getAlignmentY());
    }
}
