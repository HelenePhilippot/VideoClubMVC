package org.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import metier.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {

}
