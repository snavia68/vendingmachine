package co.edu.usbcali.vendingmachine.presentation.backingBeans;

import co.edu.usbcali.vendingmachine.exceptions.*;
import co.edu.usbcali.vendingmachine.model.*;
import co.edu.usbcali.vendingmachine.model.dto.ProductoDTO;
import co.edu.usbcali.vendingmachine.presentation.businessDelegate.*;
import co.edu.usbcali.vendingmachine.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ProductoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProductoView.class);
    private InputText txtCantidad;
    private InputText txtDescripcion;
    private InputText txtNombre;
    private InputText txtValor;
    private InputText txtIdDisplay_Display;
    private InputText txtIdProducto;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ProductoDTO> data;
    private ProductoDTO selectedProducto;
    private Producto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ProductoView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedProducto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedProducto = null;

        if (txtCantidad != null) {
            txtCantidad.setValue(null);
            txtCantidad.setDisabled(true);
        }

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtValor != null) {
            txtValor.setValue(null);
            txtValor.setDisabled(true);
        }

        if (txtIdDisplay_Display != null) {
            txtIdDisplay_Display.setValue(null);
            txtIdDisplay_Display.setDisabled(true);
        }

        if (txtIdProducto != null) {
            txtIdProducto.setValue(null);
            txtIdProducto.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Integer idProducto = FacesUtils.checkInteger(txtIdProducto);
            entity = (idProducto != null)
                ? businessDelegatorView.getProducto(idProducto) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCantidad.setDisabled(false);
            txtDescripcion.setDisabled(false);
            txtNombre.setDisabled(false);
            txtValor.setDisabled(false);
            txtIdDisplay_Display.setDisabled(false);
            txtIdProducto.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCantidad.setValue(entity.getCantidad());
            txtCantidad.setDisabled(false);
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtValor.setValue(entity.getValor());
            txtValor.setDisabled(false);
            txtIdDisplay_Display.setValue(entity.getDisplay().getIdDisplay());
            txtIdDisplay_Display.setDisabled(false);
            txtIdProducto.setValue(entity.getIdProducto());
            txtIdProducto.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedProducto = (ProductoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedProducto"));
        txtCantidad.setValue(selectedProducto.getCantidad());
        txtCantidad.setDisabled(false);
        txtDescripcion.setValue(selectedProducto.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtNombre.setValue(selectedProducto.getNombre());
        txtNombre.setDisabled(false);
        txtValor.setValue(selectedProducto.getValor());
        txtValor.setDisabled(false);
        txtIdDisplay_Display.setValue(selectedProducto.getIdDisplay_Display());
        txtIdDisplay_Display.setDisabled(false);
        txtIdProducto.setValue(selectedProducto.getIdProducto());
        txtIdProducto.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedProducto == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new Producto();

            Integer idProducto = FacesUtils.checkInteger(txtIdProducto);

            entity.setCantidad(FacesUtils.checkInteger(txtCantidad));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setIdProducto(idProducto);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setValor(FacesUtils.checkInteger(txtValor));
            entity.setDisplay((FacesUtils.checkInteger(txtIdDisplay_Display) != null)
                ? businessDelegatorView.getDisplay(FacesUtils.checkInteger(
                        txtIdDisplay_Display)) : null);
            businessDelegatorView.saveProducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Integer idProducto = new Integer(selectedProducto.getIdProducto());
                entity = businessDelegatorView.getProducto(idProducto);
            }

            entity.setCantidad(FacesUtils.checkInteger(txtCantidad));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setValor(FacesUtils.checkInteger(txtValor));
            entity.setDisplay((FacesUtils.checkInteger(txtIdDisplay_Display) != null)
                ? businessDelegatorView.getDisplay(FacesUtils.checkInteger(
                        txtIdDisplay_Display)) : null);
            businessDelegatorView.updateProducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedProducto = (ProductoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedProducto"));

            Integer idProducto = new Integer(selectedProducto.getIdProducto());
            entity = businessDelegatorView.getProducto(idProducto);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idProducto = FacesUtils.checkInteger(txtIdProducto);
            entity = businessDelegatorView.getProducto(idProducto);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteProducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String action_modifyWitDTO(Integer cantidad, String descripcion,
        Integer idProducto, String nombre, Integer valor,
        Integer idDisplay_Display) throws Exception {
        try {
            entity.setCantidad(FacesUtils.checkInteger(cantidad));
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setValor(FacesUtils.checkInteger(valor));
            businessDelegatorView.updateProducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ProductoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtCantidad(InputText txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(InputText txtValor) {
        this.txtValor = txtValor;
    }

    public InputText getTxtIdDisplay_Display() {
        return txtIdDisplay_Display;
    }

    public void setTxtIdDisplay_Display(InputText txtIdDisplay_Display) {
        this.txtIdDisplay_Display = txtIdDisplay_Display;
    }

    public InputText getTxtIdProducto() {
        return txtIdProducto;
    }

    public void setTxtIdProducto(InputText txtIdProducto) {
        this.txtIdProducto = txtIdProducto;
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

    public void setData(List<ProductoDTO> productoDTO) {
        this.data = productoDTO;
    }

    public ProductoDTO getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(ProductoDTO producto) {
        this.selectedProducto = producto;
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

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
