package com.vkt_board.message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query("SELECT m FROM Message m ORDER BY m.rating DESC")
    Page<Message> getTopList(Pageable pageable);
}
