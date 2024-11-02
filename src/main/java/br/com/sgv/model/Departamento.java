package br.com.sgv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String codigoDepartamento;
    private String descricao;
    
    @OneToMany
    private List<Funcionario> funcionarios;
}
