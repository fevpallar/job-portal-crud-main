package com.fevly.jobportal.repository;


import com.fevly.jobportal.entity.Lamaran;
import com.fevly.jobportal.entity.Lowongan;
import com.fevly.jobportal.entity.Pelamar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.sql.SQLException;
import java.util.List;

public interface PelamarRepository extends JpaRepository<Pelamar, String> {

    @Query(value = "select * from pelamar where noktp=:noktp", nativeQuery = true)
    public Pelamar findByCodePelamar(String noktp);

    @Query(value = "select * from pelamar", nativeQuery = true)
    public List<Pelamar> getAllPelamar();

}
