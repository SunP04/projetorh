package br.com.sgv.controller;

import br.com.sgv.model.Calculo;
import br.com.sgv.model.HoraExtra;
import br.com.sgv.repository.CalculoRepository;
import br.com.sgv.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculoController {
    @Autowired
    private CalculoRepository calculoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/calculos")
    public String listar(Model model) {
        model.addAttribute("listaCalculo", calculoRepository.findAll());
        
        return "gerenciar_calculos";
    }

    @GetMapping("/funcionarios/{id}/calculos/novo")
    public String novoCalculo(@PathVariable("id") long id, Model model) {
        model.addAttribute("funcionario", funcionarioRepository.findById(id).get());
        model.addAttribute("listaCalculo", calculoRepository.findAll());
        // Filler para cálculo, já que Calculo é abstrato.
        model.addAttribute("calculo", new HoraExtra());

        return "editar_calculo_funcionario";
    }

    @PostMapping("/funcionarios/{idFunc}/calculos")
    public String novoCalculo(
        @PathVariable("idFunc") long idFuncionario,
        @Valid HoraExtra calculo,
        Model model) {
        Optional<Funcionario> fc = funcionarioRepository.findById(idFuncionario);
        
        if (fc.isEmpty()) {
            return "redirect:/funcionarios";
        }
        
        Funcionario funcionario = fc.get();
        funcionario.adicionarCalculo(calculo);
        funcionarioRepository.save(funcionario);
        return String.format("redirect:/funcionarios/%d/calculos/novo", idFuncionario);
    }
}
         
