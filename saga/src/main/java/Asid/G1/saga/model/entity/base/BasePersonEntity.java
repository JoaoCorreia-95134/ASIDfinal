package Asid.G1.saga.model.entity.base;

import Asid.G1.saga.model.entity.enums.GenderEnum;
import Asid.G1.saga.model.entity.Town;

import javax.persistence.*;

@MappedSuperclass
public abstract class BasePersonEntity extends BaseEntityWithIdLong {

    public BasePersonEntity() {
        // Default constructor
    }

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "middleName", nullable = false)
    private String middleName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String EGN;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(name = "town")
    private Long townId;

    @Column
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEGN(String EGN) {
        this.EGN = EGN;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTown(Long townId) {
        this.townId = townId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(GenderEnum genderEnum) {
        this.gender = genderEnum;
    }


    public GenderEnum getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEGN() {
        return EGN;
    }

    public int getAge() {
        return age;
    }

    public Long getTown() {
        return townId;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();

        info.append("First name: ").append(firstName).append(System.lineSeparator())
                .append("Middle name: ").append(middleName).append(System.lineSeparator())
                .append("Last name: ").append(lastName).append(System.lineSeparator())
                .append("EGN: ").append(EGN).append(System.lineSeparator())
                .append("Age: ").append(age).append(System.lineSeparator())
                .append("Gender: ").append(gender).append(System.lineSeparator())
                .append("Town: ").append(townId).append(System.lineSeparator())
                .append("Email: ").append(email);

        return info.toString();

    }
}
