package com.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(
        name = "screens"
)
public class Screen extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;
    @Enumerated(EnumType.ORDINAL)
    private Feature feature;
    @ManyToOne
    private Theatre theatre;
}
