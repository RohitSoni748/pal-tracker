package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TotalUsersInfoContributor implements InfoContributor {

    @Autowired
    TimeEntryRepository timeEntryRepository;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("AllUsers", timeEntryRepository.list());

        builder.withDetail("users", userDetails);
    }
}