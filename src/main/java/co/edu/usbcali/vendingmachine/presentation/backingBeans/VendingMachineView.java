package co.edu.usbcali.vendingmachine.presentation.backingBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panel.Panel;

import co.edu.usbcali.vendingmachine.model.Compra;
import co.edu.usbcali.vendingmachine.model.Display;
import co.edu.usbcali.vendingmachine.model.Producto;
import co.edu.usbcali.vendingmachine.model.dto.ProductoDTO;
import co.edu.usbcali.vendingmachine.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.vendingmachine.utilities.FacesUtils;

@ManagedBean
@ViewScoped
public class VendingMachineView {
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	
	private List<Producto> losProductos;
	
	private Panel panelProductos;
	
	static Integer acumulado = 0;
	
	private InputText txtCantidad;
	private InputText txtNombre;
	private InputText txtDescripcion;
	private InputText txtValor;
	private InputText txtCodigoProducto;
	
	private OutputLabel saldoCompra;
	
	private Display disp;
	
	private Integer saldo = 0;
	
	private List<ProductoDTO> data;
	
	private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnClear;
    private CommandButton btnCien;
    private CommandButton btnDocientos;
    private CommandButton btnQuinientos;
    private CommandButton btnMil;
    
    public String action_create() throws Exception{
    	
    	disp = new Display();    	
    	disp.setIdProducto(0);
    	
    	businessDelegatorView.saveDisplay(disp);
    	
    	Producto producto = new Producto();
    	producto.setDisplay(disp);
    	producto.setCantidad(Integer.parseInt(txtCantidad.getValue().toString()));
    	producto.setDescripcion(txtDescripcion.getValue().toString().trim());
    	producto.setNombre(txtNombre.getValue().toString().trim());
    	producto.setValor(Integer.parseInt(txtValor.getValue().toString()));
    	
    	businessDelegatorView.saveProducto(producto);
    	
    	FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto agregado con éxito!", "El producto ha sido guardado en la máquina"));
		
    	
    	return "";
    }
    
    public void compra_producto() throws NumberFormatException, Exception{
    
    	if (txtCodigoProducto.getValue().toString().equals("123456")) {
			panelProductos.setVisible(true);
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido Staff", "Se ha ingresado el código de administración del staff"));
		}
    	try {
    	
    	Integer saldoCliente = Integer.parseInt(saldoCompra.getValue().toString());	
    	
    	
    	Producto producto = businessDelegatorView.buscarPorDisplay(Integer.parseInt(txtCodigoProducto.getValue().toString()));
    	
    	
    	if (businessDelegatorView.buscarPorDisplay(Integer.parseInt(txtCodigoProducto.getValue().toString()))==null) {
    		
    		FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto no encontrado", "No existe el producto especificado"));
    		throw new Exception("Producto no existe");
		}
    	
    	if (producto.getCantidad() == 0) {
    		FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto no disponible", "No hay producto en stock"));
	
		}
    	if (saldoCliente < producto.getValor() ) {
    		
    		FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Saldo insuficiente", "Saldo insuficiente para el producto seleccionado"));
    		throw new Exception("Saldo insuficiente");
		}
    	
    	Integer cantidadProducto = producto.getCantidad()-1;
    	producto.setCantidad(cantidadProducto);
    	
     	if (acumulado == 0) {
     		VendingMachineView.acumulado = producto.getValor();
     		FacesUtils.putinSession("acumulado", acumulado);
		} else {
			Integer sumarAcumulado = Integer.parseInt(FacesUtils.getfromSession("acumulado").toString());
			sumarAcumulado = sumarAcumulado + producto.getValor();
			FacesUtils.putinSession("acumulado", sumarAcumulado);
		}
    	
    	
    	businessDelegatorView.updateProducto(producto);
    	
    	Compra compra = new Compra();
    	compra.setProducto(producto);
    	
    	if (saldoCliente > producto.getValor() ) {
    		saldoCliente = saldoCliente - producto.getValor();
    		saldoCompra.resetValue();
    		
    		FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Su devuelta es: $"+saldoCliente, "Total: "+saldoCliente));
    		
	
		}
    	
    	businessDelegatorView.saveCompra(compra);
    	FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha dispensado el producto: "+producto.getNombre(), "Producto dispensado: "));
    				
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto no encontrado", "No existe el producto especificado"));
	
		}
    	
    }
    
    public Integer sumar_saldo(Integer valorMoneda){
    	
    	saldo = saldo + valorMoneda;
    	
    	saldoCompra.setValue(saldo);
    	
    	return saldo;
    }
    
	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(CommandButton btnModify) {
		this.btnModify = btnModify;
	}

	public CommandButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(CommandButton btnClear) {
		this.btnClear = btnClear;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public List<Producto> getLosProductos() {
		return losProductos;
	}

	public void setLosProductos(List<Producto> losProductos) {
		this.losProductos = losProductos;
	}
	public InputText getTxtCantidad() {
		return txtCantidad;
	}
	public void setTxtCantidad(InputText txtCantidad) {
		this.txtCantidad = txtCantidad;
	}
	public InputText getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}
	public InputText getTxtDescripcion() {
		return txtDescripcion;
	}
	public void setTxtDescripcion(InputText txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}
	public InputText getTxtValor() {
		return txtValor;
	}
	public void setTxtValor(InputText txtValor) {
		this.txtValor = txtValor;
	}
	public CommandButton getBtnCien() {
		return btnCien;
	}
	public void setBtnCien(CommandButton btnCien) {
		this.btnCien = btnCien;
	}
	public CommandButton getBtnDocientos() {
		return btnDocientos;
	}
	public void setBtnDocientos(CommandButton btnDocientos) {
		this.btnDocientos = btnDocientos;
	}
	public CommandButton getBtnQuinientos() {
		return btnQuinientos;
	}
	public void setBtnQuinientos(CommandButton btnQuinientos) {
		this.btnQuinientos = btnQuinientos;
	}
	public CommandButton getBtnMil() {
		return btnMil;
	}
	public void setBtnMil(CommandButton btnMil) {
		this.btnMil = btnMil;
	}
	public InputText getTxtCodigoProducto() {
		return txtCodigoProducto;
	}
	public void setTxtCodigoProducto(InputText txtCodigoProducto) {
		this.txtCodigoProducto = txtCodigoProducto;
	}
	public List<ProductoDTO> getData() {
		try {
            if (data == null) {
                data = businessDelegatorView.getDataProducto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
	}
	public void setData(List<ProductoDTO> data) {
		this.data = data;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	public OutputLabel getSaldoCompra() {
		return saldoCompra;
	}

	public void setSaldoCompra(OutputLabel saldoCompra) {
		this.saldoCompra = saldoCompra;
	}

	public Panel getPanelProductos() {
		return panelProductos;
	}

	public void setPanelProductos(Panel panelProductos) {
		this.panelProductos = panelProductos;
	}
	
	
	
	
}
