package enums;

public enum SentenciasAgua {
	SELECTUNOPORID("SELECT * FROM registro_agua WHERE id=?"),
	INSERT("INSERT INTO registro_agua (fecha,punto_muestreo,control_organoleptico,estado,firmado) VALUES(?,?,?,?,?)"),
	UPDATE("UPDATE registro_agua SET fecha=?, punto_muestreo=?, control_organoleptico=?,estado=?,firmado=? WHERE id=?"),
	DELETE("DELETE FROM registro_agua WHERE id=?"),
	SELECTALL("SELECT * FROM registro_agua"),
	SELECPORFECHA("SELECT * FROM registro_agua WHERE fecha LIKE ?"),
	SELECTPORMES("SELECT * FROM registro_agua WHERE fecha BETWEEN ? and ?"),
	;
	private String sentencia;

	private SentenciasAgua(String sen){
		this.sentencia=sen;
	}

	public String getSenten() {
        return sentencia;
    }
}


