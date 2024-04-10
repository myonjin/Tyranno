package com.tyranno.ssg.global;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({Default.class, ValidationGroups.NotEmptyGroup.class, ValidationGroups.PatternCheckGroup.class })
public interface ValidationSequence {
}
