package com.project.staybooking.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stay_reserved_date")
public class StayReservedDate implements Serializable {
    private static final long serialVersionUID = 1L;


    @EmbeddedId
    private StayReservedDateKey stayReservedDateKey;


    @MapsId("stay_id")
    @ManyToOne
    private Stay stay;

    public StayReservedDate() {

    }

    public StayReservedDate(StayReservedDateKey stayReservedDateKey, Stay stay) {
        this.stayReservedDateKey = stayReservedDateKey;
        this.stay = stay;
    }

    public StayReservedDateKey getStayReservedDateKey() {
        return stayReservedDateKey;
    }

    public Stay getStay() {
        return stay;
    }


}
