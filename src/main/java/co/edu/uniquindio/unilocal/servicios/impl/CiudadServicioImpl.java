package co.edu.uniquindio.unilocal.servicios.impl;

import co.edu.uniquindio.unilocal.modelo.Ciudad;
import co.edu.uniquindio.unilocal.repositorios.CiudadRepo;
import co.edu.uniquindio.unilocal.servicios.interfaces.CiudadServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CiudadServicioImpl implements CiudadServicio {

    private CiudadRepo ciudadRepository;

    @Override
    public List<Ciudad> obtenerTodasLasCiudades() {
        return ciudadRepository.findAll();
    }
}
