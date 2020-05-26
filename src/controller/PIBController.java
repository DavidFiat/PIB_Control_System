package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;
import threads.SaveDataThread;

public class PIBController {

	// relation
	private Software en;

	public PIBController() throws FileNotFoundException, IOException, ClassNotFoundException {
		loadData();
		SaveDataThread sdt = new SaveDataThread(this);
		sdt.start();
	}

	private void loadData() throws FileNotFoundException, IOException, ClassNotFoundException {

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/data.fiat-arboleda"));
		en = (Software) ois.readObject();
		if (en == null) {
			en = new Software();
		}
		ois.close();
	}

	@FXML
	private BorderPane mainScreen;

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
	private TextField due�o;
	private TextField calificacion;
	private TextField cantidad;
	// ventana extra de educacion
	private TextField gastosEdu;
	private TextField typeEdu;
	private TextField rector;
	// venatana extra de financiera
	private TextField gananciasFinan;
	private TextField intereses;
	private TextField permiso;
	// agregar vehiculo
	// ----------------------
	@FXML
	private TextField marca;
	@FXML
	private TextField tipoVehiculo;
	@FXML
	private TextField idVehiculo;
	@FXML
	private TextField empresaVehi;
	@FXML
	private TextField paisVehi;
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
	private RadioButton ni�o;
	@FXML
	private RadioButton mascota;
	// ventana extra de ciudadano
	// ---------------------
	@FXML
	private TextField licor;
	@FXML
	private TextField extraEducacion;
	@FXML
	private TextField eduNi�o;
	@FXML
	private TextField pedigree;
	@FXML
	private TextField race;
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
	// -----------------
	// DAR PIB
	// ----------------
	@FXML
	private TextField pibPresidente;
	@FXML
	private TextField pibPais;
	@FXML
	private TextField resultadoPais;
	@FXML
	private TextField resultadoPresidente;

	public void saveData() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/data.fiat-arboleda"));
		oos.writeObject(en);
		oos.close();
	}

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

		eduNi�o = new TextField();
		eduNi�o.setPromptText("Privada/Publica");
		extraEducacion = new TextField();
		extraEducacion.setPromptText("Gastos en educacion");
		ni�o();

		race = new TextField();
		pedigree = new TextField();
		pedigree.setPromptText("s�/no");
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
		due�o = new TextField();
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

		gananciasFinan = new TextField();
		intereses = new TextField();
		permiso = new TextField();
		permiso.setPromptText("s� o no");
		financiera();

		mainScreen.getChildren().clear();
		mainScreen.setCenter(pib);
	}

	// --------------------------
	// DAR PIB PAIS
	// -------------------------
	@FXML
	public void darPibPais(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PIBPais.fxml"));
		fxmlLoader.setController(this);

		Parent pib = fxmlLoader.load();

		mainScreen.getChildren().clear();
		mainScreen.setCenter(pib);
	}

	@FXML
	public void darPibPresidente(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PIBPresidente.fxml"));
		fxmlLoader.setController(this);

		Parent pib = fxmlLoader.load();

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

	@FXML
	public void loadSortSea(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ordenar.fxml"));
		fxmlLoader.setController(this);

		Parent pib = fxmlLoader.load();

		mainScreen.getChildren().clear();
		mainScreen.setCenter(pib);
		barChartSea();
	}

	@FXML
	public void loadSortSpending(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ordenar.fxml"));
		fxmlLoader.setController(this);

		Parent pib = fxmlLoader.load();

		mainScreen.getChildren().clear();
		mainScreen.setCenter(pib);
		barChartSpending();
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
	// ---------------------------------
	// SORT BY SEA
	// ---------------------------------

	public void barChartSea() {

		en.sortCountriesBySea();

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
	// SORT BY PUBLIC SPEDING
	// ---------------------------------

	public void barChartSpending() {

		en.sortCountriesByPublicSpending();

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
			inf.setText("�INFO EXTRA!");
			inf.setLayoutX(128);
			inf.setLayoutY(29);
			Label lb = new Label();
			lb.setText("Ganancias:");
			lb.setLayoutX(47.0);
			lb.setLayoutY(101.0);

			tf.setLayoutX(192.0);
			tf.setLayoutY(97.0);
			bt.setText("�OK!");
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
				llenar.setHeaderText("Alguno de los requerimientos est� vac�o");
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
			inf.setText("�INFO EXTRA!");
			inf.setLayoutX(123);
			inf.setLayoutY(14);

			Label lb = new Label();
			lb.setText("Objetivo:");
			lb.setLayoutX(27.0);
			lb.setLayoutY(65.0);
			// ganancias text
			tfCopera.setLayoutX(198.0);
			tfCopera.setLayoutY(61.0);
			// label type
			Label type = new Label();
			type.setText("calificaci�n:");
			type.setLayoutX(27.0);
			type.setLayoutY(101.0);
			// due�o text
			califi.setLayoutX(198.0);
			califi.setLayoutY(97);

			bt.setText("�OK!");
			bt.setLayoutX(167);
			bt.setLayoutY(139);

			p.getChildren().addAll(inf, lb, tfCopera, type, califi, bt);

			Scene sc = new Scene(p, 390, 184);
			s.setScene(sc);
			s.show();

		});
		bt.setOnAction(e -> {
			if (tfCopera.getText().equals("") || califi.getText().equals("")) {
				Alert error = new Alert(AlertType.WARNING);
				error.setTitle("Fallo en el registro");
				error.setHeaderText("Alguno de los requerimientos est� vac�o");
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
			inf.setText("�INFO EXTRA!");
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
			duenio.setText("Due�o:");
			duenio.setLayoutX(27.0);
			duenio.setLayoutY(101.0);
			// due�o text
			due�o.setLayoutX(198.0);
			due�o.setLayoutY(97);
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

			bt.setText("�OK!");
			bt.setLayoutX(167);
			bt.setLayoutY(211);

			p.getChildren().addAll(inf, ganancias, lb, due�o, duenio, califi, calificacion, canti, cantidad, bt);

			Scene sc = new Scene(p, 450, 300);
			s.setScene(sc);
			s.show();

		});
		bt.setOnAction(e -> {
			if (ganancias.getText().equals("") || due�o.getText().equals("") || calificacion.getText().equals("")
					|| cantidad.getText().equals("")) {
				Alert error = new Alert(AlertType.WARNING);
				error.setTitle("Fallo en el registro");
				error.setHeaderText("Alguno de los requerimientos est� vac�o");
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
			inf.setText("�INFO EXTRA!");
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
			// due�o text
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

			bt.setText("�OK!");
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
				error.setHeaderText("Alguno de los requerimientos est� vac�o");
				error.setContentText("Por favor llene todos los campos");

				error.showAndWait();
			} else {

				s.close();

			}
		});
	}

	@FXML
	public void financiera() {

		Button bt = new Button();
		Stage s = new Stage();

		financiera.setOnAction(e -> {
			Pane p = new Pane();
			Label inf = new Label();
			inf.setText("�INFO EXTRA!");
			inf.setLayoutX(123);
			inf.setLayoutY(14);
			// label calificacion
			Label ganan = new Label();
			ganan.setText("Ganancias:");
			ganan.setLayoutX(27.0);
			ganan.setLayoutY(101.0);
			// ganancias text
			gananciasFinan.setLayoutX(198.0);
			gananciasFinan.setLayoutY(97);
			// label caificacion
			Label inte = new Label();
			inte.setText("Tasa de inter�s:");
			inte.setLayoutX(27.0);
			inte.setLayoutY(136.0);
			// intereses text
			intereses.setLayoutX(198.0);
			intereses.setLayoutY(132.0);
			// label cantidad
			Label permi = new Label();
			permi.setText("Permiso:");
			permi.setLayoutX(27.0);
			permi.setLayoutY(174.0);
			// permiso text
			permiso.setLayoutX(198.0);
			permiso.setLayoutY(170);

			bt.setText("�OK!");
			bt.setLayoutX(167);
			bt.setLayoutY(211);

			p.getChildren().addAll(inf, gananciasFinan, ganan, intereses, inte, permiso, permi, bt);

			Scene sc = new Scene(p, 450, 300);
			s.setScene(sc);
			s.show();

		});
		bt.setOnAction(e -> {
			if (gananciasFinan.getText().equals("") || intereses.getText().equals("") || permiso.getText().equals("")) {
				Alert error = new Alert(AlertType.WARNING);
				error.setTitle("Fallo en el registro");
				error.setHeaderText("Alguno de los requerimientos est� vac�o");
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
			successful.setContentText("EL PA�S NO EXISTE.");
			successful.showAndWait();
		} else {
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
				SupermarketChain add = new SupermarketChain(name, id, date, gastos,
						Double.parseDouble(ganancias.getText()), due�o.getText(),
						(Integer.parseInt(calificacion.getText())), Integer.parseInt(cantidad.getText()));

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
					successful.setContentText("Su empresa de tipo eduaci�n ha sido agregada de forma exitosa");

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
				double earnings = Double.parseDouble(gananciasFinan.getText());
				double interestRate = Double.parseDouble(intereses.getText());
				boolean permission = true;
				if (permiso.getText().equalsIgnoreCase("no")) {
					permission = false;
				} else {
					permission = true;
				}
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
				error.setHeaderText("Alguno de los requerimientos est� vac�o");
				error.setContentText("Por favor llene todos los campos");

				error.showAndWait();
			}
			// re cargar la interfaz
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgregarEmpresa.fxml"));
			fxmlLoader.setController(this);

			Parent pib = fxmlLoader.load();

			ganancias = new TextField();
			due�o = new TextField();
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
			error.setHeaderText("Alguno de los requerimientos est� vac�o");
			error.setContentText("Por favor llene todos los campos");

			error.showAndWait();
		} else {
			String name = namePais.getText();
			int habi = Integer.parseInt(habitantes.getText());
			double exte = Double.parseDouble(extension.getText());
			String presi = presidente.getText();
			double gastosPu = Double.parseDouble(gastosPublicos.getText());
			String m = mar.getText();
			Country added = new Country(name, habi, exte, presi, gastosPu, m);
			try {
				en.addCountry(added);

				Alert successful = new Alert(AlertType.INFORMATION);
				successful.setTitle("Registro exitoso");
				successful.setHeaderText(null);
				successful.setContentText("El pa�s ha sido agregada de forma exitosa");
				successful.showAndWait();
			} catch (SameNameCountryException e) {

				Alert successful = new Alert(AlertType.ERROR);
				successful.setTitle("ERROR EN REGISTRO");
				successful.setHeaderText(null);
				successful.setContentText("El nombre del pa�s ya existe.");
				successful.showAndWait();

			} catch (SamePresidentsNameException e) {
				Alert successful = new Alert(AlertType.ERROR);
				successful.setTitle("ERROR EN REGISTRO");
				successful.setHeaderText(null);
				successful.setContentText("La nombre del presidente ya est� asiganado/n" + "a otro pa�s.");
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

	public void addPerson() throws IOException {

		if (nombreCiudadano.getText().equals("") || idCiudadano.getText().equals("")
				|| paisCiudadano.getText().equals("") || clasi.getSelectedToggle() == null
				|| gastosCiudadanos.getText().equals("")) {

			Alert error = new Alert(AlertType.WARNING);
			error.setTitle("Fallo en el registro");
			error.setHeaderText("Alguno de los requerimientos est� vac�o");
			error.setContentText("Por favor llene todos los campos");

			error.showAndWait();
		} else {

			Country co = en.searchCountry(paisCiudadano.getText());
			if (co == null) {
				Alert successful = new Alert(AlertType.WARNING);
				successful.setTitle("ERROR EN REGISTRO");
				successful.setHeaderText(null);
				successful.setContentText("EL PA�S NO EXISTE.");
				successful.showAndWait();
			} else {
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

				} else if (ni�o.isSelected()) {

					Double ninio = Double.parseDouble(extraEducacion.getText());
					boolean ti = true;
					if (eduNi�o.getText().equals("no")) {
						ti = false;
					} else {
						ti = true;
					}
					Child c = new Child(nombreCiudadano.getText(), idCiudadano.getText(),
							Double.parseDouble(gastosCiudadanos.getText()), ti, ninio);
					try {
						en.addCitizen(c, co.getName());

						Alert successful = new Alert(AlertType.INFORMATION);
						successful.setTitle("Registro exitoso");
						successful.setHeaderText(null);
						successful.setContentText("Su ciudadano ni�o ha sido registrado de forma exitosa");
						successful.showAndWait();
					} catch (RepeatedCitizenException e) {
						Alert successful = new Alert(AlertType.ERROR);
						successful.setTitle("ERROR EN REGISTRO");
						successful.setHeaderText(null);
						successful.setContentText("El ciudadano ya existe.");
						successful.showAndWait();
					}

				} else if (mascota.isSelected()) {

					boolean pedi = true;

					if (pedigree.getText().equalsIgnoreCase("no")) {
						pedi = false;
					} else {
						pedi = true;
					}

					Pet p = new Pet(nombreCiudadano.getText(), idCiudadano.getText(),
							Double.parseDouble(gastosCiudadanos.getText()), pedi, race.getText());
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

			// recargar
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgregarCiudadano.fxml"));
			fxmlLoader.setController(this);

			Parent pib = fxmlLoader.load();

			licor = new TextField();
			adultos();

			eduNi�o = new TextField();
			eduNi�o.setPromptText("Privada/Publica");
			extraEducacion = new TextField();
			extraEducacion.setPromptText("Gastos en educacion");
			ni�o();

			race = new TextField();
			pedigree = new TextField();
			pedigree.setPromptText("s�/no");
			mascota();

			mainScreen.getChildren().clear();
			mainScreen.setCenter(pib);
		}
	}

	@FXML
	public void adultos() {

		Button bt = new Button();
		Stage s = new Stage();

		adulto.setOnAction(e -> {
			Pane p = new Pane();
			Label inf = new Label();
			inf.setText("�INFO EXTRA!");
			inf.setLayoutX(128);
			inf.setLayoutY(29);
			Label lb = new Label();
			lb.setText("Gastos en licor:");
			lb.setLayoutX(47.0);
			lb.setLayoutY(101.0);

			licor.setLayoutX(192.0);
			licor.setLayoutY(97.0);
			bt.setText("�OK!");
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
				llenar.setHeaderText("Alguno de los requerimientos est� vac�o");
				llenar.setContentText("Por favor llene todos los campos");

				llenar.showAndWait();
			} else {

				s.close();
			}
		});
	}

	@FXML
	public void ni�o() {

		Button bt = new Button();
		Stage s = new Stage();

		ni�o.setOnAction(e -> {
			Pane p = new Pane();
			Label inf = new Label();
			inf.setText("�INFO EXTRA!");
			inf.setLayoutX(123);
			inf.setLayoutY(14);

			Label lb = new Label();
			lb.setText("Gastos:");
			lb.setLayoutX(27.0);
			lb.setLayoutY(65.0);
			// ganancias text
			extraEducacion.setLayoutX(198.0);
			extraEducacion.setLayoutY(61.0);
			// label type
			Label type = new Label();
			type.setText("Educaci�n privada:");
			type.setLayoutX(27.0);
			type.setLayoutY(101.0);
			// due�o text
			eduNi�o.setLayoutX(198.0);
			eduNi�o.setLayoutY(97);

			bt.setText("�OK!");
			bt.setLayoutX(167);
			bt.setLayoutY(139);

			p.getChildren().addAll(inf, extraEducacion, lb, eduNi�o, type, bt);

			Scene sc = new Scene(p, 390, 184);
			s.setScene(sc);
			s.show();

		});
		bt.setOnAction(e -> {
			if (extraEducacion.getText().equals("") || eduNi�o.getText().equals("")) {
				Alert error = new Alert(AlertType.WARNING);
				error.setTitle("Fallo en el registro");
				error.setHeaderText("Alguno de los requerimientos est� vac�o");
				error.setContentText("Por favor llene todos los campos");

				error.showAndWait();
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
			inf.setText("�INFO EXTRA!");
			inf.setLayoutX(123);
			inf.setLayoutY(14);

			Label lb = new Label();
			lb.setText("Pedigree:");
			lb.setLayoutX(27.0);
			lb.setLayoutY(65.0);
			// ganancias text
			pedigree.setLayoutX(198.0);
			pedigree.setLayoutY(61.0);
			// label type
			Label type = new Label();
			type.setText("Raza:");
			type.setLayoutX(27.0);
			type.setLayoutY(101.0);
			// due�o text
			race.setLayoutX(198.0);
			race.setLayoutY(97);

			bt.setText("�OK!");
			bt.setLayoutX(167);
			bt.setLayoutY(139);

			p.getChildren().addAll(inf, pedigree, lb, race, type, bt);

			Scene sc = new Scene(p, 390, 184);
			s.setScene(sc);
			s.show();

		});
		bt.setOnAction(e -> {
			if (pedigree.getText().equals("") || race.getText().equals("")) {
				Alert error = new Alert(AlertType.WARNING);
				error.setTitle("Fallo en el registro");
				error.setHeaderText("Alguno de los requerimientos est� vac�o");
				error.setContentText("Por favor llene todos los campos");

				error.showAndWait();
			} else {

				s.close();

			}
		});
	}

	// -------------------------------
	// AGREGAR VEHICULO
	// -------------------------------
	@FXML
	public void addVehi() {
		if (marca.getText().equals("") || tipoVehiculo.getText().equals("") || idVehiculo.getText().equals("")
				|| empresaVehi.getText().equals("") || paisVehi.getText().equals("")) {

			Alert error = new Alert(AlertType.WARNING);
			error.setTitle("Fallo en el registro");
			error.setHeaderText("Alguno de los requerimientos est� vac�o");
			error.setContentText("Por favor llene todos los campos");

			error.showAndWait();
		} else {
			Country c = en.searchCountry(paisVehi.getText());
			Enterprise e = en.searchEnterprise(paisVehi.getText(), empresaVehi.getText());

			if (c == null) {
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("ERROR EN REGISTRO");
				error.setHeaderText(null);
				error.setContentText("EL PA�S NO EXISTE.");
				error.showAndWait();
			} else if (e == null) {
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("ERROR EN REGISTRO");
				error.setHeaderText(null);
				error.setContentText("LA EMPRESA NO EXISTE EN ESTE PA�S.");
				error.showAndWait();
			} else {
				String marc = marca.getText();
				String ti = tipoVehiculo.getText();
				String idV = idVehiculo.getText();

				Vehicle v = new Vehicle(ti, marc, idV);
				try {
					en.addVehicle(v, empresaVehi.getText(), paisVehi.getText());

					Alert successful = new Alert(AlertType.INFORMATION);
					successful.setTitle("Registro exitoso");
					successful.setHeaderText(null);
					successful.setContentText("Su veh�culo ha sido registrado de forma exitosa");
					successful.showAndWait();
				} catch (RepeatedVehicleException e1) {
					Alert error = new Alert(AlertType.WARNING);
					error.setTitle("Fallo en el registro");
					error.setHeaderText(null);
					error.setContentText("La empresa que desea agregar ya existe");
				}
			}
		}
	}

	@FXML
	public void pibPais() {
		Country c = en.searchCountry(pibPais.getText());
		if (c == null) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("ERROR EN REGISTRO");
			error.setHeaderText(null);
			error.setContentText("LA PA�S NO EXISTE.");
			error.showAndWait();
		} else {
			resultadoPais.setText(en.PIBName(pibPais.getText()) + "");
		}
	}

	@FXML
	public void pibPresident() {
		Country p = en.binarySearchCountryByPresident(pibPresidente.getText());
		if (p == null) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("ERROR EN REGISTRO");
			error.setHeaderText(null);
			error.setContentText("EL PRESIDENTE NO EXISTE.");
			error.showAndWait();
		} else {
			resultadoPresidente.setText(en.PIBPresident(pibPresidente.getText()) + "");
		}
	}

	// animation

//	public void moved(int x) {
//		
//		Platform.runLater(new Runnable() {
//			
//			@Override
//			public void run() {
//				Scene s = new Scene(paneCirculos);
//				Stage st = new Stage();
//				st.setScene(s);
//				try {
//					ventana(st);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				circle.setLayoutY(circle.getLayoutY() + x);
//				circleTwo.setLayoutY(circle.getLayoutY() + x);
//
//				
//			}
//		});
//	}
////	public void moved(int one) {
////		Platform.runLater(new Runnable() {
////			@Override
////			public void run() {
////				circleTwo.setRadius(one);
////			}
////		});
////	}
//	public void ventana(Stage primaryStage) throws IOException {
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WindowMain.fxml"));
//
//		fxmlLoader.setController(this);
//		
//		Parent root = fxmlLoader.load();
//		
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("PIB APP");
//		primaryStage.show();
//
//	}
}
