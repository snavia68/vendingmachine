package co.edu.usbcali.vendingmachine.dataaccess.dao;

import co.edu.usbcali.vendingmachine.dataaccess.api.Dao;
import co.edu.usbcali.vendingmachine.model.Producto;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* Interface for   ProductoDAO.
*
*/
public interface IProductoDAO extends Dao<Producto, Integer> {
	Producto buscarPorDisplay(Integer idDisplay);
}
