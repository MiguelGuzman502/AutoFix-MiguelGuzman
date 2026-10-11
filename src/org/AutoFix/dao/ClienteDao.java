package org.AutoFix.dao;

import org.AutoFix.model.Cliente;
import java.util.List;

public interface ClienteDao {
    List<Cliente> listarClientes();
    void agregarCliente(Cliente cliente);
    void actualizarCliente(Cliente cliente);
    void eliminarCliente(int idCliente);
    Cliente buscarClientePorId(int idCliente);
}