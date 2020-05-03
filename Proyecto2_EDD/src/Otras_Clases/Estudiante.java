package Otras_Clases;

/**
 *
 * @author Robert Hernadez
 */
public class Estudiante {
    private int carnet;
    private String nombre;
    private String apellido;
    private String carrera;
    private String password;

    public Estudiante(int carnet, String nombre, String apellido, String carrera, String password) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.password = password;
    }
    
    public Estudiante (){
    this.carnet =20000000;
    this.nombre="";
    this.apellido ="";
    this.carrera="";
    this.password ="1234";
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
