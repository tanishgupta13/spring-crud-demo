package in.strikes.crudDemo.repository;

import in.strikes.crudDemo.entity.CRUDStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CRUDRepository extends JpaRepository<CRUDStudent, Long> {


    Optional<CRUDStudent> findByIdAndDeletedIsFalse(Long id);


   List<CRUDStudent> findByDeletedIsFalse();
}