package co.edu.usbcali.vendingmachine.model.control;

import co.edu.usbcali.vendingmachine.model.Producto;
import co.edu.usbcali.vendingmachine.model.dto.ProductoDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IProductoLogic {
    public List<Producto> getProducto() throws Exception;

    /**
         * Save an new Producto entity
         */
    public void saveProducto(Producto entity) throws Exception;

    /**
         * Delete an existing Producto entity
         *
         */
    public void deleteProducto(Producto entity) throws Exception;

    /**
        * Update an existing Producto entity
        *
        */
    public void updateProducto(Producto entity) throws Exception;

    /**
         * Load an existing Producto entity
         *
         */
    public Producto getProducto(Integer idProducto) throws Exception;

    public List<Producto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Producto> findPageProducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberProducto() throws Exception;

    public List<ProductoDTO> getDataProducto() throws Exception;

    public void validateProducto(Producto producto) throws Exception;

	Producto buscarPorDisplay(Integer idDisplay) throws Exception;
}
