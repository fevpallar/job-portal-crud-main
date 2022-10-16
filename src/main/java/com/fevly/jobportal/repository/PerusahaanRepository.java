package com.fevly.jobportal.repository;

import com.fevly.jobportal.entity.Pelamar;
import com.fevly.jobportal.entity.Perusahaan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.sql.SQLException;
import java.util.List;

public interface PerusahaanRepository extends JpaRepository<Perusahaan, String> {
    @Query(value = "select * from perusahaan where kode=:kode", nativeQuery = true)
    public Perusahaan findByCodePerusahaan(String kode);

    @Query(value = "select * from perusahaan", nativeQuery = true)
    public List<Perusahaan> getAllPerusahaan();
}
