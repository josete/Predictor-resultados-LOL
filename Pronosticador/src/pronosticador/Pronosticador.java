/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pronosticador;

import LeerArchivo.Leer;
import weka.classifiers.Classifier;

/**
 *
 * @author Portatil-pc
 */
public class Pronosticador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Leer l = new Leer();
        System.out.println(l.leerModelo());
    }

}
