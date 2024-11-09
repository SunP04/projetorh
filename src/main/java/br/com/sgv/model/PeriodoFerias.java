package br.com.sgv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Entity
public class PeriodoFerias {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    private Funcionario funcionario;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public String getDataInicioFormatada() {
        return this.dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getDataFimFormatada() {
        return this.dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
