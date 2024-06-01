package nicolas.example.manualparapracticantes.extras;

public class Persona {
    private String ID;
    private String Correo;
    private String Contraseña;
    private String Nombre;
    private int puntos;
    private int puntos2;
    private int puntos3;
    private int puntos4;
    private int puntos5;
    private int puntos6;
    private int puntos7;
    private int puntos8;

    public Persona(){

    }

    public String getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos2() {
        return puntos2;
    }

    public void setPuntos2(int puntos2) {
        this.puntos2 = puntos2;
    }

    public int getPuntos3() {
        return puntos3;
    }

    public void setPuntos3(int puntos3) {
        this.puntos3 = puntos3;
    }

    public int getPuntos4() {
        return puntos4;
    }

    public void setPuntos4(int puntos4) {
        this.puntos4 = puntos4;
    }

    public int getPuntos5() {
        return puntos5;
    }

    public void setPuntos5(int puntos5) {
        this.puntos5 = puntos5;
    }

    public int getPuntos6() {
        return puntos6;
    }

    public void setPuntos6(int puntos6) {
        this.puntos6 = puntos6;
    }

    public int getPuntos7() {
        return puntos7;
    }

    public void setPuntos7(int puntos7) {
        this.puntos7 = puntos7;
    }

    public int getPuntos8() {
        return puntos8;
    }

    public void setPuntos8(int puntos8) {
        this.puntos8 = puntos8;
    }

    @Override
    public String toString() {
        return Correo;
    }
}
