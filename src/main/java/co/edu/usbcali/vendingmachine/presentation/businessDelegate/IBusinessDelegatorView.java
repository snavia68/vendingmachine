package co.edu.usbcali.vendingmachine.presentation.businessDelegate;

import co.edu.usbcali.vendingmachine.model.Compra;
import co.edu.usbcali.vendingmachine.model.Display;
import co.edu.usbcali.vendingmachine.model.Producto;
import co.edu.usbcali.vendingmachine.model.control.CompraLogic;
import co.edu.usbcali.vendingmachine.model.control.DisplayLogic;
import co.edu.usbcali.vendingmachine.model.control.ICompraLogic;
import co.edu.usbcali.vendingmachine.model.control.IDisplayLogic;
import co.edu.usbcali.vendingmachine.model.control.IProductoLogic;
import co.edu.usbcali.vendingmachine.model.control.ProductoLogic;
import co.edu.usbcali.vendingmachine.model.dto.CompraDTO;
import co.edu.usbcali.vendingmachine.model.dto.DisplayDTO;
import co.edu.usbcali.vendingmachine.model.dto.ProductoDTO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IBusinessDelegatorView {
    public List<Compra> getCompra() throws Exception;

    public void saveCompra(Compra entity) throws Exception;

    public void deleteCompra(Compra entity) throws Exception;

    public void updateCompra(Compra entity) throws Exception;

    public Compra getCompra(Integer idCompra) throws Exception;

    public List<Compra> findByCriteriaInCompra(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Compra> findPageCompra(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCompra() throws Exception;

    public List<CompraDTO> getDataCompra() throws Exception;

    public void validateCompra(Compra compra) throws Exception;

    public List<Display> getDisplay() throws Exception;

    public void saveDisplay(Display entity) throws Exception;

    public void deleteDisplay(Display entity) throws Exception;

    public void updateDisplay(Display entity) throws Exception;

    public Display getDisplay(Integer idDisplay) throws Exception;

    public List<Display> findByCriteriaInDisplay(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Display> findPageDisplay(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberDisplay() throws Exception;

    public List<DisplayDTO> getDataDisplay() throws Exception;

    public void validateDisplay(Display display) throws Exception;

    public List<Producto> getProducto() throws Exception;

    public void saveProducto(Producto entity) throws Exception;

    public void deleteProducto(Producto entity) throws Exception;

    public void updateProducto(Producto entity) throws Exception;

    public Producto getProducto(Integer idProducto) throws Exception;

    public List<Producto> findByCriteriaInProducto(Object[] variables,
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
