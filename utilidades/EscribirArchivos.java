package utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import clasemain.Ejecutable;
import modelos.Agua;
import modelos.AguaDAO;
import modelos.Envase;
import modelos.EnvasesDAO;
import modelos.Laure;
import modelos.LaureDAO;
import modelos.Limpieza;
import modelos.LimpiezaDAO;
import modelos.Manipulador;
import modelos.Mantenimiento;
import modelos.MateriaPrima;
import modelos.MateriaPrimaDAO;
import modelos.MedPrev;
import modelos.MedPrevDAO;
import modelos.Produccion;
import modelos.ProduccionDAO;
import modelos.Residuo;
import modelos.ResiduoDAO;
import modelos.Salsa;
import modelos.SalsaDAO;
import modelos.SanidadDAO;
import modelos.SanidadProducto;
import modelos.Temperatura;
import modelos.TemperaturaDAO;
import modelos.TrazCarne;
import modelos.TrazCarneDAO;

public class EscribirArchivos {
	public static String pathname_agua = "./src/resources/TXT/AGUA/Registro_Agua_";
	public static String pathname_carpeta_agua = "./src/resources/TXT/AGUA";
	public static String pathname_src = "./src";

	public static boolean GuardarAgua() {
		boolean guardado = false;
		BufferedWriter bufw = null;
		List<Agua> lista = AguaDAO.SelectAguaAll();
		Collections.sort(lista);
		Set<Integer> años = new TreeSet<>();

		for (Agua a : lista) {
			años.add(a.getFecha().getYear());
		}
		String tablita = FormarlineaAgua(lista) + "**";
		for (Integer n : años) {
			File txt = null;
			if (!Ejecutable.getOPciones().isGuardartxtporDefecto()) {
				if (Ejecutable.getOPciones().getRutaAguaSeleccionada() != null
						&& new File(Ejecutable.getOPciones().getRutaAguaSeleccionada()).exists()) {
					txt = new File(Ejecutable.getOPciones().getRutaAguaSeleccionada() + "/Registro Agua_" + n + ".txt");
				} else {
					txt = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTAgua() + "/Registro Agua_"
							+ n + ".txt");
				}
			} else {
				txt = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTAgua() + "/Registro Agua_" + n
						+ ".txt");
			}

			try {
				bufw = new BufferedWriter(new FileWriter(txt));
				bufw.write("    REGISTRO AGUA AÑO --->>> " + n);
				bufw.newLine();
				bufw.newLine();
				bufw.newLine();

				for (Agua a : lista) {
					if (a.getFecha().getYear() == n) {
						bufw.write(tablita);
						bufw.newLine();
						bufw.write("*" + a.toString());
						String cade = "*" + a.toString();
						for (int i = cade.length(); i < tablita.length() - 1; i++) {
							bufw.write(" ");
						}
						bufw.write("*");
						bufw.newLine();
					}
				}
				bufw.write(tablita);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (bufw != null) {
					try {
						guardado = true;
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
						guardado = false;
					}
				}
				File carperta = null;
				if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
						&& Ejecutable.getOPciones().getRutaAguaSeleccionada() != null) {
					carperta = new File(Ejecutable.getOPciones().getRutaAguaSeleccionada());
					if (!carperta.exists()) {
						carperta = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTAgua());
					}
				} else {
					carperta = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTAgua());
				}
				String[] files = carperta.list();
				boolean esta = false;
				if (files != null) {
					for (String s : files) {
						for (Integer nu : años) {
							if (UtilidadesGenerales.ValidarNombreArchivoAgua(s)) {
								if (UtilidadesGenerales.contarAños(s) == nu) {
									esta = true;
									break;
								}
							}

						}
						if (!esta) {
							File eliminar = null;
							if (!Ejecutable.getOPciones().isGuardartxtporDefecto()) {
								// eliminar=new
								// File(Ejecutable.getOPciones().getRutaAguaSeleccionada()+"/"+s);
								if (Ejecutable.getOPciones().getRutaAguaSeleccionada() != null
										&& new File(Ejecutable.getOPciones().getRutaAguaSeleccionada()).exists()) {
									eliminar = new File(Ejecutable.getOPciones().getRutaAguaSeleccionada() + "/" + s);
								} else {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTAgua() + "/" + s);
								}
							} else {
								eliminar = new File(
										Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTAgua() + "/" + s);
							}
							if (UtilidadesGenerales.ValidarNombreArchivoAgua(s)) {
								eliminar.delete();
							}

						}
						esta = false;
					}
				}

			}
		}

		return guardado;
	}

	public static boolean GuardarRegistroCárnico() {
		boolean guardado = false;
		BufferedWriter bufw = null;
		List<SanidadProducto> lista = SanidadDAO.SelecAll();
		Collections.sort(lista);
		Set<Integer> años = new TreeSet<>();

		for (SanidadProducto a : lista) {
			años.add(a.getFecha().getYear());
		}
		String tablita = FormarlineaCarne(lista) + "**";
		for (Integer n : años) {
			File txt = null;
			if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
					&& Ejecutable.getOPciones().getRutaCarneSeleccionada() != null) {
				if (new File(Ejecutable.getOPciones().getRutaCarneSeleccionada()).exists()) {
					txt = new File(Ejecutable.getOPciones().getRutaCarneSeleccionada() + "/Registro Productos Carnicos_"
							+ n + ".txt");
				} else {
					txt = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTProductosCarnicos()
							+ "/Registro Productos Carnicos_" + n + ".txt");
				}
			} else {
				txt = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTProductosCarnicos()
						+ "/Registro Productos Carnicos_" + n + ".txt");
			}

			try {
				bufw = new BufferedWriter(new FileWriter(txt));
				bufw.write("    REGISTRO PRODUCTOS CÁRNICOS AÑO --->>> " + n);
				bufw.newLine();
				bufw.newLine();
				bufw.newLine();

				for (SanidadProducto a : lista) {
					if (a.getFecha().getYear() == n) {
						bufw.write(tablita);
						bufw.newLine();
						bufw.write("*" + a.toString());
						String cade = "*" + a.toString();
						for (int i = cade.length(); i < tablita.length() - 1; i++) {
							bufw.write(" ");
						}
						bufw.write("*");
						bufw.newLine();
					}
				}
				bufw.write(tablita);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (bufw != null) {
					try {
						guardado = true;
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
						guardado = false;
					}
				}
				File carperta = null;
				if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
						&& Ejecutable.getOPciones().getRutaCarneSeleccionada() != null) {
					carperta = new File(Ejecutable.getOPciones().getRutaCarneSeleccionada());
					if (!carperta.exists()) {
						carperta = new File(
								Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTProductosCarnicos());
					}
				} else {
					carperta = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTProductosCarnicos());
				}
				String[] files = carperta.list();
				boolean esta = false;
				if (files != null) {
					for (String s : files) {
						for (Integer nu : años) {
							if (UtilidadesGenerales.ValidarNombreArchivoCarne(s)) {
								if (UtilidadesGenerales.contarAños(s) == nu) {
									esta = true;
									break;
								}
							}

						}
						if (!esta) {
							File eliminar = null;
							if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
									&& Ejecutable.getOPciones().getRutaCarneSeleccionada() != null) {
								// eliminar=new
								// File(Ejecutable.getOPciones().getRutaCarneSeleccionada()+"/"+s);
								if (new File(Ejecutable.getOPciones().getRutaCarneSeleccionada()).exists()) {
									eliminar = new File(Ejecutable.getOPciones().getRutaCarneSeleccionada() + "/" + s);
								} else {
									eliminar = new File(Ejecutable.getOPciones()
											.getRutaDefectoGUaradoBaseDatosTXTProductosCarnicos() + "/" + s);
								}
							} else {
								eliminar = new File(
										Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTProductosCarnicos()
												+ "/" + s);
							}
							if (UtilidadesGenerales.ValidarNombreArchivoCarne(s)) {
								eliminar.delete();
							}

						}
						esta = false;
					}
				}

			}
		}

		return guardado;
	}

	public static boolean GuardarRegistroTemperaturas() {
		boolean guardado = false;
		BufferedWriter bufw = null;
		List<Temperatura> lista = TemperaturaDAO.SelectAll();
		Collections.sort(lista);
		Set<Integer> años = new TreeSet<>();

		for (Temperatura a : lista) {
			años.add(a.getFecha().getYear());
		}
		String tablita = FormarlineaTemperaturas(lista) + "**";
		for (Integer n : años) {
			File txt = null;
			if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
					&& Ejecutable.getOPciones().getRutaTemperaturaSeleccionada() != null) {
				if (new File(Ejecutable.getOPciones().getRutaTemperaturaSeleccionada()).exists()) {
					txt = new File(Ejecutable.getOPciones().getRutaTemperaturaSeleccionada() + "/Registro Temperaturas_"
							+ n + ".txt");
				} else {
					txt = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTTemperaturas()
							+ "/Registro Temperaturas_" + n + ".txt");
				}
			} else {
				txt = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTTemperaturas()
						+ "/Registro Temperaturas_" + n + ".txt");
			}

			try {
				bufw = new BufferedWriter(new FileWriter(txt));
				bufw.write("    REGISTRO TEMPERATURAS AÑO --->>> " + n);
				bufw.newLine();
				bufw.newLine();
				bufw.newLine();

				for (Temperatura a : lista) {
					if (a.getFecha().getYear() == n) {
						bufw.write(tablita);
						bufw.newLine();
						bufw.write("*" + a.toString());
						String cade = "*" + a.toString();
						for (int i = cade.length(); i < tablita.length() - 1; i++) {
							bufw.write(" ");
						}
						bufw.write("*");
						bufw.newLine();
					}
				}
				bufw.write(tablita);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (bufw != null) {
					try {
						guardado = true;
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
						guardado = false;
					}
				}
				File carperta = null;
				if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
						&& Ejecutable.getOPciones().getRutaTemperaturaSeleccionada() != null) {
					carperta = new File(Ejecutable.getOPciones().getRutaTemperaturaSeleccionada());
					if (!carperta.exists()) {
						carperta = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTTemperaturas());
					}
				} else {
					carperta = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTTemperaturas());
				}
				String[] files = carperta.list();
				boolean esta = false;
				if (files != null) {
					for (String s : files) {
						for (Integer nu : años) {
							if (UtilidadesGenerales.ValidarNombreArchivoTemperaturas(s)) {
								if (UtilidadesGenerales.contarAños(s) == nu) {
									esta = true;
									break;
								}
							}

						}
						if (!esta) {
							File eliminar = null;
							if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
									&& Ejecutable.getOPciones().getRutaTemperaturaSeleccionada() != null) {
								// eliminar=new
								// File(Ejecutable.getOPciones().getRutaCarneSeleccionada()+"/"+s);
								if (new File(Ejecutable.getOPciones().getRutaCarneSeleccionada()).exists()) {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaTemperaturaSeleccionada() + "/" + s);
								} else {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTTemperaturas()
													+ "/" + s);
								}
							} else {
								eliminar = new File(
										Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTTemperaturas() + "/"
												+ s);
							}
							if (UtilidadesGenerales.ValidarNombreArchivoTemperaturas(s)) {
								eliminar.delete();
							}

						}
						esta = false;
					}
				}

			}
		}

		return guardado;
	}

	public static boolean GuardarRegistroResiduos() {
		boolean guardado = false;
		BufferedWriter bufw = null;
		List<Residuo> lista = ResiduoDAO.SelectAll();
		Collections.sort(lista);
		Set<Integer> años = new TreeSet<>();

		for (Residuo a : lista) {
			años.add(a.getFecha().getYear());
		}
		String tablita = FormarlineaResiduos(lista) + "**";
		for (Integer n : años) {
			File txt = null;
			if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
					&& Ejecutable.getOPciones().getRutaResiduosSeleccionada() != null) {
				if (new File(Ejecutable.getOPciones().getRutaResiduosSeleccionada()).exists()) {
					txt = new File(Ejecutable.getOPciones().getRutaResiduosSeleccionada() + "/Registro Residuos_" + n
							+ ".txt");
				} else {
					txt = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTResiduos()
							+ "/Registro Residuos_" + n + ".txt");
				}
			} else {
				txt = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTResiduos()
						+ "/Registro Residuos_" + n + ".txt");
			}

			try {
				bufw = new BufferedWriter(new FileWriter(txt));
				bufw.write("    REGISTRO RESIDUOS AÑO --->>> " + n);
				bufw.newLine();
				bufw.newLine();
				bufw.newLine();

				for (Residuo a : lista) {
					if (a.getFecha().getYear() == n) {
						bufw.write(tablita);
						bufw.newLine();
						bufw.write("*" + a.toString());
						String cade = "*" + a.toString();
						for (int i = cade.length(); i < tablita.length() - 1; i++) {
							bufw.write(" ");
						}
						bufw.write("*");
						bufw.newLine();
					}
				}
				bufw.write(tablita);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (bufw != null) {
					try {
						guardado = true;
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
						guardado = false;
					}
				}
				File carperta = null;
				if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
						&& Ejecutable.getOPciones().getRutaResiduosSeleccionada() != null) {
					carperta = new File(Ejecutable.getOPciones().getRutaResiduosSeleccionada());
					if (!carperta.exists()) {
						carperta = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTResiduos());
					}
				} else {
					carperta = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTResiduos());
				}
				String[] files = carperta.list();
				boolean esta = false;
				if (files != null) {
					for (String s : files) {
						for (Integer nu : años) {
							if (UtilidadesGenerales.ValidarNombreArchivorResiduos(s)) {
								if (UtilidadesGenerales.contarAños(s) == nu) {
									esta = true;
									break;
								}
							}

						}
						if (!esta) {
							File eliminar = null;
							if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
									&& Ejecutable.getOPciones().getRutaResiduosSeleccionada() != null) {
								// eliminar=new
								// File(Ejecutable.getOPciones().getRutaCarneSeleccionada()+"/"+s);
								if (new File(Ejecutable.getOPciones().getRutaResiduosSeleccionada()).exists()) {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaResiduosSeleccionada() + "/" + s);
								} else {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTResiduos() + "/"
													+ s);
								}
							} else {
								eliminar = new File(
										Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTResiduos() + "/" + s);
							}
							if (UtilidadesGenerales.ValidarNombreArchivorResiduos(s)) {
								eliminar.delete();
							}

						}
						esta = false;
					}
				}

			}
		}

		return guardado;
	}

	public static boolean GuardarRegistroLimpieza() {
		boolean guardado = false;
		BufferedWriter bufw = null;
		List<Limpieza> lista = LimpiezaDAO.ListarAll();
		Collections.sort(lista);
		Set<Integer> años = new TreeSet<>();

		for (Limpieza a : lista) {
			años.add(a.getFecha().getYear());
		}
		String tablita = FormarlineaLimpieza(lista) + "**";
		for (Integer n : años) {
			File txt = null;
			if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
					&& Ejecutable.getOPciones().getRutaLimpiezaSeleccionada() != null) {
				if (new File(Ejecutable.getOPciones().getRutaLimpiezaSeleccionada()).exists()) {
					txt = new File(Ejecutable.getOPciones().getRutaLimpiezaSeleccionada() + "/Registro Limpieza_" + n
							+ ".txt");
				} else {
					txt = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTLimpieza()
							+ "/Registro Limpieza_" + n + ".txt");
				}
			} else {
				txt = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTLimpieza()
						+ "/Registro Limpieza_" + n + ".txt");
			}

			try {
				bufw = new BufferedWriter(new FileWriter(txt));
				bufw.write("    REGISTRO LIMPIEZA AÑO --->>> " + n);
				bufw.newLine();
				bufw.newLine();
				bufw.newLine();

				for (Limpieza a : lista) {
					if (a.getFecha().getYear() == n) {
						bufw.write(tablita);
						bufw.newLine();
						bufw.write("*" + a.toString());
						String cade = "*" + a.toString();
						for (int i = cade.length(); i < tablita.length() - 1; i++) {
							bufw.write(" ");
						}
						bufw.write("*");
						bufw.newLine();
					}
				}
				bufw.write(tablita);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (bufw != null) {
					try {
						guardado = true;
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
						guardado = false;
					}
				}
				File carperta = null;
				if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
						&& Ejecutable.getOPciones().getRutaLimpiezaSeleccionada() != null) {
					carperta = new File(Ejecutable.getOPciones().getRutaLimpiezaSeleccionada());
					if (!carperta.exists()) {
						carperta = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTLimpieza());
					}
				} else {
					carperta = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTLimpieza());
				}
				String[] files = carperta.list();
				boolean esta = false;
				if (files != null) {
					for (String s : files) {
						for (Integer nu : años) {
							if (UtilidadesGenerales.ValidarNombreArchivoLimpieza(s)) {
								if (UtilidadesGenerales.contarAños(s) == nu) {
									esta = true;
									break;
								}
							}

						}
						if (!esta) {
							File eliminar = null;
							if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
									&& Ejecutable.getOPciones().getRutaLimpiezaSeleccionada() != null) {
								// eliminar=new
								// File(Ejecutable.getOPciones().getRutaCarneSeleccionada()+"/"+s);
								if (new File(Ejecutable.getOPciones().getRutaLimpiezaSeleccionada()).exists()) {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaLimpiezaSeleccionada() + "/" + s);
								} else {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTLimpieza() + "/"
													+ s);
								}
							} else {
								eliminar = new File(
										Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTLimpieza() + "/" + s);
							}
							if (UtilidadesGenerales.ValidarNombreArchivoLimpieza(s)) {
								eliminar.delete();
							}

						}
						esta = false;
					}
				}

			}
		}

		return guardado;
	}

	public static boolean GuardarRegistroMedidasPrebentibas() {
		boolean guardado = false;
		BufferedWriter bufw = null;
		List<MedPrev> lista = MedPrevDAO.SelectAll();
		Collections.sort(lista);
		Set<Integer> años = new TreeSet<>();

		for (MedPrev a : lista) {
			años.add(a.getFecha().getYear());
		}
		String tablita = FormarlineaMedidas(lista) + "**";
		for (Integer n : años) {
			File txt = null;
			if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
					&& Ejecutable.getOPciones().getRutaManteniminetoSeleccionada() != null) {
				if (new File(Ejecutable.getOPciones().getRutaManteniminetoSeleccionada()).exists()) {
					txt = new File(Ejecutable.getOPciones().getRutaManteniminetoSeleccionada()
							+ "/Registro Medidas Prebentibas_" + n + ".txt");
				} else {
					txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtmedidasprebentibas()
							+ "/Registro Medidas Prebentibas_" + n + ".txt");
				}
			} else {
				txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtmedidasprebentibas()
						+ "/Registro Medidas Prebentibas_" + n + ".txt");
			}

			try {
				bufw = new BufferedWriter(new FileWriter(txt));
				bufw.write("    REGISTRO MEDIDAS PREBENTIBAS AÑO --->>> " + n);
				bufw.newLine();
				bufw.newLine();
				bufw.newLine();

				for (MedPrev a : lista) {
					if (a.getFecha().getYear() == n) {
						bufw.write(tablita);
						bufw.newLine();
						bufw.write("*" + a.toString());
						String cade = "*" + a.toString();
						for (int i = cade.length(); i < tablita.length() - 1; i++) {
							bufw.write(" ");
						}
						bufw.write("*");
						bufw.newLine();
					}
				}
				bufw.write(tablita);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (bufw != null) {
					try {
						guardado = true;
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
						guardado = false;
					}
				}
				File carperta = null;
				if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
						&& Ejecutable.getOPciones().getRutaManteniminetoSeleccionada() != null) {
					carperta = new File(Ejecutable.getOPciones().getRutaManteniminetoSeleccionada());
					if (!carperta.exists()) {
						carperta = new File(
								Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtmedidasprebentibas());
					}
				} else {
					carperta = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtmedidasprebentibas());
				}
				String[] files = carperta.list();
				boolean esta = false;
				if (files != null) {
					for (String s : files) {
						for (Integer nu : años) {
							if (UtilidadesGenerales.ValidarNombreArchivorMedidas(s)) {
								if (UtilidadesGenerales.contarAños(s) == nu) {
									esta = true;
									break;
								}
							}

						}
						if (!esta) {
							File eliminar = null;
							if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
									&& Ejecutable.getOPciones().getRutaManteniminetoSeleccionada() != null) {
								// eliminar=new
								// File(Ejecutable.getOPciones().getRutaCarneSeleccionada()+"/"+s);
								if (new File(Ejecutable.getOPciones().getRutaManteniminetoSeleccionada()).exists()) {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaManteniminetoSeleccionada() + "/" + s);
								} else {
									eliminar = new File(Ejecutable.getOPciones()
											.getRutadefectoguaradobasedatostxtmedidasprebentibas() + "/" + s);
								}
							} else {
								eliminar = new File(
										Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtmedidasprebentibas()
												+ "/" + s);
							}
							if (UtilidadesGenerales.ValidarNombreArchivorMedidas(s)) {
								eliminar.delete();
							}

						}
						esta = false;
					}
				}

			}
		}

		return guardado;
	}

	public static boolean GuardarTrazabilidadCarne() {
		boolean guardado = false;
		BufferedWriter bufw = null;
		List<TrazCarne> lista = TrazCarneDAO.SelectAll();
		Collections.sort(lista);
		Set<Integer> años = new TreeSet<>();

		for (TrazCarne a : lista) {
			años.add(a.getProcar().getFecha().getYear());
		}
		String tablita = FormarTrazCarne(lista) + "**";
		for (Integer n : años) {
			File txt = null;
			if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
					&& Ejecutable.getOPciones().getRutaTrazCarneSeleccionada() != null) {
				if (new File(Ejecutable.getOPciones().getRutaTrazCarneSeleccionada()).exists()) {
					txt = new File(Ejecutable.getOPciones().getRutaTrazCarneSeleccionada() + "/Trazabilidad_Carne_" + n
							+ ".txt");
				} else {
					txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadcarne()
							+ "/Trazabilidad_Carne_" + n + ".txt");
				}
			} else {
				txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadcarne()
						+ "/Trazabilidad_Carne_" + n + ".txt");
			}

			try {
				bufw = new BufferedWriter(new FileWriter(txt));
				bufw.write("    REGISTRO TRAZABILIDAD CARNE AÑO --->>> " + n);
				bufw.newLine();
				bufw.newLine();
				bufw.newLine();

				for (TrazCarne a : lista) {
					if (a.getProcar().getFecha().getYear() == n) {
						bufw.write(tablita);
						bufw.newLine();
						bufw.write("*" + a.toString());
						String cade = "*" + a.toString();
						for (int i = cade.length(); i < tablita.length() - 1; i++) {
							bufw.write(" ");
						}
						bufw.write("*");
						bufw.newLine();
					}
				}
				bufw.write(tablita);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (bufw != null) {
					try {
						guardado = true;
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
						guardado = false;
					}
				}
				File carperta = null;
				if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
						&& Ejecutable.getOPciones().getRutaTrazCarneSeleccionada() != null) {
					carperta = new File(Ejecutable.getOPciones().getRutaTrazCarneSeleccionada());
					if (!carperta.exists()) {
						carperta = new File(
								Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadcarne());
					}
				} else {
					carperta = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadcarne());
				}
				String[] files = carperta.list();
				boolean esta = false;
				if (files != null) {
					for (String s : files) {
						for (Integer nu : años) {
							if (UtilidadesGenerales.ValidarNombreArchivorTrazCarne(s)) {
								if (UtilidadesGenerales.contarAños(s) == nu) {
									esta = true;
									break;
								}
							}

						}
						if (!esta) {
							File eliminar = null;
							if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
									&& Ejecutable.getOPciones().getRutaTrazCarneSeleccionada() != null) {
								// eliminar=new
								// File(Ejecutable.getOPciones().getRutaCarneSeleccionada()+"/"+s);
								if (new File(Ejecutable.getOPciones().getRutaTrazCarneSeleccionada()).exists()) {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaTrazCarneSeleccionada() + "/" + s);
								} else {
									eliminar = new File(Ejecutable.getOPciones()
											.getRutadefectoguaradobasedatostxttrazabilidadcarne() + "/" + s);
								}
							} else {
								eliminar = new File(
										Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadcarne()
												+ "/" + s);
							}
							if (UtilidadesGenerales.ValidarNombreArchivorTrazCarne(s)) {
								eliminar.delete();
							}

						}
						esta = false;
					}
				}

			}
		}

		return guardado;
	}

	public static boolean GuardarTrazabilidadLaure() {
		boolean guardado = false;
		BufferedWriter bufw = null;
		List<Laure> lista = LaureDAO.SelectAll();
		Collections.sort(lista);
		Set<Integer> años = new TreeSet<>();

		for (Laure a : lista) {
			años.add(a.getFecha_llegada().getYear());
		}
		String tablita = FormarTrazLaure(lista) + "**";
		for (Integer n : años) {
			File txt = null;
			if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
					&& Ejecutable.getOPciones().getRutaTrazLaureSeleccionada() != null) {
				if (new File(Ejecutable.getOPciones().getRutaTrazLaureSeleccionada()).exists()) {
					txt = new File(Ejecutable.getOPciones().getRutaTrazLaureSeleccionada() + "/Trazabilidad_Laure_" + n
							+ ".txt");
				} else {
					txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadlaure()
							+ "/Trazabilidad_Laure_" + n + ".txt");
				}
			} else {
				txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadlaure()
						+ "/Trazabilidad_Laure_" + n + ".txt");
			}

			try {
				bufw = new BufferedWriter(new FileWriter(txt));
				bufw.write("    REGISTRO TRAZABILIDAD LAURE AÑO --->>> " + n);
				bufw.newLine();
				bufw.newLine();
				bufw.newLine();

				for (Laure a : lista) {
					if (a.getFecha_llegada().getYear() == n) {
						bufw.write(tablita);
						bufw.newLine();
						bufw.write("*" + a.toString());
						String cade = "*" + a.toString();
						for (int i = cade.length(); i < tablita.length() - 1; i++) {
							bufw.write(" ");
						}
						bufw.write("*");
						bufw.newLine();
					}
				}
				bufw.write(tablita);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (bufw != null) {
					try {
						guardado = true;
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
						guardado = false;
					}
				}
				File carperta = null;
				if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
						&& Ejecutable.getOPciones().getRutaTrazLaureSeleccionada() != null) {
					carperta = new File(Ejecutable.getOPciones().getRutaTrazLaureSeleccionada());
					if (!carperta.exists()) {
						carperta = new File(
								Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadlaure());
					}
				} else {
					carperta = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadlaure());
				}
				String[] files = carperta.list();
				boolean esta = false;
				if (files != null) {
					for (String s : files) {
						for (Integer nu : años) {
							if (UtilidadesGenerales.ValidarNombreArchivorTrazLaure(s)) {
								if (UtilidadesGenerales.contarAños(s) == nu) {
									esta = true;
									break;
								}
							}

						}
						if (!esta) {
							File eliminar = null;
							if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
									&& Ejecutable.getOPciones().getRutaTrazLaureSeleccionada() != null) {
								// eliminar=new
								// File(Ejecutable.getOPciones().getRutaCarneSeleccionada()+"/"+s);
								if (new File(Ejecutable.getOPciones().getRutaTrazLaureSeleccionada()).exists()) {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaTrazLaureSeleccionada() + "/" + s);
								} else {
									eliminar = new File(Ejecutable.getOPciones()
											.getRutadefectoguaradobasedatostxttrazabilidadlaure() + "/" + s);
								}
							} else {
								eliminar = new File(
										Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadlaure()
												+ "/" + s);
							}
							if (UtilidadesGenerales.ValidarNombreArchivorTrazLaure(s)) {
								eliminar.delete();
							}

						}
						esta = false;
					}
				}

			}
		}

		return guardado;
	}

	public static boolean GuardarRegistroTrazMateriaPrima() {
		boolean guardado = false;
		BufferedWriter bufw = null;
		List<MateriaPrima> lista = MateriaPrimaDAO.SelectAll();
		Collections.sort(lista);
		Set<Integer> años = new TreeSet<>();

		for (MateriaPrima a : lista) {
			años.add(a.getFecha_llegada().getYear());
		}
		String tablita = FormarTrazMateriaPrima(lista) + "**";
		for (Integer n : años) {
			File txt = null;
			if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
					&& Ejecutable.getOPciones().getRutaTrazMateriaPrimaSeleccionada() != null) {
				if (new File(Ejecutable.getOPciones().getRutaTrazMateriaPrimaSeleccionada()).exists()) {
					txt = new File(Ejecutable.getOPciones().getRutaTrazMateriaPrimaSeleccionada()
							+ "/Trazabilidad_Materia_Prima_" + n + ".txt");
				} else {
					txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadmateriaprima()
							+ "/Trazabilidad_Materia_Prima_" + n + ".txt");
				}
			} else {
				txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadmateriaprima()
						+ "/Trazabilidad_Materia_Prima_" + n + ".txt");
			}

			try {
				bufw = new BufferedWriter(new FileWriter(txt));
				bufw.write("    REGISTRO TRAZABILIDAD MATERIA PRIMA AÑO --->>> " + n);
				bufw.newLine();
				bufw.newLine();
				bufw.newLine();

				for (MateriaPrima a : lista) {
					if (a.getFecha_llegada().getYear() == n) {
						bufw.write(tablita);
						bufw.newLine();
						bufw.write("*" + a.toString());
						String cade = "*" + a.toString();
						for (int i = cade.length(); i < tablita.length() - 1; i++) {
							bufw.write(" ");
						}
						bufw.write("*");
						bufw.newLine();
					}
				}
				bufw.write(tablita);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (bufw != null) {
					try {
						guardado = true;
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
						guardado = false;
					}
				}
				File carperta = null;
				if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
						&& Ejecutable.getOPciones().getRutaTrazMateriaPrimaSeleccionada() != null) {
					carperta = new File(Ejecutable.getOPciones().getRutaTrazMateriaPrimaSeleccionada());
					if (!carperta.exists()) {
						carperta = new File(
								Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadmateriaprima());
					}
				} else {
					carperta = new File(
							Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadmateriaprima());
				}
				String[] files = carperta.list();
				boolean esta = false;
				if (files != null) {
					for (String s : files) {
						for (Integer nu : años) {
							if (UtilidadesGenerales.ValidarNombreArchivorTrazMateriaPrima(s)) {
								if (UtilidadesGenerales.contarAños(s) == nu) {
									esta = true;
									break;
								}
							}

						}
						if (!esta) {
							File eliminar = null;
							if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
									&& Ejecutable.getOPciones().getRutaTrazMateriaPrimaSeleccionada() != null) {
								// eliminar=new
								// File(Ejecutable.getOPciones().getRutaCarneSeleccionada()+"/"+s);
								if (new File(Ejecutable.getOPciones().getRutaTrazMateriaPrimaSeleccionada()).exists()) {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaTrazMateriaPrimaSeleccionada() + "/" + s);
								} else {
									eliminar = new File(Ejecutable.getOPciones()
											.getRutadefectoguaradobasedatostxttrazabilidadmateriaprima() + "/" + s);
								}
							} else {
								eliminar = new File(Ejecutable.getOPciones()
										.getRutadefectoguaradobasedatostxttrazabilidadmateriaprima() + "/" + s);
							}
							if (UtilidadesGenerales.ValidarNombreArchivorTrazMateriaPrima(s)) {
								eliminar.delete();
							}

						}
						esta = false;
					}
				}

			}
		}

		return guardado;
	}

	public static boolean GuardarRegistroTrazSalsas() {
		boolean guardado = false;
		BufferedWriter bufw = null;
		List<Salsa> lista = SalsaDAO.SelectAll();
		Collections.sort(lista);
		Set<Integer> años = new TreeSet<>();

		for (Salsa a : lista) {
			años.add(a.getFecha_llegada().getYear());
		}
		String tablita = FormarTrazSalsas(lista) + "**";
		for (Integer n : años) {
			File txt = null;
			if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
					&& Ejecutable.getOPciones().getRutaTrazSalsasSeleccionada() != null) {
				if (new File(Ejecutable.getOPciones().getRutaTrazSalsasSeleccionada()).exists()) {
					txt = new File(Ejecutable.getOPciones().getRutaTrazSalsasSeleccionada() + "/Trazabilidad_Salsas_"
							+ n + ".txt");
				} else {
					txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadsalsas()
							+ "/Trazabilidad_Salsas_" + n + ".txt");
				}
			} else {
				txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadsalsas()
						+ "/Trazabilidad_Salsas_" + n + ".txt");
			}

			try {
				bufw = new BufferedWriter(new FileWriter(txt));
				bufw.write("    REGISTRO TRAZABILIDAD SALSAS PRIMA AÑO --->>> " + n);
				bufw.newLine();
				bufw.newLine();
				bufw.newLine();

				for (Salsa a : lista) {
					if (a.getFecha_llegada().getYear() == n) {
						bufw.write(tablita);
						bufw.newLine();
						bufw.write("*" + a.toString());
						String cade = "*" + a.toString();
						for (int i = cade.length(); i < tablita.length() - 1; i++) {
							bufw.write(" ");
						}
						bufw.write("*");
						bufw.newLine();
					}
				}
				bufw.write(tablita);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (bufw != null) {
					try {
						guardado = true;
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
						guardado = false;
					}
				}
				File carperta = null;
				if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
						&& Ejecutable.getOPciones().getRutaTrazSalsasSeleccionada() != null) {
					carperta = new File(Ejecutable.getOPciones().getRutaTrazSalsasSeleccionada());
					if (!carperta.exists()) {
						carperta = new File(
								Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadsalsas());
					}
				} else {
					carperta = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadsalsas());
				}
				String[] files = carperta.list();
				boolean esta = false;
				if (files != null) {
					for (String s : files) {
						for (Integer nu : años) {
							if (UtilidadesGenerales.ValidarNombreArchivorTrazSalsas(s)) {
								if (UtilidadesGenerales.contarAños(s) == nu) {
									esta = true;
									break;
								}
							}

						}
						if (!esta) {
							File eliminar = null;
							if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
									&& Ejecutable.getOPciones().getRutaTrazSalsasSeleccionada() != null) {
								// eliminar=new
								// File(Ejecutable.getOPciones().getRutaCarneSeleccionada()+"/"+s);
								if (new File(Ejecutable.getOPciones().getRutaTrazSalsasSeleccionada()).exists()) {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaTrazSalsasSeleccionada() + "/" + s);
								} else {
									eliminar = new File(Ejecutable.getOPciones()
											.getRutadefectoguaradobasedatostxttrazabilidadsalsas() + "/" + s);
								}
							} else {
								eliminar = new File(
										Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadsalsas()
												+ "/" + s);
							}
							if (UtilidadesGenerales.ValidarNombreArchivorTrazSalsas(s)) {
								eliminar.delete();
							}

						}
						esta = false;
					}
				}

			}
		}

		return guardado;
	}

	public static boolean GuardarRegistroTrazEnvases() {
		boolean guardado = false;
		BufferedWriter bufw = null;
		List<Envase> lista = EnvasesDAO.SelectAll();
		Collections.sort(lista);
		Set<Integer> años = new TreeSet<>();

		for (Envase a : lista) {
			años.add(a.getFecha_llegada().getYear());
		}
		String tablita = FormarTrazEnvases(lista) + "**";
		for (Integer n : años) {
			File txt = null;
			if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
					&& Ejecutable.getOPciones().getRutaTrazEnvasesSeleccionada() != null) {
				if (new File(Ejecutable.getOPciones().getRutaTrazEnvasesSeleccionada()).exists()) {
					txt = new File(Ejecutable.getOPciones().getRutaTrazEnvasesSeleccionada() + "/Trazabilidad_Envases_"
							+ n + ".txt");
				} else {
					txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadenvases()
							+ "/Trazabilidad_Envases_" + n + ".txt");
				}
			} else {
				txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadenvases()
						+ "/Trazabilidad_Envases_" + n + ".txt");
			}

			try {
				bufw = new BufferedWriter(new FileWriter(txt));
				bufw.write("    REGISTRO TRAZABILIDAD SALSAS PRIMA AÑO --->>> " + n);
				bufw.newLine();
				bufw.newLine();
				bufw.newLine();

				for (Envase a : lista) {
					if (a.getFecha_llegada().getYear() == n) {
						bufw.write(tablita);
						bufw.newLine();
						bufw.write("*" + a.toString());
						String cade = "*" + a.toString();
						for (int i = cade.length(); i < tablita.length() - 1; i++) {
							bufw.write(" ");
						}
						bufw.write("*");
						bufw.newLine();
					}
				}
				bufw.write(tablita);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (bufw != null) {
					try {
						guardado = true;
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
						guardado = false;
					}
				}
				File carperta = null;
				if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
						&& Ejecutable.getOPciones().getRutaTrazEnvasesSeleccionada() != null) {
					carperta = new File(Ejecutable.getOPciones().getRutaTrazEnvasesSeleccionada());
					if (!carperta.exists()) {
						carperta = new File(
								Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadenvases());
					}
				} else {
					carperta = new File(
							Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadenvases());
				}
				String[] files = carperta.list();
				boolean esta = false;
				if (files != null) {
					for (String s : files) {
						for (Integer nu : años) {
							if (UtilidadesGenerales.ValidarNombreArchivorTrazEnvases(s)) {
								if (UtilidadesGenerales.contarAños(s) == nu) {
									esta = true;
									break;
								}
							}

						}
						if (!esta) {
							File eliminar = null;
							if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
									&& Ejecutable.getOPciones().getRutaTrazEnvasesSeleccionada() != null) {
								// eliminar=new
								// File(Ejecutable.getOPciones().getRutaCarneSeleccionada()+"/"+s);
								if (new File(Ejecutable.getOPciones().getRutaTrazEnvasesSeleccionada()).exists()) {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaTrazEnvasesSeleccionada() + "/" + s);
								} else {
									eliminar = new File(Ejecutable.getOPciones()
											.getRutadefectoguaradobasedatostxttrazabilidadenvases() + "/" + s);
								}
							} else {
								eliminar = new File(
										Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadenvases()
												+ "/" + s);
							}
							if (UtilidadesGenerales.ValidarNombreArchivorTrazEnvases(s)) {
								eliminar.delete();
							}

						}
						esta = false;
					}
				}

			}
		}

		return guardado;
	}

	public static boolean GuardarRegistroProduccion() {
		boolean guardado = false;
		BufferedWriter bufw = null;
		List<Produccion> lista = ProduccionDAO.SelectAll();
		Collections.sort(lista);
		Set<Integer> años = new TreeSet<>();

		for (Produccion a : lista) {
			años.add(a.getFecha_lote().getYear());
		}
		String tablita = Formarproduccion(lista) + "**";
		for (Integer n : años) {
			File txt = null;
			if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
					&& Ejecutable.getOPciones().getRutaproduccionSeleccionada() != null) {
				if (new File(Ejecutable.getOPciones().getRutaproduccionSeleccionada()).exists()) {
					txt = new File(
							Ejecutable.getOPciones().getRutaproduccionSeleccionada() + "/Produccion_" + n + ".txt");
				} else {
					txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtproduccion()
							+ "/Produccion_" + n + ".txt");
				}
			} else {
				txt = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtproduccion() + "/Produccion_"
						+ n + ".txt");
			}

			try {
				bufw = new BufferedWriter(new FileWriter(txt));
				bufw.write("    REGISTRO PRODUCCIÓN AÑO --->>> " + n);
				bufw.newLine();
				bufw.newLine();
				bufw.newLine();

				for (Produccion a : lista) {
					if (a.getFecha_lote().getYear() == n) {
						bufw.write(tablita);
						bufw.newLine();
						bufw.write("*" + a.toString());
						String cade = "*" + a.toString();
						for (int i = cade.length(); i < tablita.length() - 1; i++) {
							bufw.write(" ");
						}
						bufw.write("*");
						bufw.newLine();
					}
				}
				bufw.write(tablita);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (bufw != null) {
					try {
						guardado = true;
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
						guardado = false;
					}
				}
				File carperta = null;
				if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
						&& Ejecutable.getOPciones().getRutaproduccionSeleccionada() != null) {
					carperta = new File(Ejecutable.getOPciones().getRutaproduccionSeleccionada());
					if (!carperta.exists()) {
						carperta = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtproduccion());
					}
				} else {
					carperta = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtproduccion());
				}
				String[] files = carperta.list();
				boolean esta = false;
				if (files != null) {
					for (String s : files) {
						for (Integer nu : años) {
							if (UtilidadesGenerales.ValidarNombreArchivorProduccion(s)) {
								if (UtilidadesGenerales.contarAños(s) == nu) {
									esta = true;
									break;
								}
							}

						}
						if (!esta) {
							File eliminar = null;
							if (!Ejecutable.getOPciones().isGuardartxtporDefecto()
									&& Ejecutable.getOPciones().getRutaproduccionSeleccionada() != null) {
								// eliminar=new
								// File(Ejecutable.getOPciones().getRutaCarneSeleccionada()+"/"+s);
								if (new File(Ejecutable.getOPciones().getRutaproduccionSeleccionada()).exists()) {
									eliminar = new File(
											Ejecutable.getOPciones().getRutaproduccionSeleccionada() + "/" + s);
								} else {
									eliminar = new File(
											Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtproduccion() + "/"
													+ s);
								}
							} else {
								eliminar = new File(
										Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtproduccion() + "/"
												+ s);
							}
							if (UtilidadesGenerales.ValidarNombreArchivorProduccion(s)) {
								eliminar.delete();
							}

						}
						esta = false;
					}
				}

			}
		}

		return guardado;
	}

	public static String FormarlineaAgua(List<Agua> l) {
		String cadena = "";
		String caracteres = "";
		for (Agua a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String FormarlineaCarne(List<SanidadProducto> l) {
		String cadena = "";
		String caracteres = "";
		for (SanidadProducto a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String FormarlineaTemperaturas(List<Temperatura> l) {
		String cadena = "";
		String caracteres = "";
		for (Temperatura a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String FormarlineaLimpieza(List<Limpieza> l) {
		String cadena = "";
		String caracteres = "";
		for (Limpieza a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String FormarlineaResiduos(List<Residuo> l) {
		String cadena = "";
		String caracteres = "";
		for (Residuo a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String FormarlineaMedidas(List<MedPrev> l) {
		String cadena = "";
		String caracteres = "";
		for (MedPrev a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String FormarlineaMedidasMantenimiento(List<Mantenimiento> l) {
		String cadena = "";
		String caracteres = "";
		for (Mantenimiento a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String FormarlineaMedidasMantenimientoManipulador(List<Manipulador> l) {
		String cadena = "";
		String caracteres = "";
		for (Manipulador a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String FormarTrazCarne(List<TrazCarne> l) {
		String cadena = "";
		String caracteres = "";
		for (TrazCarne a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String FormarTrazMateriaPrima(List<MateriaPrima> l) {
		String cadena = "";
		String caracteres = "";
		for (MateriaPrima a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String FormarTrazSalsas(List<Salsa> l) {
		String cadena = "";
		String caracteres = "";
		for (Salsa a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String FormarTrazEnvases(List<Envase> l) {
		String cadena = "";
		String caracteres = "";
		for (Envase a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String FormarTrazLaure(List<Laure> l) {
		String cadena = "";
		String caracteres = "";
		for (Laure a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static String Formarproduccion(List<Produccion> l) {
		String cadena = "";
		String caracteres = "";
		for (Produccion a : l) {
			if (a.toString().length() > cadena.length()) {
				cadena = a.toString();
			}
		}
		for (int i = 0; i < cadena.length(); i++) {
			caracteres += "*";
		}
		return caracteres;
	}

	public static void CrearCarpetasSources() {
		File direc = new File(Ejecutable.getOPciones().getRutaDefectoGuardarTodosLosArchivos());
		if (!direc.exists()) {
			direc.mkdir();
		}
		File segundosderec = new File(Ejecutable.getOPciones().getRutaDefectoGuardarTodosLosArchivos2());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatos());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		File xmlbasededatos = new File("./src/resources/BaseDatos/ConexionesH2.xml");
		if (!xmlbasededatos.exists()) {
			CearXMLCASO();
		}

		segundosderec = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXT());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTAgua());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTProductosCarnicos());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTLimpieza());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTResiduos());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTTemperaturas());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtproduccion());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtfacturacion());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutadirectoriotxttrazabilidad());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadcarne());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadenvases());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadlaure());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadmateriaprima());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxttrazabilidadsalsas());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutaDefectoGUaradoBaseDatosTXTMedidas());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtmedidasmanipulador());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtmedidasmantenimiento());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}
		segundosderec = new File(Ejecutable.getOPciones().getRutadefectoguaradobasedatostxtmedidasprebentibas());
		if (!segundosderec.exists()) {
			segundosderec.mkdir();
		}

	}

	private static void CearXMLCASO() {
		List<CanalConexionH2> canales = new ArrayList<>();
		CanalConexionH2 s = new CanalConexionH2("Sanidad", "org.h2.Driver",
				"jdbc:h2:./src/resources/BaseDatos/Sanidad");
		s.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS productos_carnicos (id INT PRIMARY KEY auto_increment,fecha DATE ,proveedor VARCHAR(255), producto VARCHAR(255), lote VARCHAR(255),firmado ENUM('FIRMADO','NO_FIRMADO'));");
		s.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS trazabilidad_carne (id_traza int PRIMARY KEY auto_increment,id_carne INT, fecha_inicio DATE, fecha_fin DATE, firmado ENUM('FIRMADO','NO_FIRMADO'), FOREIGN KEY (id_carne) REFERENCES productos_carnicos (id) ON DELETE CASCADE ON UPDATE CASCADE);");
		s.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS trazabilidad_laure(id_traza INT PRIMARY KEY auto_increment, fecha_llegada DATE, fecha_inicio DATE, fecha_fin DATE, proveedor VARCHAR(100), mercancia VARCHAR(100), lote VARCHAR(100), firmado ENUM('FIRMADO','NO_FIRMADO'))");
		s.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS trazabilidad_materiaprima(id_traza INT PRIMARY KEY auto_increment, fecha_llegada DATE, fecha_inicio DATE, fecha_fin DATE, proveedor VARCHAR(100), mercancia VARCHAR(100), lote VARCHAR(100), firmado ENUM('FIRMADO','NO_FIRMADO'))");
		s.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS trazabilidad_salsas(id_traza INT PRIMARY KEY auto_increment, fecha_llegada DATE, fecha_inicio DATE, fecha_fin DATE, proveedor VARCHAR(100), mercancia VARCHAR(100), lote VARCHAR(100), firmado ENUM('FIRMADO','NO_FIRMADO'))");
		s.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS trazabilidad_envases(id_traza INT PRIMARY KEY auto_increment, fecha_llegada DATE, fecha_inicio DATE, fecha_fin DATE, proveedor VARCHAR(100), mercancia VARCHAR(100), lote VARCHAR(100), firmado ENUM('FIRMADO','NO_FIRMADO'))");
		s.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS produccion(id_pro INT PRIMARY KEY auto_increment, fecha_lote DATE,nombre VARCHAR(100), cantidad VARCHAR(100), lotes_carnes ARRAY, lotes_salsas ARRAY , lotes_laure ARRAY, lotes_materiaprima ARRAY, firmado ENUM('FIRMADO','NO_FIRMADO'))");
		canales.add(s);
		CanalConexionH2 producto = new CanalConexionH2("Productos", "org.h2.Driver",
				"jdbc:h2:./src/resources/BaseDatos/Productos");
		producto.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS inventario (id INT PRIMARY KEY auto_increment,nombre VARCHAR(255), marca VARCHAR(255),precio FLOAT,tipo ENUM ('Und','Kg'));");
		producto.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS Inventario_cantidad (id_producto INT PRIMARY KEY,cantidad INT UNSIGNED NOT NULL,FOREIGN KEY (id_producto) REFERENCES inventario (id) ON DELETE CASCADE ON UPDATE CASCADE);");
		canales.add(producto);
		CanalConexionH2 produccion = new CanalConexionH2("PRODUCCION", "org.h2.Driver",
				"jdbc:h2:./src/resources/BaseDatos/Produccion");
		produccion.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS registro_produccion(id INT PRIMARY KEY auto_increment,fecha DATE ,producto VARCHAR(255),cantidad VARCHAR(255),lote VARCHAR(255),firmado ENUM('FIRMADO','NO_FIRMADO'));");
		canales.add(produccion);
		CanalConexionH2 mantenimiento = new CanalConexionH2("MANTENIMIENTO", "org.h2.Driver",
				"jdbc:h2:./src/resources/BaseDatos/Limpieza_Agua_Temperatura_Residuos");
		mantenimiento.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS registro_limpieza(id INT PRIMARY KEY auto_increment,fecha DATE ,obrador ENUM('X','NULO'),aseo ENUM('X','NULO'),venta ENUM('X','NULO'),picadora ENUM('X','NULO'),embutidora ENUM('X','NULO'),sierra ENUM('X','NULO'),cuchillos_y_utensilios ENUM('X','NULO'),frigorifico_materia_prima ENUM('X','NULO'),frigorifico_producto_terminado ENUM('X','NULO'),contenedor_residuos ENUM('X','NULO'),firmado ENUM('FIRMADO','NO_FIRMADO'));");
		mantenimiento.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS registro_agua(id INT PRIMARY KEY auto_increment,fecha DATE ,punto_muestreo VARCHAR(255), control_organoleptico VARCHAR(255), estado ENUM('B','M'),firmado ENUM('FIRMADO','NO_FIRMADO'));");
		mantenimiento.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS retirada_de_residuos(id INT PRIMARY KEY auto_increment,fecha DATE ,persona VARCHAR(255), cantidad VARCHAR(255),firmado ENUM('FIRMADO','NO_FIRMADO'));");
		mantenimiento.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS registros_temperatura(id INT PRIMARY KEY auto_increment,fecha DATE ,vitrina_espositora VARCHAR(10), armario_frigorifico VARCHAR(10), armario_productos_cocinados VARCHAR(10), armario_congelador VARCHAR(10), armario_producto_terminado VARCHAR(10), alcon_congelador VARCHAR(10),firmado ENUM('FIRMADO','NO_FIRMADO'));");
		canales.add(mantenimiento);
		CanalConexionH2 medidas = new CanalConexionH2("MEDIDAS", "org.h2.Driver",
				"jdbc:h2:./src/resources/BaseDatos/Medidas");
		medidas.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS registro_medidas_preventivas_ddd(id INT PRIMARY KEY auto_increment,fecha DATE ,estado_de_puertas_y_ventanas ENUM('C','I'),estado_de_lamparas_antiinsectos ENUM('C','I'),estado_de_desagues ENUM('C','I'),estado_de_limpieza ENUM('C','I'),indicio_de_plagas ENUM('C','I'),firmado ENUM('FIRMADO','NO_FIRMADO'));");
		medidas.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS registro_de_mantenimiento(id INT PRIMARY KEY auto_increment,fecha DATE ,suelo ENUM('C','I'),paredes ENUM('C','I'),techos ENUM('C','I'),electricidad ENUM('C','I'),fontaneria ENUM('C','I'),operaciones_realizadas_a_equipos_y_utensilios ENUM('C','I'),calibracion_equipo_frio ENUM('C','I'),revision_balanzas ENUM('C','I'),firmado ENUM('FIRMADO','NO_FIRMADO'));");
		medidas.AñadirSentencia(
				"CREATE TABLE IF NOT EXISTS registro_manipuladores(id INT PRIMARY KEY auto_increment,fecha DATE ,vestimienta_limpia ENUM('C','I'),higiene_personal ENUM('C','I'),buenas_practicas ENUM('C','I'),incidencias_acciones_correctoras ENUM('C','I'),firmado ENUM('FIRMADO','NO_FIRMADO'));</");
		canales.add(medidas);
		XmlUtilidades.EscribirCanales(canales);

	}

}
