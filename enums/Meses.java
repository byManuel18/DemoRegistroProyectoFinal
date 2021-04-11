package enums;

public enum Meses {
	ENERO(1),
	FEBRERO(2),
	MARZO(3),
	ABRIL(4),
	MAYO(5),
	JUNIO(6),
	JULIO(7),
	AGOSTO(8),
	SEPTIEMPRE(9),
	OCTUBRE(10),
	NOVIEMBRE(11),
	DICIEMBRE(12)
	;

	private int numero_mes;

	private Meses(int n){
		numero_mes=n;
	}

	public int getNumero_Mes(){
		return numero_mes;
	}
}
