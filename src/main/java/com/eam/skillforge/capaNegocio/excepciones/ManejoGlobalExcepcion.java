package com.eam.skillforge.capaNegocio.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejoGlobalExcepcion {
    @ExceptionHandler(UsuarioNoEncontradoExcepcion.class)
    public ResponseEntity<ErrorResponse<String>> manejarUsuarioNoEncontrado(UsuarioNoEncontradoExcepcion ex) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CreacionUsuarioExcepcion.class)
    public ResponseEntity<ErrorResponse<String>> manejarCamposUsuario(CreacionUsuarioExcepcion ex) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CorreoNoEncontradoExcepcion.class)
    public ResponseEntity<ErrorResponse<String>> manejarCorreoNoEncontrado(CorreoNoEncontradoExcepcion ex) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse<String>> manejarCamposMedalla(IllegalArgumentException ex) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UsuarioNoAutorizadoExcepcion.class)
    public ResponseEntity<ErrorResponse<String>> manejarCamposMedalla(UsuarioNoAutorizadoExcepcion ex) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    public class ErrorResponse<T> {
        private T result;
        private boolean success = false;

        public ErrorResponse(T result) {
            this.result = result;
        }

        public T getResult() {
            return result;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}