package co.edu.usbcali.vendingmachine.model;

import org.hibernate.validator.constraints.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Entity
@Table(name = "producto", schema = "public")
public class Producto implements java.io.Serializable {
    
    private Integer idProducto;
    @NotNull
    private Display display;
    @NotNull
    private Integer cantidad;
    @NotNull
    @NotEmpty
    @Size(max = 250)
    private String descripcion;
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String nombre;
    @NotNull
    private Integer valor;
    private Set<Compra> compras = new HashSet<Compra>(0);

    public Producto() {
    }

    public Producto(Integer idProducto, Integer cantidad, Set<Compra> compras,
        String descripcion, Display display, String nombre, Integer valor) {
        this.idProducto = idProducto;
        this.display = display;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.valor = valor;
        this.compras = compras;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_producto", unique = true, nullable = false)
    public Integer getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_display")
    public Display getDisplay() {
        return this.display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    @Column(name = "cantidad", nullable = false)
    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "descripcion", nullable = false)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "valor", nullable = false)
    public Integer getValor() {
        return this.valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
    public Set<Compra> getCompras() {
        return this.compras;
    }

    public void setCompras(Set<Compra> compras) {
        this.compras = compras;
    }
}
