package com.fevly.jobportal.repository;

import com.fevly.jobportal.entity.Lamaran;
import com.fevly.jobportal.entity.Pelamar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface LamaranRepository extends JpaRepository<Lamaran, String> {
    @Query(value = " INSERT INTO lamaran " +
            "(id_pelamar,kode,kode_lowongan,kode_perusahaan,tanggal_melamar,jam_melamar,sudah_wawancara,tanggal_wawancara,jam_wawancara) " +
            "VALUES (?1,?2,?3,?4,CURDATE(), CURRENT_TIME(), ?5,?6,?7)", nativeQuery = true)
    public  void insertLamaran(String id_pelamar, String kode, String kode_lowongan,
                              String kode_perusahaan, boolean sudah_wawancara, Date tanggal_wawancara,
                              Date jam_wawancara);

    @Query(value = "select * from lamaran where kode=:kode", nativeQuery = true)
    public Lamaran findByCodeLamaran(String kode);

    @Query(value = "select * from lamaran", nativeQuery = true)
    public List<Lamaran> getAllLamaran();

    @Query(value = "SELECT  lm.kode AS kode_lamaran,lm.id_pelamar, COUNT(id_pelamar) AS total_tidak_hadir," +
            "lm.kode_lowongan ,lm.tanggal_wawancara, lm.jam_wawancara " +
            "FROM lamaran lm " +
            "WHERE (UNIX_TIMESTAMP(lm.tanggal_wawancara)*1000) - (UNIX_TIMESTAMP(CURDATE())*1000) <0", nativeQuery = true)
    public List<Object[]> findAllNoShowInterview();
}
