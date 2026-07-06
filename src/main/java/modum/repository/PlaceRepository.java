package modum.repository;

import modum.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findByMoodsId(Long moodId);
List<Place> findByNameContainingIgnoreCaseOrDistrictContainingIgnoreCase(String name, String district);


List<Place> findByDistrictContainingIgnoreCase(String district);
}