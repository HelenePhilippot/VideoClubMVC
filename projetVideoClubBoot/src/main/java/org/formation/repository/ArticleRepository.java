package org.formation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import  org.formation.metier.Adherent;
import  org.formation.metier.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	@Query("select a from Article a where a.emprunteur=:adherent")
    public List<Article> findByEmprunteur(@Param("adherent") Adherent adherent);
}
