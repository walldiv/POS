package com.posbackend.repository;

import com.posbackend.model.Timeclock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeclockRepository extends JpaRepository<Timeclock, Integer> {
    @Query("SELECT t FROM Timeclock t WHERE t.employeeid = ?1")
    List<Timeclock> getLoggedInTimestamp(int employeeid);
}
