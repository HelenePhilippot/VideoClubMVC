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
@RequestMapping("/rest/article")
public class ArticleRestController {
	@Autowired
	private ArticleRepository articleRepository;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping(value= {"","/"})
	public ResponseEntity<List<Article>> findAll(){
		return list();
	}
	
	@JsonView(JsonViews.ArticleAvecFilm.class)
	@GetMapping(value= {"/article"})
	public ResponseEntity<List<Article>> findAllWithFilm(){
		return list();
	}
	
	public ResponseEntity<List<Article>> list(){
		return new ResponseEntity<List<Article>>(articleRepository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(value= {"","/"})
	public ResponseEntity<Void> create(@RequestBody Article article,BindingResult br, UriComponentsBuilder ucb){
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		articleRepository.save(article);
		HttpHeaders headers=new HttpHeaders();
		URI uri=ucb.path("/rest/article/{numero}").buildAndExpand(article.getId()).toUri();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	@GetMapping(value= {"/{numero}"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Article> findById(@PathVariable(name="numero")Integer numero){
		return findArticleById(numero);
	}
	
	@GetMapping(value= {"/{numero}/film"})
	@JsonView(JsonViews.ArticleAvecFilm.class)
	public ResponseEntity<Article> findByIdWithFilm(@PathVariable(name="numero")Integer numero){
		return findArticleById(numero);
	}
	
	private ResponseEntity<Article> findArticleById( Integer numero){
		Optional<Article> opt=articleRepository.findById(numero);
		if (opt.isPresent()) {
			return new ResponseEntity<Article>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value= {"/{numero}"})
	public ResponseEntity<Article> update(@PathVariable(name="numero") Integer numero,@Valid@RequestBody Article article){
		Optional<Article> opt=articleRepository.findById(numero);
		if (opt.isPresent()) {
			Article articleEnBase=opt.get();//versionàjour
			articleEnBase.setEmprunteur((article.getEmprunteur()!=null)?article.getEmprunteur():articleEnBase.getEmprunteur());
			articleEnBase.setFilm((article.getFilm()!=null)?article.getFilm():articleEnBase.getFilm());
			articleEnBase.setNbDisques((article.getNbDisques()!=null)?article.getNbDisques():articleEnBase.getNbDisques());
						
			articleRepository.save(articleEnBase);
			return new  ResponseEntity<>(HttpStatus.OK);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Article> delete(@PathVariable(name="numero") Integer numero){
		Optional<Article> opt=articleRepository.findById(numero);
		if (opt.isPresent()) {
			articleRepository.deleteById(numero);
			return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
