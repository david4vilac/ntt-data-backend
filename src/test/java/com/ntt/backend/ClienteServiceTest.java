package com.ntt.backend;
import com.ntt.backend.models.Cliente;
import com.ntt.backend.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    void listarClientes_devuelveListaConClientes() {
        // Act
        var clientes = clienteService.listarClientes();

        // Assert
        assertNotNull(clientes);
        assertEquals(3, clientes.size());
    }

    @Test
    void obtenerClientePorId_devuelveClienteExistente() {
        // Act
        Optional<Cliente> cliente = clienteService.obtenerClientePorId("C",23445322L);

        // Assert
        assertTrue(cliente.isPresent());
        assertEquals("Juan", cliente.get().getPrimerNombre());
        assertEquals("Pérez", cliente.get().getPrimerApellido());
    }

    @Test
    void obtenerClientePorId_devuelveVacioParaIdNoExistente() {
        // Act
        Optional<Cliente> cliente = clienteService.obtenerClientePorId("C",99999999L);

        // Assert
        assertFalse(cliente.isPresent());
    }
}