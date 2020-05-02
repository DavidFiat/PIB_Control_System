package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.RepeatedEnterpriseException;
import exceptions.SameNameCountryException;
import exceptions.SamePresidentsNameException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;

public class PIBController{
	
	private Software en;
	
	public PIBController() {
		en = new Software();
	}
	//relation
	
	
	
	@FXML
	private BorderPane mainScreen;
	//----------------
	//agregar pais
	//----------------
	@FXML
	private TextField namePais;
	@FXML
	private TextField habitantes;
	@FXML
	private TextField extension;
	@FXML
	private TextField presidente;
	@FXML 
	private TextField gastosPublicos;
	@FXML
	private TextField mar;
	//---------------
	//agregar empresa
	//----------------
	@FXML
	private TextField nombreEmpresa;
	@FXML
	private TextField idEmpresa;
	@FXML
	private TextField fechaCreacion;
	@FXML
	private TextField gastosEmpresa;
	@FXML
	private RadioButton transporte;
	@FXML
	private RadioButton supermercados;
	@FXML
	private RadioButton financiera;
	@FXML
	private RadioButton educacion;
	@FXML
	private RadioButton cooperativa;
	@FXML
	private ToggleGroup tipo;
	@FXML
	private TextField pais;
	@FXML
	private Button seguir;
	//ventana extra de transporte
	@FXML
	private TextField tf;
	//ventana extra cooperativa
	@FXML
	private TextField tfCopera;
	@FXML
	private TextField califi;
	//ventana extra supermercados
	private TextField ganancias;
	private TextField dueño ;
	private TextField calificacion;
	private TextField cantidad;
	//agregar vehiculo
	//----------------------
	@FXML
	private TextField marca;
	@FXML
	private TextField tipoVehiculo;
	@FXML
	private TextField idVehiculo;
	//----------------------
	//agregar ciudadano
	//----------------------
	@FXML
	private TextField nombreCiudadano;
	@FXML
	private TextField idCiudadano;
	@FXML
	private TextField paisCiudadano;
	@FXML
	private ToggleGroup clasi;
	
	
	//-----------------------------------------------------
	//     				INTERFAZ  AGREGAR
	//-----------------------------------------------------
	@FXML
    public void loadAddCiudadno(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgregarCiudadano.fxml"));
		fxmlLoader.setController(this);    
		
		Parent pib = fxmlLoader.load();
    	
		mainScreen.getChildren().clear();
    	mainScreen.setCenter(pib);
    }
	@FXML
    public void loadAddPais(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgregarPais.fxml"));
		fxmlLoader.setController(this);    
		
		Parent pib = fxmlLoader.load();
    	
		mainScreen.getChildren().clear();
    	mainScreen.setCenter(pib);
    }
	@FXML
    public void loadAddVehiculo(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgregarVehiculo.fxml"));
		fxmlLoader.setController(this);    
		
		Parent pib = fxmlLoader.load();
    	
		mainScreen.getChildren().clear();
    	mainScreen.setCenter(pib);
    }
	@FXML
    public void loadAddEmpresa(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgregarEmpresa.fxml"));
		fxmlLoader.setController(this);    
		
		Parent pib = fxmlLoader.load();
		
		ganancias = new TextField();
		dueño = new TextField();
		calificacion = new TextField();
		cantidad = new TextField();
		supermarket();
		
		tfCopera = new TextField();
		califi = new TextField();
		cooperative();
		
		tf = new TextField();
		transporte();
		
		mainScreen.getChildren().clear();
    	mainScreen.setCenter(pib);
    }
	//------------------------------------------
	//         INTERFAZ ORDENAR
	//------------------------------------------
	@FXML
	public void loadSort(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ordenar.fxml"));
		fxmlLoader.setController(this);    
		
		Parent pib = fxmlLoader.load();
  	
		mainScreen.getChildren().clear();
		mainScreen.setCenter(pib);
	}
	
	@FXML
	public void transporte() {
		
		Button bt = new Button();
		Stage s = new Stage();
		
		transporte.setOnAction(e->{
			Pane p = new Pane();
			Label inf = new Label();
			inf.setText("¡INFO EXTRA!");
			inf.setLayoutX(128);
			inf.setLayoutY(29);
			Label lb = new Label();
			lb.setText("Ganancias:");
			lb.setLayoutX(47.0);
			lb.setLayoutY(101.0);
			
			tf.setLayoutX(192.0);
			tf.setLayoutY(97.0);
			bt.setText("¡OK!");
			bt.setLayoutX(160);
			bt.setLayoutY(145);

			p.getChildren().addAll(inf, lb, tf, bt);
			
			Scene sc = new Scene(p,390,184);
			s.setScene(sc);
			s.show();
			
		});
			bt.setOnAction(e -> {
				
				if (tf.getText() == "") {
					
					Alert llenar = new Alert(AlertType.WARNING);
					llenar.setTitle("Fallo en el registro");
					llenar.setHeaderText("Alguno de los requerimientos está vacío");
					llenar.setContentText("Por favor llene todos los campos");
					
					llenar.showAndWait();
				}else {
					
					s.close();
				}
			});
	}
	@FXML
	public void cooperative() {
		Button bt = new Button();
		Stage s = new Stage();
		
		cooperativa.setOnAction(e->{
			Pane p = new Pane();
			
			Label inf = new Label();
			inf.setText("¡INFO EXTRA!");
			inf.setLayoutX(128);
			inf.setLayoutY(29);
			
			Label lb = new Label();
			lb.setText("Objetivo:");
			lb.setLayoutX(47.0);
			lb.setLayoutY(65.0);
			//tfCopera
			tfCopera.setLayoutX(193);
			tfCopera.setLayoutY(61);
			// label calificacion
			Label cali = new Label();
			cali.setText("Calificación:");
			cali.setLayoutX(47.0);
			cali.setLayoutY(101.0);
			//califi
			califi.setLayoutX(193);
			califi.setLayoutY(97);
			
			bt.setText("¡OK!");
			bt.setLayoutX(160);
			bt.setLayoutY(145);

			p.getChildren().addAll(inf, lb, tf, cali, califi, bt);
			
			Scene sc = new Scene(p,410,184);
			s.setScene(sc);
			s.show();
			
		});
		if (tfCopera.getText() == "" || califi.getText() == "") {
			Alert error = new Alert(AlertType.WARNING);
			error.setTitle("Fallo en el registro");
			error.setHeaderText("Alguno de los requerimientos está vacío");
			error.setContentText("Por favor llene todos los campos");

			error.showAndWait();
		} else {

			bt.setOnAction(e -> {
				s.close();

			});
		}
	}
	
	@FXML
	public void supermarket() {

		Button bt = new Button();
		Stage s = new Stage();
		
		supermercados.setOnAction(e->{
			Pane p = new Pane();
			Label inf = new Label();
			inf.setText("¡INFO EXTRA!");
			inf.setLayoutX(123);
			inf.setLayoutY(14);
			
			Label lb = new Label();
			lb.setText("Ganancias:");
			lb.setLayoutX(27.0);
			lb.setLayoutY(65.0);
			//ganancias text
			ganancias.setLayoutX(198.0);
			ganancias.setLayoutY(61.0);
			// label calificacion
			Label duenio = new Label();
			duenio.setText("Dueño:");
			duenio.setLayoutX(27.0);
			duenio.setLayoutY(101.0);
			//dueño text
			dueño.setLayoutX(198.0);
			dueño.setLayoutY(97);
			//label caificacion
			Label califi = new Label();
			califi.setText("Calificacion:");
			califi.setLayoutX(27.0);
			califi.setLayoutY(136.0);
			//calificacion text
			calificacion.setLayoutX(198.0);
			calificacion.setLayoutY(132.0);
			//label cantidad
			Label canti = new Label();
			canti.setText("Cantidad:");
			canti.setLayoutX(27.0);
			canti.setLayoutY(174.0);
			//cantidad text
			cantidad.setLayoutX(198.0);
			cantidad.setLayoutY(170);
			
			bt.setText("¡OK!");
			bt.setLayoutX(167);
			bt.setLayoutY(211);

			p.getChildren().addAll(inf, ganancias, lb, dueño, duenio, califi, calificacion, canti, cantidad, bt);
			
			Scene sc = new Scene(p,450,300);
			s.setScene(sc);
			s.show();
			
		});
		if (ganancias.getText() == "" || dueño.getText() == "" || calificacion.getText() == "" || cantidad.getText() == "") {
			Alert error = new Alert(AlertType.WARNING);
			error.setTitle("Fallo en el registro");
			error.setHeaderText("Alguno de los requerimientos está vacío");
			error.setContentText("Por favor llene todos los campos");

			error.showAndWait();
		} else {

			bt.setOnAction(e -> {
				s.close();

			});
		}
	}
	
	//--------------------------------
	//		agregar una empresa
	//--------------------------------
	public void addEnterprise() throws RepeatedEnterpriseException {
		
		String name = nombreEmpresa.getText();
		String id = idEmpresa.getText();
		String date = fechaCreacion.getText();
		String gasto = gastosEmpresa.getText();
		String country = pais.getText();
		
		
		if(transporte.isSelected()) {
			Double gastos = Double.parseDouble(gasto);
			Double ganancias = Double.parseDouble(tf.getText());
			Transport add = new Transport(name, id, date, gastos, ganancias);
			
			en.addEnterprise(add, country);
			
			Alert successful = new Alert(AlertType.INFORMATION);
			successful.setTitle("Registro exitoso");
			successful.setHeaderText(null);
			successful.setContentText("Su empresa de tipo transporte ha sido agregada de forma exitosa");
    		
			successful.showAndWait();
		}else if(cooperativa.isSelected()) {
			Double gastos = Double.parseDouble(gasto);
			Cooperative add = new Cooperative(name, id, date, gastos, tfCopera.getText(), (Integer.parseInt(califi.getText())));
			
			en.addEnterprise(add, country);
			
			Alert successful = new Alert(AlertType.INFORMATION);
			successful.setTitle("Registro exitoso");
			successful.setHeaderText(null);
			successful.setContentText("Su empresa de tipo cooperativa ha sido agregada de forma exitosa");
    		
			successful.showAndWait();
		}else if(supermercados.isSelected()) {
			Double gastos = Double.parseDouble(gasto);
			SupermarketChain add = new SupermarketChain(name, id, date, gastos, Double.parseDouble(ganancias.getText()), dueño.getText(), (Integer.parseInt(calificacion.getText())), Integer.parseInt(cantidad.getText()));
			
			en.addEnterprise(add, country);
			
			Alert successful = new Alert(AlertType.INFORMATION);
			successful.setTitle("Registro exitoso");
			successful.setHeaderText(null);
			successful.setContentText("Su empresa de tipo cadena de supermercado ha sido agregada de forma exitosa");
    		
			successful.showAndWait();
		}else if(educacion.isSelected()) {
			Double gastos = Double.parseDouble(gasto);
			String type ="";
			String rector = "";
			Education add = new Education(name, id, date, gastos, type, rector);
			
			en.addEnterprise(add, country);
			
			Alert successful = new Alert(AlertType.INFORMATION);
			successful.setTitle("Registro exitoso");
			successful.setHeaderText(null);
			successful.setContentText("Su empresa de tipo eduación ha sido agregada de forma exitosa");
    		
			successful.showAndWait();
		}else if(financiera.isSelected()) {
			Double gastos = Double.parseDouble(gasto);
			double earnings = 0.0;
			double interestRate = 0.0;
			boolean permission = true;
			Financial add = new Financial(name, id, date, gastos, earnings, interestRate, permission);
			
			en.addEnterprise(add, country);
			
			Alert successful = new Alert(AlertType.INFORMATION);
			successful.setTitle("Registro exitoso");
			successful.setHeaderText(null);
			successful.setContentText("Su empresa de tipo financiera ha sido agregada de forma exitosa");
    		
			successful.showAndWait();
		}
		
		if(tipo.getSelectedToggle() == null || nombreEmpresa.getText().equals("") || idEmpresa.getText().equals("") || fechaCreacion.getText().equals("") 
				|| gastosEmpresa.getText().equals("")) {
			
			Alert error = new Alert(AlertType.WARNING);
    		error.setTitle("Fallo en el registro");
    		error.setHeaderText("Alguno de los requerimientos está vacío");
    		error.setContentText("Por favor llene todos los campos");

    		error.showAndWait();
		}
	}
	//------------------------------
	//     	 agregar pais
	//------------------------------
	@FXML
	public void addCountry() throws SameNameCountryException, SamePresidentsNameException {
		
		if(namePais.getText().equals("") || habitantes.getText().equals("") || extension.getText().equals("") || presidente.getText().equals("") 
				|| gastosPublicos.getText().equals("")) {
			
			Alert error = new Alert(AlertType.WARNING);
    		error.setTitle("Fallo en el registro");
    		error.setHeaderText("Alguno de los requerimientos está vacío");
    		error.setContentText("Por favor llene todos los campos");

    		error.showAndWait();
		}else {
			String name = namePais.getText();
			int habi = Integer.parseInt(habitantes.getText());
			double exte = Double.parseDouble(extension.getText());
			String presi = presidente.getText();
			double gastosPu = Double.parseDouble(gastosPublicos.getText());
			String m = "";
			
			if(mar.getText().equals("")) {
				m = "ninguno";
			}else {
				m = mar.getText();
			}
			Country added = new Country(name, habi, exte, presi, gastosPu, m);
			en.addCountry(added);

			Alert successful = new Alert(AlertType.INFORMATION);
			successful.setTitle("Registro exitoso");
			successful.setHeaderText(null);
			successful.setContentText("El país ha sido agregada de forma exitosa");
			successful.showAndWait();
		}


	}
	
	
}
