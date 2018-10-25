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

import co.edu.usbcali.vendingmachine.dto.mapper.ICompraMapper;
import co.edu.usbcali.vendingmachine.model.Compra;
import co.edu.usbcali.vendingmachine.model.dto.CompraDTO;
import co.edu.usbcali.vendingmachine.presentation.businessDelegate.IBusinessDelegatorView;

import java.util.List;


@RestController
@RequestMapping("/compra")
public class CompraRestController {
    private static final Logger log = LoggerFactory.getLogger(CompraRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private ICompraMapper compraMapper;

    @PostMapping(value = "/saveCompra")
    public void saveCompra(@RequestBody
    CompraDTO compraDTO) throws Exception {
        try {
            Compra compra = compraMapper.compraDTOToCompra(compraDTO);

            businessDelegatorView.saveCompra(compra);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteCompra/{idCompra}")
    public void deleteCompra(@PathVariable("idCompra")
    Integer idCompra) throws Exception {
        try {
            Compra compra = businessDelegatorView.getCompra(idCompra);

            businessDelegatorView.deleteCompra(compra);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateCompra/")
    public void updateCompra(@RequestBody
    CompraDTO compraDTO) throws Exception {
        try {
            Compra compra = compraMapper.compraDTOToCompra(compraDTO);

            businessDelegatorView.updateCompra(compra);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataCompra")
    public List<CompraDTO> getDataCompra() throws Exception {
        try {
            return businessDelegatorView.getDataCompra();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getCompra/{idCompra}")
    public CompraDTO getCompra(@PathVariable("idCompra")
    Integer idCompra) throws Exception {
        try {
            Compra compra = businessDelegatorView.getCompra(idCompra);

            return compraMapper.compraToCompraDTO(compra);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
