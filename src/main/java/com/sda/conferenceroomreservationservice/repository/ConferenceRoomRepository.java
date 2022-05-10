package com.sda.conferenceroomreservationservice.repository;

import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import org.springframework.stereotype.Repository;

// Spring Repository annotation is a specialization of @Component annotation,
// so Spring Repository classes are autodetected by spring framework through classpath scanning.
// Spring Repository is very close to DAO pattern where DAO classes are responsible for
// providing CRUD operations on database tables. However, if you are using Spring Data for managing database operations,
// then you should use Spring Data Repository interface.
@Repository
public interface ConferenceRoomRepository extends GenericRepository<ConferenceRoom> {
    // JpaRepository will enable Spring Data to find this interface and automatically create an implementation for it.
    // By extending the interface, we get the most relevant CRUD methods
    // for standard data access available in a standard DAO.
}
