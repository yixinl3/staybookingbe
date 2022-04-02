package com.project.staybooking.repository;

import com.project.staybooking.model.Stay;
import com.project.staybooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StayRepository extends JpaRepository<Stay, Long> {
    List<Stay> findByHost(User user);

    Stay findByIdAndHost(Long id, User host);
}
