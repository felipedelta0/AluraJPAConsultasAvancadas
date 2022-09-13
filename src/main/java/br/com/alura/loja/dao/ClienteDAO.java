package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Pedido;
import jakarta.persistence.EntityManager;

public class ClienteDAO {

    private final EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public void atualizar(Cliente cliente) {
        this.em.merge(cliente);
    }

    public void remover(Cliente cliente) {
        cliente = em.merge(cliente);
        this.em.remove(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return this.em.find(Cliente.class, id);
    }
}
