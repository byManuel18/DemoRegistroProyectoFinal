package enums;

public enum SentenciasProduccion {
	SELECTBYID("SELECT * FROM produccion WHERE id_pro=?"),
	SELECTPORFECHACONCRETA("SELECT * FROM produccion WHERE fecha_lote=?"),
	SELECTPORMES("SELECT * FROM produccion WHERE MONTH(fecha_lote)=?"),
	SELECTPORAÑO("SELECT * FROM produccion WHERE YEAR(fecha_lote)=?"),
	SELECTMESYAÑO("SELECT * FROM produccion WHERE YEAR(fecha_lote)=? and MONTH(fecha_lote)=?"),
	SELECTALL("SELECT * FROM produccion"),
	INSERT("INSERT INTO produccion(fecha_lote,nombre,cantidad,lotes_carnes,lotes_salsas,lotes_laure,lotes_materiaprima,firmado) VALUES(?,?,?,?,?,?,?,?)"),
	UPDATE("UPDATE produccion SET fecha_lote=?,nombre=?,cantidad=?,lotes_carnes=?,lotes_salsas=?,lotes_laure=?,lotes_materiaprima=?,firmado=? WHERE id_pro=?"),
	DELETE("DELETE FROM produccion WHERE id_pro=? "),
	;
	// CREATE TABLE IF NOT EXISTS produccion(id_pro INT PRIMARY KEY
	// auto_increment, fecha_lote DATE, nombre VARCHAR(100), cantidad VARCHAR(100), lotes_carnes
	// ARRAY, lotes_salsas ARRAY , lotes_laure ARRAY, lotes_materiaprima ARRAY,
	// firmado ENUM('FIRMADO','NO_FIRMADO'))
	private String sql;

	private SentenciasProduccion(String cadena){
		sql=cadena;
	}

	public String getSentencia(){
		return sql;
	}
}
