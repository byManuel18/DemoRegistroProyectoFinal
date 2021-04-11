package enums;

public enum Busqueda {
	PORFECHALLEGADA("POR FECHA DE LLEGADA"),
	PORFECHAINICIO("POR FECHA DE INICIO"),
	PORFECHAFIN("POR FECHA DE FIN");
	private String cade;

	private Busqueda(String cadena){
		cade=cadena;
	}

	public String getCadena(){
		return cade;
	}

}
