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

import co.edu.usbcali.vendingmachine.dto.mapper.IProductoMapper;
import co.edu.usbcali.vendingmachine.model.Compra;
import co.edu.usbcali.vendingmachine.model.Producto;
import co.edu.usbcali.vendingmachine.model.dto.ProductoDTO;
import co.edu.usbcali.vendingmachine.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.vendingmachine.utilities.FacesUtils;

import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/producto")
public class ProductoRestController {
    private static final Logger log = LoggerFactory.getLogger(ProductoRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IProductoMapper productoMapper;
    
    List<Compra> lasCompras;
    
    @PostMapping(value = "/saveProducto")
    public void saveProducto(@RequestBody
    ProductoDTO productoDTO) throws Exception {
        try {
            Producto producto = productoMapper.productoDTOToProducto(productoDTO);

            businessDelegatorView.saveProducto(producto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteProducto/{idProducto}")
    public void deleteProducto(@PathVariable("idProducto")
    Integer idProducto) throws Exception {
        try {
            Producto producto = businessDelegatorView.getProducto(idProducto);

            businessDelegatorView.deleteProducto(producto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateProducto/")
    public void updateProducto(@RequestBody
    ProductoDTO productoDTO) throws Exception {
        try {
            Producto producto = productoMapper.productoDTOToProducto(productoDTO);

            businessDelegatorView.updateProducto(producto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataProducto")
    public List<ProductoDTO> getDataProducto() throws Exception {
        try {
            return businessDelegatorView.getDataProducto();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
    
    @GetMapping(value = "/getExistenciaProducto/{idProducto}")
    public Integer getExistenciaProducto(@PathVariable("idProducto") Integer idProducto)throws Exception{
    	try {
    		Producto producto = businessDelegatorView.getProducto(idProducto);
    		
    		return producto.getCantidad();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    	return 0;
    }

    @GetMapping(value = "/getProducto/{idProducto}")
    public ProductoDTO getProducto(@PathVariable("idProducto") Integer idProducto) throws Exception {
        try {
            Producto producto = businessDelegatorView.getProducto(idProducto);

            return productoMapper.productoToProductoDTO(producto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
    
    @GetMapping(value = "/getAcumulado")
    public Integer getAcumulado() throws Exception{
    	
    	lasCompras = businessDelegatorView.getCompra();
    	Integer valorTotal = 0;
    	for (Compra compra : lasCompras) {
			Producto producto = businessDelegatorView.getProducto(compra.getProducto().getIdProducto());
			valorTotal+=producto.getValor();
		}    	
    	return valorTotal;
    }
    
    
}
