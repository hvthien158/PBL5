package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Bookmarks;
import com.pbl5.PBL5_Elearning.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookmarkService implements BookmarkServiceImp {

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Override
    public Bookmarks insertNewBookmark(Bookmarks bookmarks) {
        return bookmarkRepository.saveAndFlush(bookmarks);
    }

    @Override
    public List<Map<String, ?>> findBookmarksByMyID(String user_id) {
        return bookmarkRepository.customFindBlogByMyId(user_id);
    }
}
