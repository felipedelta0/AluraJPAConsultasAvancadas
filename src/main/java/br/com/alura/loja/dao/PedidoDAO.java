package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVO;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

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

    public BigDecimal valorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

//    public List<Object[]> relatorioDeVendas() {
//        String jpql = "SELECT produto.nome, " +
//                "SUM(item.quantidade), " +
//                "MAX(pedido.data) " +
//                "FROM Pedido pedido " +
//                "JOIN pedido.itens item " +
//                "JOIN item.produto produto " +
//                "GROUP BY produto.nome " +
//                "ORDER BY 2 DESC";
//
//        return em.createQuery(jpql, Object[].class)
//                .getResultList();
//    }

    public List<RelatorioDeVendasVO> relatorioDeVendas() {
        String jpql = "SELECT new " +
                "br.com.alura.loja.vo.RelatorioDeVendasVO(" +
                "produto.nome, " +
                "SUM(item.quantidade), " +
                "MAX(pedido.data)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome " +
                "ORDER BY 2 DESC";

        return em.createQuery(jpql, RelatorioDeVendasVO.class)
                .getResultList();
    }
}
