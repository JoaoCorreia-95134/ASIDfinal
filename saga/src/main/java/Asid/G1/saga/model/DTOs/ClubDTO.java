package Asid.G1.saga.model.DTOs;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClubDTO {

    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Size(min = 5, max = 500)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
