package com.ntt.backend.services;

import com.ntt.backend.models.Cliente;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    /**
     * Devuelve una lista de clientes con datos de ejemplo (datos quemados) en lugar de recuperar
     * información de una base de datos.
     *
     * @return Una lista inmutable de {@link Cliente} que contiene tres instancias de clientes
     * con datos ficticios.
     */
    public List<Cliente> listarClientes() {
        return Arrays.asList(
                new Cliente(23445322L, "Juan", "Carlos", "Pérez", "González", "C", "12345678", "555-1234", "Calle Falsa 123", "Madrid"),
                new Cliente(23445323L, "María", "Teresa", "Rodríguez", "Fernández", "P", "87654321", "555-5678", "Calle Verdadera 456", "Barcelona"),
                new Cliente(23445324L, "Pedro", "Luis", "Martínez", "López", "C", "45678912", "555-8765", "Avenida Siempreviva 789", "Valencia")
        );
    }

    /**
     * Obtiene un cliente de la lista de datos quemados según el tipo de documento y el ID del cliente.
     *
     * @param tipoDocumento El tipo de documento del cliente (por ejemplo, "C" para cédula, "P" para pasaporte).
     * @param idCliente     El ID del cliente que se desea buscar.
     * @return Un {@link Optional} que contiene el cliente encontrado si existe, o un {@link Optional#empty()} si no se encuentra.
     * @author David Avila
     */
    public Optional<Cliente> obtenerClientePorId(String tipoDocumento ,Long idCliente) {
        return listarClientes().stream()
                .filter(cliente -> cliente.getTipoDocumento().equals(tipoDocumento) && cliente.getIdCliente().equals(idCliente) )
                .findFirst();
    }

}
