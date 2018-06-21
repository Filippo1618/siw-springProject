package it.uniroma3.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Attivita {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataSvolgimento;

	@ManyToMany(mappedBy="attivitaAllievo")
	private List<Allievo> listaAllievi;
	
	@ManyToOne
	private Centro centro;

	public Attivita() {}

	public Attivita(String nome, Date data) {
		this.nome = nome;
		this.dataSvolgimento = data;
	}

	public Long getId() {
		return id;
	}

	public List<Allievo> getListaAllievi() {
		return listaAllievi;
	}


	public void setListaAllievi(List<Allievo> listaAllievi) {
		this.listaAllievi = listaAllievi;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataSvolgimento() {
		return dataSvolgimento;
	}

	public void setDataSvolgimento(Date dataSvolgimento) {
		this.dataSvolgimento = dataSvolgimento;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	

}
