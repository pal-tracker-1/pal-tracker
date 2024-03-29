package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> repo = new HashMap<>();

    private long lastId = 0L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        if (timeEntry.getId() == 0L) {
            timeEntry.setId(++lastId);
        }
        repo.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return repo.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(repo.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (repo.keySet().contains(id)) {
            timeEntry.setId(id);
            repo.put(id, timeEntry);
            return repo.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        repo.remove(id);
    }
}
