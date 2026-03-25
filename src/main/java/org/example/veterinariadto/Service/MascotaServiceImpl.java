package org.example.veterinariadto.Service;

import org.example.veterinariadto.DTO.MascotaDTO;
import org.example.veterinariadto.Model.Mascota;
import org.example.veterinariadto.Repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;

    public MascotaServiceImpl(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public List<MascotaDTO> getAll() {
        return mascotaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MascotaDTO getById(Long id) {
        Mascota mascota = mascotaRepository.findById(id).orElse(null);
        return toDTO(mascota);
    }

    @Override
    public MascotaDTO create(MascotaDTO dto) {
        Mascota mascota = toEntity(dto);
        Mascota guardada = mascotaRepository.save(mascota);
        return toDTO(guardada);
    }

    @Override
    public MascotaDTO update(Long id, MascotaDTO dto) {
        Mascota mascota = mascotaRepository.findById(id).orElse(null);

        if (mascota != null) {
            mascota.setNombre(dto.getNombre());
            mascota.setEdad(dto.getEdad());
            mascotaRepository.save(mascota);
        }

        return toDTO(mascota);
    }

    @Override
    public void delete(Long id) {
        mascotaRepository.deleteById(id);
    }

    // 🔄 Conversiones
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