package enums;

public enum SentenciasManipulador {
	SELECTPORID("SELECT * FROM registro_manipuladores WHERE id=?"),
	SELECTALL("SELECT * FROM registro_manipuladores"),
	SELECPORFECHA("SELECT * FROM registro_manipuladores WHERE fecha LIKE ?"),
	SELECTPORMES("Select * from registro_manipuladores where MONTH(fecha)=?"),
	DLETE("DELETE FROM registro_manipuladores WHERE id=?"),
	INSERT("INSERT INTO registro_manipuladores(fecha,vestimienta_limpia,higiene_personal,buenas_practicas,incidencias_acciones_correctoras,firmado) VALUES (?,?,?,?,?,?)"),
	UPDATE("UPDATE registro_manipuladores SET fecha=?, vestimienta_limpia=?, higiene_personal=?,buenas_practicas=?,incidencias_acciones_correctoras=?,firmado=? WHERE id=?"),
	;

	private String sql;

	private SentenciasManipulador(String cadena){
		sql=cadena;
	}

	public String getSentencia(){
		return sql;
	}
}
