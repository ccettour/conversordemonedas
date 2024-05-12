package com.ccettour.conversordemonedas.principal;

import com.ccettour.conversordemonedas.calculos.ConsultaIndice;
import com.ccettour.conversordemonedas.modelos.Moneda;

import java.util.*;

public class principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConsultaIndice consultaIndice = new ConsultaIndice();

        Moneda pesoArgentino = new Moneda("ARS", "Peso argentino");
        Moneda bolivianoBoliviano = new Moneda("BOB", "Boliviano boliviano");
        Moneda realBrasil = new Moneda("BRL", "Real brasileño");
        Moneda pesoChileno = new Moneda("CLP", "Peso chileno");
        Moneda pesoColombiano = new Moneda("COP", "Peso colombiano");
        Moneda dolarEstadounidense = new Moneda("USD", "Dólar estadounidense");

        List<Moneda> monedas = new ArrayList<>(Arrays.asList(pesoArgentino,bolivianoBoliviano,realBrasil,pesoChileno,pesoColombiano,dolarEstadounidense));


        Moneda monedaBase;
        Moneda monedaTarget;

        var opcion = 0;
        while(opcion==0){
            double valor=0;
            try{
                System.out.println("Ingrese el valor que desea convertir: ");
                valor = sc.nextDouble();
            }catch(InputMismatchException e){
                System.out.println("El valor ingresado es incorrecto. Debe ser un número decimal.");
                break;
            }

            try{
                System.out.println("\nIngrese la moneda base:");
                for (int i = 0; i < monedas.size(); i++) {
                    System.out.println(i+1 + " - " + monedas.get(i).getNombre());
                }

                int seleccion = sc.nextInt();
                monedaBase = monedas.get(seleccion-1);
            } catch(InputMismatchException | IndexOutOfBoundsException e){
                System.out.println("Debe ingresar el número correspondiente a la moneda deseada");
                break;
            }

            try{
                System.out.println("\nIngrese la moneda a la que desea convertir el valor:");
                for (int i = 0; i < monedas.size(); i++) {
                    System.out.println(i+1 + " - " + monedas.get(i).getNombre());
                }

                int seleccion = sc.nextInt();
                monedaTarget = monedas.get(seleccion-1);
            }catch(InputMismatchException | IndexOutOfBoundsException e){
                System.out.println("Debe ingresar el número correspondiente a la moneda deseada");
                break;
            }

            double indice = consultaIndice.getIndice(monedaBase.getCodigo(),monedaTarget.getCodigo());

            double resultado = valor * indice;

            System.out.println("\n" + valor + " " + monedaBase.getNombre() + " equivalen a " + resultado + " " + monedaTarget.getNombre());

            System.out.println("\n¿Desea realizar otra conversión?\n" +
                    "0 - SI\n" +
                    "1 - NO");

            opcion = sc.nextInt();

            if(opcion==1){
                System.out.println("Gracias por usar el conversor.");
            }
        }
    }
}
