public class Automovil extends Vehiculo {

    private int pasajeros;
    private boolean automatico;
    private String categoria;

    public Automovil(String placa, String marca, String modelo, double tarifadiaria, int pasajeros, boolean automatico){
        super(placa,marca,modelo,tarifadiaria);
        if (pasajeros<=0){
            throw new IllegalArgumentException("La cantidad de pasajeros debe ser mayor a 0");
        }
        this.pasajeros = pasajeros;
        this.automatico = automatico;   
        this.categoria = "Automovil";
    }

    @Override
    public String obtenerDetalles() {
        return super.obtenerDetalles()
                + ", pasajeros: " + getPasajeros()
                + ", transmisión: " + getTransmision()
                + ", categoría: " + getCategoria();
    }

    public int getPasajeros(){
        return pasajeros;
    }

    public String getTransmision(){
        return (automatico ? "Automático" : "Mecánico");
    }
    @Override 
    public String getCategoria(){
        return categoria;
    }

    @Override 
    public double calcularTarifa(int dias){
        validar(dias);
        double total = (getTarifa()) * dias;
        if (automatico){
            total+= (50.0*dias);
        }
        return total;

    }

}
