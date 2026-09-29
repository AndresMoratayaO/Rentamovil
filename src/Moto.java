public class Moto extends Vehiculo {

    private int cilindraje;
    private String categoria;

    public Moto(String placa, String marca, String modelo, double tarifadiaria, int cilindraje){
        super(placa,marca,modelo,tarifadiaria);
        if (cilindraje<=0){
            throw new IllegalArgumentException("El cilindraje debe ser mayor a 0");
        }
        this.cilindraje = cilindraje;   
        this.categoria = "Motocicleta";
    }

    @Override
    public String obtenerDetalles() {
        return super.obtenerDetalles()
                + ", cilindraje (cc): " + getCilindraje()
                + ", categoría: " + getCategoria();
    }

    public int getCilindraje(){
        return cilindraje;
    }

    @Override 
    public String getCategoria(){
        return categoria;
    }

    @Override 
    public double calcularTarifa(int dias){
        validar(dias);
        double total = (getTarifa()) * dias;
        if (cilindraje>250){
            total+=75;
        }
        return total;

    }

}
