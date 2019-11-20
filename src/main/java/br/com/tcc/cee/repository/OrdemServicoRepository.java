package br.com.tcc.cee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tcc.cee.modelo.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

	@Query(value = "SELECT (COUNT(*) + 1)  FROM ORDEM_SERVICOS WHERE YEAR(DATA_ABERTURA) = :ano", nativeQuery = true)
	int totalOsNoAno(@Param("ano") int anoDaOS);

	@Query(nativeQuery = true, value = "SELECT OS.* FROM ORDEM_SERVICOS OS JOIN ORDEM_SERVICO_ITEM IT ON IT.ORDEM_SERVICO_ID  = OS.ID JOIN EQUIPAMENTOS EQ ON EQ.ID  = IT.EQUIPAMENTO_ID  WHERE EQ.NUMERO_SERIE  = :id")
	List<OrdemServico> findByItensEquipamentoNumeroSerie(@Param("id") String numeroSerie);	
	
	OrdemServico findByNumeroOrdemServico(String numeroOrdemServico);

}
