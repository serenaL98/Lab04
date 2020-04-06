package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<Studente> elencoStudenti(){
		
		final String sql = "SELECT * from studente";
		List<Studente> studenti = new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String matr = rs.getString("matricola");
				String n = rs.getString("nome");
				String c = rs.getString("cognome");
				String cod = rs.getString("CDS");
				
				Studente stemp = new Studente(matr, c, n, cod);
				studenti.add(stemp);
			}
			
			conn.close();
			
			return studenti;
			
		}catch(SQLException e) {
			//System.out.println("Errore nella connessione del db.\n");
			throw new RuntimeException("Errore Db", e);
		}
		
	}

	public Studente dammiCredenziali(String matricola) {
		
		List<Studente> stud = this.elencoStudenti();
		
		for(Studente s: stud) {
			if(s.getMatricola().equals(matricola)) {
				return s;
			}
		}
		
		return null;
	}
	
}
