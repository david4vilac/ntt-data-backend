package com.ntt.backend.controllers;

import com.ntt.backend.models.Cliente;
import com.ntt.backend.responses.ApiResponse;
import com.ntt.backend.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Obtiene una lista de todos los clientes
     *
     * @return ResponseEntity que contiene la lista de clientes y un código de estado 200 OK
     *         si hay clientes disponibles, o un código de estado 204 No Content si no se
     *         encuentran clientes.
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "getAll", headers = "Accept=application/json")
    public ResponseEntity<ApiResponse<List<Cliente>>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();

        if (clientes.isEmpty()) {
            ApiResponse<List<Cliente>> response = new ApiResponse<>(204, "No se encontraron clientes.", null);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        ApiResponse<List<Cliente>> response = new ApiResponse<>(200, "Clientes encontrados.", clientes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Busca un cliente por su tipo de documento y su ID.
     *
     * @param tipoDocumento el tipo de documento del cliente a buscar, por ejemplo, "C" para cédula o "P" para pasaporte.
     * @param id el ID del cliente a buscar.
     * @return ResponseEntity que contiene el cliente encontrado y un código de estado 200 OK,
     *         o un código de estado 404 Not Found si no se encuentra el cliente, o 400 Bad Request si el tipo de documento es inválido.
     * @author David Avila
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "listarPorId/{tipoDocumento}/{id}", headers = "Accept=application/json")
    public ResponseEntity<ApiResponse<?>> buscarPorId(@PathVariable String tipoDocumento, @PathVariable Long id) {
        // Validación del tipo de documento
        if (!tipoDocumento.equals("C") && !tipoDocumento.equals("P")) {
            ApiResponse<String> response = new ApiResponse<>(400, "Tipo de documento inválido. Use 'C' para cédula o 'P' para pasaporte.", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<Cliente> clienteOpt = clienteService.obtenerClientePorId(tipoDocumento, id);
        if (clienteOpt.isPresent()) {
            ApiResponse<Cliente> response = new ApiResponse<>(200, "Cliente encontrado.", clienteOpt.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        ApiResponse<String> response = new ApiResponse<>(404, "Cliente no encontrado.", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
