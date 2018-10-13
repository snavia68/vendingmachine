package co.edu.usbcali.vendingmachine.model;

import org.hibernate.validator.constraints.*;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Entity
@Table(name = "compra", schema = "public")
public class Compra implements java.io.Serializable {
    
    private Integer idCompra;
    @NotNull
    private Producto producto;

    public Compra() {
    }

    public Compra(Integer idCompra, Producto producto) {
        this.idCompra = idCompra;
        this.producto = producto;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_compra", unique = true, nullable = false)
    public Integer getIdCompra() {
        return this.idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
