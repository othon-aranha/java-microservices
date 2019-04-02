package acesso.tse.jus.br.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

import acesso.tse.jus.br.AcessoConstants;
//import br.net.woodstock.rockframework.domain.persistence.AbstractIntegerEntity;


@NamedQuery(name = "Modulo.moduloByModuloDTO", query = "SELECT a FROM Modulo a")
@NamedNativeQuery(name="findBytipoModulo", query = "SELECT * FROM ADMACESSO.MODULO WHERE TP_MODULO = :tipoModulo ", resultClass = Modulo.class)
@Entity
@Table(name = "modulo", schema = "admacesso")
public class Modulo implements Serializable {

	private static final long	serialVersionUID				= AcessoConstants.VERSAO;

	public static final int		TIPO_AUTENTICACAO_USUARIO_SENHA	= 1;

	public static final int		TIPO_AUTENTICACAO_CERTIFICADO	= 2;

	@Id
	@Column(name = "cd_modulo", unique = true)
	private Integer				id;

	@Column(name = "sigla_modulo", nullable = false)
	@NotNull
	@Size(min = 2, max = 10)
	private String				sigla;

	@Column(name = "sis_descricao", nullable = false)
	@NotNull
	@Size(min = 2, max = 50)
	private String				nome;

	@Column(name = "titulo")
	@Size(max = 200)
	private String				descricao;

	@Column(name = "alias")
	@Size(max = 20)
	private String				alias;

	@Column(name = "esquema")
	@Size(max = 30)
	private String				esquema;
	
	@Column(name="email_responsavel")
	@Size(max = 250)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "caminho_modulo")
	@Size(max = 1000)
	private String				caminhoModulo;

	@Column(name = "caminho_hlp")
	@Size(max = 100)
	private String				caminhoHelp;

	@Column(name = "nome_executavel")
	@Size(max = 50)
	private String				nomeExecutavel;

	@Column(name = "major_version")
	private Integer				majorVersion;

	@Column(name = "minor_version")
	private Integer				minorVersion;

	@Column(name = "release")
	private Integer				release;

	@Column(name = "build")
	private Integer				build;

	@Column(name = "dt_exe")
	private Date				dataModulo;

	@Column(name = "dt_hlp")
	private Date				dataHelp;

	//@Column(name = "nm_chave")
	//private String				chave;

	//@Column(name = "tp_autenticacao")
	//private Integer				tipoAutenticacao;

	@Column(name = "tabela_mensagem", nullable = false, columnDefinition = "NUMBER")
	@NotNull
	private Boolean	mensagemCompartilhada;

	@Column(name = "controla_acesso", nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private SimNaoType			controlaAcesso;

	@Column(name = "tp_modulo", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private TipoModulo			tipoModulo;

	@Column(name = "tp_atualizacao", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private TipoAtualizacao		tipoAtualizacao;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private StatusModulo		statusModulo;

	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)	
	@JoinColumn(name = "cd_trib", referencedColumnName = "cd_trib", nullable = false)
	private Tribunal tribunal;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "modulo")	
	@OrderBy(value = "nome")
	private Set<Perfil>	perfis;
    
 	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objeto")
	@OrderBy(value = "nome")
	private Set<ObjetoModulo> objetos;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "acesso_modulo", schema = "admacesso", joinColumns = @JoinColumn(name = "cd_modulo", referencedColumnName = "cd_modulo"), inverseJoinColumns = @JoinColumn(name = "sq_usuario", referencedColumnName = "sq_usuario"))
	private Set<Usuario> usuarios;
	
 	
	//@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	//@JoinTable(name = "maquina_servidora", schema = "admacesso", joinColumns = @JoinColumn(name = "cd_modulo", referencedColumnName = "cd_modulo"))
	//private Set<MaquinaServidora> servidores;	
	
	/*
	  public Set<MaquinaServidora> getServidores() {
	 
		return servidores;
	}

	public void setServidores(Set<MaquinaServidora> servidores) {
		this.servidores = servidores;
	}
	
	*/
	
	@JsonIgnore
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(final Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}	
    
	
	public Modulo() {
		super();
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

	public Modulo(Integer id, String sigla, String nome, String descricao, String alias, String esquema,
			String caminhoModulo, String caminhoHelp, String nomeExecutavel, Integer majorVersion, Integer minorVersion,
			Integer release, Integer build, Date dataModulo, Date dataHelp, 
			Boolean mensagemCompartilhada, SimNaoType controlaAcesso, TipoModulo tipoModulo,
			TipoAtualizacao tipoAtualizacao, StatusModulo statusModulo, 
			Tribunal tribunal,			 
			Set<ObjetoModulo> objetos,
			Set<Perfil> perfis,
			Set<Usuario> usuarios) {
			//Set<MaquinaServidora> servidores) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
		this.descricao = descricao;
		this.alias = alias;
		this.esquema = esquema;
		this.caminhoModulo = caminhoModulo;
		this.caminhoHelp = caminhoHelp;
		this.nomeExecutavel = nomeExecutavel;
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.release = release;
		this.build = build;
		this.dataModulo = dataModulo;
		this.dataHelp = dataHelp;
		this.mensagemCompartilhada = mensagemCompartilhada;
		this.controlaAcesso = controlaAcesso;
		this.tipoModulo = tipoModulo;
		this.tipoAtualizacao = tipoAtualizacao;
		this.statusModulo = statusModulo;
		this.tribunal = tribunal;
		
		this.objetos = objetos;
		this.perfis = perfis;
		this.usuarios = usuarios;
		//this.servidores = servidores;		
		
	}

	
	public String getVersao() {
		if ( this.getTipoAtualizacao() == TipoAtualizacao.POR_VERSAO ) { 
			return this.getMajorVersion().toString().concat(".").concat(this.getMinorVersion().toString()).concat(".").concat(this.getRelease().toString()).concat(".").concat(this.getBuild().toString());}
		else {			
			Date g = this.getDataModulo();
			if ( g != null ) {
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			return f.format(g);
			}else {
				return "";
			}
		}
		
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(final String alias) {
		this.alias = alias;
	}

	public String getEsquema() {
		return this.esquema;
	}

	public void setEsquema(final String esquema) {
		this.esquema = esquema;
	}

	public String getCaminhoModulo() {
		return this.caminhoModulo;
	}

	public void setCaminhoModulo(final String caminhoModulo) {
		this.caminhoModulo = caminhoModulo;
	}

	public String getCaminhoHelp() {
		return this.caminhoHelp;
	}

	public void setCaminhoHelp(final String caminhoHelp) {
		this.caminhoHelp = caminhoHelp;
	}

	public String getNomeExecutavel() {
		return this.nomeExecutavel;
	}

	public void setNomeExecutavel(final String nomeExecutavel) {
		this.nomeExecutavel = nomeExecutavel;
	}

	public Integer getMajorVersion() {
		return this.majorVersion;
	}

	public void setMajorVersion(final Integer majorVersion) {
		this.majorVersion = majorVersion;
	}

	public Integer getMinorVersion() {
		return this.minorVersion;
	}

	public void setMinorVersion(final Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	public Integer getRelease() {
		return this.release;
	}

	public void setRelease(final Integer release) {
		this.release = release;
	}

	public Integer getBuild() {
		return this.build;
	}

	public void setBuild(final Integer build) {
		this.build = build;
	}

	public Date getDataModulo() {
		return this.dataModulo;
	}

	public void setDataModulo(final Date dataModulo) {
		this.dataModulo = dataModulo;
	}

	public Date getDataHelp() {
		return this.dataHelp;
	}

	public void setDataHelp(final Date dataHelp) {
		this.dataHelp = dataHelp;
	}

	public SimNaoType getControlaAcesso() {
		return this.controlaAcesso;
	}

	public void setControlaAcesso(final SimNaoType controlaAcesso) {
		this.controlaAcesso = controlaAcesso;
	}

	public TipoModulo getTipoModulo() {
		return this.tipoModulo;
	}

	public void setTipoModulo(final TipoModulo tipoModulo) {
		this.tipoModulo = tipoModulo;
	}

	public TipoAtualizacao getTipoAtualizacao() {
		return this.tipoAtualizacao;
	}

	public void setTipoAtualizacao(final TipoAtualizacao tipoAtualizacao) {
		this.tipoAtualizacao = tipoAtualizacao;
	}

	public StatusModulo getStatusModulo() {
		return this.statusModulo;
	}

	public void setStatusModulo(final StatusModulo statusModulo) {
		this.statusModulo = statusModulo;
	}

	
	public Tribunal getTribunal() {
		return this.tribunal;
	}

	public void setTribunal(final Tribunal tribunal) {
		this.tribunal = tribunal;
	}
	
	

	@JsonIgnore
	public Set<Perfil> getPerfis() {
		return this.perfis;
	}

	public void setPerfis(final Set<Perfil> perfis) {
		this.perfis = perfis;
	}	

	
	@JsonIgnore
	public Set<ObjetoModulo> getObjetos() {
		return this.objetos;
	}

	public void setObjetos(final Set<ObjetoModulo> objetos) {
		this.objetos = objetos;
	}
	
	public Boolean getMensagemCompartilhada() {
		return mensagemCompartilhada;
	}

	public void setMensagemCompartilhada(Boolean mensagemCompartilhada) {
		this.mensagemCompartilhada = mensagemCompartilhada;
	}
	
    
}
