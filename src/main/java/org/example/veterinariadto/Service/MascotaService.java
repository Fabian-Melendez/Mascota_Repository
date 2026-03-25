package org.example.veterinariadto.Service;

import org.example.veterinariadto.DTO.MascotaDTO;

import java.util.List;

public interface MascotaService {

    List<MascotaDTO> getAll();

    MascotaDTO getById(Long id);

    MascotaDTO create(MascotaDTO dto);

    MascotaDTO update(Long id, MascotaDTO dto);

    void delete(Long id);
}