package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import customExceptions.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;
import threads.ThreadImageOne;

public class PIBController {

	private Software en;

	public PIBController() {
		en = new Software();
	}
	// relation

	@FXML
	private BorderPane mainScreen;
	@FXML
	private ImageView imageOne;
	@FXML
	private ImageView imageTwo;
	// ----------------
	// agregar pais
	// ----------------
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
	// ---------------
	// agregar empresa
	// ----------------
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
	// ventana extra de transporte
	@FXML
	private TextField tf;
	// ventana extra cooperativa
	@FXML
	private TextField tfCopera;
	@FXML
	private TextField califi;
	// ventana extra supermercados
	private TextField ganancias;
	private TextField dueño;
	private TextField calificacion;
	private TextField cantidad;
	//ventana extra de educacion
	private TextField gastosEdu;
	private TextField typeEdu;
	private TextField rector;
	// agregar vehiculo
	// ----------------------
	@FXML
	private TextField marca;
	@FXML
	private TextField tipoVehiculo;
	@FXML
	private TextField idVehiculo;
	// ----------------------
	// agregar ciudadano
	// ----------------------
	@FXML
	private TextField nombreCiudadano;
	@FXML
	private TextField idCiudadano;
	@FXML
	private TextField paisCiudadano;
	@FXML
	private TextField gastosCiudadanos;
	@FXML
	private ToggleGroup clasi;
	@FXML
	private RadioButton adulto;
	@FXML
	private RadioButton niño;
	@FXML
	private RadioButton mascota;
	// ventana extra de ciudadano
	// ---------------------
	@FXML
	private TextField licor;
	@FXML
	private TextField extraEducacion;
	@FXML
	private ToggleGroup pedigree;
	@FXML
	private RadioButton si;
	@FXML
	private RadioButton no;
	// --------------------
	// table view sort
	// --------------------
	@FXML
	private TableView<Country> countryListTable;
	@FXML
	private TableColumn<Country, String> columnName;
	@FXML
	private TableColumn<Country, String> columnPopulation;
	@FXML
	private TableColumn<Country, String> columnExtension;
	@FXML
	private TableColumn<Country, String> columnPresident;
	@FXML
	private TableColumn<Country, String> columnSpending;
	@FXML
	private TableColumn<Country, String> columnSea;

	private ObservableList<String> creditsList;

	// -----------------------------------------------------
	// INTERFAZ AGREGAR
	// -----------------------------------------------------
	@FXML
	public void loadAddCiudadno(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgregarCiudadano.fxml"));
		fxmlLoader.setController(this);

		Parent pib = fxmlLoader.load();

		licor = new TextField();
		adultos();

		extraEducacion = new TextField();
		niño();

		mascota();

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
		
		
		gastosEdu = new TextField();
		typeEdu = new TextField();
		typeEdu.setPromptText("Privada/Publica");
		rector = new TextField();
		educacion();

		mainScreen.getChildren().clear();
		mainScreen.setCenter(pib);
	}

	// ------------------------------------------
	// INTERFAZ ORDENAR
	// ------------------------------------------
	@FXML
	public void loadSortName(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ordenar.fxml"));
		fxmlLoader.setController(this);

		Parent pib = fxmlLoader.load();

		mainScreen.getChildren().clear();
		mainScreen.setCenter(pib);
		barChartName();
	}

	@FXML
	public void loadSortExtension(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ordenar.fxml"));
		fxmlLoader.setController(this);

		Parent pib = fxmlLoader.load();

		mainScreen.getChildren().clear();
		mainScreen.setCenter(pib);
		barChartExtension();
	}

	@FXML
	public void loadSortPopulation(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ordenar.fxml"));
		fxmlLoader.setController(this);

		Parent pib = fxmlLoader.load();

		mainScreen.getChildren().clear();
		mainScreen.setCenter(pib);
		barChartPopulation();
	}

	@FXML
	public void loadSortPresident(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ordenar.fxml"));
		fxmlLoader.setController(this);

		Parent pib = fxmlLoader.load();

		mainScreen.getChildren().clear();
		mainScreen.setCenter(pib);
		barChartPresident();
	}

	// ---------------------------------
	// SORT BY NAME
	// ---------------------------------
	public void barChartName() {

		en.sortCountriesByName();

		ObservableList<Country> observableList;
		observableList = FXCollections.observableArrayList(en.getCountries());

		countryListTable.setItems(observableList);
		columnName.setCellValueFactory(new PropertyValueFactory<Country, String>("name"));
		columnPopulation.setCellValueFactory(new PropertyValueFactory<Country, String>("population"));
		columnExtension.setCellValueFactory(new PropertyValueFactory<Country, String>("extension"));
		columnPresident.setCellValueFactory(new PropertyValueFactory<Country, String>("president"));
		columnSpending.setCellValueFactory(new PropertyValueFactory<Country, String>("publicSpending"));
		columnSea.setCellValueFactory(new PropertyValueFactory<Country, String>("sea"));
	}

	// ---------------------------------
	// SORT BY EXTENSION
	// ---------------------------------
	public void barChartExtension() {

		en.sortCountriesByExtension();

		ObservableList<Country> observableList;
		observableList = FXCollections.observableArrayList(en.getCountries());

		countryListTable.setItems(observableList);
		columnName.setCellValueFactory(new PropertyValueFactory<Country, String>("name"));
		columnPopulation.setCellValueFactory(new PropertyValueFactory<Country, String>("population"));
		columnExtension.setCellValueFactory(new PropertyValueFactory<Country, String>("extension"));
		columnPresident.setCellValueFactory(new PropertyValueFactory<Country, String>("president"));
		columnSpending.setCellValueFactory(new PropertyValueFactory<Country, String>("publicSpending"));
		columnSea.setCellValueFactory(new PropertyValueFactory<Country, String>("sea"));
	}

	// ---------------------------------
	// SORT BY POPULATION
	// ---------------------------------
	public void barChartPopulation() {

		en.sortCountriesByPopulation();

		ObservableList<Country> observableList;
		observableList = FXCollections.observableArrayList(en.getCountries());

		countryListTable.setItems(observableList);
		columnName.setCellValueFactory(new PropertyValueFactory<Country, String>("name"));
		columnPopulation.setCellValueFactory(new PropertyValueFactory<Country, String>("population"));
		columnExtension.setCellValueFactory(new PropertyValueFactory<Country, String>("extension"));
		columnPresident.setCellValueFactory(new PropertyValueFactory<Country, String>("president"));
		columnSpending.setCellValueFactory(new PropertyValueFactory<Country, String>("publicSpending"));
		columnSea.setCellValueFactory(new PropertyValueFactory<Country, String>("sea"));
	}

	// ---------------------------------
	// SORT BY PRESIDENT
	// ---------------------------------
	public void barChartPresident() {

		en.sortCountriesByPresident();

		ObservableList<Country> observableList;
		observableList = FXCollections.observableArrayList(en.getCountries());

		countryListTable.setItems(observableList);
		columnName.setCellValueFactory(new PropertyValueFactory<Country, String>("name"));
		columnPopulation.setCellValueFactory(new PropertyValueFactory<Country, String>("population"));
		columnExtension.setCellValueFactory(new PropertyValueFactory<Country, String>("extension"));
		columnPresident.setCellValueFactory(new PropertyValueFactory<Country, String>("president"));
		columnSpending.setCellValueFactory(new PropertyValueFactory<Country, String>("publicSpending"));
		columnSea.setCellValueFactory(new PropertyValueFactory<Country, String>("sea"));
	}

	@FXML
	public void transporte() {

		Button bt = new Button();
		Stage s = new Stage();

		transporte.setOnAction(e -> {
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

			Scene sc = new Scene(p, 390, 184);
			s.setScene(sc);
			s.show();

		});
		bt.setOnAction(e -> {

			if (tf.getText().equals("")) {

				Alert llenar = new Alert(AlertType.WARNING);
				llenar.setTitle("Fallo en el registro");
				llenar.setHeaderText("Alguno de los requerimientos está vacío");
				llenar.setContentText("Por favor llene todos los campos");

				llenar.showAndWait();
			} else {

				s.close();
			}
		});
	}

	@FXML
	public void cooperative() {
		Button bt = new Button();
		Stage s = new Stage();

		cooperativa.setOnAction(e -> {
			Pane p = new Pane();

			Label inf = new Label();
			inf.setText("¡INFO EXTRA!");
			inf.setLayoutX(128);
			inf.setLayoutY(29);

			Label lb = new Label();
			lb.setText("Objetivo:");
			lb.setLayoutX(47.0);
			lb.setLayoutY(65.0);
			// tfCopera
			tfCopera.setLayoutX(193);
			tfCopera.setLayoutY(61);
			// label calificacion
			Label cali = new Label();
			cali.setText("Calificación:");
			cali.setLayoutX(47.0);
			cali.setLayoutY(101.0);
			// califi
			califi.setLayoutX(193);
			califi.setLayoutY(97);

			bt.setText("¡OK!");
			bt.setLayoutX(160);
			bt.setLayoutY(145);

			p.getChildren().addAll(inf, lb, tf, cali, califi, bt);

			Scene sc = new Scene(p, 410, 184);
			s.setScene(sc);
			s.show();

		});
		bt.setOnAction(e -> {
			if (tfCopera.getText().equals("") || califi.getText().equals("")) {
				Alert error = new Alert(AlertType.WARNING);
				error.setTitle("Fallo en el registro");
				error.setHeaderText("Alguno de los requerimientos está vacío");
				error.setContentText("Por favor llene todos los campos");

				error.showAndWait();
			} else {
				s.close();
			}
		});
	}

	@FXML
	public void supermarket() {

		Button bt = new Button();
		Stage s = new Stage();

		supermercados.setOnAction(e -> {
			Pane p = new Pane();
			Label inf = new Label();
			inf.setText("¡INFO EXTRA!");
			inf.setLayoutX(123);
			inf.setLayoutY(14);

			Label lb = new Label();
			lb.setText("Ganancias:");
			lb.setLayoutX(27.0);
			lb.setLayoutY(65.0);
			// ganancias text
			ganancias.setLayoutX(198.0);
			ganancias.setLayoutY(61.0);
			// label calificacion
			Label duenio = new Label();
			duenio.setText("Dueño:");
			duenio.setLayoutX(27.0);
			duenio.setLayoutY(101.0);
			// dueño text
			dueño.setLayoutX(198.0);
			dueño.setLayoutY(97);
			// label caificacion
			Label califi = new Label();
			califi.setText("Calificacion:");
			califi.setLayoutX(27.0);
			califi.setLayoutY(136.0);
			// calificacion text
			calificacion.setLayoutX(198.0);
			calificacion.setLayoutY(132.0);
			// label cantidad
			Label canti = new Label();
			canti.setText("Cantidad:");
			canti.setLayoutX(27.0);
			canti.setLayoutY(174.0);
			// cantidad text
			cantidad.setLayoutX(198.0);
			cantidad.setLayoutY(170);

			bt.setText("¡OK!");
			bt.setLayoutX(167);
			bt.setLayoutY(211);

			p.getChildren().addAll(inf, ganancias, lb, dueño, duenio, califi, calificacion, canti, cantidad, bt);

			Scene sc = new Scene(p, 450, 300);
			s.setScene(sc);
			s.show();

		});
		bt.setOnAction(e -> {
			if (ganancias.getText().equals("") || dueño.getText().equals("") || calificacion.getText().equals("")
					|| cantidad.getText().equals("")) {
				Alert error = new Alert(AlertType.WARNING);
				error.setTitle("Fallo en el registro");
				error.setHeaderText("Alguno de los requerimientos está vacío");
				error.setContentText("Por favor llene todos los campos");

				error.showAndWait();
			} else {

				s.close();

			}
		});

	}
	@FXML
	public void educacion() {

		Button bt = new Button();
		Stage s = new Stage();

		educacion.setOnAction(e -> {
			Pane p = new Pane();
			Label inf = new Label();
			inf.setText("¡INFO EXTRA!");
			inf.setLayoutX(123);
			inf.setLayoutY(14);

			Label lb = new Label();
			lb.setText("Gastos:");
			lb.setLayoutX(27.0);
			lb.setLayoutY(65.0);
			// ganancias text
			gastosEdu.setLayoutX(198.0);
			gastosEdu.setLayoutY(61.0);
			// label type
			Label type = new Label();
			type.setText("Type:");
			type.setLayoutX(27.0);
			type.setLayoutY(101.0);
			// dueño text
			typeEdu.setLayoutX(198.0);
			typeEdu.setLayoutY(97);
			// label rector
			Label rec = new Label();
			rec.setText("Calificacion:");
			rec.setLayoutX(27.0);
			rec.setLayoutY(136.0);
			// calificacion text
			rector.setLayoutX(198.0);
			rector.setLayoutY(132.0);
			// label cantidad

			bt.setText("¡OK!");
			bt.setLayoutX(167);
			bt.setLayoutY(211);

			p.getChildren().addAll(inf, gastosEdu, lb, typeEdu, type, rector, rec, bt);

			Scene sc = new Scene(p, 450, 300);
			s.setScene(sc);
			s.show();

		});
		bt.setOnAction(e -> {
			if (gastosEdu.getText().equals("") || typeEdu.getText().equals("") || rector.getText().equals("")) {
				Alert error = new Alert(AlertType.WARNING);
				error.setTitle("Fallo en el registro");
				error.setHeaderText("Alguno de los requerimientos está vacío");
				error.setContentText("Por favor llene todos los campos");

				error.showAndWait();
			} else {

				s.close();

			}
		});

	}

	// --------------------------------
	// agregar una empresa
	// --------------------------------
	public void addEnterprise() throws IOException {

		String name = nombreEmpresa.getText();
		String id = idEmpresa.getText();
		String date = fechaCreacion.getText();
		String gasto = gastosEmpresa.getText();

		Country c = en.searchCountry(pais.getText());

		if (c == null) {
			Alert successful = new Alert(AlertType.WARNING);
			successful.setTitle("ERROR EN REGISTRO");
			successful.setHeaderText(null);
			successful.setContentText("EL PAÍS NO EXISTE.");
			successful.showAndWait();
		}

		if (transporte.isSelected()) {
			Double gastos = Double.parseDouble(gasto);
			Double ganancias = Double.parseDouble(tf.getText());
			Transport add = new Transport(name, id, date, gastos, ganancias);

			try {
				en.addEnterprise(add, c.getName());

				Alert successful = new Alert(AlertType.INFORMATION);
				successful.setTitle("Registro exitoso");
				successful.setHeaderText(null);
				successful.setContentText("Su empresa de tipo transporte ha sido agregada de forma exitosa");

				successful.showAndWait();

			} catch (RepeatedEnterpriseException e) {
				Alert successful = new Alert(AlertType.ERROR);
				successful.setTitle("ERROR EN REGISTRO");
				successful.setHeaderText(null);
				successful.setContentText("La empresa ya existe.");
				successful.showAndWait();
			}

		} else if (cooperativa.isSelected()) {
			Double gastos = Double.parseDouble(gasto);
			Cooperative add = new Cooperative(name, id, date, gastos, tfCopera.getText(),
					(Integer.parseInt(califi.getText())));

			try {
				en.addEnterprise(add, c.getName());
				Alert successful = new Alert(AlertType.INFORMATION);
				successful.setTitle("Registro exitoso");
				successful.setHeaderText(null);
				successful.setContentText("Su empresa de tipo cooperativa ha sido agregada de forma exitosa");

				successful.showAndWait();
			} catch (RepeatedEnterpriseException e) {
				Alert successful = new Alert(AlertType.ERROR);
				successful.setTitle("ERROR EN REGISTRO");
				successful.setHeaderText(null);
				successful.setContentText("La empresa ya existe.");
				successful.showAndWait();
			}

		} else if (supermercados.isSelected()) {
			Double gastos = Double.parseDouble(gasto);
			SupermarketChain add = new SupermarketChain(name, id, date, gastos, Double.parseDouble(ganancias.getText()),
					dueño.getText(), (Integer.parseInt(calificacion.getText())), Integer.parseInt(cantidad.getText()));

			try {
				en.addEnterprise(add, c.getName());
				Alert successful = new Alert(AlertType.INFORMATION);
				successful.setTitle("Registro exitoso");
				successful.setHeaderText(null);
				successful.setContentText("Su cadena de supermercados ha sido agregada de forma exitosa");

				successful.showAndWait();
			} catch (RepeatedEnterpriseException e) {
				Alert successful = new Alert(AlertType.ERROR);
				successful.setTitle("ERROR EN REGISTRO");
				successful.setHeaderText(null);
				successful.setContentText("La empresa ya existe.");
				successful.showAndWait();
			}

		} else if (educacion.isSelected()) {
			Double gastos = Double.parseDouble(gastosEdu.getText());
			String type = typeEdu.getText();
			String recto = rector.getText();
			Education add = new Education(name, id, date, gastos, type, recto);

			try {
				en.addEnterprise(add, c.getName());

				Alert successful = new Alert(AlertType.INFORMATION);
				successful.setTitle("Registro exitoso");
				successful.setHeaderText(null);
				successful.setContentText("Su empresa de tipo eduación ha sido agregada de forma exitosa");

				successful.showAndWait();
			} catch (RepeatedEnterpriseException e) {
				Alert successful = new Alert(AlertType.ERROR);
				successful.setTitle("ERROR EN REGISTRO");
				successful.setHeaderText(null);
				successful.setContentText("La empresa ya existe.");
				successful.showAndWait();
			}

		} else if (financiera.isSelected()) {
			Double gastos = Double.parseDouble(gasto);
			double earnings = 0.0;
			double interestRate = 0.0;
			boolean permission = true;
			Financial add = new Financial(name, id, date, gastos, earnings, interestRate, permission);

			try {
				en.addEnterprise(add, c.getName());
				Alert successful = new Alert(AlertType.INFORMATION);
				successful.setTitle("Registro exitoso");
				successful.setHeaderText(null);
				successful.setContentText("Su empresa de tipo financiera ha sido agregada de forma exitosa");

				successful.showAndWait();
			} catch (RepeatedEnterpriseException e) {
				Alert successful = new Alert(AlertType.ERROR);
				successful.setTitle("ERROR EN REGISTRO");
				successful.setHeaderText(null);
				successful.setContentText("La empresa ya existe.");
				successful.showAndWait();
			}

		}
		if (tipo.getSelectedToggle() == null || nombreEmpresa.getText().equals("") || idEmpresa.getText().equals("")
				|| fechaCreacion.getText().equals("") || gastosEmpresa.getText().equals("")) {

			Alert error = new Alert(AlertType.WARNING);
			error.setTitle("Fallo en el registro");
			error.setHeaderText("Alguno de los requerimientos está vacío");
			error.setContentText("Por favor llene todos los campos");

			error.showAndWait();
		}
		// re cargar la interfaz
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

	// ------------------------------
	// agregar pais
	// ------------------------------
	@FXML
	public void addCountry() throws IOException {

		if (namePais.getText().equals("") || habitantes.getText().equals("") || extension.getText().equals("")
				|| presidente.getText().equals("") || gastosPublicos.getText().equals("")) {

			Alert error = new Alert(AlertType.WARNING);
			error.setTitle("Fallo en el registro");
			error.setHeaderText("Alguno de los requerimientos está vacío");
			error.setContentText("Por favor llene todos los campos");

			error.showAndWait();
		} else {
			String name = namePais.getText();
			int habi = Integer.parseInt(habitantes.getText());
			double exte = Double.parseDouble(extension.getText());
			String presi = presidente.getText();
			double gastosPu = Double.parseDouble(gastosPublicos.getText());
			String m = "";

			if (mar.getText().equals("")) {
				m = "ninguno";
			} else {
				m = mar.getText();
			}
			Country added = new Country(name, habi, exte, presi, gastosPu, m);
			try {
				en.addCountry(added);

				Alert successful = new Alert(AlertType.INFORMATION);
				successful.setTitle("Registro exitoso");
				successful.setHeaderText(null);
				successful.setContentText("El país ha sido agregada de forma exitosa");
				successful.showAndWait();
			} catch (SameNameCountryException e) {

				Alert successful = new Alert(AlertType.ERROR);
				successful.setTitle("ERROR EN REGISTRO");
				successful.setHeaderText(null);
				successful.setContentText("El nombre del país ya existe.");
				successful.showAndWait();

			} catch (SamePresidentsNameException e) {
				Alert successful = new Alert(AlertType.ERROR);
				successful.setTitle("ERROR EN REGISTRO");
				successful.setHeaderText(null);
				successful.setContentText("La nombre del presidente ya está asiganado/n" + "a otro país.");
				successful.showAndWait();
			}

			// re cargar la interfaz
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgregarPais.fxml"));
			fxmlLoader.setController(this);

			Parent pib = fxmlLoader.load();

			mainScreen.getChildren().clear();
			mainScreen.setCenter(pib);

		}
	}
	// ---------------------------
	// AGREGAR CIUDADANO
	// ---------------------------

	public void addPerson() {

		if (nombreCiudadano.getText().equals("") || idCiudadano.getText().equals("")
				|| paisCiudadano.getText().equals("") || clasi.getSelectedToggle() == null) {

			Alert error = new Alert(AlertType.WARNING);
			error.setTitle("Fallo en el registro");
			error.setHeaderText("Alguno de los requerimientos está vacío");
			error.setContentText("Por favor llene todos los campos");

			error.showAndWait();
		} else {

			Country co = en.searchCountry(paisCiudadano.getText());
			if (co == null) {
				Alert successful = new Alert(AlertType.WARNING);
				successful.setTitle("ERROR EN REGISTRO");
				successful.setHeaderText(null);
				successful.setContentText("EL PAÍS NO EXISTE.");
				successful.showAndWait();
			}
			if (adulto.isSelected()) {

				String adu = licor.getText();
				Double adul = Double.parseDouble(adu);
				Adult a = new Adult(nombreCiudadano.getText(), idCiudadano.getText(),
						Double.parseDouble(gastosCiudadanos.getText()), adul);
				try {
					en.addCitizen(a, co.getName());
					Alert successful = new Alert(AlertType.INFORMATION);
					successful.setTitle("Registro exitoso");
					successful.setHeaderText(null);
					successful.setContentText("Su ciudadano adulto ha sido registrado de forma exitosa");
					successful.showAndWait();
				} catch (RepeatedCitizenException e) {
					Alert successful = new Alert(AlertType.ERROR);
					successful.setTitle("ERROR EN REGISTRO");
					successful.setHeaderText(null);
					successful.setContentText("El ciudadano ya existe.");
					successful.showAndWait();
				}

			} else if (niño.isSelected()) {

				String nino = extraEducacion.getText();
				Double ninio = Double.parseDouble(nino);
				Child c = new Child(nombreCiudadano.getText(), idCiudadano.getText(),
						Double.parseDouble(gastosCiudadanos.getText()), true, ninio);
				try {
					en.addCitizen(c, co.getName());

					Alert successful = new Alert(AlertType.INFORMATION);
					successful.setTitle("Registro exitoso");
					successful.setHeaderText(null);
					successful.setContentText("Su ciudadano niño ha sido registrado de forma exitosa");
					successful.showAndWait();
				} catch (RepeatedCitizenException e) {
					Alert successful = new Alert(AlertType.ERROR);
					successful.setTitle("ERROR EN REGISTRO");
					successful.setHeaderText(null);
					successful.setContentText("El ciudadano ya existe.");
					successful.showAndWait();
				}

			} else if (mascota.isSelected()) {

				Pet p = new Pet(nombreCiudadano.getText(), idCiudadano.getText(),
						Double.parseDouble(gastosCiudadanos.getText()), true, "");
				try {
					en.addCitizen(p, co.getName());

					Alert successful = new Alert(AlertType.INFORMATION);
					successful.setTitle("Registro exitoso");
					successful.setHeaderText(null);
					successful.setContentText("Su mascota ha sido registrada de forma exitosa");
					successful.showAndWait();
				} catch (RepeatedCitizenException e) {
					Alert successful = new Alert(AlertType.ERROR);
					successful.setTitle("ERROR EN REGISTRO");
					successful.setHeaderText(null);
					successful.setContentText("El ciudadano ya existe.");
					successful.showAndWait();
				}
			}

		}
	}

	@FXML
	public void adultos() {

		Button bt = new Button();
		Stage s = new Stage();

		adulto.setOnAction(e -> {
			Pane p = new Pane();
			Label inf = new Label();
			inf.setText("¡INFO EXTRA!");
			inf.setLayoutX(128);
			inf.setLayoutY(29);
			Label lb = new Label();
			lb.setText("Gastos en licor:");
			lb.setLayoutX(47.0);
			lb.setLayoutY(101.0);

			licor.setLayoutX(192.0);
			licor.setLayoutY(97.0);
			bt.setText("¡OK!");
			bt.setLayoutX(160);
			bt.setLayoutY(145);

			p.getChildren().addAll(inf, lb, licor, bt);

			Scene sc = new Scene(p, 390, 184);
			s.setScene(sc);
			s.show();

		});
		bt.setOnAction(e -> {

			if (licor.getText().equals("")) {

				Alert llenar = new Alert(AlertType.WARNING);
				llenar.setTitle("Fallo en el registro");
				llenar.setHeaderText("Alguno de los requerimientos está vacío");
				llenar.setContentText("Por favor llene todos los campos");

				llenar.showAndWait();
			} else {

				s.close();
			}
		});
	}

	@FXML
	public void niño() {

		Button bt = new Button();
		Stage s = new Stage();

		niño.setOnAction(e -> {
			Pane p = new Pane();
			Label inf = new Label();
			inf.setText("¡INFO EXTRA!");
			inf.setLayoutX(128);
			inf.setLayoutY(29);
			Label lb = new Label();
			lb.setText("Gastos en educación:");
			lb.setLayoutX(47.0);
			lb.setLayoutY(101.0);

			extraEducacion.setLayoutX(192.0);
			extraEducacion.setLayoutY(97.0);
			bt.setText("¡OK!");
			bt.setLayoutX(160);
			bt.setLayoutY(145);

			p.getChildren().addAll(inf, lb, extraEducacion, bt);

			Scene sc = new Scene(p, 390, 184);
			s.setScene(sc);
			s.show();

		});
		bt.setOnAction(e -> {

			if (educacion.getText().equals("")) {

				Alert llenar = new Alert(AlertType.WARNING);
				llenar.setTitle("Fallo en el registro");
				llenar.setHeaderText("Alguno de los requerimientos está vacío");
				llenar.setContentText("Por favor llene todos los campos");

				llenar.showAndWait();
			} else {

				s.close();
			}
		});
	}

	@FXML
	public void mascota() {

		Button bt = new Button();
		Stage s = new Stage();

		mascota.setOnAction(e -> {
			Pane p = new Pane();
			Label inf = new Label();
			inf.setText("¡INFO EXTRA!");
			inf.setLayoutX(128);
			inf.setLayoutY(29);
			Label lb = new Label();
			lb.setText("Pedigree:");
			lb.setLayoutX(47.0);
			lb.setLayoutY(101.0);

			bt.setText("¡OK!");
			bt.setLayoutX(160);
			bt.setLayoutY(145);

			p.getChildren().addAll(inf, lb, bt);

			Scene sc = new Scene(p, 390, 184);
			s.setScene(sc);
			s.show();

		});
		bt.setOnAction(e -> {

			if (pedigree.getSelectedToggle() == null) {

				Alert llenar = new Alert(AlertType.WARNING);
				llenar.setTitle("Fallo en el registro");
				llenar.setHeaderText("Alguno de los requerimientos está vacío");
				llenar.setContentText("Por favor llene todos los campos");

				llenar.showAndWait();
			} else {

				s.close();
			}
		});
	}
	// animation

	public void moved(int x) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				imageOne.setLayoutX(x);
			}
		});
	}

}
