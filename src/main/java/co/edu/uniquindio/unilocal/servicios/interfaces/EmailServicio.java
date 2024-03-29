package co.edu.uniquindio.unilocal.servicios.interfaces;

import co.edu.uniquindio.unilocal.dto.EmailDTO;
import co.edu.uniquindio.unilocal.dto.ResultadoDTO;

public interface EmailServicio {

    ResultadoDTO enviarCorreo(EmailDTO emailDTO) throws Exception;
}
