package org.example.veterinariadto.Controller;

import org.example.veterinariadto.DTO.MascotaDTO;
import org.example.veterinariadto.Model.Mascota;
import org.example.veterinariadto.Repository.MascotaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private final MascotaRepository mascotaRepository;

    public MascotaController(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    // GET ALL
    @GetMapping
    public List<MascotaDTO> getAll() {
        return mascotaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public MascotaDTO getById(@PathVariable Long id) {
        Mascota mascota = mascotaRepository.findById(id).orElse(null);
        return toDTO(mascota);
    }

    // POST
    @PostMapping
    public MascotaDTO create(@RequestBody MascotaDTO dto) {
        Mascota mascota = toEntity(dto);
        Mascota guardada = mascotaRepository.save(mascota);
        return toDTO(guardada);
    }

    // PUT
    @PutMapping("/{id}")
    public MascotaDTO update(@PathVariable Long id, @RequestBody MascotaDTO dto) {
        Mascota mascota = mascotaRepository.findById(id).orElse(null);

        if (mascota != null) {
            mascota.setNombre(dto.getNombre());
            mascota.setEdad(dto.getEdad());
            mascotaRepository.save(mascota);
        }

        return toDTO(mascota);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mascotaRepository.deleteById(id);
    }

    // 🔄 Conversión
    private MascotaDTO toDTO(Mascota mascota) {
        if (mascota == null) return null;

        return MascotaDTO.builder()
                .id(mascota.getId())
                .nombre(mascota.getNombre())
                .edad(mascota.getEdad())
                .build();
    }

    private Mascota toEntity(MascotaDTO dto) {
        return Mascota.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .edad(dto.getEdad())
                .build();
    }
}