package modelos;

import java.time.LocalDate;

import enums.Estado;

public class Temperatura implements Comparable<Temperatura>{

	protected int id_tempe;
	protected LocalDate fecha;
	protected String vitrina_espositora;
	protected String armario_frigorifico;
	protected String armario_productos_cocinados;
	protected String armario_congelador;
	protected String alcon_congelador;
	protected Estado firmado;

	public Temperatura() {
		this(-1,null,"","","","","",Estado.NO_FIRMADO);
	}

	public Temperatura(LocalDate fecha, String vitrina_espositora, String armario_frigorifico,
			String armario_productos_cocinados, String armario_congelador, String alcon_congelador, Estado firmado) {
		this(-1,fecha,vitrina_espositora,armario_frigorifico,armario_productos_cocinados,armario_congelador,alcon_congelador,firmado);
	}

	 public Temperatura(int id_tempe, LocalDate fecha, String vitrina_espositora, String armario_frigorifico,
			String armario_productos_cocinados, String armario_congelador, String alcon_congelador, Estado firmado) {
		this.id_tempe = id_tempe;
		this.fecha = fecha;
		this.vitrina_espositora = vitrina_espositora;
		this.armario_frigorifico = armario_frigorifico;
		this.armario_productos_cocinados = armario_productos_cocinados;
		this.armario_congelador = armario_congelador;
		this.alcon_congelador = alcon_congelador;
		this.firmado = firmado;
	}

	public int getId_tempe() {
		return id_tempe;
	}

	public void setId_tempe(int id_tempe) {
		this.id_tempe = id_tempe;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getVitrina_espositora() {
		return vitrina_espositora;
	}

	public void setVitrina_espositora(String vitrina_espositora) {
		this.vitrina_espositora = vitrina_espositora;
	}

	public String getArmario_frigorifico() {
		return armario_frigorifico;
	}

	public void setArmario_frigorifico(String armario_frigorifico) {
		this.armario_frigorifico = armario_frigorifico;
	}

	public String getArmario_productos_cocinados() {
		return armario_productos_cocinados;
	}

	public void setArmario_productos_cocinados(String armario_productos_cocinados) {
		this.armario_productos_cocinados = armario_productos_cocinados;
	}

	public String getArmario_congelador() {
		return armario_congelador;
	}

	public void setArmario_congelador(String armario_congelador) {
		this.armario_congelador = armario_congelador;
	}

	public String getAlcon_congelador() {
		return alcon_congelador;
	}

	public void setAlcon_congelador(String alcon_congelador) {
		this.alcon_congelador = alcon_congelador;
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
		result = prime * result + id_tempe;
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
		Temperatura other = (Temperatura) obj;
		if (id_tempe != other.id_tempe)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "--> Fecha=" + fecha + ", Vitrina expositora=" + vitrina_espositora
				+ ", Armario frigorifico=" + armario_frigorifico + ", Armario productos cocinados="
				+ armario_productos_cocinados + ", Armario congelador=" + armario_congelador + ", Alcon congelador="
				+ alcon_congelador + ", Firmado=" + firmado;
	}

	@Override
	public int compareTo(Temperatura o) {

		return this.fecha.compareTo(o.fecha);
	}




}
