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
package br.univille.sistema.entity;

import java.util.Date;

public class Cliente {
    
    // Atributos
    private long id;
    private String name;
    private String cpf;
    private Date birthDate;

    // Construtores
    public Cliente() {
        this.id = -1;
    }

    public Cliente(String nome, String CPF) {
        
        this();
        
        if(!name.equals("") || !CPF.equals("")) {
            this.name = nome;
            this.cpf = CPF;
        }
    }

    // GETTER E SETTER - id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // GETTER E SETTER - name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // GETTER E SETTER - CPF
    public String getCPF() {
        return cpf;
    }

    public void setCPF(String CPF) {
        cpf = CPF;
    }

    // GETTER E SETTER - birthDate
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }   
}
