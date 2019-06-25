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
import org.springframework.web.bind.annotation.PostMapping;

import videoClub.model.Film;
import videoClub.repository.FilmRepository;
import videoClub.repository.RealisateurRepository;

@Controller
@RequestMapping ("/film")
public class FilmController {

	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private RealisateurRepository realisateurRepository;
	
	
	
	/*
	 * @GetMapping("/delete") public String delete(@RequestParam(name="id") int id,
	 * Model model) { filmRepository.deleteById(id); return
	 * "redirect:/article/list";
	 * 
	 * 
	 * }
	 */
	@GetMapping("/addFilm")
	public ModelAndView addFilm() {
		return goEditFilm(new Film());
	}
	public ModelAndView goEditFilm(Film film) {
		return new ModelAndView("film/editFilm", "film", film);
	}
	
	
	@PostMapping("/saveFilm")
	private ModelAndView saveFilm (Film film) {
		filmRepository.save(film);
		return new ModelAndView("redirect:/article/list");
	}
	
	
	@GetMapping("/editFilm")
	public ModelAndView editFilm(@RequestParam(name="id") int id) {
		Optional<Film> opt = filmRepository.findById(id);
		if(opt.isPresent()) {
			return goEditFilm(opt.get());}
		else {return new ModelAndView ("redirect:/article/list");}
		}
}
