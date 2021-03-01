package it.academy.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_VISITOR_COUNT")
@Data
public class VisitorCount {

    @Id
    private Integer id;

    @Column
    private Integer count;
}
