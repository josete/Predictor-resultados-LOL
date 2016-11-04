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
import weka.classifiers.trees.J48;
import weka.core.Instances;

/**
 *
 * @author Portatil-pc
 */
public class Leer {

    public Leer() {
        try {
            // create J48
            Classifier cls = new J48();
            // train
            Instances inst = new Instances(
                    new BufferedReader(
                            new FileReader("./datos.arff")));

            inst.setClassIndex(inst.numAttributes() - 1);
            cls.buildClassifier(inst);
            // serialize model
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("/some/where/j48.model"));
            oos.writeObject(cls);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
