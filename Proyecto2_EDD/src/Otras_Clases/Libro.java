package Otras_Clases;

/**
 *
 * @author Robert Hernandez
 */
 public class Libro {
    private int isbn;
    private String Titulo;
    private String Autor;
    private String editorial;
    private int anio;
    private int edicion;
    private String Categoria;
    private String idioma;
    private int carnet;
    
    public Libro (){
    this.isbn =0;
    this.Titulo="";
    this.Autor="";
    this.editorial="";
    this.anio=2000;
    this.edicion = 1;
    this.Categoria="Sin Categoria";
    this.idioma = "";
    this.carnet =0;
    
    
    
    }

    public Libro(int isbn, String Titulo, int carnet) {
        this.isbn = isbn;
        this.Titulo = Titulo;
        this.carnet = carnet;
    }
    

    
    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

   
    
    
    
}
