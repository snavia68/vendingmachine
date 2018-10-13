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
public class DisplayDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DisplayDTO.class);
    private Integer idDisplay;
    private Integer idProducto;

    public Integer getIdDisplay() {
        return idDisplay;
    }

    public void setIdDisplay(Integer idDisplay) {
        this.idDisplay = idDisplay;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}
