package modelos;

import enums.TipoProducto;

public class Producto {
	protected int id;
	protected String nombre;
	protected String marca;
	protected float precio;
	protected TipoProducto tipo;
	protected int cantidad;

	public Producto(int id, String nombre, String marca, float precio, TipoProducto tipo, int cantidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
		this.precio = precio;
		this.tipo = tipo;
		this.cantidad = cantidad;
	}

	public Producto(String nombre, String marca, float precio, TipoProducto tipo, int cantidad) {

		this(-1,nombre,marca,precio,tipo,cantidad);
	}

	public Producto() {
		this(-1,"","",0,TipoProducto.Und,0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public TipoProducto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProducto tipo) {
		this.tipo = tipo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
		Producto other = (Producto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto -->" +" Nombre=" + nombre + ", Marca=" + marca + ", Precio=" + precio + ", Tipo="
				+ tipo + ", Cantidad=" + cantidad ;
	}





}
