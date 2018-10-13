package co.edu.usbcali.vendingmachine.model.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public class CompraDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CompraDTO.class);
    private Integer idCompra;
    private Integer idProducto_Producto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto_Producto() {
        return idProducto_Producto;
    }

    public void setIdProducto_Producto(Integer idProducto_Producto) {
        this.idProducto_Producto = idProducto_Producto;
    }
}
