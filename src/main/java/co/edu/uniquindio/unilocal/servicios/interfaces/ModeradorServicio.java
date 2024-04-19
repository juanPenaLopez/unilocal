package co.edu.uniquindio.unilocal.servicios.interfaces;

import co.edu.uniquindio.unilocal.dto.ResultadoDTO;
import co.edu.uniquindio.unilocal.dto.CambioPasswordDTO;

public interface ModeradorServicio {

    /**
     * Método para eliminar una cuenta.
     * @param idCuenta El ID de la cuenta a eliminar.
     * @return Un ResultadoDTO con los detalles del resultado de la operación.
     * @throws Exception En caso de algún problema durante la operación.
     */
    ResultadoDTO eliminarCuenta(String idCuenta) throws Exception;

    /**
     * Método para enviar un enlace de recuperación de contraseña a un email.
     * @param email El email al que enviar el enlace de recuperación.
     * @return Un ResultadoDTO con los detalles del resultado de la operación.
     * @throws Exception En caso de algún problema durante la operación.
     */
    ResultadoDTO enviarLinkRecuperacion(String email) throws Exception;

    /**
     * Método para cambiar la contraseña de una cuenta.
     * @param cambioPasswordDTO El DTO con los datos necesarios para cambiar la contraseña.
     * @return Un ResultadoDTO con los detalles del resultado de la operación.
     * @throws Exception En caso de algún problema durante la operación.
     */
    ResultadoDTO cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception;
}