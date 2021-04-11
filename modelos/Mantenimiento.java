package modelos;

import java.time.LocalDate;

import enums.CorreIncorre;
import enums.Estado;

public class Mantenimiento {
	protected int id_mantenimiento;
	protected LocalDate fecha;
	protected CorreIncorre suelo;
	protected CorreIncorre paredes;
	protected CorreIncorre techo;
	protected CorreIncorre electricidad;
	protected CorreIncorre fontan;
	protected CorreIncorre operaciones_a_equipos_y_utensilios;
	protected CorreIncorre calibracion_equipo_de_frio;
	protected CorreIncorre revision_balanzas;
	protected Estado firmado;

	public Mantenimiento(){
		this(-1,null,CorreIncorre.C,CorreIncorre.C,CorreIncorre.C,CorreIncorre.C,CorreIncorre.C,CorreIncorre.C,CorreIncorre.C,CorreIncorre.C,Estado.NO_FIRMADO);
	}



	public Mantenimiento(LocalDate fecha, CorreIncorre suelo, CorreIncorre paredes, CorreIncorre techo,
			CorreIncorre electricidad, CorreIncorre fontan, CorreIncorre operaciones_a_equipos_y_utensilios,
			CorreIncorre calibracion_equipo_de_frio, CorreIncorre revision_balanzas, Estado firmado) {
		this(-1,fecha,suelo,paredes,techo,electricidad,fontan,operaciones_a_equipos_y_utensilios,calibracion_equipo_de_frio,revision_balanzas,firmado);
	}



	public Mantenimiento(int id_mantenimiento, LocalDate fecha, CorreIncorre suelo, CorreIncorre paredes,
			CorreIncorre techo, CorreIncorre electricidad, CorreIncorre fontan,
			CorreIncorre operaciones_a_equipos_y_utensilios, CorreIncorre calibracion_equipo_de_frio,
			CorreIncorre revision_balanzas, Estado firmado) {
		this.id_mantenimiento = id_mantenimiento;
		this.fecha = fecha;
		this.suelo = suelo;
		this.paredes = paredes;
		this.techo = techo;
		this.electricidad = electricidad;
		this.fontan = fontan;
		this.operaciones_a_equipos_y_utensilios = operaciones_a_equipos_y_utensilios;
		this.calibracion_equipo_de_frio = calibracion_equipo_de_frio;
		this.revision_balanzas = revision_balanzas;
		this.firmado = firmado;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_mantenimiento;
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
		Mantenimiento other = (Mantenimiento) obj;
		if (id_mantenimiento != other.id_mantenimiento)
			return false;
		return true;
	}



	public int getId_mantenimiento() {
		return id_mantenimiento;
	}



	public void setId_mantenimiento(int id_mantenimiento) {
		this.id_mantenimiento = id_mantenimiento;
	}



	public LocalDate getFecha() {
		return fecha;
	}



	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}



	public CorreIncorre getSuelo() {
		return suelo;
	}



	public void setSuelo(CorreIncorre suelo) {
		this.suelo = suelo;
	}



	public CorreIncorre getParedes() {
		return paredes;
	}



	public void setParedes(CorreIncorre paredes) {
		this.paredes = paredes;
	}



	public CorreIncorre getTecho() {
		return techo;
	}



	public void setTecho(CorreIncorre techo) {
		this.techo = techo;
	}



	public CorreIncorre getElectricidad() {
		return electricidad;
	}



	public void setElectricidad(CorreIncorre electricidad) {
		this.electricidad = electricidad;
	}



	public CorreIncorre getFontan() {
		return fontan;
	}



	public void setFontan(CorreIncorre fontan) {
		this.fontan = fontan;
	}



	public CorreIncorre getOperaciones_a_equipos_y_utensilios() {
		return operaciones_a_equipos_y_utensilios;
	}



	public void setOperaciones_a_equipos_y_utensilios(CorreIncorre operaciones_a_equipos_y_utensilios) {
		this.operaciones_a_equipos_y_utensilios = operaciones_a_equipos_y_utensilios;
	}



	public CorreIncorre getCalibracion_equipo_de_frio() {
		return calibracion_equipo_de_frio;
	}



	public void setCalibracion_equipo_de_frio(CorreIncorre calibracion_equipo_de_frio) {
		this.calibracion_equipo_de_frio = calibracion_equipo_de_frio;
	}



	public CorreIncorre getRevision_balanzas() {
		return revision_balanzas;
	}



	public void setRevision_balanzas(CorreIncorre revision_balanzas) {
		this.revision_balanzas = revision_balanzas;
	}



	public Estado getFirmado() {
		return firmado;
	}



	public void setFirmado(Estado firmado) {
		this.firmado = firmado;
	}



	@Override
	public String toString() {
		return "Mantenimiento [id_mantenimiento=" + id_mantenimiento + ", fecha=" + fecha + ", suelo=" + suelo
				+ ", paredes=" + paredes + ", techo=" + techo + ", electricidad=" + electricidad + ", fontan=" + fontan
				+ ", operaciones_a_equipos_y_utensilios=" + operaciones_a_equipos_y_utensilios
				+ ", calibracion_equipo_de_frio=" + calibracion_equipo_de_frio + ", revision_balanzas="
				+ revision_balanzas + ", firmado=" + firmado + "]";
	}





}
