package it.polito.tdp.lab04.model;

//import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	CorsoDAO daoC = new CorsoDAO();
	
	StudenteDAO daoS = new StudenteDAO();
	/*
	public void run() {
		List<Corso> tutticorsi = daoC.getTuttiICorsi();
		List<Corso> corsiunici = new LinkedList<Corso>();
		
		for(Corso c: tutticorsi) {
			for(Corso o: corsiunici) {
				if( !c.getCodins().equals(o.getCodins()) || !c.getNome().equals(o.getNome())) {
					corsiunici.add(c);
				}
			}
		}
		
		for(Corso c: corsiunici) {
			System.out.println(c.toString());
		}
	}

	public static void main(String arg[]) {
		Model model = new Model();
		model.run();
	}
	*/
	public List<Corso> getElencoCorsi(){
		return daoC.getTuttiICorsi();
	}
	
	public List<Studente> getElencoStudenti(){
		return daoS.elencoStudenti();
	}
	
	public Studente dammiCredenziali(String matricola) {
		return daoS.dammiCredenziali(matricola);
	}
	
	public List<Studente> getStudentiPerCorso(Corso corso){
		return daoC.getStudentiIscrittiAlCorso(corso);
	}
	
	public Corso getCorso(Corso corso) {
		return daoC.getCorso(corso);
	}

	public List<Corso> getCorsiPerStudente(Studente studente){
		return daoC.getCorsiPerStudente(studente);
	}
	
	public Studente getStudente(Studente studente) {
		return daoC.getStudente(studente);
	}
	
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		return daoC.inscriviStudenteACorso(studente, corso);
	}
	
}
