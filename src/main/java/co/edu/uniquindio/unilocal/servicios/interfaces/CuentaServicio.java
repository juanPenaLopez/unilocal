package co.edu.uniquindio.unilocal.servicios.interfaces;

import co.edu.uniquindio.unilocal.dto.CambioPasswordDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.dto.SesionDTO;

public interface CuentaServicio {

    void iniciarSesion(SesionDTO sesionDTO)throws Exception;
    ResultadoDTO eliminarCuenta(String idCuenta)throws Exception;
    ResultadoDTO enviarLinkRecuperacion(String email)throws Exception;
    ResultadoDTO cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;
}
