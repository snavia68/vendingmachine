package co.edu.usbcali.vendingmachine.dataaccess.dao;

import co.edu.usbcali.vendingmachine.dataaccess.api.JpaDaoImpl;
import co.edu.usbcali.vendingmachine.model.Display;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * A data access object (DAO) providing persistence and search support for
 * Display entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Display
 */
@Scope("singleton")
@Repository("DisplayDAO")
public class DisplayDAO extends JpaDaoImpl<Display, Integer>
    implements IDisplayDAO {
    private static final Logger log = LoggerFactory.getLogger(DisplayDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    public static IDisplayDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IDisplayDAO) ctx.getBean("DisplayDAO");
    }
}
