package com.fpa.testeuol.projetocliente.data;

import com.fpa.testeuol.projetocliente.entity.cliente.ClienteGeoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteGeoRepository extends JpaRepository<ClienteGeoModel, Long> {

	@Override
	Optional<ClienteGeoModel> findById(Long aLong);
}
