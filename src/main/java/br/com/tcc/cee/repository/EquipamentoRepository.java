package br.com.tcc.cee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.cee.modelo.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>{
	
	List<Equipamento> findByNomeContaining(String nome);


}
