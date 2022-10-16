package com.fevly.jobportal.controller;

import com.fevly.jobportal.entity.Lamaran;
import com.fevly.jobportal.entity.Lowongan;
import com.fevly.jobportal.entity.Pelamar;
import com.fevly.jobportal.entity.Perusahaan;
import com.fevly.jobportal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/portal")
public class PortalController {

    @Autowired
    private PortalService service;

    @GetMapping("/getall/lamaran")
    public ResponseEntity<?> getAllLamaran() {
        List<Lamaran> listLamaran = service.getAllLamaran();
        if (listLamaran == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<List<Lamaran>>(listLamaran, headers, HttpStatus.OK);
    }

    @GetMapping("/getall/pelamar")
    public ResponseEntity<?> getAllPelamar() {
        List<Pelamar> listPelamar = service.getAllPelamar();
        if (listPelamar == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<List<Pelamar>>(listPelamar, headers, HttpStatus.OK);
    }

    @GetMapping("/getall/perusahaan")
    public ResponseEntity<?> getAllPerusahaan() {
        List<Perusahaan> listPerusahaan = service.getAllPerusahaan();
        if (listPerusahaan == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<List<Perusahaan>>(listPerusahaan, headers, HttpStatus.OK);
    }

    @GetMapping("/getall/lowongan")
    public ResponseEntity<?> getAllLowongan() {
        List<Lowongan> listLowongan = service.getAllLowongan();
        if (listLowongan == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<List<Lowongan>>(listLowongan, headers, HttpStatus.OK);
    }

    @GetMapping("/get/lamaran/{kode}")
    public ResponseEntity<?> getLamaranByCode(@PathVariable String kode) {
        Lamaran lamaran = service.findByKode(kode);
        if (lamaran == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Lamaran>(lamaran, headers, HttpStatus.OK);
    }

    @PostMapping("/update/lamaran")
    public ResponseEntity<?> updateLamaran(@RequestBody Lamaran lamaran) {
        if (lamaran == null || lamaran.getTanggal_wawancara().getTime() - new Date().getTime() < 0)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Lamaran>(service.updateLamaran(lamaran), headers, HttpStatus.OK);
    }

    @PostMapping("/update/pelamar")
    public ResponseEntity<?> updatePelamar(@RequestBody Pelamar pelamar) {
        if (pelamar == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Pelamar>(service.updatePelamar(pelamar), headers, HttpStatus.OK);
    }

    @PostMapping("/update/perusahaan")
    public ResponseEntity<?> updatePerusahaan(@RequestBody Perusahaan perusahaan) {
        if (perusahaan == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Perusahaan>(service.updatePerusahaan(perusahaan), headers, HttpStatus.OK);
    }

    @PostMapping("/update/lowongan")
    public ResponseEntity<?> updateLowongan(@RequestBody Lowongan lowongan) {
        if (lowongan == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Lowongan>(service.updateLowongan(lowongan), headers, HttpStatus.OK);
    }


    @PostMapping("/add/pelamar")
    public ResponseEntity<?> addPelamar(@RequestBody Pelamar pelamar) {
        if (pelamar == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Pelamar>(service.addPelamar(pelamar), headers, HttpStatus.OK);
    }

    @PostMapping("/add/perusahaan")
    public ResponseEntity<?> addPerusahaan(@RequestBody Perusahaan perusahaan) {
        if (perusahaan == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Perusahaan>(service.addPerusahaan(perusahaan), headers, HttpStatus.OK);
    }


    @PostMapping("/add/lowongan")
    public ResponseEntity<?> addLowongan(@RequestBody Lowongan lowongan) {
        if (lowongan == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Lowongan>(service.addLowongan(lowongan), headers, HttpStatus.OK);
    }

    @PostMapping("/add/lamaran")
    public ResponseEntity<?> addLamaran(@RequestBody Lamaran lamaran) {
        if (lamaran == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Lamaran>(service.addLamaran(lamaran), headers, HttpStatus.OK);
    }
    @GetMapping("/noshowatinterview")
    public ResponseEntity<?> getAllNoShowAtInterview() {
        List<Object[]> list = service.getAllNoShowInInterview().stream()
                .filter(s -> {
                            s[0] = "kode_lamaran : " + s[0];
                            s[1] = "id_pelamar : " + s[1];
                            s[2] = "id_lowongan : " + s[2];
                            s[3] = "kode_lowongan : " + s[3];
                            s[4] = "tangggal_wawancara : " + s[4];
                            s[5] = "jam_wawancara : " + s[5];
                            return true;
                        }
                ).collect(Collectors.toList());

        if (list == null )
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<List<Object[]>>(list, headers, HttpStatus.OK);
    }

}
