package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.*;

public class  InMemoryTimeEntryRepository implements TimeEntryRepository {

    //List<TimeEntry> list = new ArrayList<TimeEntry>();
    Long counter = 1L;
    HashMap<Long,TimeEntry> timeEntryRepository = new HashMap<>();
    List<TimeEntry> list = new ArrayList<TimeEntry>();

    @Override
    public TimeEntry create(TimeEntry timeEntryToCreate) {


        TimeEntry local = new TimeEntry();
        local.setId(counter);
        local.setDate(timeEntryToCreate.getDate());
        local.setHours(timeEntryToCreate.getHours());
        local.setProjectId(timeEntryToCreate.getProjectId());
        local.setUserId(timeEntryToCreate.getUserId());
        timeEntryToCreate.setId(counter);
        timeEntryRepository.put(counter,local);

        ++counter;
        return local;
    }

    @Override
    public TimeEntry find(long timeEntryId) {

       return timeEntryRepository.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {


        // For list() method
     /*   list.add(new TimeEntry(1L,123L,456L,LocalDate.parse("2017-01-08"),8));
        list.add(new TimeEntry(2L, 789L, 654L, LocalDate.parse("2017-01-07"), 4));

        return list;*/

     return new ArrayList<>(timeEntryRepository.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry updateEntry) {

        TimeEntry actualEntry = timeEntryRepository.get(id);
        if(actualEntry==null)
            return null;
        System.out.println("id====>"+id);
        updateEntry.setId(id);
        timeEntryRepository.replace(id,updateEntry);
        return updateEntry;
    }

    @Override
    public void delete(long timeEntryIdLoc) {
/*
        TimeEntry entry = timeEntryRepository.get(timeEntryId);
        if(entry!=null)
            timeEntryRepository.remove(timeEntryId);
        System.out.println(timeEntryRepository);*/
        timeEntryRepository.remove(timeEntryIdLoc);


    }
}