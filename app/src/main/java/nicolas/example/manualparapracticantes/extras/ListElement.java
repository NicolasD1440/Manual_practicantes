package nicolas.example.manualparapracticantes.extras;

public class ListElement {
    private String color;
    private String Nivel;
    private String descripcion;

    public ListElement(String color, String nivel, String descripcion) {
        this.color = color;
        Nivel = nivel;
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String nivel) {
        Nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
