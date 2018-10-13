package co.edu.usbcali.vendingmachine.model.control;

import co.edu.usbcali.vendingmachine.model.Display;
import co.edu.usbcali.vendingmachine.model.dto.DisplayDTO;

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
public interface IDisplayLogic {
    public List<Display> getDisplay() throws Exception;

    /**
         * Save an new Display entity
         */
    public void saveDisplay(Display entity) throws Exception;

    /**
         * Delete an existing Display entity
         *
         */
    public void deleteDisplay(Display entity) throws Exception;

    /**
        * Update an existing Display entity
        *
        */
    public void updateDisplay(Display entity) throws Exception;

    /**
         * Load an existing Display entity
         *
         */
    public Display getDisplay(Integer idDisplay) throws Exception;

    public List<Display> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Display> findPageDisplay(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberDisplay() throws Exception;

    public List<DisplayDTO> getDataDisplay() throws Exception;

    public void validateDisplay(Display display) throws Exception;
}
