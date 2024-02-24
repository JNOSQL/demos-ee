/*
 *  Copyright (c) 2024 Contributors to the Eclipse Foundation
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 */
package org.acme;


import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import net.datafaker.Faker;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Entity
public class Beer {

    @Id
    public String id;

    @Column
    public String style;

    @Column
    public String hop;

    @Column
    public String malt;

    @Column
    public List<String> comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getHop() {
        return hop;
    }

    public void setHop(String hop) {
        this.hop = hop;
    }

    public String getMalt() {
        return malt;
    }

    public void setMalt(String malt) {
        this.malt = malt;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id='" + id + '\'' +
                ", style='" + style + '\'' +
                ", hop='" + hop + '\'' +
                ", malt='" + malt + '\'' +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Beer beer = (Beer) object;
        return Objects.equals(id, beer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static Beer of(Faker faker){
        var beer = faker.beer();
        Beer entity = new Beer();
        entity.hop = beer.hop();
        entity.malt = beer.malt();
        entity.style = beer.style();
        entity.id= UUID.randomUUID().toString();
        entity.comments  = List.of("comment1", "comment2");
        return entity;
    }

}