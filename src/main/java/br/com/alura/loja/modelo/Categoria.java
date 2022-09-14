package br.com.alura.loja.modelo;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@NoArgsConstructor
public class Categoria {

    @EmbeddedId
    private CategoriaID id;

    public Categoria(String nome) {
        this.id = new CategoriaID(nome, "xpto");
    }

    public String getNome() {
        return id.getNome();
    }
}
