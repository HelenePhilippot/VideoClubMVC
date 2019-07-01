package org.formation.restController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.formation.metier.*;
import org.formation.repository.AdherentRepository;
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

@RestController
@RequestMapping("/rest/adherent")
public class AdherentRestController {

	@Autowired
	private AdherentRepository adherentRepository;

//	@JsonView(JsonViews.Commod.class)
//	GetMapping(value= {"","/"})
	public ResponseEntity<List<Adherent>> findAll() {
		return list();
	}

//	@JsonView(JsonViews.Commod.class)
//	@GetMapping(value = { "/Article" })
	private ResponseEntity<List<Adherent>> findAllWithArticle() {
		return list();
	}

	private ResponseEntity<List<Adherent>> list() {
		return new ResponseEntity<List<Adherent>>(adherentRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> create(@Valid @RequestBody Adherent adherent, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		adherentRepository.save(adherent);
		HttpHeaders headers = new HttpHeaders();
		URI uri = uCB.path("/rest/adherent/{numero}").buildAndExpand(adherent.getId()).toUri(); // permet de construite une uri
																						// à partir d'un chemin
		headers.setLocation(uri);
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	//@JsonView(JsonViews.Commod.class)
	@GetMapping("/{numero}")
	public ResponseEntity<Adherent> findAllWithNumero(@PathVariable(name = "numero") Integer numero) {
		return findAdherentById(numero);

	}

	@GetMapping("/{numero}/article")
	//@JsonView(JsonViews.Commod.class)
	public ResponseEntity<Adherent> findByIdWithArticle(@PathVariable(name = "numero") Integer numero) {
		return findAdherentById(numero);
	}

	private ResponseEntity<Adherent> findAdherentById(Integer numero) {
		Optional<Adherent> opt = adherentRepository.findById(numero);
		if (opt.isPresent()) {
			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{numero}") // mise à jours du web service
	public ResponseEntity<Void> Update(@PathVariable(name = "numero") Integer numero, @RequestBody Adherent adherent) {
		Optional<Adherent> opt = adherentRepository.findById(numero);
		if (opt.isPresent()) {
			Adherent adherentEnBase = opt.get();
			adherentEnBase.setPrenom((adherent.getPrenom() != null) ? adherent.getPrenom() : adherentEnBase.getPrenom());
			adherentEnBase.setNom((adherent.getNom() != null) ? adherent.getNom() : adherentEnBase.getNom());
			adherentEnBase.setAdresse((adherent.getAdresse() != null) ? adherent.getAdresse() : adherentEnBase.getAdresse());
			adherentEnBase.setArticle(adherent.getArticle());
			adherentRepository.save(adherentEnBase);
			return new ResponseEntity<>(HttpStatus.OK);

		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{numero}")
	public ResponseEntity<Void> delete(@PathVariable(name = "numero") Integer numero) {
		Optional<Adherent> opt = adherentRepository.findById(numero);
		if (opt.isPresent()) {
			adherentRepository.deleteById(numero);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
