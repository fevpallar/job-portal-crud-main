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
@Table(name = "lowongan")
public class Lowongan implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @Column( unique = true)
    private String kode;
    private String posisi;
    private String industri;
    private String deskripsi;
    private String kualifikasi;
}
