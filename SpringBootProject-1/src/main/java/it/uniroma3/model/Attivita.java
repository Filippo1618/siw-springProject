package it.uniroma3.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Attivita {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSvolgimento;

	@ManyToMany(mappedBy="attivitaAllievo")
	private List<Allievo> listaAllievi;

	public Attivita() {}

	public Attivita(String nome) {
		this.nome = nome;
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

}
