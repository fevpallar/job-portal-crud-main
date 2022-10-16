package com.fevly.jobportal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pelamar")
public class Pelamar implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Column( unique = true)
    private String noktp;
    private String nama;
    private byte usia;
    private String alamat;
}
