package br.com.tcc.cee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tcc.cee.modelo.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

	@Query(value = "SELECT (COUNT(*) + 1)  FROM ORDEM_SERVICOS WHERE YEAR(DATA_ABERTURA) = :ano", nativeQuery = true)
	int totalOsNoAno(@Param("ano") int anoDaOS);

}
