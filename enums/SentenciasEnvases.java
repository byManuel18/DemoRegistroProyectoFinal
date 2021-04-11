package enums;

public enum SentenciasEnvases {
	SELECTBYID("SELCT * FROM trazabilidad_envases WHERE id_traza=?"),
	SELECTALL("SELECT * FROM trazabilidad_envases"),
	SELECTFECHACONCRETALLEGADA("SELECT * FROM trazabilidad_envases WHERE fecha_llegada=?"),
	SELECTFECHACONCRETAFIN("SELECT * FROM trazabilidad_envases WHERE fecha_fin=?"),
	SELECTFECHACONCRETAINICIO("SELECT * FROM trazabilidad_envases WHERE fecha_inicio=?"),
	SELECTSOLOMESFECHALLEGADA("SELECT * FROM trazabilidad_envases WHERE MONTH(fecha_llegada)=?"),
	SELECTSOLOMESFECHAFIN("SELECT * FROM trazabilidad_envases WHERE MONTH(fecha_fin)=?"),
	SELECTSOLOMESFECHAINICIO("SELECT * FROM trazabilidad_envases WHERE MONTH(fecha_inicio)=?"),
	SELECTSOLOA�OFECHALLEGADA("SELECT * FROM trazabilidad_envases WHERE YEAR(fecha_llegada)=?"),
	SELECTSOLOA�OFECHAFIN("SELECT * FROM trazabilidad_envases WHERE YEAR(fecha_fin)=?"),
	SELECTSOLOA�OFECHAINICIO("SELECT * FROM trazabilidad_envases WHERE YEAR(fecha_inicio)=?"),
	SELECTSOLOMESYA�OFECHAINICIO("SELECT * FROM trazabilidad_envases WHERE  MONTH(fecha_inicio)=? and YEAR(fecha_inicio)=?"),
	SELECTSOLOMESYA�OFECHAFIN("SELECT * FROM trazabilidad_envases WHERE  MONTH(fecha_fin)=? and YEAR(fecha_fin)=?"),
	SELECTSOLOMESYA�OFECHALLEGADA("SELECT * FROM trazabilidad_envases WHERE  MONTH(fecha_llegada)=? and YEAR(fecha_llegada)=?"),
	DELETE("DELETE FROM trazabilidad_envases WHERE id_traza=?"),
	INSERT("INSERT INTO trazabilidad_envases(fecha_llegada,fecha_inicio,fecha_fin,proveedor,mercancia,lote,firmado) VALUES(?,?,?,?,?,?,?) "),
	UPDATE("UPDATE trazabilidad_envases SET fecha_llegada=?,fecha_inicio=?,fecha_fin=?,proveedor=?,mercancia=?,lote=?,firmado=? WHERE id_traza=?"),
	;
	/*
	 * CREATE TABLE IF NOT EXISTS trazabilidad_envases(id_traza INT PRIMARY KEY
	 * auto_increment, fecha_llegada DATE, fecha_inicio DATE, fecha_fin DATE,
	 * proveedor VARCHAR(100), mercancia VARCHAR(100), lote VARCHAR(100),
	 * firmado ENUM('FIRMADO','NO_FIRMADO'))
	 */
	private String sql;

	private SentenciasEnvases(String cadena) {
		sql = cadena;
	}

	public String getSentencia() {
		return sql;
	}
}
