package enums;

public enum SentenciasMantenimiento {
	SELECTPORID("SELEC * FROM registro_de_mantenimiento WHERE id=?"),
	SELCTALL("SELECT * from registro_de_mantenimiento"),
	SELECPORFECHA("SELECT * FROM registro_de_mantenimiento WHERE fecha LIKE ?"),
	SELECTPORMES("Select * from registro_de_mantenimiento where MONTH(fecha)=?"),
	DELETE("DELETE FROM registro_de_mantenimiento WHERE id=?"),
	UPDATE("UPDATE registro_de_mantenimiento SET fecha=?,suelo=?,paredes=?,techos=?,electricidad=?,fontaneria=?,operaciones_realizadas_a_equipos_y_utensilios=?,calibracion_equipo_frio=?,revision_balanzas=?,firmado=? WHERE id=?"),
	INSERT("INSERT INTO registro_de_mantenimiento(fecha,suelo,paredes,techos,electricidad,fontaneria,operaciones_realizadas_a_equipos_y_utensilios,calibracion_equipo_frio,revision_balanzas,firmado) VALUES(?,?,?,?,?,?,?,?,?,?)");
	// CREATE TABLE IF NOT EXISTS registro_de_mantenimiento(id INT PRIMARY KEY
	// auto_increment,fecha DATE ,suelo ENUM('C','I'),paredes
	// ENUM('C','I'),techos ENUM('C','I'),electricidad ENUM('C','I'),fontaneria
	// ENUM('C','I'),operaciones_realizadas_a_equipos_y_utensilios
	// ENUM('C','I'),calibracion_equipo_frio ENUM('C','I'),revision_balanzas
	// ENUM('C','I'),firmado ENUM('FIRMADO','NO_FIRMADO'));
	;
	private String sql;

	private SentenciasMantenimiento(String sentencia) {
		sql = sentencia;
	}

	public String getSentencia() {
		return sql;
	}
}
