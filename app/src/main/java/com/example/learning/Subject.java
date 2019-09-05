package com.example.learning;

import android.support.annotation.Nullable;

import java.util.Objects;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-08-31
 * <p>
 * Description:
 */
public class Subject {
    private String title;

    private String subtitle;

    private boolean test;

    public Subject(String title) {
        this.title = title;
    }

    public Subject(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public Subject(String title, String subtitle, boolean test) {
        this.title = title;
        this.subtitle = subtitle;
        this.test = test;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title) ^ Objects.hashCode(subtitle);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Subject) {
            Subject subject = (Subject) obj;
            return Objects.equals(subject.title, title) &&
                    Objects.equals(subject.subtitle, subtitle);
        }

        return false;
    }
}
