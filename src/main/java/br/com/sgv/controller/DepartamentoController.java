package br.com.sgv.controller;

import br.com.sgv.model.Departamento;
import br.com.sgv.model.Funcionario;
import br.com.sgv.repository.DepartamentoRepository;
import br.com.sgv.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import java.util.ArrayList;
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
@RequestMapping("/departamentos")
public class DepartamentoController {
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @GetMapping()
    public String listar(Model model) {
        model.addAttribute("listaDepartamento", departamentoRepository.findAll());
        
        return "gerenciar_departamentos";
    }
    
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("listaFuncionario", funcionarioRepository.findAll());
        model.addAttribute("departamento", new Departamento());
        model.addAttribute("funcionario", new Funcionario());
        return "editar_departamento";
    }

    @GetMapping("/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<Departamento> departamento = departamentoRepository.findById(id);
        model.addAttribute("listaFuncionario", funcionarioRepository.findAll());
        model.addAttribute("departamento", departamento.get());
        model.addAttribute("funcionario", new Funcionario());
        return "editar_departamento";
    }
    
    
    @PostMapping()
    public String salvar(@Valid Departamento departamento, BindingResult result) {
        if (result.hasErrors()) {
            for (var e : result.getAllErrors()) {
                System.out.println(e);
            }
            return "editar_departamento";
        }
        departamentoRepository.save(departamento);
        return String.format("redirect:/departamentos/%d", departamento.getCodigoDepartamento());
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable("id") long id) {
        departamentoRepository.deleteById(id);
        return "redirect:/departamentos";
    }
    
    @PostMapping("/{id}/funcionario")
    public String adicionarFuncionario(
            @PathVariable("id") long idDepartamento,
            @Valid Funcionario funcionario,
            Model model) {
        Departamento departamento = departamentoRepository
                            .findById(idDepartamento)
                            .orElse(new Departamento(idDepartamento, "", new ArrayList<>()));
        
        departamento.adicionarFuncionario(funcionario);
        departamentoRepository.save(departamento);
       
        return "redirect:/departamentos";
    }
    
    @DeleteMapping("/{idDep}/funcionario/{idFunc}")
    public String removerFuncionario(
            @PathVariable("idDep") long idDepartamento,
            @PathVariable("idFunc") long idFuncionario,
            Model model
    ) {
        // FIXME: Handle optionals properly
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();
        Departamento departamento = departamentoRepository.findById(idDepartamento).get();
        
        departamento.removerFuncionario(funcionario);
        departamentoRepository.save(departamento);
        
        return "redirect:/departamentos";
    }

}
