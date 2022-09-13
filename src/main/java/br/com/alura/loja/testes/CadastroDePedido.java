package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {

    public static void main(String[] args) {
//        popularBancoDeDados();
//
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);
//
//        Produto produto = produtoDAO.buscarPorId(1L);
//        Produto produto2 = produtoDAO.buscarPorId(2L);
//        Cliente cliente = clienteDAO.buscarPorId(1L);
//        Pedido pedido = new Pedido(cliente);
//
        em.getTransaction().begin();
//
//        pedido.adicionarItem(new ItemPedido(7, pedido, produto));
//        pedido.adicionarItem(new ItemPedido(2, pedido, produto2));
//        pedidoDAO.cadastrar(pedido);
//
        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
        System.out.println("VALOR TOTAL: " + totalVendido);

        List<Object[]> relatorio = pedidoDAO.relatorioDeVendas();

        for (Object[] obj : relatorio) {
            System.out.println(obj[0]);
            System.out.println(obj[1]);
            System.out.println(obj[2]);
        }

        em.close();
    }

    private static void popularBancoDeDados() {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        Categoria celulares = new Categoria("CELULARES");
        Categoria informatica = new Categoria("INFORMATICA");
        Produto celular = new Produto("Xiaomi Redmi", "Topper", new BigDecimal("800"), CategoriaEnum.CELULARES, celulares);
        Produto notebook = new Produto("Macbook Pro", "Apple", new BigDecimal("4999.99"), CategoriaEnum.INFORMATICA, informatica);
        Cliente cliente = new Cliente("Luis Felipe", "11122233344");

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        categoriaDAO.cadastrar(informatica);
        produtoDAO.cadastrar(celular);
        produtoDAO.cadastrar(notebook);
        clienteDAO.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
