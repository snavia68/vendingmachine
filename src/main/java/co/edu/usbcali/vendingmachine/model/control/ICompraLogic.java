package co.edu.usbcali.vendingmachine.model.control;

import co.edu.usbcali.vendingmachine.model.Compra;
import co.edu.usbcali.vendingmachine.model.dto.CompraDTO;

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
public interface ICompraLogic {
    public List<Compra> getCompra() throws Exception;

    /**
         * Save an new Compra entity
         */
    public void saveCompra(Compra entity) throws Exception;

    /**
         * Delete an existing Compra entity
         *
         */
    public void deleteCompra(Compra entity) throws Exception;

    /**
        * Update an existing Compra entity
        *
        */
    public void updateCompra(Compra entity) throws Exception;

    /**
         * Load an existing Compra entity
         *
         */
    public Compra getCompra(Integer idCompra) throws Exception;

    public List<Compra> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Compra> findPageCompra(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCompra() throws Exception;

    public List<CompraDTO> getDataCompra() throws Exception;

    public void validateCompra(Compra compra) throws Exception;
}
