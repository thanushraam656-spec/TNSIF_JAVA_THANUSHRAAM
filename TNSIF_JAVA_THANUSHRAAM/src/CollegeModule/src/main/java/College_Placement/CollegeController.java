package College_Placement;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("college")
public class CollegeController {

    @Autowired
    private CollegeService ser;

    // GET ALL
    @GetMapping
    public List<College> getAllCollege() {
        return ser.getCollege();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<College> get(@PathVariable Long id) {
        try {
            College cl = ser.getCollegeId(id);
            return new ResponseEntity<>(cl, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ADD NEW COLLEGE
    @PostMapping
    public ResponseEntity<College> add(@RequestBody College college) {
        ser.save(college);
        return new ResponseEntity<>(college, HttpStatus.CREATED);
    }

    // UPDATE COLLEGE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody College college, @PathVariable Long id) {
        try {
            college.setId(id);  // IMPORTANT: sets the same ID → JPA updates

            ser.save(college);

            return new ResponseEntity<>(college, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ser.delete(id);
    }
}
