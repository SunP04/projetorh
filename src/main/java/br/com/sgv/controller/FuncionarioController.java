package br.com.sgv.controller;

import br.com.sgv.model.Funcionario;
import br.com.sgv.model.PessoaFisica;
import br.com.sgv.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping()
    public String listar(Model model) {
        model.addAttribute("listafuncionario", funcionarioRepository.findAll());
        
        return "gerenciar_funcionarios";
    }
    
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "editar_funcionario";
    }

    @GetMapping("/{id}")
    public String editar(@PathVariable("id") String id, Model model) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        model.addAttribute("funcionario", funcionario.get());
        return "editar_funcionario";
    }
    
    
    @PostMapping()
    public String salvar(@Valid Funcionario funcionario, BindingResult result) {
        if (result.hasErrors()) {
            for (var e : result.getAllErrors()) {
                System.out.println(e);
            }
            return "editar_funcionario";
        }
        funcionarioRepository.save(funcionario);
        return "redirect:/funcionarios";
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable("id") String id) {
        funcionarioRepository.deleteById(id);
        return "redirect:/funcionarios";
    }
}
         