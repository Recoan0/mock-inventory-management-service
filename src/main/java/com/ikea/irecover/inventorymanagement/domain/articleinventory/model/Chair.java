package com.ikea.irecover.inventorymanagement.domain.articleinventory.model;

import java.util.List;

public class Chair extends Article {
    public Chair() {
        super("chair", generateParts());
    }

    private static List<ArticlePart> generateParts() {
        ArticlePart seat = new ArticlePart("seat", 1);
        ArticlePart backRest = new ArticlePart("backRest", 1);
        ArticlePart legs = new ArticlePart("leg", 4);
        return List.of(seat, backRest, legs);
    }
}
