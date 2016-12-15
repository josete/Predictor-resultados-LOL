/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LeerArchivo.Leer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Portatil-pc
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    ComboBox equipo1;
    @FXML
    ComboBox equipo2;
    @FXML
    TextField ganador;

    String[] equipos = {"Elements", "Fnatic", "G2", "Giants", "H2K", "Origen",
        "Splyce", "Roccat", "Vitality", "UOL", "Gambit", "Copenhagen wolves", "SK"};

    ArrayList<String> historico = new ArrayList<>();
    ArrayList<String> ultimo = new ArrayList<>();
    ArrayList<String> lineas = new ArrayList<>();
    
    Leer l;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        equipo1.setItems(FXCollections.observableArrayList(equipos));
        equipo2.setItems(FXCollections.observableArrayList(equipos));
        
        //Añadimos los equipos en orden
        historico.add("Fnatic");
        historico.add("Origen");
        historico.add("Gambit");
        historico.add("UOL");
        historico.add("Elements");
        historico.add("H2K");
        historico.add("SK");
        historico.add("Roccat");
        historico.add("Copenhagen wolves");
        historico.add("Giants");
        historico.add("G2");
        historico.add("Vitality");
        historico.add("Splyce");
        //Añadimos los equipos en orden segun su ultimo resultado
        ultimo.add("Fnatic");
        ultimo.add("Origen");
        ultimo.add("H2K");
        ultimo.add("UOL");
        ultimo.add("Roccat");
        ultimo.add("Giants");
        ultimo.add("Elements");
        ultimo.add("G2");
        ultimo.add("Splyce");
        ultimo.add("Vitality");
        ultimo.add("Copenhagen wolves");
        ultimo.add("SK");
        ultimo.add("Gambit");
        l = new Leer();
    }

    @FXML
    public void anadir() {
        String azul = equipo1.getSelectionModel().getSelectedItem().toString();
        String rojo = equipo2.getSelectionModel().getSelectedItem().toString();
        String gana = "?";
        String linea = (historico.indexOf(azul) + 1) + "," + (ultimo.indexOf(azul) + 1) + "," + (historico.indexOf(rojo) + 1) + ","
                + (ultimo.indexOf(rojo) + 1) + "," + 0 + "," + gana;
        lineas.clear();
        lineas.add(linea);
        predecir();
    }

    @FXML
    public void predecir() {
        FileWriter fichero = null;
        try {
            String cabecera = "%Partidos en una temporada 2016 LCS EU Spring\n@relation Teams\n"
                    + "@attribute posicionHistoricaE1 {1,2,3,4,5,6,7,8,9,10,11,12,13}\n"
                    + "@attribute posicionUltimaE1 {1,2,3,4,5,6,7,8,9,10,11,12,13}\n"
                    + "@attribute posicionHistoricaE2 {1,2,3,4,5,6,7,8,9,10,11,12,13}\n"
                    + "@attribute posicionUltimaE2 {1,2,3,4,5,6,7,8,9,10,11,12,13}\n"
                    + "@attribute diferenciasVicorias Real\n"
                    + "@attribute ganador {0,1}\n@data";
            PrintWriter pw = null;
            fichero = new FileWriter("./test.arff");
            pw = new PrintWriter(fichero);
            pw.println(cabecera);
            for (String s : lineas) {
                pw.println(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if(l.leerModelo().equals("0")){
            ganador.setText(equipo1.getSelectionModel().getSelectedItem().toString());
        }else{
            ganador.setText(equipo2.getSelectionModel().getSelectedItem().toString());
        }
        System.out.println(l.leerModelo());
    }
}