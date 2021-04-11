package enums;

public enum SentenciasTemperatura {
	SELECTPORID("SELECT * FROM registros_temperatura WHERE id=?"),
	INSERT("INSERT INTO registros_temperatura (fecha,vitrina_espositora,armario_frigorifico,armario_congelador,armario_productos_cocinados,alcon_congelador,firmado) VALUES(?,?,?,?,?,?,?)"),
	UPDATE("UPDATE registros_temperatura SET fecha=?,vitrina_espositora=?,armario_frigorifico=?,armario_congelador=?,armario_productos_cocinados=?,alcon_congelador=?,firmado=? WHERE id=?"),
	DELETE("DELETE FROM registros_temperatura WHERE id=?"),
	SELECTALL("SELECT * FROM registros_temperatura"),
	SELECPORFECHA("SELECT * FROM registros_temperatura WHERE fecha LIKE ?"),
	SELECTPORMES("SELECT * FROM registros_temperatura WHERE fecha BETWEEN ? and ?"),
	;

	private String sentecia;

	private SentenciasTemperatura(String sql){
		sentecia=sql;
	}

	public String getSentencia(){
		return sentecia;
	}
}
