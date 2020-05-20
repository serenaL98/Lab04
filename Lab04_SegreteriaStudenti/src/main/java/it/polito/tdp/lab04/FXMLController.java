package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import java.util.*;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<Corso> boxCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnGreen;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtRisultato;

    //dimmi se lo studente è iscirtto a quel corso
    @FXML
    void doCerca(ActionEvent event) {
    	txtRisultato.clear();
    	
    	Corso corso = this.boxCorsi.getValue();
    	String matricola = this.txtMatricola.getText();
    	Studente studente = model.getStudente(matricola);
    	
    	//soluzione prof
    	try {
    		if(matricola.isEmpty()) {
    			txtRisultato.setText("Inserire una matricola.");
    			return;
    		}
    		if(corso == null) {
    			txtRisultato.setText("Inserire un corso.");
    			return;
    		}
    		if(studente == null) {
    			txtRisultato.appendText("Matricola inesistente.");
    			return;
    		}
    		if(!model.iscriviStudenteACorso(studente, corso)) {
    			txtRisultato.appendText("Errore nell'iscrizione al corso");
    			return;
    		}
    		else {
    			txtRisultato.appendText("Studente iscritto al corso!");
    		}
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Inserire una matricola nel formato corretto.");
    	}/*catch(RuntimeException e) {
    		txtRisultato.setText("ERRORE DI CONNESSIONE AL DATABASE!");
    	}*/
    	catch(IllegalStateException i) {
    		txtRisultato.setText(i.getMessage()+"\n");
    	}

    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtRisultato.clear();
    	
    	try {
    	//leggo la matricola
    	String leggimatricola = txtMatricola.getText();
    	
    	List <Corso> corsiStudente = new LinkedList<Corso>();
    	
    	for(Studente s: this.model.getElencoStudenti()) {
    		if(s.getMatricola().equals(leggimatricola)) {
    			corsiStudente = (this.model.getCorsiPerStudente(s));
    		}
    	}
    	
    	String elenco="";
    	for(Corso c: corsiStudente) {
    		elenco +=c.toLongerString()+"\n";
    	}
    	
    	txtRisultato.appendText(elenco);
    	
    	} catch(NullPointerException e) {
    		txtRisultato.appendText("Nessun corso per questo studente.\n");
    	}
    	
    	
    }

    //dammi tutti gli iscritti di quel corso
    @FXML
    void doCercaIscritti(ActionEvent event) {
    	//pulisco il resto di risposta
    	txtRisultato.clear();
    	
    	txtNome.clear();
    	txtCognome.clear();
    	
    	try {
    	//leggo il corso dalla tendina
    	Corso leggiCorso = boxCorsi.getValue();
    	    	
    	String elenco ="";
    	for(Studente s: this.model.getStudentiPerCorso(leggiCorso)) {
    		elenco+= s.toString()+"\n";
    	}
    	
    	txtRisultato.appendText(elenco);
    	
    	}catch(NullPointerException e) {
    		txtRisultato.appendText("Errore! Corso non selezionato.\n");
    	}
    }

    //dammi nome e cognome inserita la matricola
    @FXML
    void doNomeCognome(ActionEvent event) {

    	txtRisultato.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    	String leggimatricola = txtMatricola.getText();
    	
    	for( int i=0; i<leggimatricola.length(); i++) {
    		if(!Character.isDigit(leggimatricola.charAt(i))) {
    			txtRisultato.setText("Inserire una matricola corretta!\nP.S. Non inserire segni di punteggiatura.");
    		}
    	}
    	
    	Studente daCercare = this.model.dammiCredenziali(leggimatricola);
    	Studente esiste = this.model.getStudente(leggimatricola);
    	
    	if(esiste.getNome()==null) {
    		txtRisultato.appendText("Non esiste uno studente con questa matricola.\n");
    		return;
    	}
    	
    	txtNome.setText(daCercare.getNome());
    	
    	txtCognome.setText(daCercare.getCognome());
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtRisultato.clear();
    	boxCorsi.getSelectionModel().clearSelection();
    }

    @FXML
    void initialize() {
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGreen != null : "fx:id=\"btnGreen\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	boxCorsi.getItems().addAll(this.model.getElencoCorsi());
    }
}
