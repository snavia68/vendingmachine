package co.edu.usbcali.vendingmachine.presentation.backingBeans;

import co.edu.usbcali.vendingmachine.exceptions.*;
import co.edu.usbcali.vendingmachine.model.*;
import co.edu.usbcali.vendingmachine.model.dto.DisplayDTO;
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
public class DisplayView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DisplayView.class);
    private InputText txtIdProducto;
    private InputText txtIdDisplay;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<DisplayDTO> data;
    private DisplayDTO selectedDisplay;
    private Display entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public DisplayView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedDisplay = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedDisplay = null;

        if (txtIdProducto != null) {
            txtIdProducto.setValue(null);
            txtIdProducto.setDisabled(true);
        }

        if (txtIdDisplay != null) {
            txtIdDisplay.setValue(null);
            txtIdDisplay.setDisabled(false);
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
            Integer idDisplay = FacesUtils.checkInteger(txtIdDisplay);
            entity = (idDisplay != null)
                ? businessDelegatorView.getDisplay(idDisplay) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtIdProducto.setDisabled(false);
            txtIdDisplay.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtIdProducto.setValue(entity.getIdProducto());
            txtIdProducto.setDisabled(false);
            txtIdDisplay.setValue(entity.getIdDisplay());
            txtIdDisplay.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedDisplay = (DisplayDTO) (evt.getComponent().getAttributes()
                                           .get("selectedDisplay"));
        txtIdProducto.setValue(selectedDisplay.getIdProducto());
        txtIdProducto.setDisabled(false);
        txtIdDisplay.setValue(selectedDisplay.getIdDisplay());
        txtIdDisplay.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedDisplay == null) && (entity == null)) {
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
            entity = new Display();

            Integer idDisplay = FacesUtils.checkInteger(txtIdDisplay);

            entity.setIdDisplay(idDisplay);
            entity.setIdProducto(FacesUtils.checkInteger(txtIdProducto));
            businessDelegatorView.saveDisplay(entity);
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
                Integer idDisplay = new Integer(selectedDisplay.getIdDisplay());
                entity = businessDelegatorView.getDisplay(idDisplay);
            }

            entity.setIdProducto(FacesUtils.checkInteger(txtIdProducto));
            businessDelegatorView.updateDisplay(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedDisplay = (DisplayDTO) (evt.getComponent().getAttributes()
                                               .get("selectedDisplay"));

            Integer idDisplay = new Integer(selectedDisplay.getIdDisplay());
            entity = businessDelegatorView.getDisplay(idDisplay);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idDisplay = FacesUtils.checkInteger(txtIdDisplay);
            entity = businessDelegatorView.getDisplay(idDisplay);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteDisplay(entity);
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

    public String action_modifyWitDTO(Integer idDisplay, Integer idProducto)
        throws Exception {
        try {
            entity.setIdProducto(FacesUtils.checkInteger(idProducto));
            businessDelegatorView.updateDisplay(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("DisplayView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtIdProducto() {
        return txtIdProducto;
    }

    public void setTxtIdProducto(InputText txtIdProducto) {
        this.txtIdProducto = txtIdProducto;
    }

    public InputText getTxtIdDisplay() {
        return txtIdDisplay;
    }

    public void setTxtIdDisplay(InputText txtIdDisplay) {
        this.txtIdDisplay = txtIdDisplay;
    }

    public List<DisplayDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataDisplay();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<DisplayDTO> displayDTO) {
        this.data = displayDTO;
    }

    public DisplayDTO getSelectedDisplay() {
        return selectedDisplay;
    }

    public void setSelectedDisplay(DisplayDTO display) {
        this.selectedDisplay = display;
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
