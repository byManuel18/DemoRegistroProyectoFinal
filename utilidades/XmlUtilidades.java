package utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import modelos.CanalWrapper;
import modelos.Opciones;

public class XmlUtilidades {

	private static String file="./src/resources/BaseDatos/ConexionesH2.xml";
	private static String fileopci="./Opciones.xml";

	public static void EscribirCanales(List<CanalConexionH2> canales){
		JAXBContext context;

		try{
			context = JAXBContext.newInstance(CanalWrapper.class);
			Marshaller m=context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			CanalWrapper wrapper=new CanalWrapper();
			wrapper.setCanales(canales);
			m.marshal(wrapper, new File(file));


		}catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public static List<CanalConexionH2> LeerCanales(){
		List<CanalConexionH2> canales=new ArrayList<>();
		File f=new File(file);

		if(!f.exists()){
			EscribirArchivos.CrearCarpetasSources();
		}

		if(f.exists()&&f.canRead()){
			try{
				JAXBContext context=JAXBContext.newInstance(CanalWrapper.class);
				Unmarshaller um= context.createUnmarshaller();
				CanalWrapper wrappe= (CanalWrapper) um.unmarshal(f);
				canales.addAll(wrappe.getCanales());
			}catch (JAXBException e) {
				 e.printStackTrace();
			}
		}


		return canales;
	}

	public static void EscribirOPciones(Opciones op){

		JAXBContext context;

		try{
			context = JAXBContext.newInstance(Opciones.class);
			Marshaller m=context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(op, new File(fileopci));
		}catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public static Opciones LeerOpciones(){
		Opciones op=null;
		File f=new File(fileopci);

		if(f.exists()&&f.canRead()){
			try{
				JAXBContext context=JAXBContext.newInstance(Opciones.class);
				Unmarshaller um= context.createUnmarshaller();
				op= (Opciones) um.unmarshal(f);

			}catch (JAXBException e) {
				 e.printStackTrace();
			}
		}


		return op;
	}

	public static Opciones LeerOpcionesOCrearlas(){
		Opciones op=null;
		File f=new File(fileopci);

		if(f.exists()&&f.canRead()){
			try{
				JAXBContext context=JAXBContext.newInstance(Opciones.class);
				Unmarshaller um= context.createUnmarshaller();
				op= (Opciones) um.unmarshal(f);

			}catch (JAXBException e) {
				 e.printStackTrace();
			}
		}else{
			op=new Opciones();
		}


		return op;
	}
}
