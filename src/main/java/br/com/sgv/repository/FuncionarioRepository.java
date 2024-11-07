package br.com.sgv.repository;

import br.com.sgv.model.Funcionario;
import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Long>{

}
