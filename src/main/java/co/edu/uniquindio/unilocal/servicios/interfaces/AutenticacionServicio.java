package co.edu.uniquindio.unilocal.servicios.interfaces;

import co.edu.uniquindio.unilocal.dto.LoginDTO;
import co.edu.uniquindio.unilocal.dto.TokenDTO;

public interface AutenticacionServicio {

    TokenDTO login(LoginDTO loginDTO) throws Exception;
}
