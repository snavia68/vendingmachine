package co.edu.usbcali.vendingmachine.dto.mapper;

import co.edu.usbcali.vendingmachine.model.Compra;
import co.edu.usbcali.vendingmachine.model.dto.CompraDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface ICompraMapper {
    public CompraDTO compraToCompraDTO(Compra compra) throws Exception;

    public Compra compraDTOToCompra(CompraDTO compraDTO)
        throws Exception;

    public List<CompraDTO> listCompraToListCompraDTO(List<Compra> compras)
        throws Exception;

    public List<Compra> listCompraDTOToListCompra(List<CompraDTO> compraDTOs)
        throws Exception;
}
