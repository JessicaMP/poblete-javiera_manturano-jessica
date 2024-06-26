package BackEndC3.repository;


import BackEndC3.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TurnoRepository extends JpaRepository<Turno,Long> {
//    List<Turno> findByIdPacientes(Long pacienteId);
//    List<Turno> findAllByIdOdontologos(Long odontologoId);
}
