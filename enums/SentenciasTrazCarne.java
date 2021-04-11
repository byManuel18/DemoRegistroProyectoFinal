package enums;

public enum SentenciasTrazCarne {
	SELECTBYID("SELECT * FROM trazabilidad_carne WHERE id_traza=?"),
	SELECTPORMESYAÑO("SELECT * FROM trazabilidad_carne tc,productos_carnicos pc  WHERE tc.id_carne=pc.id and MONTH(pc.fecha)=? and YEAR(pc.fecha)=?"),
	SELECTSOLOMES("SELECT * FROM trazabilidad_carne tc,productos_carnicos pc  WHERE tc.id_carne=pc.id and MONTH(pc.fecha)=?"),
	SELECTSOLOAÑO("SELECT * FROM trazabilidad_carne tc,productos_carnicos pc  WHERE tc.id_carne=pc.id and YEAR(pc.fecha)=?"),
	SELECTALL("SELECT * FROM trazabilidad_carne"),
	INSERT("INSERT INTO trazabilidad_carne(id_carne,fecha_inicio,fecha_fin,firmado) VALUES(?,?,?,?)"),
	UPDATE("UPDATE trazabilidad_carne SET id_carne=?,fecha_inicio=?,fecha_fin=?,firmado=? WHERE id_traza=?"),
	DELETE("DELETE FROM trazabilidad_carne WHERE  id_traza=?"),
	SELECTFECGALLEGADACARNE("SELECT * FROM trazabilidad_carne tc,productos_carnicos pc  WHERE tc.id_carne=pc.id and pc.fecha=?"),
	SELECTFECHAINICIO("SELECT * FROM trazabilidad_carne WHERE fecha_inicio=?"),
	SELECTFECHAFIN("SELECT * FROM trazabilidad_carne WHERE fecha_fin=?"),
	;
	/*
	 * CREATE TABLE IF NOT EXISTS trazabilidad_carne (id_traza int PRIMARY KEY
	 * auto_increment,id_carne INT, fecha_inicio DATE, fecha_fin DATE, firmado
	 * ENUM('FIRMADO','NO_FIRMADO'), FOREIGN KEY (id_carne) REFERENCES
	 * productos_carnicos (id) ON DELETE CASCADE ON UPDATE CASCADE);
	 */

	private String sql;

	private SentenciasTrazCarne(String cadena) {
		sql = cadena;
	}

	public String getSentencia() {
		return sql;
	}
}
