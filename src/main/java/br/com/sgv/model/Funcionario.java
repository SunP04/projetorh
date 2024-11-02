package br.com.sgv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String codigoCadastro;
    private String nome;
    @Setter(AccessLevel.NONE)
    private String cpf;
    private Date dataAdmissao;
    private Date dataDemissao;
    
    public void setCpf(String cpf) {
        if (cpfValido(cpf)) {
            this.cpf = cpf;
        }
    }
    
    // https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
    private boolean cpfValido(String cpf) {
        // 123.456.789-00
        if (cpf.length() != 14) return false;

        String[] partes = cpf.split("-");

        String digitos = partes[1].replace(".", "");
        Integer verificador = Integer.valueOf(partes[2]);
        
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
