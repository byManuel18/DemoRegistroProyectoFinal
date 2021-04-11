package enums;

public enum Escenas {
	INICIO("/view/Inicio"),
	SANIDAD("/view/VistaProductosCarnicos"),
	AGUA("/view/ViewAgua"),
	LIMPIEZA("/view/ViewLimpieza"),
	TEMPERATURA("/view/ViewTemperaturas"),
	RESIDUOS("/view/ViewResiduos"),
	MANTENIMIENTO("/view/ViewMedidas"),
	PRIMERACARGA("/view/ViewPantallaPrimerInicio"),
	OPCIONESPRIMERAVEZINICIO("/view/ViewCambiarPrimeraVezOpciones"),
	OPCIONESPRIMERAVEZINICIO2("/view/ViewCambiarPrimeraVezOpciones2"),
	TRAZABILIDAD("/view/ViewTrazabilidad"),
	PRODUCCION("/view/ViewProduccion"),
	AJUSTES("/view/Ajustes"),
	;


    private String url;

    Escenas(String fxmlFile) {
        this.url = fxmlFile;
    }

    public String getUrl() {
        return url;
    }
}
