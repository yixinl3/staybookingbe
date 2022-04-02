package com.project.staybooking.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class StayReservedDateKey implements Serializable {
    private Long stay_id;

    private LocalDate date;

    public StayReservedDateKey() {

    }

    public StayReservedDateKey(Long stay_id, LocalDate date) {
        this.stay_id = stay_id;
        this.date = date;

    }

    public Long getStay_id() {
        return stay_id;
    }

    public void setStay_id(Long stay_id) {
        this.stay_id = stay_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StayReservedDateKey that = (StayReservedDateKey) o;
        return stay_id.equals(that.stay_id) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stay_id, date);
    }
}
