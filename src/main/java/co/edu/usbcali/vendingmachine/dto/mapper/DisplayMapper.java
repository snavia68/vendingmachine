package co.edu.usbcali.vendingmachine.dto.mapper;

import co.edu.usbcali.vendingmachine.model.*;
import co.edu.usbcali.vendingmachine.model.Display;
import co.edu.usbcali.vendingmachine.model.control.*;
import co.edu.usbcali.vendingmachine.model.dto.DisplayDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Component
@Scope("singleton")
public class DisplayMapper implements IDisplayMapper {
    private static final Logger log = LoggerFactory.getLogger(DisplayMapper.class);

    @Transactional(readOnly = true)
    public DisplayDTO displayToDisplayDTO(Display display)
        throws Exception {
        try {
            DisplayDTO displayDTO = new DisplayDTO();

            displayDTO.setIdDisplay(display.getIdDisplay());
            displayDTO.setIdProducto((display.getIdProducto() != null)
                ? display.getIdProducto() : null);

            return displayDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Display displayDTOToDisplay(DisplayDTO displayDTO)
        throws Exception {
        try {
            Display display = new Display();

            display.setIdDisplay(displayDTO.getIdDisplay());
            display.setIdProducto((displayDTO.getIdProducto() != null)
                ? displayDTO.getIdProducto() : null);

            return display;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<DisplayDTO> listDisplayToListDisplayDTO(
        List<Display> listDisplay) throws Exception {
        try {
            List<DisplayDTO> displayDTOs = new ArrayList<DisplayDTO>();

            for (Display display : listDisplay) {
                DisplayDTO displayDTO = displayToDisplayDTO(display);

                displayDTOs.add(displayDTO);
            }

            return displayDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Display> listDisplayDTOToListDisplay(
        List<DisplayDTO> listDisplayDTO) throws Exception {
        try {
            List<Display> listDisplay = new ArrayList<Display>();

            for (DisplayDTO displayDTO : listDisplayDTO) {
                Display display = displayDTOToDisplay(displayDTO);

                listDisplay.add(display);
            }

            return listDisplay;
        } catch (Exception e) {
            throw e;
        }
    }
}
