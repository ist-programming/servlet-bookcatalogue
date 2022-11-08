package ru.kpfu.itis.entity;

public class Book {
    private Integer id;
    private String title;
    private int issued;

    public Book(String title, int issued) {
        this.id = null;
        this.title = title;
        this.issued = issued;
    }
    public Book(Integer id, String title, int issued) {
        this.id = id;
        this.title = title;
        this.issued = issued;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (issued != book.issued) return false;
        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        return title.equals(book.title);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + issued;
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIssued() {
        return issued;
    }

    public void setIssued(int issued) {
        this.issued = issued;
    }
}
