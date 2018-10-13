package co.edu.usbcali.vendingmachine.presentation.backingBeans;

import co.edu.usbcali.vendingmachine.exceptions.*;
import co.edu.usbcali.vendingmachine.model.*;
import co.edu.usbcali.vendingmachine.model.dto.CompraDTO;
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
public class CompraView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CompraView.class);
    private InputText txtIdProducto_Producto;
    private InputText txtIdCompra;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<CompraDTO> data;
    private CompraDTO selectedCompra;
    private Compra entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CompraView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedCompra = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCompra = null;

        if (txtIdProducto_Producto != null) {
            txtIdProducto_Producto.setValue(null);
            txtIdProducto_Producto.setDisabled(true);
        }

        if (txtIdCompra != null) {
            txtIdCompra.setValue(null);
            txtIdCompra.setDisabled(false);
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
            Integer idCompra = FacesUtils.checkInteger(txtIdCompra);
            entity = (idCompra != null)
                ? businessDelegatorView.getCompra(idCompra) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtIdProducto_Producto.setDisabled(false);
            txtIdCompra.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtIdProducto_Producto.setValue(entity.getProducto().getIdProducto());
            txtIdProducto_Producto.setDisabled(false);
            txtIdCompra.setValue(entity.getIdCompra());
            txtIdCompra.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedCompra = (CompraDTO) (evt.getComponent().getAttributes()
                                         .get("selectedCompra"));
        txtIdProducto_Producto.setValue(selectedCompra.getIdProducto_Producto());
        txtIdProducto_Producto.setDisabled(false);
        txtIdCompra.setValue(selectedCompra.getIdCompra());
        txtIdCompra.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedCompra == null) && (entity == null)) {
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
            entity = new Compra();

            Integer idCompra = FacesUtils.checkInteger(txtIdCompra);

            entity.setIdCompra(idCompra);
            entity.setProducto((FacesUtils.checkInteger(txtIdProducto_Producto) != null)
                ? businessDelegatorView.getProducto(FacesUtils.checkInteger(
                        txtIdProducto_Producto)) : null);
            businessDelegatorView.saveCompra(entity);
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
                Integer idCompra = new Integer(selectedCompra.getIdCompra());
                entity = businessDelegatorView.getCompra(idCompra);
            }

            entity.setProducto((FacesUtils.checkInteger(txtIdProducto_Producto) != null)
                ? businessDelegatorView.getProducto(FacesUtils.checkInteger(
                        txtIdProducto_Producto)) : null);
            businessDelegatorView.updateCompra(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCompra = (CompraDTO) (evt.getComponent().getAttributes()
                                             .get("selectedCompra"));

            Integer idCompra = new Integer(selectedCompra.getIdCompra());
            entity = businessDelegatorView.getCompra(idCompra);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idCompra = FacesUtils.checkInteger(txtIdCompra);
            entity = businessDelegatorView.getCompra(idCompra);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCompra(entity);
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

    public String action_modifyWitDTO(Integer idCompra,
        Integer idProducto_Producto) throws Exception {
        try {
            businessDelegatorView.updateCompra(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("CompraView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtIdProducto_Producto() {
        return txtIdProducto_Producto;
    }

    public void setTxtIdProducto_Producto(InputText txtIdProducto_Producto) {
        this.txtIdProducto_Producto = txtIdProducto_Producto;
    }

    public InputText getTxtIdCompra() {
        return txtIdCompra;
    }

    public void setTxtIdCompra(InputText txtIdCompra) {
        this.txtIdCompra = txtIdCompra;
    }

    public List<CompraDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCompra();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<CompraDTO> compraDTO) {
        this.data = compraDTO;
    }

    public CompraDTO getSelectedCompra() {
        return selectedCompra;
    }

    public void setSelectedCompra(CompraDTO compra) {
        this.selectedCompra = compra;
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
