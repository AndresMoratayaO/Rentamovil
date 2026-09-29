import java.util.ArrayList;

public class Renta {
    private ArrayList<Vehiculo> flota;
    private double ingresosacu;

    public Renta(){
        flota = new ArrayList<>();
        ingresosacu = 0;
    }

    public Vehiculo buscarPlaca(String placa){
        if (placa == null || placa.isBlank()){
            throw new IllegalArgumentException("Debe ingresar una placa");
        }

        for (Vehiculo vehiculoactual : flota){
            if (vehiculoactual.getPlaca().trim().equalsIgnoreCase(placa.trim())){
                return vehiculoactual;
            }
        }

        return null;
    }

    public void registrarVehiculo(Vehiculo vehiculo){
        if (vehiculo == null){
            throw new IllegalArgumentException("Debe proporcionar un vehículo");
        }

        if (buscarPlaca(vehiculo.getPlaca()) != null){
            throw new IllegalArgumentException("Ya existe un vehículo con esa placa");
        }

        if (!vehiculo.getDisponibilidad()){
            throw new IllegalArgumentException("El vehículo debe estar disponible");
        }

        flota.add(vehiculo);
    }

    public String consultarFlota(){
        String resultado = "";

        if (flota.isEmpty()){
            return "No hay vehículos registrados";
        }

        for (Vehiculo vehiculoactual : flota){
            resultado += vehiculoactual.obtenerDetalles() + "\n";
        }

        return resultado;
    }

    public double cotizar(String placa, int dias){
        Vehiculo vehiculo = buscarPlaca(placa);

        if (vehiculo == null){
            throw new IllegalArgumentException("Placa no registrada");
        }

        return vehiculo.calcularTarifa(dias);
    }

    public double alquilar(String placa, int dias){
        Vehiculo vehiculo = buscarPlaca(placa);

        if (vehiculo == null){
            throw new IllegalArgumentException("Placa no registrada");
        }

        if (!vehiculo.getDisponibilidad()){
            throw new IllegalArgumentException("El vehículo ya está alquilado");
        }

        double total = vehiculo.calcularTarifa(dias);

        vehiculo.quitarDisponibilidad();
        ingresosacu += total;

        return total;
    }

    public void devolver(String placa){
        Vehiculo vehiculo = buscarPlaca(placa);

        if (vehiculo == null){
            throw new IllegalArgumentException("Placa no registrada");
        }

        if (vehiculo.getDisponibilidad()){
            throw new IllegalArgumentException("El vehículo ya está disponible");
        }

        vehiculo.habilitarDisponibilidad();
    }

    public double getIngresosacu(){
        return ingresosacu;
    }

    public String reporte(){
        String resultado = "Vehículos registrados: " + flota.size() + "\n";
        ArrayList<String> categorias = new ArrayList<>();

        for (Vehiculo vehiculoactual : flota){
            if (!categorias.contains(vehiculoactual.getCategoria())){
                categorias.add(vehiculoactual.getCategoria());
            }
        }

        for (String categoria : categorias){
            int registrados = 0;
            int disponibles = 0;
            int alquilados = 0;

            for (Vehiculo vehiculoactual : flota){
                if (vehiculoactual.getCategoria().equals(categoria)){
                    registrados++;

                    if (vehiculoactual.getDisponibilidad()){
                        disponibles++;
                    } else {
                        alquilados++;
                    }
                }
            }

            resultado += "\nCategoría: " + categoria;
            resultado += "\nRegistrados: " + registrados;
            resultado += "\nDisponibles: " + disponibles;
            resultado += "\nAlquilados: " + alquilados + "\n";
        }

        resultado += "\nIngresos acumulados: Q"+ String.format("%.2f", ingresosacu);

        return resultado;
    }
}