public abstract class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private double tarifadiaria;
    private boolean disponibilidad;

    protected Vehiculo(String placa, String marca, String modelo, double tarifadiaria){
        if (placa==null || placa.isBlank()){
            throw new IllegalArgumentException("Deba asignar una placa");
        }
        if (marca==null || marca.isBlank()){
            throw new IllegalArgumentException("Debe asignar una marca");
        }
        if (modelo==null || modelo.isBlank()){
            throw new IllegalArgumentException("Debe asignar un modelo");
        }
        if (tarifadiaria<=0){
            throw new IllegalArgumentException("La tarifa debe ser mayor a 0");
        }
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tarifadiaria = tarifadiaria;
        this.disponibilidad = true;
    }

    protected void validar(int dias){
        if (dias <= 0) {
            throw new IllegalArgumentException(
                "Los días de alquiler deben ser mayores que cero"
            );
        }
    }

    public abstract double calcularTarifa(int dias);

    public abstract String getCategoria();

    public String obtenerDetalles(){
        return "Placa: " + getPlaca()
            + ", marca: " + getMarca()
            + ", modelo: " + getModelo()
            + ", tarifa diaria: Q" + getTarifa()
            + ", disponibilidad: " + (getDisponibilidad() ? "Disponible" : "Alquilado");
    };

    public void quitarDisponibilidad(){
        disponibilidad=false;
    }

    public void habilitarDisponibilidad(){
        disponibilidad=true;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getTarifa() {
        return tarifadiaria;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
}

}
