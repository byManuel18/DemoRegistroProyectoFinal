package clasemain;

import java.io.IOException;

import enums.Escenas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {


	private static Scene scene;
	private static Stage stge;

    @Override
    public void start(Stage stage) throws IOException {
    	if(Ejecutable.getOPciones().isPrimeracargar()){
    		scene = new Scene(loadFXML(Escenas.PRIMERACARGA.getUrl()), 800, 700);
    		Ejecutable.getOPciones().setPrimeracargar(false);
    		stge=stage;
    		stge.setTitle("INICIO PROGRAMA. MENÚ OPCIONES");
    		stage.setResizable(false);
    	}else{
    		 scene = new Scene(loadFXML(Escenas.INICIO.getUrl()), 800, 700);
    		 stge=stage;
    		 stge.setTitle("TIENDA ALIMENTACIÓN ANTONIO GUERRERO");
    	     stge.setMaximized(true);
    	}
        //scene.getStylesheets().add("/assets/pruebafondo.css");
        stge.getIcons().add(new Image("/assets/icnoposible.jpg"));
        stge.setScene(scene);
        //stage.setResizable(false);
        stge.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void CambiarTitulo(String tit){
    	stge.setTitle(tit);
    }
    public static void CambiarResizable(boolean re){
    	stge.setResizable(re);
    }
    public static void PonerMaximizado(boolean max){
    	stge.setMaximized(max);
    }

	public static void main(String[] args) {
		launch(args);

	}


}
