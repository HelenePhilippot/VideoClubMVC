package org.formation.restController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import org.formation.metier.*;
import org.formation.metier.view.*;
import org.formation.repository.*;

@RestController
@RequestMapping("/rest/film")
public class FilmRestController {
	@Autowired
	private FilmRepository filmRepository;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping(value= {"","/"})
	public ResponseEntity<List<Film>> findAll(){
		return list();
	}
	
	@JsonView(JsonViews.FilmAvecRealisateur.class)
	@GetMapping(value= {"/film"})
	public ResponseEntity<List<Film>> findAllWithRealisateur(){
		return list();
	}
	
	public ResponseEntity<List<Film>> list(){
		return new ResponseEntity<List<Film>>(filmRepository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(value= {"","/"})
	public ResponseEntity<Void> create(@RequestBody Film film,BindingResult br, UriComponentsBuilder ucb){
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		filmRepository.save(film);
		HttpHeaders headers=new HttpHeaders();
		URI uri=ucb.path("/rest/film/{id}").buildAndExpand(film.getId()).toUri();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	@GetMapping(value= {"/{id}"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Film> findById(@PathVariable(name="id")Integer id){
		return findFilmById(id);
	}
	
	@GetMapping(value= {"/{id}/film"})
	@JsonView(JsonViews.FilmAvecRealisateur.class)
	public ResponseEntity<Film> findByIdWithFilm(@PathVariable(name="id")Integer id){
		return findFilmById(id);
	}
	
	private ResponseEntity<Film> findFilmById( Integer id){
		Optional<Film> opt=filmRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Film>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value= {"/{id}"})
	public ResponseEntity<Article> update(@PathVariable(name="id") Integer id,@Valid@RequestBody Film film){
		Optional<Film> opt=filmRepository.findById(id);
		if (opt.isPresent()) {
			Film filmEnBase=opt.get();//versionàjour
			filmEnBase.setDateSortie((film.getDateSortie()!=null)?film.getDateSortie():filmEnBase.getDateSortie());
			filmEnBase.setRealisateur((film.getRealisateur()!=null)?film.getRealisateur():filmEnBase.getRealisateur());
			filmEnBase.setTitre((film.getTitre()!=null)?film.getTitre():filmEnBase.getTitre());
						
			filmRepository.save(filmEnBase);
			return new  ResponseEntity<>(HttpStatus.OK);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Film> delete(@PathVariable(name="id") Integer id){
		Optional<Film> opt=filmRepository.findById(id);
		if (opt.isPresent()) {
			filmRepository.deleteById(id);
			return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
