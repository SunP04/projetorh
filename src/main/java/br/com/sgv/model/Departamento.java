package br.com.sgv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigoDepartamento;
    private String descricao;
    
    @OneToMany
    private List<Funcionario> funcionarios;
    
    public Departamento() {
        this.funcionarios = new ArrayList<>();
    }
    
    public Departamento(long codigo, String descricao, List<Funcionario> funcionarios) {
        this.codigoDepartamento = codigo;
        this.descricao = descricao;
        this.funcionarios = funcionarios;
    }
    
    public void adicionarFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }
    
    public void removerFuncionario(Funcionario funcionario) {
        this.funcionarios.remove(funcionario);
    }
}
