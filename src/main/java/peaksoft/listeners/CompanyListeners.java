package peaksoft.listeners;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import peaksoft.entities.Company;

import java.time.ZonedDateTime;

public class CompanyListeners {
    @PrePersist
    public void save(Company company){
        company.setCreatedAt(ZonedDateTime.now());
        company.setUpdatedAt(ZonedDateTime.now());
    }

    @PreUpdate
    public void update(Company company){
        company.setUpdatedAt(ZonedDateTime.now());
    }
}
