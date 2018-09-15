package model;

import util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    private Link homepage;
    private List<Position> positions;

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link homepage, List<Position> positions) {
        this.homepage = homepage;
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homepage, that.homepage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homepage);
    }

    @Override
    public String toString() {
        return "Organization {" + homepage + ", " + positions + '}';
    }

    public static class Position implements Serializable {
        private String position;
        private LocalDate startDate;
        private LocalDate endDate;
        private String description;

        public Position(String position, LocalDate startDate, LocalDate endDate, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(position, "position must not be null");
            this.position = position;
            this.startDate = startDate;
            this.endDate = endDate;
            this.description = description;
        }

        public Position(String position, int startYear, Month startMonth, int endYear, Month endMonth, String description) {
            this(position, DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth), description);
        }

        public Position(String position, int startYear, Month startMonth, String description) {
            this(position, DateUtil.of(startYear, startMonth), DateUtil.NOW, description);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position1 = (Position) o;
            return Objects.equals(position, position1.position) &&
                    Objects.equals(startDate, position1.startDate) &&
                    Objects.equals(endDate, position1.endDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, startDate, endDate);
        }

        @Override
        public String toString() {
            return "Position{" +
                    "position='" + position + '\'' +
                    ", startDate=" + startDate +
                    ", endDate=" + endDate +
                    '}';
        }
    }
}
