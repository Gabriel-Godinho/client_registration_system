package br.univille.sistema;

import br.univille.sistema.view.ListagemClientes;

public class App3 {
    public static void main(String[] args) {
        ListagemClientes listing = new ListagemClientes();
        System.out.println(listing.getHeight());
        System.out.println(listing.getWidth());
    }
}
