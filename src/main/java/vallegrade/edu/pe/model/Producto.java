package vallegrade.edu.pe.model;

public class Producto {

    // id -> id_producto (DB)
    private int id;
    // codigo -> codigo (DB)
    private String codigo;
    // nombre -> nombre (DB)
    private String nombre;
    // descripcion -> descripcion (DB)
    private String descripcion;
    // idCategoria -> id_categoria (DB)
    private int idCategoria;
    // precio -> precio (DB)
    private double precio;
    // stock -> stock (DB)
    private int stock;

    // Constructor vacío (requerido por muchas herramientas/serializadores)
    public Producto() {
    }

    // Constructor existente (compatibilidad hacia atrás)
    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Constructor completo alineado al esquema de la tabla productos
    public Producto(int id, String codigo, String nombre, String descripcion, int idCategoria, double precio, int stock) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
