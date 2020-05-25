package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
public class JeuVideo {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdJeu;
	private String nomJeu;
	private String genrePrincipal;
	private double note;
	//@ManyToMany(mappedBy="listeJeuxPossedes")
	//private List<Inscription> listeUtilisateurs= new ArrayList<Inscription>();
	
	
	
	public JeuVideo() {}

	public int getIdJeu() {
		return IdJeu;
	}

	public void setIdJeu(int idJeu) {
		IdJeu = idJeu;
	}

	public String getNomJeu() {
		return nomJeu;
	}

	public void setNomJeu(String nomJeu) {
		this.nomJeu = nomJeu;
	}

	public String getGenrePrincipal() {
		return genrePrincipal;
	}

	public void setGenrePrincipal(String genrePrincipal) {
		this.genrePrincipal = genrePrincipal;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}
	
	
	

	
	public JeuVideo(String nomJeu, String genrePrincipal, double note) {
		super();
		this.nomJeu = nomJeu;
		this.genrePrincipal = genrePrincipal;
		this.note = note;
	}

	@Override
	public String toString() {
		return "IdJeu=" + IdJeu + ", nomJeu=" + nomJeu + ", genrePrincipal=" + genrePrincipal
				+ ", note=" + note;
	}
	
	
	
	
	
	
	
}
