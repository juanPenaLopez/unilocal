package co.edu.uniquindio.unilocal.utils;

import co.edu.uniquindio.unilocal.dto.MensajeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FiltroToken implements Filter {

    private final JWTUtils jwtUtils = new JWTUtils();
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        String token = getToken(req);
        boolean error = true;
        try{
            if (requestURI.startsWith("/api/usuarios") || requestURI.startsWith("/api/moderadores")) {
                if(token != null) {
                    Jws<Claims> jws = jwtUtils.parseJwt(token);
                    if (
                            ( requestURI.startsWith("/api/usuarios") &&

                                    !jws.getBody().get("rol").equals("usuario") ) ||

                                    ( requestURI.startsWith("/api/moderador") &&

                                            !jws.getBody().get("rol").equals("moderador") )) {

                        crearRespuestaError("No tiene los permisos para acceder a este recurso",

                                HttpServletResponse.SC_FORBIDDEN, res);

                    }else{
                        error = false;
                    }
                }else{
                    crearRespuestaError("No hay un Token", HttpServletResponse.SC_FORBIDDEN,

                            res);

                }
            }else{
                error = false;
            }
        }catch (MalformedJwtException | SignatureException e){
            crearRespuestaError("El token es incorrecto",
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, res);
        }catch (ExpiredJwtException e ){
            crearRespuestaError("El token está vencido",
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, res);
        }catch (Exception e){
            crearRespuestaError(e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR,

                    res);
        }
        if(!error){
            chain.doFilter(request, response);
        }
    }
    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer "))
            return header.replace("Bearer ", "");
        return null;
    }
    private void crearRespuestaError(String mensaje, int codigoError, HttpServletResponse
            response) throws IOException {
        MensajeDTO<String> dto = new MensajeDTO<>(true, mensaje);
        response.setContentType("application/json");
        response.setStatus(codigoError);
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
