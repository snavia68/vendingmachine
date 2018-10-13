package co.edu.usbcali.vendingmachine.dto.mapper;

import co.edu.usbcali.vendingmachine.model.Producto;
import co.edu.usbcali.vendingmachine.model.dto.ProductoDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IProductoMapper {
    public ProductoDTO productoToProductoDTO(Producto producto)
        throws Exception;

    public Producto productoDTOToProducto(ProductoDTO productoDTO)
        throws Exception;

    public List<ProductoDTO> listProductoToListProductoDTO(
        List<Producto> productos) throws Exception;

    public List<Producto> listProductoDTOToListProducto(
        List<ProductoDTO> productoDTOs) throws Exception;
}
