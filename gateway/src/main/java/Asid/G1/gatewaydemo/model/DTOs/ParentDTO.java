package Asid.G1.gatewaydemo.model.DTOs;

import Asid.G1.gatewaydemo.model.DTOs.base.PersonEntityDTO;

import javax.validation.constraints.NotNull;

public class ParentDTO extends PersonEntityDTO {

    @NotNull
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
