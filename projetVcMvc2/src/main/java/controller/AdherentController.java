package controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import videoClub.model.Adherent;
import videoClub.repository.AdherentRepository;
import videoClub.repository.ArticleRepository;


@Controller
@RequestMapping("/adherent")
public class AdherentController 
{    
	@Autowired
	private AdherentRepository adherentRepository;
	@Autowired
	private ArticleRepository articleRepository;

	@GetMapping("/list")
	public String list(Model model) {

		model.addAttribute("listeAdherent", adherentRepository.findAll());
		return "adherent/list";
	}
	
	@GetMapping("/panier")
	private String panier(@RequestParam(name = "numero") int numero, Model model)
	{
		model.addAttribute("listeArticles", adherentRepository.findByIdWithArticle(numero));
		return "adherent/panier";
	}
	
	
	private String goEdit(Adherent adherent, Model model) {
		model.addAttribute("adherent", adherent);
		model.addAttribute("article", articleRepository.findAll());
		return "adherent/edit";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam(name = "numero") int numero, Model model) {
		Optional<Adherent> opt = adherentRepository.findById(numero);
		if (opt.isPresent()) {
			return goEdit(opt.get(), model);
		} else {
			return goEdit(new Adherent(), model);
		}
	}

	@GetMapping("/add")
	public String add(Model model) {
		return goEdit(new Adherent(), model);
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("adherent") Adherent adherent, BindingResult br, Model model) 
	{
		if (br.hasErrors()) {
			return "adherent/edit";
		}
		adherentRepository.save(adherent);
		return "redirect:/adherent/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "numero") int numero) {
		Optional<Adherent> opt = adherentRepository.findById(numero);
		if (opt.isPresent()) {
			adherentRepository.deleteById(numero);
		}
		return "redirect:/adherent/list";
	}
	
}
