package modelos;

import java.time.LocalDate;

import enums.Estado;

public class SanidadProducto implements Comparable<SanidadProducto> {
	protected LocalDate fecha;
	protected String nombre_producto;
	protected String proveedor;
	protected Estado estado;
	protected String codigolote;
	protected int id;

	public SanidadProducto() {
		this(LocalDate.now(), "", "", Estado.NO_FIRMADO, "", -1);
	}

	public SanidadProducto(LocalDate fecha, String nombre_producto, String proveedor, Estado estado,
			String codigolote) {
		this(fecha,nombre_producto,proveedor,estado,codigolote,-1);
	}



	public SanidadProducto(LocalDate fecha, String nombre_producto, String proveedor, Estado estado,
			String codigolote, int id) {
		this.fecha = fecha;
		this.nombre_producto = nombre_producto;
		this.proveedor = proveedor;
		this.estado = estado;
		this.codigolote = codigolote;
		this.id = id;
	}



	public LocalDate getFecha() {
		return fecha;
	}



	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}



	public String getNombre_producto() {
		return nombre_producto;
	}



	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}



	public String getProveedor() {
		return proveedor;
	}



	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}



	public Estado getEstado() {
		return estado;
	}



	public void setEstado(Estado estado) {
		this.estado = estado;
	}



	public String getCodigolote() {
		return codigolote;
	}



	public void setCodigolote(String codigolote) {
		this.codigolote = codigolote;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanidadProducto other = (SanidadProducto) obj;
		if (id != other.id)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Producto --> FECHA=" + fecha + ", PROVEEDOR=" +  proveedor  + ", NOMBRE PRODUCTO=" + nombre_producto
				+ ", LOTE=" + codigolote + ", FIRMADO=" + estado  ;
	}

	@Override
	public int compareTo(SanidadProducto o) {
		return this.fecha.compareTo(o.fecha);
	}




}
