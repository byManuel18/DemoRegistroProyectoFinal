package enums;

public enum SentenciasMedPrev {
	SELECTPORID("SELECT * FROM registro_medidas_preventivas_ddd WHERE id=?"),
	SELECTALL("SELECT * FROM registro_medidas_preventivas_ddd"),
	SELECTPORFECHA("SELECT * FROM registro_medidas_preventivas_ddd WHERE fecha=?"),
	SELECTPORMES("Select * from registro_medidas_preventivas_ddd where MONTH(fecha)=?"),
	DELETE("DELETE FROM registro_medidas_preventivas_ddd WHERE id=?"),
	INSERT("INSERT INTO registro_medidas_preventivas_ddd(fecha,estado_de_puertas_y_ventanas,estado_de_lamparas_antiinsectos,estado_de_desagues,estado_de_limpieza,indicio_de_plagas,firmado) VALUES(?,?,?,?,?,?,?)"),
	UPDATE("UPDATE registro_medidas_preventivas_ddd SET fecha=?,estado_de_puertas_y_ventanas=?,estado_de_lamparas_antiinsectos=?,estado_de_desagues=?,estado_de_limpieza=?,indicio_de_plagas=?,firmado=? WHERE id=?"),
	;
	private String sql;

	private SentenciasMedPrev(String sentencia) {
		sql = sentencia;
	}

	public String getSentencia() {
		return sql;
	}

}
