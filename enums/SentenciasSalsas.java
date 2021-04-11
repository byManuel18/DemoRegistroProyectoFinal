package enums;

public enum SentenciasSalsas {
	SELECTBYID("SELECT * FROM trazabilidad_salsas WHERE id_traza=?"),
	SELECTALL("SELECT * FROM trazabilidad_salsas"),
	SELECTFECHACONCRETALLEGADA("SELECT * FROM trazabilidad_salsas WHERE fecha_llegada=?"),
	SELECTFECHACONCRETAFIN("SELECT * FROM trazabilidad_salsas WHERE fecha_fin=?"),
	SELECTFECHACONCRETAINICIO("SELECT * FROM trazabilidad_salsas WHERE fecha_inicio=?"),
	SELECTSOLOMESFECHALLEGADA("SELECT * FROM trazabilidad_salsas WHERE MONTH(fecha_llegada)=?"),
	SELECTSOLOMESFECHAFIN("SELECT * FROM trazabilidad_salsas WHERE MONTH(fecha_fin)=?"),
	SELECTSOLOMESFECHAINICIO("SELECT * FROM trazabilidad_salsas WHERE MONTH(fecha_inicio)=?"),
	SELECTSOLOAÑOFECHALLEGADA("SELECT * FROM trazabilidad_salsas WHERE YEAR(fecha_llegada)=?"),
	SELECTSOLOAÑOFECHAFIN("SELECT * FROM trazabilidad_salsas WHERE YEAR(fecha_fin)=?"),
	SELECTSOLOAÑOFECHAINICIO("SELECT * FROM trazabilidad_salsas WHERE YEAR(fecha_inicio)=?"),
	SELECTSOLOMESYAÑOFECHAINICIO("SELECT * FROM trazabilidad_salsas WHERE  MONTH(fecha_inicio)=? and YEAR(fecha_inicio)=?"),
	SELECTSOLOMESYAÑOFECHAFIN("SELECT * FROM trazabilidad_salsas WHERE  MONTH(fecha_fin)=? and YEAR(fecha_fin)=?"),
	SELECTSOLOMESYAÑOFECHALLEGADA("SELECT * FROM trazabilidad_salsas WHERE  MONTH(fecha_llegada)=? and YEAR(fecha_llegada)=?"),
	DELETE("DELETE FROM trazabilidad_salsas WHERE id_traza=?"),
	INSERT("INSERT INTO trazabilidad_salsas(fecha_llegada,fecha_inicio,fecha_fin,proveedor,mercancia,lote,firmado) VALUES(?,?,?,?,?,?,?) "),
	UPDATE("UPDATE trazabilidad_salsas SET fecha_llegada=?,fecha_inicio=?,fecha_fin=?,proveedor=?,mercancia=?,lote=?,firmado=? WHERE id_traza=?"),
	;
	private String sql;
	/*
	 * CREATE TABLE IF NOT EXISTS trazabilidad_salsas(id_traza INT PRIMARY KEY
	 * auto_increment, fecha_llegada DATE, fecha_inicio DATE, fecha_fin DATE,
	 * proveedor VARCHAR(100), mercancia VARCHAR(100), lote VARCHAR(100),
	 * firmado ENUM('FIRMADO','NO_FIRMADO'))
	 */

	private SentenciasSalsas(String cadena) {
		sql = cadena;
	}

	public String getSentenia() {
		return sql;
	}
}
