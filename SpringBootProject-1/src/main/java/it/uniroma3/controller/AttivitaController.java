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

import it.uniroma3.controller.validator.AttivitaValidator;
import it.uniroma3.model.Attivita;
import it.uniroma3.service.AttivitaService;

@Controller
public class AttivitaController {
	
	@Autowired
	private AttivitaService attivitaService;
	
	@Autowired
	private AttivitaValidator validator;
	
	@RequestMapping("/attivitaList")
	public String attivitaList(Model model) {
		model.addAttribute("attivitaList",this.attivitaService.findAll());
		return "attivitaList";
	}
	
	@RequestMapping(value = "/attivita/{id}", method = RequestMethod.GET)
	public String getAllievo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("attivita", this.attivitaService.findById(id));
		return "showAttivita";
	}
	
	@RequestMapping("/addAttivita")
	public String addAttivita(Model model) {
		model.addAttribute("attivita", new Attivita());
		return "attivitaForm";
	}
	
	@RequestMapping(value = "/attivita" , method = RequestMethod.POST )
	public String newAttivita(@Valid @ModelAttribute ("attivita") Attivita attivita, Model model, BindingResult result) {

		this.validator.validate(attivita, result);

		if(this.attivitaService.alreadyExists(attivita)) {
			model.addAttribute("exists", "L'allievo gia' è presente nel database");
			return "allievoForm";
		}
		else {
			if(!result.hasErrors()) {
				this.attivitaService.save(attivita);
				model.addAttribute("attivitaList", this.attivitaService.findAll());
				return "attivitaList";
			}
		}

		return "allievoForm";
	}


	

}
