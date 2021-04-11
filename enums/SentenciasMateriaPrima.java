package enums;

public enum SentenciasMateriaPrima {
	SELECTBYID("SELECT * FROM trazabilidad_materiaprima WHERE id_traza=?"),
	SELECTALL("SELECT * FROM trazabilidad_materiaprima"),
	SELECTFECHACONCRETALLEGADA("SELECT * FROM trazabilidad_materiaprima WHERE fecha_llegada=?"),
	SELECTFECHACONCRETAFIN("SELECT * FROM trazabilidad_materiaprima WHERE fecha_fin=?"),
	SELECTFECHACONCRETAINICIO("SELECT * FROM trazabilidad_materiaprima WHERE fecha_inicio=?"),
	SELECTSOLOMESFECHALLEGADA("SELECT * FROM trazabilidad_materiaprima WHERE MONTH(fecha_llegada)=?"),
	SELECTSOLOMESFECHAFIN("SELECT * FROM trazabilidad_materiaprima WHERE MONTH(fecha_fin)=?"),
	SELECTSOLOMESFECHAINICIO("SELECT * FROM trazabilidad_materiaprima WHERE MONTH(fecha_inicio)=?"),
	SELECTSOLOAÑOFECHALLEGADA("SELECT * FROM trazabilidad_materiaprima WHERE YEAR(fecha_llegada)=?"),
	SELECTSOLOAÑOFECHAFIN("SELECT * FROM trazabilidad_materiaprima WHERE YEAR(fecha_fin)=?"),
	SELECTSOLOAÑOFECHAINICIO("SELECT * FROM trazabilidad_materiaprima WHERE YEAR(fecha_inicio)=?"),
	SELECTSOLOMESYAÑOFECHAINICIO("SELECT * FROM trazabilidad_materiaprima WHERE  MONTH(fecha_inicio)=? and YEAR(fecha_inicio)=?"),
	SELECTSOLOMESYAÑOFECHAFIN("SELECT * FROM trazabilidad_materiaprima WHERE  MONTH(fecha_fin)=? and YEAR(fecha_fin)=?"),
	SELECTSOLOMESYAÑOFECHALLEGADA("SELECT * FROM trazabilidad_materiaprima WHERE  MONTH(fecha_llegada)=? and YEAR(fecha_llegada)=?"),
	DELETE("DELETE FROM trazabilidad_materiaprima WHERE id_traza=?"),
	INSERT("INSERT INTO trazabilidad_materiaprima(fecha_llegada,fecha_inicio,fecha_fin,proveedor,mercancia,lote,firmado) VALUES(?,?,?,?,?,?,?) "),
	UPDATE("UPDATE trazabilidad_materiaprima SET fecha_llegada=?,fecha_inicio=?,fecha_fin=?,proveedor=?,mercancia=?,lote=?,firmado=? WHERE id_traza=?"),
	;
	/*
	 * CREATE TABLE IF NOT EXISTS trazabilidad_materiaprima(id_traza INT PRIMARY
	 * KEY auto_increment, fecha_llegada DATE, fecha_inicio DATE, fecha_fin
	 * DATE, proveedor VARCHAR(100), mercancia VARCHAR(100), lote VARCHAR(100),
	 * firmado ENUM('FIRMADO','NO_FIRMADO'))
	 */
	private String sql;

	private SentenciasMateriaPrima(String cadena) {
		sql = cadena;
	}

	public String GetSentencia() {
		return sql;
	}
}
