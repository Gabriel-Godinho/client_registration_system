package br.univille.sistema.service;

import java.util.ArrayList;
// import java.util.List;

import br.univille.sistema.dao.ClienteDAO;
import br.univille.sistema.entity.Cliente;

public class ClientService {
    
    private ClienteDAO clienteDAO = new ClienteDAO();
    //private ArrayList<Cliente> listClients = new ArrayList<>();

    public ArrayList<Cliente> getListClients() {
        return clienteDAO.getAll();
    }

    public ClientService() {
        
    }

    public ArrayList<Cliente> getAllClientes() {
        return clienteDAO.getAll();
    }

    public Cliente save(Cliente cliente) {
        /*if (cliente.getId() > 0 && !listClients.contains(cliente)) {
            //listClients.add(cliente);
        } else {
            listClients.set(listagem.rowNumber(), form.getCliente());
        }*/

        clienteDAO.save(cliente);

        return cliente;
    }

    public void remove(Cliente cliente) {
        clienteDAO.delete(cliente.getId());
    }
}
