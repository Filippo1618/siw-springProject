package it.uniroma3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.controller.validator.AllievoValidator;
import it.uniroma3.model.Allievo;
import it.uniroma3.service.AllievoService;

@Controller
public class AllievoController {

	@Autowired
	private AllievoService allievoService;

	@Autowired
	private AllievoValidator validator;

	@RequestMapping("/allievi")
	public String allievi(Model model) {
		model.addAttribute("allievi", this.allievoService.findAll());
		return "allieviList";
	}

	@RequestMapping("/addAllievo")
	public String addAllievo(Model model) {
		model.addAttribute("allievo", new Allievo());
		return "allievoForm";
	}

	@RequestMapping(value = "/allievo/{id}", method = RequestMethod.GET)
	public String getAllievo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("allievo", this.allievoService.findById(id));
		return "showAllievo";
	}

	@RequestMapping(value = "/allievo" , method = RequestMethod.POST )
	public String newAllievo(@Valid @ModelAttribute ("allievo") Allievo allievo, Model model, BindingResult result) {

		this.validator.validate(allievo, result);

		if(this.allievoService.alreadyExists(allievo)) {
			model.addAttribute("exists", "L'allievo gia' è presente nel database");
			return "allievoForm";
		}
		else {
			if(!result.hasErrors()) {
				this.allievoService.save(allievo);
				model.addAttribute("allievi", this.allievoService.findAll());
				return "allieviList";
			}
		}

		return "allievoForm";
	}
}
