package com.space_booker.model;

import com.space_booker.view.IModelListener;

import java.util.ArrayList;

/*
Model class for data used in interactions
 */
public class InteractionModel {
    private Booking adminSelectedRequest;
    private boolean _switchToAdminViewFlag = false;
    private boolean _switchToBookerViewFlag = false;
    private boolean _switchToCreateAdminViewFlag = false;

    private boolean _switchToLoginViewFlag = false;
    private ArrayList<IModelListener> subscribers;

    public InteractionModel() {
        subscribers = new ArrayList<>();
    }

    public void setAdminSelectedRequest(Booking r) {adminSelectedRequest = r;}
    public Booking getAdminSelectedRequest() {return adminSelectedRequest;}
    public boolean ShouldSwitchToAdminView() {
        return _switchToAdminViewFlag;
    }

    public boolean ShouldSwitchToBookerView() {
        return _switchToBookerViewFlag;
    }

    public boolean ShouldSwitchToCreateAdminView(){
        return _switchToCreateAdminViewFlag;
    }

    public boolean ShouldSwitchToLoginView(){
        return _switchToLoginViewFlag;
    }

    public void TriggerSwitchToAdminView(){
        _switchToAdminViewFlag = true;
        this.notifySubscribers();
        _switchToAdminViewFlag = false;
    }
    public void TriggerSwitchToBookerView() {
        _switchToBookerViewFlag = true;
        this.notifySubscribers();
        _switchToBookerViewFlag = false;
    }
    public void TriggerSwitchToCreateAdminView(){
        _switchToCreateAdminViewFlag = true;
        this.notifySubscribers();
        _switchToCreateAdminViewFlag = false;
    }
    public void TriggerSwitchToLoginView(){
        _switchToLoginViewFlag = true;
        this.notifySubscribers();
        _switchToLoginViewFlag = false;
    }
    public void addSubscriber(IModelListener sub) {subscribers.add(sub);}
    public void notifySubscribers() {
        subscribers.forEach(IModelListener::iModelChanged);
    }


}
