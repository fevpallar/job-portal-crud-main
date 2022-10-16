package com.fevly.jobportal.repository;

import com.fevly.jobportal.entity.Lowongan;
import com.fevly.jobportal.entity.Perusahaan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.SQLException;
import java.util.List;

public interface LowonganRepository extends JpaRepository<Lowongan, String> {
    @Query(value = "select * from lowongan where kode=:kode", nativeQuery = true)
    public Lowongan findByCodeLowongan(String kode) ;

    @Query(value = "select * from lowongan", nativeQuery = true)
    public List<Lowongan> getAllLowongan() ;
}
