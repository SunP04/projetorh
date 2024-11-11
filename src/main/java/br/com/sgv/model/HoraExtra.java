package br.com.sgv.model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Data
public class HoraExtra extends Calculo {
    // Usada como base uma jornada de 22h semanais
    private static final int horasPorMes = 220;

    private float percentual;

    @Override
    BigDecimal aplicarCalculo(Funcionario funcionario) {
        // salario mensal / horas mensais
        BigDecimal salarioPorHora = funcionario.getSalarioBruto().divide(new BigDecimal(horasPorMes));
        BigDecimal valorHoraExtra = salarioPorHora.multiply(new BigDecimal(getPercentual() + 1));

        return valorHoraExtra;
    }
}
