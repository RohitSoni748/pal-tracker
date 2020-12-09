package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("time-entries")
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
       return new ResponseEntity<TimeEntry>( timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        return getTimeEntry(timeEntryRepository.find(timeEntryId));

      }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>( timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        return getTimeEntry(timeEntryRepository.update(timeEntryId,timeEntryToUpdate));
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<TimeEntry> getTimeEntry(TimeEntry timeEntry)
    {         return timeEntry!=null ?
            new ResponseEntity<>(timeEntry,HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
