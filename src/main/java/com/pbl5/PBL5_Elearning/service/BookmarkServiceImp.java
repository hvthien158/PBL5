package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Bookmarks;

import java.util.List;
import java.util.Map;

public interface BookmarkServiceImp {
    public Bookmarks insertNewBookmark(Bookmarks bookmarks);

    public List<Map<String, ?>> findBookmarksByMyID(String user_id);
}
