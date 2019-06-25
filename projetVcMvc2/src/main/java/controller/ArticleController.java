package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping("/addBluRayt")
	public ModelAndView addPistolet() {
		return goEdit(new BluRay());
	}
	public ModelAndView goEdit(Article article) {
		return new ModelAndView("article/edit", "article", article);
	}
}
