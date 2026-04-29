
package com.filesystem;

public class Main {
    public static void main(String[] args) {

        // Create files
        File file1 = new File("report.pdf", 2.5);
        File file2 = new File("photo.jpg", 5.0);
        File file3 = new File("notes.txt", 1.2);
        File file4 = new File("presentation.pptx", 10.0);

        // Create directories
        Directory root = new Directory("root");
        Directory docs = new Directory("docs");
        Directory media = new Directory("media");

        // Build structure
        docs.add(file1);
        docs.add(file3);

        media.add(file2);
        media.add(file4);

        root.add(docs);
        root.add(media);

        // =========================
        // 1. Size Calculation Visitor
        // =========================
        SizeCalculatorVisitor sizeVisitor = new SizeCalculatorVisitor();
        root.accept(sizeVisitor);

        System.out.println("Total file size: " + sizeVisitor.getTotalSize() + " MB");

        // =========================
        // 2. Search Visitor
        // =========================
        SearchVisitor searchVisitor = new SearchVisitor("report");
        root.accept(searchVisitor);

        System.out.println("Search results:");
        for (File f : searchVisitor.getResults()) {
            System.out.println("- " + f.getName());
        }
    }
}