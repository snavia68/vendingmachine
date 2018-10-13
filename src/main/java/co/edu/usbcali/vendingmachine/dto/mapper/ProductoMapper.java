package co.edu.usbcali.vendingmachine.dto.mapper;

import co.edu.usbcali.vendingmachine.model.*;
import co.edu.usbcali.vendingmachine.model.Producto;
import co.edu.usbcali.vendingmachine.model.control.*;
import co.edu.usbcali.vendingmachine.model.dto.ProductoDTO;

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
public class ProductoMapper implements IProductoMapper {
    private static final Logger log = LoggerFactory.getLogger(ProductoMapper.class);

    /**
    * Logic injected by Spring that manages Display entities
    *
    */
    @Autowired
    IDisplayLogic logicDisplay1;

    @Transactional(readOnly = true)
    public ProductoDTO productoToProductoDTO(Producto producto)
        throws Exception {
        try {
            ProductoDTO productoDTO = new ProductoDTO();

            productoDTO.setIdProducto(producto.getIdProducto());
            productoDTO.setCantidad((producto.getCantidad() != null)
                ? producto.getCantidad() : null);
            productoDTO.setDescripcion((producto.getDescripcion() != null)
                ? producto.getDescripcion() : null);
            productoDTO.setNombre((producto.getNombre() != null)
                ? producto.getNombre() : null);
            productoDTO.setValor((producto.getValor() != null)
                ? producto.getValor() : null);
            productoDTO.setIdDisplay_Display((producto.getDisplay()
                                                      .getIdDisplay() != null)
                ? producto.getDisplay().getIdDisplay() : null);

            return productoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Producto productoDTOToProducto(ProductoDTO productoDTO)
        throws Exception {
        try {
            Producto producto = new Producto();

            producto.setIdProducto(productoDTO.getIdProducto());
            producto.setCantidad((productoDTO.getCantidad() != null)
                ? productoDTO.getCantidad() : null);
            producto.setDescripcion((productoDTO.getDescripcion() != null)
                ? productoDTO.getDescripcion() : null);
            producto.setNombre((productoDTO.getNombre() != null)
                ? productoDTO.getNombre() : null);
            producto.setValor((productoDTO.getValor() != null)
                ? productoDTO.getValor() : null);

            Display display = new Display();

            if (productoDTO.getIdDisplay_Display() != null) {
                display = logicDisplay1.getDisplay(productoDTO.getIdDisplay_Display());
            }

            if (display != null) {
                producto.setDisplay(display);
            }

            return producto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> listProductoToListProductoDTO(
        List<Producto> listProducto) throws Exception {
        try {
            List<ProductoDTO> productoDTOs = new ArrayList<ProductoDTO>();

            for (Producto producto : listProducto) {
                ProductoDTO productoDTO = productoToProductoDTO(producto);

                productoDTOs.add(productoDTO);
            }

            return productoDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Producto> listProductoDTOToListProducto(
        List<ProductoDTO> listProductoDTO) throws Exception {
        try {
            List<Producto> listProducto = new ArrayList<Producto>();

            for (ProductoDTO productoDTO : listProductoDTO) {
                Producto producto = productoDTOToProducto(productoDTO);

                listProducto.add(producto);
            }

            return listProducto;
        } catch (Exception e) {
            throw e;
        }
    }
}
