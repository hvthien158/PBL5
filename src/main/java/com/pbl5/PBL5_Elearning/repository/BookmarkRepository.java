package com.pbl5.PBL5_Elearning.repository;

import com.pbl5.PBL5_Elearning.entity.Bookmarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmarks, Integer> {
}
