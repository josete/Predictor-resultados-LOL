/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generador;

/**
 *
 * @author Familia
 */
public class Resultado {
    
    String e1;
    String e2;
    String ganador;

    public Resultado(String e1, String e2, String ganador) {
        this.e1 = e1;
        this.e2 = e2;
        this.ganador = ganador;
    }

    public String getE1() {
        return e1;
    }

    public void setE1(String e1) {
        this.e1 = e1;
    }

    public String getE2() {
        return e2;
    }

    public void setE2(String e2) {
        this.e2 = e2;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
    
    public boolean esEncuentro(String equipo,String equipo2){
        boolean tiene = false;
        if((e1.equals(equipo)||e2.equals(equipo))&&(e1.equals(equipo2)||e2.equals(equipo2))){
            tiene = true;
        }
        return tiene;
    }
}
