package br.com.alura.loja.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

    /*
        Relacionamentos toOne: carregamento eager, carrega junto com a entidade mesmo que não use
        Relacionamentos toMany: carregamento lazy, apenas se for feito o acesso
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data = LocalDate.now();

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // sempre que for ToMany não carrega automaticamente, apenas quando chama
    private List<ItemPedido> itens = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY) // sempre que for xToOne -> carrega junto através de um join
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(ItemPedido item) {
        item.setPedido(this);
        this.itens.add(item);
        valorTotal = valorTotal.add(item.getValorTotal());
    }
}
