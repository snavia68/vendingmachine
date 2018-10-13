package co.edu.usbcali.vendingmachine.dto.mapper;

import co.edu.usbcali.vendingmachine.model.Display;
import co.edu.usbcali.vendingmachine.model.dto.DisplayDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IDisplayMapper {
    public DisplayDTO displayToDisplayDTO(Display display)
        throws Exception;

    public Display displayDTOToDisplay(DisplayDTO displayDTO)
        throws Exception;

    public List<DisplayDTO> listDisplayToListDisplayDTO(List<Display> displays)
        throws Exception;

    public List<Display> listDisplayDTOToListDisplay(
        List<DisplayDTO> displayDTOs) throws Exception;
}
