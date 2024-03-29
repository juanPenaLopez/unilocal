package co.edu.uniquindio.unilocal.servicios.impl;

import co.edu.uniquindio.unilocal.dto.CambioPasswordDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.dto.SesionDTO;
import co.edu.uniquindio.unilocal.servicios.interfaces.ModeradorServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModeradorServicioImpl implements ModeradorServicio {

    @Override
    public void iniciarSesion(SesionDTO sesionDTO) throws Exception {

    }

    @Override
    public ResultadoDTO eliminarCuenta(String idCuenta) throws Exception {
        return null;
    }

    @Override
    public ResultadoDTO enviarLinkRecuperacion(String email) throws Exception {
        return null;
    }

    @Override
    public ResultadoDTO cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        return null;
    }
}
