package com.pbl5.PBL5_Elearning.repository;

import com.pbl5.PBL5_Elearning.entity.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogRepository extends JpaRepository<Blogs, Integer> {

    @Query(nativeQuery = true, value = "call searchBlogByTitle(:title1)")
    public List<Map<String, ?>> customSearchByTitle(@PathVariable String title1);
}
