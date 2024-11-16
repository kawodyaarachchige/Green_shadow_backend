package com.example.green_shadow.dao;

import com.example.green_shadow.entity.impl.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogDAO  extends JpaRepository<Log,String> {
    @Query("SELECT l FROM Log l WHERE l.LogDate BETWEEN :date1 AND :date2")
    List<Log> getLogsBetweenDates(Date date1, Date date2);

    @Query("SELECT l FROM Log l JOIN l.crops c WHERE c.cropCode = :cropCode")
    List<Log> findLogsByCropCode(String cropCode);
}
