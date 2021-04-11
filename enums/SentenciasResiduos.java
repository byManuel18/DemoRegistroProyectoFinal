package enums;

public enum SentenciasResiduos {
	SELECTPORID("SELECT * FROM retirada_de_residuos WHERE id=?"),
	DELETE("DELETE FROM retirada_de_residuos WHERE id=?"),
	INSERT("INSERT INTO retirada_de_residuos(fecha,persona,cantidad,firmado) VALUES(?,?,?,?)"),
	UPDATE("UPDATE retirada_de_residuos SET fecha=?,persona=?,cantidad=?,firmado=? WHERE id=?"),
	SELECTALL("SELECT * FROM retirada_de_residuos"),
	SELECTPORFECHA("SELECT * FROM retirada_de_residuos WHERE fecha like ?"),
	SELECTPORMES("SELECT * FROM retirada_de_residuos WHERE fecha BETWEEN ? and ?")
	;
	private String sql;

	private SentenciasResiduos(String senten){
		sql=senten;
	}

	public String getSentencia(){
		return sql;
	}

}
