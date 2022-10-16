package com.fevly.jobportal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lamaran")
public class Lamaran {

    @Id
    @GeneratedValue
    private int id;
    @Column( unique = true)
    private String kode;
    private String kode_lowongan;
    private String id_pelamar;

    private String kode_perusahaan;
    @Column(name = "tanggal_melamar", nullable = false, updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tanggal_melamar;
    @CreationTimestamp
    @JsonFormat(pattern = "HH:mm:ss")
    private Date jam_melamar;
    private boolean sudah_wawancara;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tanggal_wawancara;
    @JsonFormat(pattern = "HH:mm:ss")
    private Date jam_wawancara;
}
