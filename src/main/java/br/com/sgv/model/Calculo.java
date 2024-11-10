package br.com.sgv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Data
@Inheritance (strategy=InheritanceType.JOINED)
public abstract class Calculo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String descricao;

    abstract BigDecimal aplicarCalculo(Funcionario funcionario);
}
