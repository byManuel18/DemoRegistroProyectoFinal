package modelos;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import utilidades.CanalConexionH2;

@XmlRootElement(name="CANALES")
@XmlAccessorType(XmlAccessType.FIELD)
public class CanalWrapper {

	@XmlElement(name="Canal")
	private List<CanalConexionH2> canales;

	public List<CanalConexionH2> getCanales(){
		return canales;
	}

	public void setCanales(List<CanalConexionH2> cs){
		this.canales=cs;
	}
}
