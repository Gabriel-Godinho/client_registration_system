/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2022 Organization Name
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */
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
