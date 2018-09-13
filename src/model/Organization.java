package model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private Link homepage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String position;
    private String description;

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String position, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(position, "position must not be null");
        this.homepage = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homepage.equals(that.homepage)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        return position.equals(that.position);
    }

    @Override
    public int hashCode() {
        int result = homepage.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + position.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homepage=" + homepage +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
