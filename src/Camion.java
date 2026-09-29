public class Camion extends Vehiculo {

    private double capacidad;
    private String categoria;

    public Camion(String placa, String marca, String modelo, double tarifadiaria, double capacidad){
        super(placa,marca,modelo,tarifadiaria);
        if (capacidad<=0){
            throw new IllegalArgumentException("El capacidad debe ser mayor a 0");
        }
        this.capacidad = capacidad;   
        this.categoria = "Camion de carga";
    }

    @Override
    public String obtenerDetalles() {
        return super.obtenerDetalles()
                + ", capacidad (toneladas): " + getcapacidad()
                + ", categoría: " + getCategoria();
    }

    public double getcapacidad(){
        return capacidad;
    }

    @Override 
    public String getCategoria(){
        return categoria;
    }

    @Override 
    public double calcularTarifa(int dias){
        validar(dias);
        double total = (getTarifa() + 100 * capacidad) * dias;
        return total;

    }

}
