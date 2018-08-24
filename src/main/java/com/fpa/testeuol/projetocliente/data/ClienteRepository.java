package com.fpa.testeuol.projetocliente.data;

import com.fpa.testeuol.projetocliente.entity.cliente.ClienteModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    @Override
    Optional<ClienteModel> findById(Long idCliente);
}
