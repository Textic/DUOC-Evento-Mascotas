package com.pets.events.evento_mascotas.services;

import com.pets.events.evento_mascotas.models.Mascota;
import com.pets.events.evento_mascotas.repository.MascotaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository repo;

    public Mascota addMascota(Mascota mascota) {
        if (mascota.getId() != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud no debe contener un ID.");
        }
        return repo.save(mascota);
    }

    public Mascota findMascota(int id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar la mascota con ID: " + id));
    }

    public List<Mascota> allMascotas() {
        return repo.findAll();
    }

    public Mascota deleteMascota(int id) {
        Mascota mascota = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar la mascota con ID: " + id));
        repo.deleteById(id);
        return mascota;
    }

    public boolean existsById(int id) {
        return repo.existsById(id);
    }

    public Mascota updateMascota(int id, Mascota mascota) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar la mascota con ID: " + id);
        }
        mascota.setId(id);
        return repo.save(mascota);
    }
}
