package br.com.sgv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigoCadastro;
    private String nome;
    @Setter(AccessLevel.NONE)
    private String cpf;
    private LocalDate dataAdmissao;
    private LocalDate dataDemissao;

    private BigDecimal salarioBruto = new BigDecimal(0.0);

    @OneToMany
    private List<Calculo> calculos = new ArrayList<>();
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getDataAdmissaoFormatada() {
        return this.dataAdmissao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void adicionarCalculo(Calculo calculo) {
        this.calculos.add(calculo);
    }

    public void removerCalculo(Calculo calculo) {
        this.calculos.remove(calculo);
    }

    public String getSalarioBrutoFormatado() {
        salarioBruto = salarioBruto.setScale(2, RoundingMode.FLOOR);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        String twoDecimalMoney = df.format(salarioBruto);

        return String.format("R$ %s", twoDecimalMoney);
    }
    
    // https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
    private boolean cpfValido(String cpf) {
        // 123.456.789-00
        if (cpf.length() != 14) return false;

        String[] partes = cpf.split("-");

        String digitos = partes[0].replace(".", "");
        Integer verificador = Integer.valueOf(partes[1]);
        
        // 1° Digito Verificador
        int fstDig = 0;
        int peso = 10;

        for (char dig : digitos.toCharArray()) {
            int n = dig - '0';
            fstDig += n * peso;

            peso--;
        }

        int fstRes = fstDig % 11;
        int primeiroVerificador = fstRes >= 10 ? 0 : fstRes;

        // 1° Digito Verificador
        var primeirosDigitos = digitos + (int) (verificador / 10); // 11 primeiros digitos

        int sndDig = 0;
        peso = 11;
        
        for (char dig : primeirosDigitos.toCharArray()) {
            int n = dig - '0';
            sndDig += n * peso;

            peso--;
        }
    
        int sndRes = sndDig % 11;
        int segundoVerificador = sndRes >= 10 ? 0 : fstRes;

        return ((primeiroVerificador * 10) + segundoVerificador) == verificador;
    }
}
