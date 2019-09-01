package com.example.learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-01
 * <p>
 * Description:
 */
public class SubjectManager {
    private static volatile SubjectManager subjectManager;
    private List<Subject> subjects;
    private Map<Subject, Instantiable> map;

    private SubjectManager() {
        map = new HashMap<>();
    }

    public static SubjectManager get() {
        return subjectManager;
    }

    public Instantiable getInstantiableBySubject(Subject subject) {
        return map.get(subject);
    }

    public Creator creator() {
        return new Creator();
    }

    public class Creator {
        private List<Subject> subjects;

        public Creator() {
            subjects = new ArrayList<>();
        }

        public <T> Creator register(Subject subject, Instantiable<T> instantiatable) {
            subjects.add(subject);
            map.put(subject, instantiatable);
            return this;
        }

        public List<Subject> create() {
            SubjectManager.this.subjects = subjects;
            return subjects;
        }
    }
}
