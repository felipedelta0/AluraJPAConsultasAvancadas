package br.com.alura.loja.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_enum")
    private CategoriaEnum categoriaEnum;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Produto(String nome, String descricao, BigDecimal preco, CategoriaEnum categoriaEnum, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoriaEnum = categoriaEnum;
        this.categoria = categoria;
    }
}
