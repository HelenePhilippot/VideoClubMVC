package org.formation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.formation.metier.Adherent;

public interface AdherentRepository extends JpaRepository<Adherent, Integer> {

	@Query("select a from Adherent a left join fetch a.articlesEmpruntes where a.id=:id")
    public Optional<Adherent> findByIdWithArticle(@Param("id") Integer id);
}
