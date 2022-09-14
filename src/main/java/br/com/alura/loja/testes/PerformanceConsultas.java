package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class PerformanceConsultas {

    public static void main(String[] args) {
        popularBancoDeDados();

        EntityManager em = JPAUtil.getEntityManager();

        Pedido pedido = em.find(Pedido.class, 1L);
        System.out.println(pedido.getItens().size());
    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria informatica = new Categoria("INFORMATICA");
        Categoria livros = new Categoria("LIVROS");

        Produto celular = new Produto("Xiaomi Redmi", "Topper", new BigDecimal("800"), CategoriaEnum.CELULARES, celulares);
        Produto notebook = new Produto("Macbook Pro", "Apple", new BigDecimal("4999.99"), CategoriaEnum.INFORMATICA, informatica);
        Produto livro = new Produto("Percy Jackson", "Sei la", new BigDecimal("39.99"), CategoriaEnum.LIVROS, livros);

        Cliente cliente = new Cliente("Luis Felipe", "11122233344");
        Cliente cliente2 = new Cliente("Celisa", "99988877766");

        Pedido pedido = new Pedido(cliente);
        Pedido pedido2 = new Pedido(cliente2);

        pedido.adicionarItem(new ItemPedido(21, pedido, celular));
        pedido.adicionarItem(new ItemPedido(7, pedido, notebook));
        pedido.adicionarItem(new ItemPedido(8, pedido, livro));

        pedido2.adicionarItem(new ItemPedido(34, pedido2, livro));
        pedido2.adicionarItem(new ItemPedido(2, pedido2, notebook));

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        categoriaDAO.cadastrar(informatica);
        categoriaDAO.cadastrar(livros);
        produtoDAO.cadastrar(celular);
        produtoDAO.cadastrar(notebook);
        produtoDAO.cadastrar(livro);
        clienteDAO.cadastrar(cliente);
        clienteDAO.cadastrar(cliente2);
        pedidoDAO.cadastrar(pedido);
        pedidoDAO.cadastrar(pedido2);

        em.getTransaction().commit();
        em.close();
    }
}
