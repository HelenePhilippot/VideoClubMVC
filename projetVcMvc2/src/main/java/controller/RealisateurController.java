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

import videoClub.model.Realisateur;
import videoClub.repository.RealisateurRepository;

@Controller
@RequestMapping("/article") //
public class RealisateurController {

	@Autowired
	private RealisateurRepository realisateurRepository;

	/*
	 * @GetMapping("/delete") public String delete(@RequestParam(name = "id") int
	 * id, Model model) { realisateurRepository.deleteById(id); return
	 * "redirect:/article/list";
	 * 
	 * }
	 */

	@GetMapping("/addRealisateur")
	public ModelAndView addRealisateur() {
		return goEdit(new Realisateur());
	}


	public ModelAndView goEdit(Realisateur realisateur) {
		return new ModelAndView("realisateur/edit", "realisateur", realisateur);
	}


	@PostMapping("/saveRealisateur")
	public ModelAndView saveRealisateur(@ModelAttribute("realisateur") Realisateur b) {
		return save(b);
	}

	private ModelAndView save(Realisateur realisateur) {
		realisateurRepository.save(realisateur);
		return new ModelAndView("redirect:/article/list");
	}

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(name = "id") int id) {
		Optional<Realisateur> opt = realisateurRepository.findById(id);
		if (opt.isPresent()) {
			return goEdit(opt.get());
		} else {
			return new ModelAndView("redirect:/article/list");
		}
	}
}
