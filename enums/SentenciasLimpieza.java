package enums;

public enum SentenciasLimpieza {
	SELECTPORID("SELECT * FROM registro_limpieza WHERE id=?"),
	INSERT("INSERT INTO registro_limpieza(fecha,obrador,aseo,venta,picadora,embutidora,sierra,cuchillos_y_utensilios,frigorifico_materia_prima,frigorifico_producto_terminado,contenedor_residuos,firmado) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)"),
	UPDATE("UPDATE registro_limpieza SET fecha=?,obrador=?,aseo=?,venta=?,picadora=?,embutidora=?,sierra=?,cuchillos_y_utensilios=?,frigorifico_materia_prima=?,frigorifico_producto_terminado=?,contenedor_residuos=? ,firmado=? WHERE id=?"),
	DELETE("DELETE  FROM registro_limpieza WHERE id=?"),
	SELECTALL("SELECT * FROM registro_limpieza "),
	SELECTPORFECHA("SELECT * FROM registro_limpieza WHERE fecha LIKE ?"),
	SELECTPORMES("SELECT * FROM registro_limpieza WHERE fecha BETWEEN ? and ? ")
	;
	private String sentencia;

	private SentenciasLimpieza(String cad){
		sentencia=cad;
	}

	public  String getSentencia(){
		return sentencia;
	}
}
