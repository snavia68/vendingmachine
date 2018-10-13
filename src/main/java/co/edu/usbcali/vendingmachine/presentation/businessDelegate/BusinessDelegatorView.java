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
import co.edu.usbcali.vendingmachine.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.sql.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private ICompraLogic compraLogic;
    @Autowired
    private IDisplayLogic displayLogic;
    @Autowired
    private IProductoLogic productoLogic;

    public List<Compra> getCompra() throws Exception {
        return compraLogic.getCompra();
    }

    public void saveCompra(Compra entity) throws Exception {
        compraLogic.saveCompra(entity);
    }

    public void deleteCompra(Compra entity) throws Exception {
        compraLogic.deleteCompra(entity);
    }

    public void updateCompra(Compra entity) throws Exception {
        compraLogic.updateCompra(entity);
    }

    public Compra getCompra(Integer idCompra) throws Exception {
        Compra compra = null;

        try {
            compra = compraLogic.getCompra(idCompra);
        } catch (Exception e) {
            throw e;
        }

        return compra;
    }

    public List<Compra> findByCriteriaInCompra(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return compraLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Compra> findPageCompra(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return compraLogic.findPageCompra(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberCompra() throws Exception {
        return compraLogic.findTotalNumberCompra();
    }

    public List<CompraDTO> getDataCompra() throws Exception {
        return compraLogic.getDataCompra();
    }

    public void validateCompra(Compra compra) throws Exception {
        compraLogic.validateCompra(compra);
    }

    public List<Display> getDisplay() throws Exception {
        return displayLogic.getDisplay();
    }

    public void saveDisplay(Display entity) throws Exception {
        displayLogic.saveDisplay(entity);
    }

    public void deleteDisplay(Display entity) throws Exception {
        displayLogic.deleteDisplay(entity);
    }

    public void updateDisplay(Display entity) throws Exception {
        displayLogic.updateDisplay(entity);
    }

    public Display getDisplay(Integer idDisplay) throws Exception {
        Display display = null;

        try {
            display = displayLogic.getDisplay(idDisplay);
        } catch (Exception e) {
            throw e;
        }

        return display;
    }

    public List<Display> findByCriteriaInDisplay(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return displayLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Display> findPageDisplay(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return displayLogic.findPageDisplay(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberDisplay() throws Exception {
        return displayLogic.findTotalNumberDisplay();
    }

    public List<DisplayDTO> getDataDisplay() throws Exception {
        return displayLogic.getDataDisplay();
    }

    public void validateDisplay(Display display) throws Exception {
        displayLogic.validateDisplay(display);
    }

    public List<Producto> getProducto() throws Exception {
        return productoLogic.getProducto();
    }

    public void saveProducto(Producto entity) throws Exception {
        productoLogic.saveProducto(entity);
    }

    public void deleteProducto(Producto entity) throws Exception {
        productoLogic.deleteProducto(entity);
    }

    public void updateProducto(Producto entity) throws Exception {
        productoLogic.updateProducto(entity);
    }

    public Producto getProducto(Integer idProducto) throws Exception {
        Producto producto = null;

        try {
            producto = productoLogic.getProducto(idProducto);
        } catch (Exception e) {
            throw e;
        }

        return producto;
    }

    public List<Producto> findByCriteriaInProducto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return productoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Producto> findPageProducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return productoLogic.findPageProducto(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberProducto() throws Exception {
        return productoLogic.findTotalNumberProducto();
    }

    public List<ProductoDTO> getDataProducto() throws Exception {
        return productoLogic.getDataProducto();
    }

    public void validateProducto(Producto producto) throws Exception {
        productoLogic.validateProducto(producto);
    }

	@Override
	public Producto  buscarPorDisplay(Integer display) throws Exception {
		return productoLogic.buscarPorDisplay(display);
	}
}
