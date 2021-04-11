package enums;

public enum SentenciasSanidad {

	SELECTSANIDAD("SELECT * FROM productos_carnicos WHERE id= ?"),
	INSERTSANIDAD("INSERT INTO productos_carnicos (fecha,proveedor,producto,firmado,lote) VALUES(?,?,?,?,?);"),
	SELECTSANIDADBYNOMBRE("SELECT * FROM productos_carnicos WHERE producto LIKE ?"),
	SLECTSANIDADALL("SELECT * FROM productos_carnicos order by fecha"),
	SELECTSANIDADBYPROVEEDOR("SELECT * FROM productos_carnicos WHERE proveedor LIKE ?"),
	SELECTSANIDADBYDATE("SELECT * FROM productos_carnicos WHERE fecha LIKE ?"),
	UPDATESQANIDAD("UPDATE productos_carnicos SET fecha=?, proveedor=?, producto=?, firmado=?, lote=? WHERE id=? ;"),
	REMUVESANIDAD("DELETE FROM productos_carnicos WHERE id=? "),
	SELECTSANIDADENTREDOSFECHAS("SELECT * FROM productos_carnicos WHERE fecha BETWEEN ? AND ?;"),
	SLECTPORMES("SELECT * FROM productos_carnicos WHERE MONTH(fecha)=?"),
	SLECTPORAÑO("SELECT * FROM productos_carnicos WHERE YEAR(fecha)=?"),
	SLECTPORAÑOYMES("SELECT * FROM productos_carnicos WHERE MONTH(fecha)=? and YEAR(fecha)=?"),
	;
	private String senten;

	private SentenciasSanidad(String senten) {
        this.senten = senten;
    }

	public String getSenten() {
        return senten;
    }
}
