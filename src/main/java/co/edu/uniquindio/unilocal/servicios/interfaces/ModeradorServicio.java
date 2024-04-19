package co.edu.uniquindio.unilocal.servicios.interfaces;

import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.dto.CambioPasswordDTO;

public interface ModeradorServicio {

    /**
     * Elimina una cuenta de usuario basada en su ID.
     *
     * @param idCuenta el ID de la cuenta a eliminar
     * @return un ResultadoDTO indicando el éxito o fracaso de la operación
     * @throws Exception en caso de errores durante la operación
     */
    ResultadoDTO eliminarCuenta(String idCuenta) throws Exception;

    /**
     * Envía un enlace de recuperación de contraseña a un email específico.
     *
     * @param email el correo electrónico al que se enviará el enlace
     * @return un ResultadoDTO indicando el éxito o fracaso de la operación
     * @throws Exception en caso de errores durante la operación
     */
    ResultadoDTO enviarLinkRecuperacion(String email) throws Exception;

    /**
     * Cambia la contraseña de un usuario.
     *
     * @param cambioPasswordDTO datos necesarios para cambiar la contraseña
     * @return un ResultadoDTO indicando el éxito o fracaso de la operación
     * @throws Exception en caso de errores durante la operación
     */
    ResultadoDTO cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception;
}
