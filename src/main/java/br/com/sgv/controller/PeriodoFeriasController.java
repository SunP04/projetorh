package br.com.sgv.controller;

import br.com.sgv.model.PeriodoFerias;
import br.com.sgv.repository.FuncionarioRepository;
import br.com.sgv.repository.PeriodoFeriasRepository;
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
@RequestMapping("/ferias")
public class PeriodoFeriasController {
    @Autowired
    private PeriodoFeriasRepository feriasRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping()
    public String listar(Model model) {
        model.addAttribute("listaFerias", feriasRepository.findAll());
        return "gerenciar_ferias";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("listaFuncionario", funcionarioRepository.findAll());
        model.addAttribute("ferias", new PeriodoFerias());
        return "editar_ferias";
    }

    @GetMapping("/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<PeriodoFerias> ferias = feriasRepository.findById(id);
        if (ferias.isPresent()) {
            model.addAttribute("listaFuncionario", funcionarioRepository.findAll());
            model.addAttribute("ferias", ferias.get());
            return "editar_ferias";
        } else {
            return "redirect:/ferias";
        }
    }

    @PostMapping()
    public String salvar(@Valid PeriodoFerias ferias, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_ferias";
        }
        feriasRepository.save(ferias);
        return "redirect:/ferias";
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable("id") long id) {
        feriasRepository.deleteById(id);
        return "redirect:/ferias";
    }

}
