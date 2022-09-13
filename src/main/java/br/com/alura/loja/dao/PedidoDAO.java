package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Pedido;
import jakarta.persistence.EntityManager;

public class PedidoDAO {

    private final EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public void atualizar(Pedido pedido) {
        this.em.merge(pedido);
    }

    public void remover(Pedido pedido) {
        pedido = em.merge(pedido);
        this.em.remove(pedido);
    }

    public Pedido buscarPorId(Long id) {
        return this.em.find(Pedido.class, id);
    }
}
