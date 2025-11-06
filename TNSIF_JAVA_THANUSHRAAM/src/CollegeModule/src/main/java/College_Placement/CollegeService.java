package College_Placement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollegeService {

    @Autowired
    private CollegeRepo repo;

    // Get all colleges
    public List<College> getCollege() {
        return repo.findAll();
    }

    // Get college by ID
    public College getCollegeId(Long id) {
        return repo.findById(id).orElseThrow();
    }

    // Save or Update
    public void save(College college) {
        repo.save(college);
    }

    // Delete by ID
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
