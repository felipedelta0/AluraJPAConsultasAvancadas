package br.com.alura.loja.testes;

import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Produto celular = new Produto("Xiaomi Redmi", "Topper", new BigDecimal("800"), Categoria.CELULARES);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO dao = new ProdutoDAO(em);

        em.getTransaction().begin();
        dao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
