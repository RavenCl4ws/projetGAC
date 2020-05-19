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
	private double noteMoyenne;
	@ManyToMany(mappedBy="listeJeuxPossedes")
	private List<Inscription> listeUtilisateurs= new ArrayList<Inscription>();
	
	
	
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

	public double getNoteMoyenne() {
		return noteMoyenne;
	}

	public void setNoteMoyenne(double noteMoyenne) {
		this.noteMoyenne = noteMoyenne;
	}
	
	
	

	public List<Inscription> getListeUtilisateurs() {
		return listeUtilisateurs;
	}

	public void setListeUtilisateurs(List<Inscription> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}

	public JeuVideo(String nomJeu, String genrePrincipal, double noteMoyenne) {
		super();
		this.nomJeu = nomJeu;
		this.genrePrincipal = genrePrincipal;
		this.noteMoyenne = noteMoyenne;
	}

	@Override
	public String toString() {
		return "JeuVideo [IdJeu=" + IdJeu + ", nomJeu=" + nomJeu + ", genrePrincipal=" + genrePrincipal
				+ ", noteMoyenne=" + noteMoyenne + "]";
	}
	
	
	
	
	
	
	
}
