package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaEnum;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Produto p = produtoDAO.buscarPorId(1L);

        System.out.println(p.getPreco());

        List<Produto> todos = produtoDAO.buscarTodos();
        todos.forEach(p2 -> System.out.println(p.getNome()));

        todos = produtoDAO.buscarPorNome("Xiaomi Redmi");
        todos.forEach(p2 -> System.out.println(p.getDescricao()));

        todos = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
        todos.forEach(p2 -> System.out.println(p.getCategoria().getNome()));

        BigDecimal preco = produtoDAO.buscarPrecoPorId(1L);
        System.out.println("Preço: " + preco);
    }

    private static void cadastrarProduto() {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Topper", new BigDecimal("800"), CategoriaEnum.CELULARES, celulares);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
