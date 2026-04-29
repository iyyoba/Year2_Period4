package com.filesystem;

import java.util.ArrayList;
import java.util.List;

public class SearchVisitor implements FileSystemVisitor {

    private String keyword;
    private List<File> results = new ArrayList<>();

    public SearchVisitor(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    public List<File> getResults() {
        return results;
    }

    @Override
    public void visit(File file) {
        if (file.getName().toLowerCase().contains(keyword)) {
            results.add(file);
        }
    }

    @Override
    public void visit(Directory directory) {
        // nothing needed here
    }
}
