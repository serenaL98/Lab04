package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso ctemp = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(ctemp);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(Corso corso) {
		final String sql = "SELECT corso.nome, corso.crediti, corso.pd FROM corso WHERE corso.codins = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				corso.setNome(rs.getString("nome"));
				corso.setNumeroCrediti(rs.getInt("crediti"));
				corso.setPeriodoDidattico(rs.getInt("pd"));
			}
			
			if(corso.getNome() == null) {
				throw new NullPointerException("Non esiste alcun corso con questo codice.");
			}
			
			conn.close();
			return corso;

		}catch(SQLException e) {
			throw new RuntimeException("Corso inesistente", e);
		}
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		final String sql = "select studente.* from iscrizione, corso, studente WHERE corso.codins=iscrizione.codins AND studente.matricola=iscrizione.matricola AND corso.codins = ?";
		List<Studente> studentiCorso = new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String matr = rs.getString("matricola");
				String cogn = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				
				Studente stemp = new Studente(matr, cogn, nome, cds);
				
				studentiCorso.add(stemp);
			}

			conn.close();
			
			if(studentiCorso.size() == 0) {
				throw new NullPointerException("Nessuno studente per quel corso.");
			}
			
			return studentiCorso;
			
		} catch(SQLException e) {
			throw new RuntimeException("Errore db.", e);
		}
				
	}

	//dato lo studente dammi i corsi che segue: se non esiste lo studente segnalalo
	public List<Corso> getCorsiPerStudente(Studente studente){
		
		final String sql = "SELECT c.* FROM iscrizione AS i, studente AS s, corso AS c WHERE c.codins=i.codins AND s.matricola=i.matricola AND s.matricola = ?";
		
		List <Corso> corsi = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String cod = rs.getString("codins");
				int c = rs.getInt("crediti");
				String n = rs.getString("nome");
				int p = rs.getInt("pd");
				
				Corso ctemp = new Corso(cod, c, n, p);
				corsi.add(ctemp);
			}
			
			conn.close();
			
			if(corsi.size()==0) {
				throw new NullPointerException("Matricola errata.\n");
			}
			
			return corsi;
			
		}catch(SQLException e) {
			throw new RuntimeException("Errore nel db", e);
		}
		
	}
	
	//cerca studente nel db
	public Studente getStudente(Studente studente) {
		
		final String sql = "SELECT studente.* FROM studente WHERE studente.matricola =?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, studente.getMatricola());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				studente.setCds(rs.getString("cds"));
				studente.setCognome(rs.getString("cognome"));
				studente.setNome(rs.getString("nome"));
			}
			
			conn.close();
			
			if(studente.getCognome()==null) {
				throw new NullPointerException("Nessun corso per quello studente.");
			}
			
			return studente;
			
		}catch(SQLException e) {
			throw new RuntimeException("Errore nel db.", e);
		}
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		final String sql = "INSERT INTO iscrizione(matricola, codins) VALUES (?, ?)";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			
			Integer rs = st.executeUpdate();
			
			if(rs == 1) {
				return true;
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("Errore nell'inserimento dei dati nel db!");
		}
		
		return false;
	}

}
