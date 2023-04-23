package com.pbl5.PBL5_Elearning.repository;

import com.pbl5.PBL5_Elearning.entity.Bookmarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmarks, Integer> {

    @Query(nativeQuery = true, value = "call customFindBlogsFromBookmarkByUserId(:id)")
    public List<Map<String, ?>> customFindBlogByMyId(@PathVariable String id);
}
