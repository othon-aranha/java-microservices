package acesso.tse.jus.br.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import acesso.tse.jus.br.AcessoConstants;
//import br.net.woodstock.rockframework.domain.persistence.AbstractIntegerEntity;

@Entity
@Table(name = "tribunal", schema = "admacesso")
public class Tribunal  {

	@SuppressWarnings("unused")
	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@Id
	@Column(name = "cd_trib", unique = true)
	private Integer				id;

	@Column(name = "sigla_trib", nullable = false)
	@NotNull
	@Size(max = 6)
	private String				sigla;

	@Column(name = "nom_trib", nullable = false)
	@NotNull
	@Size(max = 50)
	private String				nome;

	@Column(name = "logra_trib", nullable = false)
	@NotNull
	@Size(max = 60)
	private String				logradouro;

	@Column(name = "bairro_trib", nullable = false)
	@NotNull
	@Size(max = 30)
	private String				bairro;

	@Column(name = "uf_trib", nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private UF				uf;

	@Column(name = "cep_trib")
	@Size(max = 8)
	private String				cep;

	@Column(name = "cidade_trib")
	@Size(max = 35)
	private String				cidade;

	@Column(name = "fone_trib")
	@Size(max = 25)
	private String				telefone;

	@Column(name = "cgc_trib")
	@Size(max = 15)
	private String				cgc;

	@Column(name = "num_contrato")
	@Size(max = 25)
	private String				numeroContrato;

	@Column(name = "des_contrato")
	@Size(max = 20)
	private String				descricaoContrato;

	@Column(name = "cd_municipio_ibge")
	private Integer				codigoMunicipioIBGE;

	@Column(name = "cd_natureza_juridica")
	@Size(max = 15)
	private String				codigoNaturezaJuridica;

	@Column(name = "email")
	@Size(max = 240)
	private String				email;

	@Column(name = "acesso")
	@Size(max = 1)
	private String				acesso;

	public Tribunal() {
		super();
	}


	public Tribunal(Integer id, String sigla, String nome, String logradouro, String bairro, UF uf, String cep,
			String cidade, String telefone, String cgc, String numeroContrato, String descricaoContrato,
			Integer codigoMunicipioIBGE, String codigoNaturezaJuridica, String email, String acesso) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.uf = uf;
		this.cep = cep;
		this.cidade = cidade;
		this.telefone = telefone;
		this.cgc = cgc;
		this.numeroContrato = numeroContrato;
		this.descricaoContrato = descricaoContrato;
		this.codigoMunicipioIBGE = codigoMunicipioIBGE;
		this.codigoNaturezaJuridica = codigoNaturezaJuridica;
		this.email = email;
		this.acesso = acesso;
	}


	public Integer getId() {
		return this.id;
	}

	
	public void setId(final Integer id) {
		this.id = id;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(final String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(final String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(final String bairro) {
		this.bairro = bairro;
	}

	public UF getUf() {
		return this.uf;
	}

	public void setUf(final UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(final String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(final String cidade) {
		this.cidade = cidade;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(final String telefone) {
		this.telefone = telefone;
	}

	public String getCgc() {
		return this.cgc;
	}

	public void setCgc(final String cgc) {
		this.cgc = cgc;
	}

	public String getNumeroContrato() {
		return this.numeroContrato;
	}

	public void setNumeroContrato(final String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getDescricaoContrato() {
		return this.descricaoContrato;
	}

	public void setDescricaoContrato(final String descricaoContrato) {
		this.descricaoContrato = descricaoContrato;
	}

	public Integer getCodigoMunicipioIBGE() {
		return this.codigoMunicipioIBGE;
	}

	public void setCodigoMunicipioIBGE(final Integer codigoMunicipioIBGE) {
		this.codigoMunicipioIBGE = codigoMunicipioIBGE;
	}

	public String getCodigoNaturezaJuridica() {
		return this.codigoNaturezaJuridica;
	}

	public void setCodigoNaturezaJuridica(final String codigoNaturezaJuridica) {
		this.codigoNaturezaJuridica = codigoNaturezaJuridica;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getAcesso() {
		return this.acesso;
	}

	public void setAcesso(final String acesso) {
		this.acesso = acesso;
	}

}
