package it.uniroma3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.model.Centro;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.CentroService;

@SpringBootApplication
public class SiwDemoSpringApplication {


	@Autowired
	private AllievoService allievoService;

	@Autowired
	private CentroService centroService;

	@Autowired
	private AttivitaService attivitaService;


	public static void main(String[] args) {
		SpringApplication.run(SiwDemoSpringApplication.class, args);
	}

	@PostConstruct
	public void init() {
		//creo allievi, centri e attivita
		Allievo allievo1 = new Allievo("Filippo","Galimberti", "filippomaria.galimberti.rossi@gmail.com", "0630531", "via pineta sacchetti", new Date());
		Allievo allievo2 = new Allievo("Fabrizio","Cicala", "fabrizio.cicala@hotmail.it", "012345", "via jenner", new Date());
		Centro centroRoma = new Centro("RomaCenter","rmc@gmail.com",100);
		Centro centroMilano = new Centro("MilanoCenter", "mlc@gmail.com", 50);

		Attivita a1 = new Attivita("Programmazione Orientata agli Oggetti", new Date());
		Attivita a2 = new Attivita("Sistemi Informativi sul Web",new Date());
		Attivita a3 = new Attivita("Basket",new Date());
		Attivita a4 = new Attivita("Calcietto",new Date());
		
		//creo le varie liste
		List<Attivita> attivitaRoma = new ArrayList<>();
		List<Attivita> attivitaMilano = new ArrayList<>();

		List<Allievo> listaAllieviAttivita1 = new ArrayList<Allievo>();
		List<Allievo> listaAllieviAttivita2 = new ArrayList<Allievo>();
		List<Allievo> listaAllieviAttivita3 = new ArrayList<Allievo>();
		List<Attivita> attivitaAllievo1 = new ArrayList<Attivita>();
		List<Attivita> attivitaAllievo2 = new ArrayList<Attivita>();
		
		//riempio le liste 
		attivitaRoma.add(a1);
		attivitaRoma.add(a2);
		attivitaRoma.add(a3);
		attivitaMilano.add(a4);

		attivitaAllievo1.add(a1);
		attivitaAllievo1.add(a2);
		
		attivitaAllievo2.add(a1);
		attivitaAllievo2.add(a3);

		listaAllieviAttivita1.add(allievo1);
		listaAllieviAttivita1.add(allievo2);
		listaAllieviAttivita2.add(allievo1);
		listaAllieviAttivita3.add(allievo2);
		
		//setto le liste
		centroRoma.setListaAttivita(attivitaRoma);
		centroMilano.setListaAttivita(attivitaMilano);
		
		allievo1.setAttivita(attivitaAllievo1);
		allievo2.setAttivita(attivitaAllievo2);
		
		a1.setListaAllievi(listaAllieviAttivita1);
		a2.setListaAllievi(listaAllieviAttivita2);
		a3.setListaAllievi(listaAllieviAttivita3);
		
		a1.setCentro(centroRoma);
		a2.setCentro(centroRoma);
		a3.setCentro(centroRoma);
		a4.setCentro(centroRoma);

		//persisto gli oggetti
		centroService.save(centroRoma);
		centroService.save(centroMilano);
		attivitaService.save(a1);
		attivitaService.save(a2);
		attivitaService.save(a3);
		attivitaService.save(a4);
		allievoService.save(allievo1);
		allievoService.save(allievo2);

	}
}
