package modelos;

import java.time.LocalDate;

import enums.Estado;
import enums.Mirado;

public class Limpieza implements Comparable<Limpieza> {

	protected int id_limpieza;
	protected LocalDate fecha;
	protected Mirado obrador;
	protected Mirado aseo;
	protected Mirado venta;
	protected Mirado picadora;
	protected Mirado embutidora;
	protected Mirado sierra;
	protected Mirado cuchillos_y_utensilios;
	protected Mirado frigorifico_materia_prima;
	protected Mirado frigorifico_producto_terminado;
	protected Mirado contenedor_residuos;
	protected Estado firmado;

	public Limpieza(){
		this(-1,null,Mirado.NULO,Mirado.NULO,Mirado.NULO,Mirado.NULO,Mirado.NULO,Mirado.NULO,Mirado.NULO,
				Mirado.NULO,Mirado.NULO,Mirado.NULO,Estado.NO_FIRMADO);
	}

	public Limpieza(LocalDate fecha, Mirado obrador, Mirado aseo, Mirado venta, Mirado picadora,
			Mirado embutidora, Mirado sierra, Mirado cuchillos_y_utensilios, Mirado frigorifico_materia_prima,
			Mirado frigorifico_producto_terminado, Mirado contenedor_residuos, Estado firmado) {
				this(-1,fecha,  obrador,  aseo,  venta,  picadora, embutidora,  sierra,  cuchillos_y_utensilios,
						frigorifico_materia_prima,frigorifico_producto_terminado,  contenedor_residuos,  firmado);
			}

	public Limpieza(int id_limpieza, LocalDate fecha, Mirado obrador, Mirado aseo, Mirado venta, Mirado picadora,
			Mirado embutidora, Mirado sierra, Mirado cuchillos_y_utensilios, Mirado frigorifico_materia_prima,
			Mirado frigorifico_producto_terminado, Mirado contenedor_residuos, Estado firmado) {
		this.id_limpieza = id_limpieza;
		this.fecha = fecha;
		this.obrador = obrador;
		this.aseo = aseo;
		this.venta = venta;
		this.picadora = picadora;
		this.embutidora = embutidora;
		this.sierra = sierra;
		this.cuchillos_y_utensilios = cuchillos_y_utensilios;
		this.frigorifico_materia_prima = frigorifico_materia_prima;
		this.frigorifico_producto_terminado = frigorifico_producto_terminado;
		this.contenedor_residuos = contenedor_residuos;
		this.firmado = firmado;
	}



	public int getId_limpieza() {
		return id_limpieza;
	}



	public void setId_limpieza(int id_limpieza) {
		this.id_limpieza = id_limpieza;
	}



	public LocalDate getFecha() {
		return fecha;
	}



	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}



	public Mirado getObrador() {
		return obrador;
	}



	public void setObrador(Mirado obrador) {
		this.obrador = obrador;
	}



	public Mirado getAseo() {
		return aseo;
	}



	public void setAseo(Mirado aseo) {
		this.aseo = aseo;
	}



	public Mirado getVenta() {
		return venta;
	}



	public void setVenta(Mirado venta) {
		this.venta = venta;
	}



	public Mirado getPicadora() {
		return picadora;
	}



	public void setPicadora(Mirado picadora) {
		this.picadora = picadora;
	}



	public Mirado getEmbutidora() {
		return embutidora;
	}



	public void setEmbutidora(Mirado embutidora) {
		this.embutidora = embutidora;
	}



	public Mirado getSierra() {
		return sierra;
	}



	public void setSierra(Mirado sierra) {
		this.sierra = sierra;
	}



	public Mirado getCuchillos_y_utensilios() {
		return cuchillos_y_utensilios;
	}



	public void setCuchillos_y_utensilios(Mirado cuchillos_y_utensilios) {
		this.cuchillos_y_utensilios = cuchillos_y_utensilios;
	}



	public Mirado getFrigorifico_materia_prima() {
		return frigorifico_materia_prima;
	}



	public void setFrigorifico_materia_prima(Mirado frigorifico_materia_prima) {
		this.frigorifico_materia_prima = frigorifico_materia_prima;
	}



	public Mirado getFrigorifico_producto_terminado() {
		return frigorifico_producto_terminado;
	}



	public void setFrigorifico_producto_terminado(Mirado frigorifico_producto_terminado) {
		this.frigorifico_producto_terminado = frigorifico_producto_terminado;
	}



	public Mirado getContenedor_residuos() {
		return contenedor_residuos;
	}



	public void setContenedor_residuos(Mirado contenedor_residuos) {
		this.contenedor_residuos = contenedor_residuos;
	}



	public Estado getFirmado() {
		return firmado;
	}



	public void setFirmado(Estado firmado) {
		this.firmado = firmado;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_limpieza;
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
		Limpieza other = (Limpieza) obj;
		if (id_limpieza != other.id_limpieza)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "--> Fecha=" + fecha + ", Obrador=" + obrador + ", Aseo=" + aseo
				+ ", Venta=" + venta + ", Picadora=" + picadora + ", embutidora=" + embutidora + ", sierra=" + sierra
				+ ", Cuchillos y utensilios=" + cuchillos_y_utensilios + ", Frigorifico materia prima="
				+ frigorifico_materia_prima + ", Frigorifico producto terminado=" + frigorifico_producto_terminado
				+ ", Contenedor residuos=" + contenedor_residuos + ", Firmado=" + firmado;
	}

	@Override
	public int compareTo(Limpieza o) {
		// TODO Auto-generated method stub
		return this.fecha.compareTo(o.fecha);
	}



}
