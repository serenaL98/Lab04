package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */
		for(Corso c: model.getElencoCorsi()) {
			System.out.println(c.toLongerString());
		}
		
		System.out.println("-------------------------------------------------------------------------");

		Corso corsoIo = new Corso("09AQGNG",0, null, 0);
		System.out.println(model.getCorso(corsoIo).toLongerString());
		
		System.out.println("-------------------------------------------------------------------------");
		
		for(Studente s: model.getElencoStudenti()) {
			System.out.println(s.toString());
		}
		
		System.out.println("-------------------------------------------------------------------------");
		
		Studente stu = new Studente("146101", null, null, null);
		System.out.println(model.getStudente(stu).toString());

	}

}
