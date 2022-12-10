package examen2p1_diegomaradiaga;

import java.util.ArrayList;

public class MaquinaEstados {

    ArrayList<String> estados = new ArrayList();
    ArrayList<String> estados_aceptacion = new ArrayList();
    ArrayList<String> aristas = new ArrayList();
    String estado_actual;

    public MaquinaEstados(String estados, String aristas) {
        this.estados = Split(estados, ';');
        extractAcceptNodes();
        this.aristas = Split(aristas, ';');
        this.estado_actual = this.estados.get(0);
        for (int i = 0; i < this.estados.size(); i++) {
            System.out.println("[" + this.estados.get(i) + "]");
        }
        for (int i = 0; i < this.aristas.size(); i++) {
            System.out.println("[" + this.aristas.get(i) + "]");
        }
        
    }

    public ArrayList<String> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<String> estados) {
        this.estados = estados;
    }

    public ArrayList<String> getEstados_aceptacion() {
        return estados_aceptacion;
    }

    public void setEstados_aceptacion(ArrayList<String> estados_aceptacion) {
        this.estados_aceptacion = estados_aceptacion;
    }

    public ArrayList<String> getAristas() {
        return aristas;
    }

    public void setAristas(ArrayList<String> aristas) {
        this.aristas = aristas;
    }

    public String getEstado_actual() {
        return estado_actual;
    }

    public void setEstado_actual(String estado_actual) {
        this.estado_actual = estado_actual;
    }

    public ArrayList<String> Split(String Cad, char delimitador) {

        String[] Split = Cad.split(String.valueOf(delimitador));
        ArrayList<String> Cadena = new ArrayList();

        for (int i = 0; i < Split.length; i++) {
            Cadena.add(Split[i]);
        }
        return Cadena;
    }//Fin metodo Split

    public void extractAcceptNodes() {

        String caracter = "";

        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).contains(".")) {
                caracter = estados.get(i);
                caracter = caracter.substring(1);
                estados_aceptacion.add(caracter);
                estados.set(i, caracter);
            } else {
                caracter = "1";
            }
        }
        System.out.println("estados 2");
        for (int i = 0; i < estados.size(); i++) {
            System.out.println("["+estados.get(i)+"]");
        }
    }//Fin metodo extractAcceptNodes

    public String Computar(String cadena) {
        String chain = "";
        estado_actual = estados.get(0);

        for (int i = 0; i < cadena.length(); i++) {
            String aristas = GetArista(estado_actual + "," + cadena.charAt(i));
            if (aristas != "") {
                chain += estado_actual + ": " + cadena.charAt(i) + "-> ";
                String[] arr = aristas.split(",");
                estado_actual = arr[2];
                chain += estado_actual + "\n";
                chain += "\n";
            } else {
                chain = "Error";
                return chain;
            }
        }
        if (estados_aceptacion.contains(estado_actual)) {
            chain += "Aceptado";
        } else {
            chain += "Rechazado";
        }

        return chain;
    }

    public String GetArista(String cadena) {
        for (int i = 0; i < aristas.size(); i++) {
            if (aristas.get(i).contains(cadena)) {
                return aristas.get(i);
            }
        }
        return "";
    }
}
