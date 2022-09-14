package br.com.alura.loja.modelo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaID implements Serializable {

    private static final long serialVersionUID = -8207896143110294006L;
    private String nome;
    private String tipo;
}
