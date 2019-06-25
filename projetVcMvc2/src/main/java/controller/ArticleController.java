package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import formationJpaSpring.repository.SoldatRepository;

@Controller
@RequestMapping ("/article")//
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping("/list")
	public String list(Model model) {
		
		model.addAttribute("listeArticle", articleRepository.findAll());
		return "article/list";
	}
}
