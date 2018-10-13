package co.edu.usbcali.vendingmachine.dto.mapper;

import co.edu.usbcali.vendingmachine.model.*;
import co.edu.usbcali.vendingmachine.model.Compra;
import co.edu.usbcali.vendingmachine.model.control.*;
import co.edu.usbcali.vendingmachine.model.dto.CompraDTO;

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
public class CompraMapper implements ICompraMapper {
    private static final Logger log = LoggerFactory.getLogger(CompraMapper.class);

    /**
    * Logic injected by Spring that manages Producto entities
    *
    */
    @Autowired
    IProductoLogic logicProducto1;

    @Transactional(readOnly = true)
    public CompraDTO compraToCompraDTO(Compra compra) throws Exception {
        try {
            CompraDTO compraDTO = new CompraDTO();

            compraDTO.setIdCompra(compra.getIdCompra());
            compraDTO.setIdProducto_Producto((compra.getProducto()
                                                    .getIdProducto() != null)
                ? compra.getProducto().getIdProducto() : null);

            return compraDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Compra compraDTOToCompra(CompraDTO compraDTO)
        throws Exception {
        try {
            Compra compra = new Compra();

            compra.setIdCompra(compraDTO.getIdCompra());

            Producto producto = new Producto();

            if (compraDTO.getIdProducto_Producto() != null) {
                producto = logicProducto1.getProducto(compraDTO.getIdProducto_Producto());
            }

            if (producto != null) {
                compra.setProducto(producto);
            }

            return compra;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<CompraDTO> listCompraToListCompraDTO(List<Compra> listCompra)
        throws Exception {
        try {
            List<CompraDTO> compraDTOs = new ArrayList<CompraDTO>();

            for (Compra compra : listCompra) {
                CompraDTO compraDTO = compraToCompraDTO(compra);

                compraDTOs.add(compraDTO);
            }

            return compraDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Compra> listCompraDTOToListCompra(List<CompraDTO> listCompraDTO)
        throws Exception {
        try {
            List<Compra> listCompra = new ArrayList<Compra>();

            for (CompraDTO compraDTO : listCompraDTO) {
                Compra compra = compraDTOToCompra(compraDTO);

                listCompra.add(compra);
            }

            return listCompra;
        } catch (Exception e) {
            throw e;
        }
    }
}
