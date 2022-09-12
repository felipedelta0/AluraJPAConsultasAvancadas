package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;
import jakarta.persistence.EntityManager;

public class ProdutoDAO {

    private final EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }
}
