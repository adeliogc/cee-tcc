package br.com.tcc.cee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.cee.modelo.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	List<Cidade> findByNomeContaining(String descricao);

}
