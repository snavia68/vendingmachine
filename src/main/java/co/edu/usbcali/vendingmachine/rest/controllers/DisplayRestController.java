package co.edu.usbcali.vendingmachine.rest.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.vendingmachine.dto.mapper.IDisplayMapper;
import co.edu.usbcali.vendingmachine.model.Display;
import co.edu.usbcali.vendingmachine.model.dto.DisplayDTO;
import co.edu.usbcali.vendingmachine.presentation.businessDelegate.IBusinessDelegatorView;

import java.util.List;


@RestController
@RequestMapping("/display")
public class DisplayRestController {
    private static final Logger log = LoggerFactory.getLogger(DisplayRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IDisplayMapper displayMapper;

    @PostMapping(value = "/saveDisplay")
    public void saveDisplay(@RequestBody
    DisplayDTO displayDTO) throws Exception {
        try {
            Display display = displayMapper.displayDTOToDisplay(displayDTO);

            businessDelegatorView.saveDisplay(display);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteDisplay/{idDisplay}")
    public void deleteDisplay(@PathVariable("idDisplay")
    Integer idDisplay) throws Exception {
        try {
            Display display = businessDelegatorView.getDisplay(idDisplay);

            businessDelegatorView.deleteDisplay(display);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateDisplay/")
    public void updateDisplay(@RequestBody
    DisplayDTO displayDTO) throws Exception {
        try {
            Display display = displayMapper.displayDTOToDisplay(displayDTO);

            businessDelegatorView.updateDisplay(display);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataDisplay")
    public List<DisplayDTO> getDataDisplay() throws Exception {
        try {
            return businessDelegatorView.getDataDisplay();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDisplay/{idDisplay}")
    public DisplayDTO getDisplay(@PathVariable("idDisplay")
    Integer idDisplay) throws Exception {
        try {
            Display display = businessDelegatorView.getDisplay(idDisplay);

            return displayMapper.displayToDisplayDTO(display);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
