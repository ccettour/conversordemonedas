package com.ccettour.conversordemonedas.principal;

import com.ccettour.conversordemonedas.calculos.ConsultaIndice;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConsultaIndice consultaIndice = new ConsultaIndice();

        Map<String, String> monedas = new HashMap<>();
        monedas.put("ARS", "Peso argentino");
        monedas.put("BOB", "Boliviano boliviano");
        monedas.put("BRL", "Real brasileño");
        monedas.put("CLP", "Peso chileno");
        monedas.put("COP", "Peso colombiano");
        monedas.put("USD", "Dólar estadounidense");

        String monedaBase;
        String monedaTarget;

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

            do{
                System.out.println("Ingrese la moneda base:\n" +
                        "ARS - Peso argentino\n" +
                        "BOB - Boliviano boliviano\n" +
                        "BRL - Real brasileño\n" +
                        "CLP - Peso chileno\n" +
                        "COP - Peso colombiano\n" +
                        "USD - Dólar estadounidense");

                monedaBase = sc.next().toUpperCase();

                if(!monedas.containsKey(monedaBase)){
                    System.out.println("El código ingresado es inválido.\n");
                }
            } while(!monedas.containsKey(monedaBase));

            do{
                System.out.println("Ingrese la moneda final:\n" +
                        "ARS - Peso argentino\n" +
                        "BOB - Boliviano boliviano\n" +
                        "BRL - Real brasileño\n" +
                        "CLP - Peso chileno\n" +
                        "COP - Peso colombiano\n" +
                        "USD - Dólar estadounidense");

                monedaTarget = sc.next().toUpperCase();
                if(!monedas.containsKey(monedaTarget)){
                    System.out.println("El código ingresado es inválido.\n");
                }
            } while(!monedas.containsKey(monedaTarget));

            double indice = consultaIndice.getIndice(monedaBase,monedaTarget);

            double resultado = valor * indice;

            System.out.println(valor + " " + monedas.get(monedaBase) + " equivalen a " + resultado + " " + monedas.get(monedaTarget));

            System.out.println("¿Desea realizar otra conversión?\n" +
                    "0 - SI\n" +
                    "1 - NO");

            opcion = sc.nextInt();

            if(opcion==1){
                System.out.println("Gracias por usar el conversor.");
            }
        }
    }
}
