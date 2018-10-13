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
@Table(name = "display", schema = "public")
public class Display implements java.io.Serializable {
    
    private Integer idDisplay;
    private Integer idProducto;
    private Set<Producto> productos = new HashSet<Producto>(0);

    public Display() {
    }

    public Display(Integer idDisplay, Integer idProducto,
        Set<Producto> productos) {
        this.idDisplay = idDisplay;
        this.idProducto = idProducto;
        this.productos = productos;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_display", unique = true, nullable = false)
    public Integer getIdDisplay() {
        return this.idDisplay;
    }

    public void setIdDisplay(Integer idDisplay) {
        this.idDisplay = idDisplay;
    }

    @Column(name = "id_producto", nullable = false)
    public Integer getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "display")
    public Set<Producto> getProductos() {
        return this.productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
}
