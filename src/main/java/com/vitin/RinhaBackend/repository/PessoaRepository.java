package com.vitin.RinhaBackend.repository;

import com.vitin.RinhaBackend.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
    Optional<Pessoa> findByApelido(String apelido);

    @Query(
            nativeQuery = true,
            value = "SELECT p.* FROM Pessoa p " +
                    "WHERE CAST(p.apelido AS text) ILIKE '%' || CAST(:termo AS text) || '%' OR " +
                    "CAST(p.nome AS text) ILIKE '%' || CAST(:termo AS text) || '%' OR " +
                    "CAST(p.stack AS text) ILIKE '%' || CAST(:termo AS text) || '%' " +
                    "LIMIT 50"
    )
    List<Pessoa> buscarTermo(@Param("termo") String termo);

}
