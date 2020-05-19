package beans;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
public class Inscription {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	private String pseudo;
	private String mail;
	@Column(name="mot_passe")
	private String motPasse;
	private String nom;
	private String prenom;
	@Column(name="date_naissance")
	private String dateNaissance;
	private String pays;
	@Column(name="numero_tel")
	private String numeroTel;
	//private String plateforme;
	//private String genreFavori;
	@ManyToMany(cascade= {CascadeType.ALL})
	@JoinTable(
			name="liste_Jeux_Possedes",
			joinColumns= { @JoinColumn(name="user_id") },
			inverseJoinColumns = { @JoinColumn(name="jeuvideo_idjeu")}
			)
	private List<JeuVideo> listeJeuxPossedes=new ArrayList<JeuVideo>(); 
	
	public Inscription() {}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	
	
	public List<JeuVideo> getListeJeuxPossedes() {
		return listeJeuxPossedes;
	}

	public void setListeJeuxPossedes(List<JeuVideo> listeJeuxPossedes) {
		this.listeJeuxPossedes = listeJeuxPossedes;
	}

	public Inscription(String pseudo, String mail, String motPasse, String nom, String prenom, String dateNaissance,
			String pays, String numeroTel) {
		super();
		this.pseudo = pseudo;
		this.mail = mail;
		this.motPasse = motPasse;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.pays = pays;
		this.numeroTel = numeroTel;
	}

	@Override
	public String toString() {
		return "Inscription [Id=" + Id + ", pseudo=" + pseudo + ", mail=" + mail + ", motPasse=" + motPasse + ", nom="
				+ nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", pays=" + pays + ", numeroTel="
				+ numeroTel + ", listeJeuxPossedes=" + listeJeuxPossedes + "]";
	}


	
	
	
	
}
