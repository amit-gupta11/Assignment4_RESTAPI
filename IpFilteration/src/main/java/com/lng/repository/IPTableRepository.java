package com.lng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lng.entity.IPTable;

@Repository
public interface IPTableRepository extends JpaRepository<IPTable, Long> {

    public List<IPTable> findAllByAddedby(String userId);

    public IPTable findByIp(String ip);
}
