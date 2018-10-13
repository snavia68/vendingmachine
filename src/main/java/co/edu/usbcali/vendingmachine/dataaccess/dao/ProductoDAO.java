package co.edu.usbcali.vendingmachine.dataaccess.dao;

import co.edu.usbcali.vendingmachine.dataaccess.api.JpaDaoImpl;
import co.edu.usbcali.vendingmachine.model.Producto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * A data access object (DAO) providing persistence and search support for
 * Producto entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Producto
 */
@Scope("singleton")
@Repository("ProductoDAO")
public class ProductoDAO extends JpaDaoImpl<Producto, Integer>
    implements IProductoDAO {
    private static final Logger log = LoggerFactory.getLogger(ProductoDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    public static IProductoDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IProductoDAO) ctx.getBean("ProductoDAO");
    }
    
	@Override
	public Producto buscarPorDisplay(Integer idDisplay) {
		String jpql="SELECT pro FROM Producto pro WHERE pro.display.idDisplay ="+idDisplay;
		return (Producto)entityManager.createQuery(jpql).getSingleResult();
	}

}
