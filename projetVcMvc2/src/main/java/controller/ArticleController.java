package controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import videoClub.model.Article;
import videoClub.model.BluRay;
import videoClub.model.Dvd;
import videoClub.repository.ArticleRepository;


@Controller
@RequestMapping ("/article")//
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping("/list")
	public ModelAndView list() {//gere model et string en meme temps
		
		return new ModelAndView ("article/list", "listeArticle", articleRepository.findAll());//la ou il doit aller, nom de ce qu'on affiche, ce quon affiche
	
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name="id") int id, Model model) {
		articleRepository.deleteById(id);
		return "redirect:/article/list";

	}
	
	@GetMapping("/addDvd")
	public ModelAndView addDvd() {
		return goEdit(new Dvd());
	}
	@GetMapping("/addBluRay")
	public ModelAndView addBluRay() {
		return goEdit(new BluRay());
	}
	public ModelAndView goEdit(Article article) {
		return new ModelAndView("article/edit", "article", article);
	}
	
	
	@PostMapping("/saveDvd")
	public ModelAndView saveDvd(@ModelAttribute("article") Dvd d) {
		return save(d);		
	}
	@PostMapping("/saveBluRay")
	public ModelAndView saveBluray(@ModelAttribute("article") BluRay b) {
		return save(b);	
	}
	private ModelAndView save (Article article) {
		articleRepository.save(article);
		return new ModelAndView("redirect:/article/list");
	}
	
	
	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(name="id") int id) {
		Optional<Article> opt = articleRepository.findById(id);
		if(opt.isPresent()) {
			return goEdit(opt.get());}
		else {return new ModelAndView ("redirect:/article/list");}
		}
}

