package br.com.sgv.model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Data
public class INSS extends Calculo {
    private BigDecimal aliquota;
    private BigDecimal parcelaDeduzir;
    private int anoCalculo;

    @Override
    BigDecimal aplicarCalculo(Funcionario funcionario) {
        // Tecnicamente incorreto, horas extras fazem parte da base de INSS
        var baseInss = funcionario.getSalarioBruto();
        // Calculo simplificado de INSS (aproximado): base * aliquota - constante
        var valorInss = baseInss.multiply(aliquota).subtract(parcelaDeduzir);

        // Valor de INSS deduz do salário, então é retornado um valor negativo
        return valorInss.multiply(new BigDecimal(-1));
    }

}
