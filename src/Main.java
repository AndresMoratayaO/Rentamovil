import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        Renta renta = new Renta();
        renta.registrarVehiculo(new Automovil("P001","BMW","Serie 3",200,5,true));
        renta.registrarVehiculo(new Automovil("P002","Volkswagen","Golf",180,5,false));
        renta.registrarVehiculo(new Moto("M001","KTM","250 Duke",100,249));
        renta.registrarVehiculo(new Moto("M002","BMW","G 310 R",150,313));
        renta.registrarVehiculo(new Camion("C001","Mercedes-Benz","Sprinter",200,1.5));
        renta.registrarVehiculo(new Camion("C002","Iveco","Daily",300,3));
        int opcion = -1;
        while (opcion != 0){
            System.out.println("\n--- RENTAMOVIL ---");
            System.out.println("1. Registrar vehículo");
            System.out.println("2. Consultar flota");
            System.out.println("3. Buscar placa");
            System.out.println("4. Cotizar");
            System.out.println("5. Alquilar");
            System.out.println("6. Devolver");
            System.out.println("7. Reporte");
            System.out.println("0. Salir");
            opcion = leerEntero(entrada,"Seleccione una opción: ");
            try {
                switch (opcion){
                    case 1: {
                        System.out.println("1. Automóvil");
                        System.out.println("2. Moto");
                        System.out.println("3. Camión");
                        int tipo = leerEntero(entrada,"Tipo: ");
                        if (tipo < 1 || tipo > 3){
                            System.out.println("Tipo no válido");
                            break;
                        }
                        String placa = leerTexto(entrada,"Placa: ");
                        if (renta.buscarPlaca(placa) != null){
                            System.out.println("La placa ya está registrada");
                            break;
                        }
                        String marca = leerTexto(entrada,"Marca: ");
                        String modelo = leerTexto(entrada,"Modelo: ");
                        double tarifa = leerDecimal(entrada,"Tarifa diaria: ");
                        if (tipo == 1){
                            int pasajeros = leerEntero(entrada,"Cantidad de pasajeros: ");
                            boolean automatico = leerConfirmacion(entrada,"¿Es automático? (s/n): ");
                            Automovil automovil = new Automovil(placa,marca,modelo,tarifa,pasajeros,automatico);
                            renta.registrarVehiculo(automovil);
                        } else if (tipo == 2){
                            int cilindraje = leerEntero(entrada,"Cilindraje: ");
                            Moto moto = new Moto(placa,marca,modelo,tarifa,cilindraje);
                            renta.registrarVehiculo(moto);
                        } else {
                            double capacidad = leerDecimal(entrada,"Capacidad en toneladas: ");
                            Camion camion = new Camion(placa,marca,modelo,tarifa,capacidad);
                            renta.registrarVehiculo(camion);
                        }
                        System.out.println("Vehículo registrado");
                        break;
                    }
                    case 2: {
                        System.out.println(renta.consultarFlota());
                        break;
                    }
                    case 3: {
                        String placa = leerTexto(entrada,"Placa: ");
                        Vehiculo vehiculo = renta.buscarPlaca(placa);
                        if (vehiculo == null){
                            System.out.println("Placa no registrada");
                        } else {
                            System.out.println(vehiculo.obtenerDetalles());
                        }
                        break;
                    }
                    case 4: {
                        String placa = leerTexto(entrada,"Placa: ");
                        Vehiculo vehiculo = renta.buscarPlaca(placa);
                        if (vehiculo == null){
                            System.out.println("Placa no registrada");
                            break;
                        }
                        int dias = leerEntero(entrada,"Días: ");
                        double total = renta.cotizar(placa,dias);
                        System.out.println(vehiculo.obtenerDetalles());
                        System.out.printf("Cotización: Q%.2f%n",total);
                        break;
                    }
                    case 5: {
                        String placa = leerTexto(entrada,"Placa: ");
                        Vehiculo vehiculo = renta.buscarPlaca(placa);
                        if (vehiculo == null){
                            System.out.println("Placa no registrada");
                            break;
                        }
                        if (!vehiculo.getDisponibilidad()){
                            System.out.println("El vehículo ya está alquilado");
                            break;
                        }
                        int dias = leerEntero(entrada,"Días: ");
                        double total = renta.cotizar(placa,dias);
                        System.out.println(vehiculo.obtenerDetalles());
                        System.out.printf("Total: Q%.2f%n",total);
                        boolean confirmar = leerConfirmacion(entrada,"¿Confirma el alquiler? (s/n): ");
                        if (confirmar){
                            renta.alquilar(placa,dias);
                            System.out.println("Alquiler confirmado");
                        } else {
                            System.out.println("Alquiler cancelado");
                        }
                        break;
                    }
                    case 6: {
                        String placa = leerTexto(entrada,"Placa: ");
                        renta.devolver(placa);
                        System.out.println("Devolución registrada");
                        break;
                    }
                    case 7: {
                        System.out.println(renta.reporte());
                        break;
                    }
                    case 0: {
                        System.out.println("Programa finalizado");
                        break;
                    }
                    default: {
                        System.out.println("Opción no válida");
                    }
                }
            } catch (IllegalArgumentException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        entrada.close();
    }
    public static String leerTexto(Scanner entrada,String mensaje){
        while (true){
            System.out.print(mensaje);
            String texto = entrada.nextLine().trim();
            if (!texto.isBlank()){
                return texto;
            }
            System.out.println("Debe ingresar un dato");
        }
    }
    public static int leerEntero(Scanner entrada,String mensaje){
        while (true){
            System.out.print(mensaje);
            try {
                int numero = Integer.parseInt(entrada.nextLine().trim());
                return numero;
            } catch (NumberFormatException e){
                System.out.println("Debe ingresar un número entero");
            }
        }
    }
    public static double leerDecimal(Scanner entrada,String mensaje){
        while (true){
            System.out.print(mensaje);
            try {
                double numero = Double.parseDouble(entrada.nextLine().trim());
                if (Double.isFinite(numero) && numero > 0){
                    return numero;
                }
                System.out.println("Debe ingresar un número positivo y finito");
            } catch (NumberFormatException e){
                System.out.println("Ingrese un número usando punto para decimales");
            }
        }
    }
    public static boolean leerConfirmacion(Scanner entrada,String mensaje){
        while (true){
            String respuesta = leerTexto(entrada,mensaje);
            if (respuesta.equalsIgnoreCase("s")){
                return true;
            }
            if (respuesta.equalsIgnoreCase("n")){
                return false;
            }
            System.out.println("Responda s o n");
        }
    }
}