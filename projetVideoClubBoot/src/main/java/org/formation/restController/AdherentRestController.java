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

import org.formation.metier.Adherent;
import org.formation.metier.view.*;
import org.formation.repository.AdherentRepository;

@RestController
@RequestMapping("/rest/adherent")
public class AdherentRestController {
	@Autowired
	private AdherentRepository adherentRepository;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping(value= {"","/"})
	public ResponseEntity<List<Adherent>> findAll(){
		return list();
	}
	
	@JsonView(JsonViews.AdherentAvecArticles.class)
	@GetMapping(value= {"/article"})
	public ResponseEntity<List<Adherent>> findAllWithArticles(){
		return list();
	}
	
	public ResponseEntity<List<Adherent>> list(){
		return new ResponseEntity<List<Adherent>>(adherentRepository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(value= {"","/"})
	public ResponseEntity<Void> create(@RequestBody Adherent adherent,BindingResult br, UriComponentsBuilder ucb){
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		adherentRepository.save(adherent);
		HttpHeaders headers=new HttpHeaders();
		URI uri=ucb.path("/rest/adherent/{id}").buildAndExpand(adherent.getNumero()).toUri();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	@GetMapping(value= {"/{id}"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Adherent> findById(@PathVariable(name="id")Integer id){
		return findAdherentById(id);
	}
	
	@GetMapping(value= {"/{id}/article"})
	@JsonView(JsonViews.AdherentAvecArticles.class)
	public ResponseEntity<Adherent> findByIdWithArme(@PathVariable(name="id")Integer id){
		return findAdherentById(id);
	}
	
	private ResponseEntity<Adherent> findAdherentById( Integer id){
		Optional<Adherent> opt=adherentRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Adherent>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Adherent>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value= {"/{id}"})
	public ResponseEntity<Adherent> update(@PathVariable(name="id") Integer id,@Valid@RequestBody Adherent adherent){
		Optional<Adherent> opt=adherentRepository.findById(id);
		if (opt.isPresent()) {
			Adherent adherentEnBase=opt.get();//versionàjour
			adherentEnBase.setPrenom((adherent.getPrenom()!=null)?adherent.getPrenom():adherentEnBase.getPrenom());
			adherentEnBase.setNom((adherent.getNom()!=null)?adherent.getNom():adherentEnBase.getNom());
			adherentEnBase.setDtNaiss((adherent.getDtNaiss()!=null)?adherent.getDtNaiss():adherentEnBase.getDtNaiss());
			adherentEnBase.setAdresse((adherent.getAdresse()!=null)?adherent.getAdresse():adherentEnBase.getAdresse());
			adherentEnBase.setAdresse(adherent.getAdresse());
			
			adherentRepository.save(adherentEnBase);
			return new  ResponseEntity<>(HttpStatus.OK);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Adherent> delete(@PathVariable(name="id") Integer id){
		Optional<Adherent> opt=adherentRepository.findById(id);
		if (opt.isPresent()) {
			adherentRepository.deleteById(id);
			return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
