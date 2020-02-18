package io.pivotal.pal.tracker;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){
        TimeEntry te =  timeEntryRepository.create(timeEntry);
        return new ResponseEntity<TimeEntry>(te,HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = timeEntryRepository.list();
        return new ResponseEntity<>(list,HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id,@RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(id,expected);
        if(timeEntry==null)
            return new ResponseEntity<TimeEntry>(timeEntry,HttpStatus.NOT_FOUND);
        return new ResponseEntity<TimeEntry>(timeEntry,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
        timeEntryRepository.delete(id);

        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id){
    TimeEntry timeEntry = timeEntryRepository.find(id);
    if(timeEntry==null)
        return new ResponseEntity<TimeEntry>(timeEntry,HttpStatus.NOT_FOUND);
    return new ResponseEntity<TimeEntry>(timeEntry,HttpStatus.OK);


    }


}
