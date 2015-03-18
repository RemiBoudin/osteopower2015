/**
 * Convertisseur Euro - Implementation de l'objet CORBA
 * @author Yann Steff & Emmanuel Lavinal
 */
public class EuroImpl extends Convertisseur.EuroPOA  {

	// Constructeur
	//***************
	public EuroImpl() {

		_taux=6.55957;
		_devise="Francs";
	}

	// Taux de conversion
	//********************
	private double _taux;

	public void taux(double taux) {
		System.out.println(taux);
		_taux=taux;
	}
	public double taux() {
		return _taux;
	}

	// Devise utilise
	//*****************
	private String _devise;

	public void devise(java.lang.String devise) {
      _devise=devise;
	}
	public java.lang.String devise() {
      return _devise;
	}

	// Implementation des operations de conversion
	//*********************************************
	public double toEuro(double devise) {
		return devise / _taux;
	}

	public double toDevise(double euro) {
		return euro * _taux;
	}
}
