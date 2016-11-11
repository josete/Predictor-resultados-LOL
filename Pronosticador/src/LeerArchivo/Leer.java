/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeerArchivo;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.lazy.KStar;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author Portatil-pc
 */
public class Leer {

    public Leer() {

    }

    public void leerArchivoArff() {
        try {
            // create J48
            Classifier cls = new KStar();
            // train
            Instances inst = new Instances(
                    new BufferedReader(
                            new FileReader("../datos.arff")));

            inst.setClassIndex(inst.numAttributes() - 1);
            cls.buildClassifier(inst);
            // serialize model
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("./KStar.model"));
            oos.writeObject(cls);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String leerModelo() {
        try {
            String[] valoresAtributos = {"Origen", "Fnatic"};
            Classifier clasificador = (Classifier) weka.core.SerializationHelper.read("./KStar.model");
            ConverterUtils.DataSource source = new ConverterUtils.DataSource("../test.arff");
            Instances data = source.getDataSet();
            data.setClassIndex(5);
            System.out.println(data.instance(0));
            return valoresAtributos[(int) clasificador.classifyInstance(data.instance(0))];
        } catch (Exception ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
