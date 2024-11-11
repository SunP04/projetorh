package br.com.sgv.repository;

import br.com.sgv.model.Calculo;
import org.springframework.data.repository.CrudRepository;

public interface CalculoRepository extends CrudRepository<Calculo, Long> {
}