package br.com.alura.loja.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioDeVendasVO {

    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDate dataUltimaVenda;

    @Override
    public String toString() {
        return "RelatorioDeVendasVO{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidadeVendida=" + quantidadeVendida +
                ", dataUltimaVenda=" + dataUltimaVenda +
                '}';
    }
}
