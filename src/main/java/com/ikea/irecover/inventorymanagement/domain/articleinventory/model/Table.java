package com.ikea.irecover.inventorymanagement.domain.articleinventory.model;

import java.util.List;

public class Table extends Article {

    public Table() {
        super("table", generateParts());
    }

    private static List<ArticlePart> generateParts() {
        ArticlePart tableTop = new ArticlePart("tableTop", 1);
        ArticlePart legs = new ArticlePart("leg", 4);
        return List.of(tableTop, legs);
    }
}
